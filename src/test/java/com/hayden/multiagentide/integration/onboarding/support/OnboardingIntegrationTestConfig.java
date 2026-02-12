package com.hayden.multiagentide.integration.onboarding.support;

import com.hayden.commitdiffcontext.cdc_config.OnboardingPipelineConfigProps;
import com.hayden.commitdiffcontext.git.GitFactory;
import com.hayden.commitdiffcontext.git.RepositoryHolder;
import com.hayden.commitdiffcontext.git.embed.ModelServerEmbeddingClient;
import com.hayden.commitdiffcontext.git.operations.ConsumingOperation;
import com.hayden.commitdiffcontext.git.parser.support.episodic.EpisodicMemoryAgent;
import com.hayden.commitdiffcontext.git.parser.support.episodic.model.OnboardingRunMetadata;
import com.hayden.commitdiffcontext.git.parser.support.episodic.service.DefaultContiguousSegmentationService;
import com.hayden.commitdiffcontext.git.parser.support.episodic.service.DefaultRewriteHistoryService;
import com.hayden.commitdiffcontext.git.parser.support.episodic.service.InMemoryOnboardingRunLogStore;
import com.hayden.commitdiffcontext.git.parser.support.episodic.service.OnboardingOrchestrationService;
import com.hayden.commitdiffcontext.git.parser.support.episodic.service.RewriteHistoryService;
import com.hayden.commitdiffcontext.git.parser.support.episodic.service.SerialSegmentEpisodicService;
import com.hayden.commitdiffcontext.git.repo.CommitDiffRepository;
import com.hayden.commitdiffmodel.codegen.types.ParseGitOptions;
import com.hayden.commitdiffmodel.codegen.types.RagOptions;
import com.hayden.commitdiffmodel.err.GitErrors;
import com.hayden.multiagentide.agent.episodic.HindsightOnboardingClient;
import com.hayden.utilitymodule.result.Result;
import org.eclipse.jgit.api.Git;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public abstract class OnboardingIntegrationTestConfig {

    protected final RepositoryFixtureFactory repositoryFixtureFactory = new RepositoryFixtureFactory();

    @MockitoBean
    protected ModelServerEmbeddingClient modelServerEmbeddingClient;

    @MockitoBean
    protected HindsightOnboardingClient hindsightOnboardingClient;

    @MockitoBean
    protected EpisodicMemoryAgent episodicMemoryAgent;

    protected OnboardingPipelineConfigProps onboardingPipelineConfigProps;
    protected OnboardingOrchestrationService onboardingOrchestrationService;
    protected RewriteHistoryService rewriteHistoryService;
    protected SerialSegmentEpisodicService serialSegmentEpisodicService;

    @BeforeEach
    void initOnboardingServices() {
        this.onboardingPipelineConfigProps = new OnboardingPipelineConfigProps();
        CommitDiffRepository commitDiffRepository = org.mockito.Mockito.mock(CommitDiffRepository.class);
        when(commitDiffRepository.findById(any())).thenReturn(Optional.empty());

        this.onboardingOrchestrationService = new OnboardingOrchestrationService(
                new InMemoryOnboardingRunLogStore(),
                onboardingPipelineConfigProps
        );
        this.rewriteHistoryService = new DefaultRewriteHistoryService(
                commitDiffRepository,
                new DefaultContiguousSegmentationService(),
                onboardingOrchestrationService,
                onboardingPipelineConfigProps
        );

        ObjectProvider<EpisodicMemoryAgent> provider = new StaticListableBeanFactory(
                Map.of("episodicMemoryAgent", episodicMemoryAgent)
        ).getBeanProvider(EpisodicMemoryAgent.class);
        this.serialSegmentEpisodicService = new SerialSegmentEpisodicService(
                provider,
                onboardingOrchestrationService,
                onboardingPipelineConfigProps
        );
    }

    protected OnboardingExecutionContext onboardingContext(Path repositoryRoot) throws Exception {
        var repoArgs = RepositoryHolder.RepositoryArgs.builder()
                .branch("main")
                .ragOptions(RagOptions.newBuilder()
                        .parseGitOptions(ParseGitOptions.newBuilder()
                                .maxCommitDepth(500)
                                .maxCommitDiffs(500)
                                .build())
                        .build())
                .gitRepoDirectory(GitFactory.retrieveGitRepoDir(repositoryRoot))
                .build();
        Git git = Git.open(repositoryRoot.toFile());
        RepositoryHolder holder = new RepositoryHolder(git, repoArgs, () -> {});
        var operationArgs = ConsumingOperation.OperationArgs.builder()
                .repositoryArgs(repoArgs)
                .holder(holder)
                .build();
        return new OnboardingExecutionContext(holder, operationArgs);
    }

    protected Result<OnboardingRunMetadata, GitErrors.GitAggregateError> runOnboarding(OnboardingExecutionContext context) {
        var rewriteResult = rewriteHistoryService.rewrite(context.operationArgs());
        if (rewriteResult.e().isPresent()) {
            return Result.err(rewriteResult.e().get());
        }
        var rewritten = rewriteResult.r().get();
        if (onboardingPipelineConfigProps.isDryRun()) {
            serialSegmentEpisodicService.markDryRunComplete(rewritten);
            return onboardingOrchestrationService.findRun(rewritten.onboardingRunId())
                    .map(Result::<OnboardingRunMetadata, GitErrors.GitAggregateError>ok)
                    .orElseGet(() -> Result.err(new GitErrors.GitAggregateError("Run metadata missing after dry-run.")));
        }
        var episodic = serialSegmentEpisodicService.execute(rewritten);
        if (episodic.e().isPresent()) {
            return Result.err(episodic.e().get());
        }
        return onboardingOrchestrationService.findRun(rewritten.onboardingRunId())
                .map(Result::<OnboardingRunMetadata, GitErrors.GitAggregateError>ok)
                .orElseGet(() -> Result.err(new GitErrors.GitAggregateError("Run metadata missing after execution.")));
    }

    protected OnboardingRunMetadata latestRun() {
        return onboardingOrchestrationService.findRuns().stream()
                .max(Comparator.comparing(OnboardingRunMetadata::getStartedAt))
                .orElseThrow();
    }

    protected record OnboardingExecutionContext(
            RepositoryHolder holder,
            ConsumingOperation.OperationArgs operationArgs
    ) implements AutoCloseable {
        @Override
        public void close() {
            holder.close();
        }
    }
}
