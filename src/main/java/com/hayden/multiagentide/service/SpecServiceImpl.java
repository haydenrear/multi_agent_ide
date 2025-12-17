package com.hayden.multiagentide.service;

import com.hayden.multiagentide.model.spec.Spec;
import com.hayden.multiagentide.model.spec.SpecSection;
import com.hayden.multiagentide.model.spec.SpecSummary;
import com.hayden.multiagentide.model.spec.SpecValidationResult;
import com.hayden.multiagentide.repository.SpecRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Implementation of SpecService with Markdown-based spec files.
 */
//@Service
public class SpecServiceImpl implements SpecService {

    private final SpecRepository specRepository;
    private static final Pattern HEADER_PATTERN = Pattern.compile("^(#{1,6})\\s+(.+)$", Pattern.MULTILINE);

    public SpecServiceImpl(SpecRepository specRepository) {
        this.specRepository = specRepository;
    }

    @Override
    public Spec createSpec(String worktreeId, Path specPath, String goal) {
        String specId = UUID.randomUUID().toString();

        String header = String.format("""
                # Specification
                
                **Goal**: %s
                **Created**: %s
                
                """, goal, Instant.now());

        String planContent = "## Plan\n\nTo be filled in during execution.\n";
        String statusContent = "## Status\n\nPending initialization.\n";

        Spec spec = new Spec(
                specId,
                worktreeId,
                specPath.toString(),
                header,
                planContent,
                statusContent,
                new ArrayList<>(),
                new HashMap<>(),
                new ArrayList<>(),
                Instant.now(),
                Instant.now(),
                0,
                new HashMap<>()
        );

        specRepository.save(spec);
        saveSpec(spec);
        return spec;
    }

    @Override
    public Optional<Spec> getSpec(String specId) {
        return specRepository.findById(specId);
    }

    @Override
    public SpecValidationResult validateSpec(String specId) {
        Optional<Spec> spec = specRepository.findById(specId);
        if (spec.isEmpty()) {
            return new SpecValidationResult(
                    false,
                    specId,
                    List.of("Spec not found"),
                    List.of("Spec not found"),
                    0,
                    Instant.now()
            );
        }

        List<String> errors = new ArrayList<>();
        List<String> missingSections = new ArrayList<>();
        Spec s = spec.get();

        // Check required sections
        if (s.header() == null || s.header().isEmpty()) {
            missingSections.add("Header");
            errors.add("Missing Header section");
        }
        if (s.plan() == null || s.plan().isEmpty()) {
            missingSections.add("Plan");
            errors.add("Missing Plan section");
        }
        if (s.status() == null || s.status().isEmpty()) {
            missingSections.add("Status");
            errors.add("Missing Status section");
        }

        if (errors.isEmpty()) {
            Spec validated = s.markValidated(s.lastValidatedAt() + 1);
            specRepository.save(validated);
            return new SpecValidationResult(
                    true,
                    specId,
                    List.of(),
                    List.of(),
                    s.lastValidatedAt() + 1,
                    Instant.now()
            );
        }

        return new SpecValidationResult(
                false,
                specId,
                missingSections,
                errors,
                s.lastValidatedAt(),
                Instant.now()
        );
    }

    @Override
    public SpecSummary getSummary(String specId) {
        Optional<Spec> spec = specRepository.findById(specId);
        if (spec.isEmpty()) {
            return new SpecSummary(
                    specId,
                    "",
                    "",
                    List.of(),
                    List.of(),
                    List.of(),
                    Instant.now()
            );
        }

        Spec s = spec.get();
        String title = extractTitle(s.header());
        String description = extractFirstParagraph(s.header());

        // Build section info
        List<SpecSummary.SpecSectionInfo> sectionInfos = new ArrayList<>();
        sectionInfos.add(new SpecSummary.SpecSectionInfo("#/Header", "#", countLines(s.header()), preview(s.header())));
        sectionInfos.add(new SpecSummary.SpecSectionInfo("#/Plan", "##", countLines(s.plan()), preview(s.plan())));
        sectionInfos.add(new SpecSummary.SpecSectionInfo("#/Status", "##", countLines(s.status()), preview(s.status())));

        for (Map.Entry<String, String> entry : s.submoduleSections().entrySet()) {
            String path = "#/Submodules/" + entry.getKey();
            sectionInfos.add(new SpecSummary.SpecSectionInfo(path, "###", countLines(entry.getValue()), preview(entry.getValue())));
        }

        for (SpecSection section : s.sections()) {
            sectionInfos.add(new SpecSummary.SpecSectionInfo(
                    section.headerPath(),
                    section.headerLevel(),
                    countLines(section.content()),
                    preview(section.content())
            ));
        }

        List<String> submoduleNames = new ArrayList<>(s.submoduleSections().keySet());

        return new SpecSummary(
                specId,
                title,
                description,
                sectionInfos,
                submoduleNames,
                s.links(),
                Instant.now()
        );
    }

