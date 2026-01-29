package com.hayden.multiagentide.artifacts.repository;

import com.hayden.multiagentide.artifacts.ArtifactService;
import com.hayden.multiagentide.artifacts.ArtifactTreeBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Slf4j
@SpringBootTest
@Profile("test")
class ArtifactRepositoryTest {

    @Autowired
    private ArtifactRepository artifactRepository;
    @Autowired
    private ArtifactService artifactService;
    @Autowired
    private ArtifactTreeBuilder artifactTreeBuilder;

    @Test
    public void testAddRemoveArtifactRepository() {

    }

}