    @Override
    public Optional<SpecSection> getSection(String specId, String headerPath) {
        Optional<Spec> spec = specRepository.findById(specId);
        if (spec.isEmpty()) {
            return Optional.empty();
        }

        Spec s = spec.get();

        // Handle special sections
        if (headerPath.equals("#/Header")) {
            return Optional.of(new SpecSection(
                    UUID.randomUUID().toString(),
                    "#/Header",
                    "#",
                    s.header(),
                    new ArrayList<>(),
                    s.createdAt(),
                    s.lastUpdatedAt()
            ));
        }

        if (headerPath.equals("#/Plan")) {
            return Optional.of(new SpecSection(
                    UUID.randomUUID().toString(),
                    "#/Plan",
                    "##",
                    s.plan(),
                    new ArrayList<>(),
                    s.createdAt(),
                    s.lastUpdatedAt()
            ));
        }

        if (headerPath.equals("#/Status")) {
            return Optional.of(new SpecSection(
                    UUID.randomUUID().toString(),
                    "#/Status",
                    "##",
                    s.status(),
                    new ArrayList<>(),
                    s.createdAt(),
                    s.lastUpdatedAt()
            ));
        }

        // Handle submodule sections
        if (headerPath.startsWith("#/Submodules/")) {
            String submoduleName = headerPath.substring("#/Submodules/".length());
            String content = s.submoduleSections().get(submoduleName);
            if (content != null) {
                return Optional.of(new SpecSection(
                        UUID.randomUUID().toString(),
                        headerPath,
                        "###",
                        content,
                        new ArrayList<>(),
                        s.createdAt(),
                        s.lastUpdatedAt()
                ));
            }
        }

        // Search in sections
        for (SpecSection section : s.sections()) {
            if (section.headerPath().equals(headerPath)) {
                return Optional.of(section);
            }
        }

        return Optional.empty();
    }

    @Override
    public Spec updateSection(String specId, String headerPath, String content) {
        Optional<Spec> spec = specRepository.findById(specId);
        if (spec.isEmpty()) {
            throw new RuntimeException("Spec not found: " + specId);
        }

        Spec s = spec.get();

        if (headerPath.equals("#/Plan")) {
            Spec updated = s.withPlan(content);
            specRepository.save(updated);
            saveSpec(updated);
            return updated;
        }

        if (headerPath.equals("#/Status")) {
            Spec updated = s.withStatus(content);
            specRepository.save(updated);
            saveSpec(updated);
            return updated;
        }

        if (headerPath.startsWith("#/Submodules/")) {
            String submoduleName = headerPath.substring("#/Submodules/".length());
            Spec updated = s.addSubmoduleSection(submoduleName, content);
            specRepository.save(updated);
            saveSpec(updated);
            return updated;
        }

        // Update or add section
        List<SpecSection> newSections = new ArrayList<>(s.sections());
        boolean found = false;
        for (int i = 0; i < newSections.size(); i++) {
            if (newSections.get(i).headerPath().equals(headerPath)) {
                newSections.set(i, newSections.get(i).withContent(content));
                found = true;
                break;
            }
        }

        if (!found) {
            newSections.add(new SpecSection(
                    UUID.randomUUID().toString(),
                    headerPath,
                    "##",
                    content,
                    new ArrayList<>(),
                    Instant.now(),
                    Instant.now()
            ));
        }

        Spec updated = new Spec(
                s.specId(),
                s.worktreeId(),
                s.filePath(),
                s.header(),
                s.plan(),
                s.status(),
                newSections,
                s.submoduleSections(),
                s.links(),
                s.createdAt(),
                Instant.now(),
                s.lastValidatedAt(),
                s.metadata()
        );

        specRepository.save(updated);
        saveSpec(updated);
        return updated;
    }

    @Override
    public Spec updatePlan(String specId, String planContent) {
        return updateSection(specId, "#/Plan", planContent);
    }

    @Override
    public Spec updateStatus(String specId, String statusContent) {
        return updateSection(specId, "#/Status", statusContent);
    }

    @Override
    public Spec updateSubmoduleSection(String specId, String submoduleName, String content) {
        return updateSection(specId, "#/Submodules/" + submoduleName, content);
    }

    @Override
    public Optional<String> getSubmoduleSection(String specId, String submoduleName) {
        return getSection(specId, "#/Submodules/" + submoduleName)
                .map(SpecSection::content);
    }

    @Override
    public Spec mergeSpecs(String childSpecId, String parentSpecId) {
        Optional<Spec> childOpt = specRepository.findById(childSpecId);
        Optional<Spec> parentOpt = specRepository.findById(parentSpecId);

        if (childOpt.isEmpty() || parentOpt.isEmpty()) {
            throw new RuntimeException("Child or parent spec not found");
        }

        Spec child = childOpt.get();
        Spec parent = parentOpt.get();

        // Get child summary
        SpecSummary childSummary = getSummary(childSpecId);

        // Merge child plan into parent plan
        String mergedPlan = parent.plan() + "\n\n### " + child.header() + "\n" + child.plan();

        // Merge child status into parent status
        String mergedStatus = parent.status() + "\n\n- Child: " + childSummary.summary();

        // Update parent with merged content
        Spec updated = new Spec(
                parent.specId(),
                parent.worktreeId(),
                parent.filePath(),
                parent.header(),
                mergedPlan,
                mergedStatus,
                parent.sections(),
                mergeMaps(parent.submoduleSections(), child.submoduleSections()),
                parent.links(),
                parent.createdAt(),
                Instant.now(),
                parent.lastValidatedAt(),
                parent.metadata()
        );

        specRepository.save(updated);
        saveSpec(updated);
        return updated;
    }

    @Override
    public void saveSpec(Spec spec) {
        try {
            Path specPath = Path.of(spec.filePath());
            Files.createDirectories(specPath.getParent());

            StringBuilder content = new StringBuilder();
            content.append(spec.header()).append("\n");
            content.append(spec.plan()).append("\n");
            content.append(spec.status()).append("\n");

            if (!spec.submoduleSections().isEmpty()) {
                content.append("\n## Submodules\n\n");
                for (Map.Entry<String, String> entry : spec.submoduleSections().entrySet()) {
                    content.append("### ").append(entry.getKey()).append("\n\n");
                    content.append(entry.getValue()).append("\n\n");
                }
            }

            for (SpecSection section : spec.sections()) {
                content.append(section.headerLevel()).append(" ").append(section.headerPath()).append("\n\n");
                content.append(section.content()).append("\n\n");
            }

            Files.write(specPath, content.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Warning: Could not save spec to disk: " + e.getMessage());
        }
    }

    @Override
    public Optional<Spec> loadSpecFromFile(Path filePath) {
        try {
            if (!Files.exists(filePath)) {
                return Optional.empty();
            }

            String content = Files.readString(filePath);
            String specId = UUID.randomUUID().toString();

            // Parse header
            String header = extractSection(content, "# Specification", "##");
            String plan = extractSection(content, "## Plan", "##");
            String status = extractSection(content, "## Status", "##");

            Spec spec = new Spec(
                    specId,
                    "", // worktreeId will be set by caller
                    filePath.toString(),
                    header,
                    plan,
                    status,
                    new ArrayList<>(),
                    new HashMap<>(),
                    new ArrayList<>(),
                    Files.getLastModifiedTime(filePath).toInstant(),
                    Instant.now(),
                    0,
                    new HashMap<>()
            );

            specRepository.save(spec);
            return Optional.of(spec);
        } catch (IOException e) {
            System.err.println("Warning: Could not load spec from file: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteSpec(String specId) {
        Optional<Spec> spec = specRepository.findById(specId);
        spec.ifPresent(s -> {
            try {
                Files.deleteIfExists(Path.of(s.filePath()));
            } catch (IOException e) {
                System.err.println("Warning: Could not delete spec file: " + e.getMessage());
            }
            specRepository.delete(specId);
        });
    }

    @Override
    public List<Spec> getSpecsForWorktree(String worktreeId) {
        return specRepository.findByWorktreeId(worktreeId);
    }

    // ======== PRIVATE HELPERS ========

    private String extractTitle(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.trim().startsWith("#")) {
                return line.replaceAll("#+\\s+", "").trim();
            }
        }
        return "";
    }

    private String extractFirstParagraph(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String[] parts = text.split("\n\n");
        return parts[0].replaceAll("#+\\s+", "").trim();
    }

    private int countLines(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return text.split("\n").length;
    }

    private String preview(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String cleaned = text.replaceAll("#+\\s+", "").trim();
        if (cleaned.length() <= 100) {
            return cleaned;
        }
        return cleaned.substring(0, 100) + "...";
    }

    private String extractSection(String content, String sectionStart, String nextSectionMarker) {
        int startIdx = content.indexOf(sectionStart);
        if (startIdx == -1) {
            return "";
        }

        startIdx += sectionStart.length();
        int endIdx = content.indexOf(nextSectionMarker, startIdx);

        if (endIdx == -1) {
            return content.substring(startIdx).trim();
        }

        return content.substring(startIdx, endIdx).trim();
    }

    private <K, V> Map<K, V> mergeMaps(Map<K, V> parent, Map<K, V> child) {
        Map<K, V> result = new HashMap<>(parent);
        result.putAll(child);
        return result;
    }
}
