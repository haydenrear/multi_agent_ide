# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `fullWorkflow_withSubmoduleChanges_propagateToSource` |
| **Started at** | 2026-02-26T23:16:35.458966Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes",
  "phase" : "DISCOVERY"
}
```

### Response (`OrchestratorRouting`)

```json
{
  "discoveryOrchestratorRequest" : {
    "goal" : "Implement feature with lib changes"
  }
}
```

---

## Call 2: `workflow/discovery_orchestrator`

**Request type**: `DiscoveryOrchestratorRequest`  
**Response type**: `DiscoveryOrchestratorRouting`  

### Decorated Request (`DiscoveryOrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes"
}
```

### Response (`DiscoveryOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "goal" : "Implement feature with lib changes",
      "subdomainFocus" : "Primary"
    } ]
  }
}
```

---

## Call 3: `workflow/discovery_agent`

**Request type**: `DiscoveryAgentRequest`  
**Response type**: `DiscoveryAgentRouting`  

### Decorated Request (`DiscoveryAgentRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
      "createdAt" : "2026-02-26T23:16:39.885169Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:40.375453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
      "createdAt" : "2026-02-26T23:16:40.375453Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes",
  "subdomainFocus" : "Primary"
}
```

### Response (`DiscoveryAgentRouting`)

```json
{
  "agentResult" : {
    "output" : "Found stuff"
  }
}
```

---

## Call 4: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
      "createdAt" : "2026-02-26T23:16:39.885169Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:40.375453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
      "createdAt" : "2026-02-26T23:16:40.375453Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
        "baseBranch" : "derived-AJKPVHP7",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:39.885169Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:40.375453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:40.375453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "metadata" : { }
      } ]
    },
    "goal" : "Implement feature with lib changes",
    "subdomainFocus" : "Primary"
  },
  "goal" : "Implement feature with lib changes",
  "sourceAgentType" : "DISCOVERY_AGENT",
  "sourceRequestType" : "DiscoveryAgentRequest",
  "mergeDirection" : "TRUNK_TO_CHILD",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 5: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
      "createdAt" : "2026-02-26T23:16:39.885169Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:40.375453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
      "createdAt" : "2026-02-26T23:16:40.375453Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "result" : [ {
      "output" : "Found stuff",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "968f9915-b8f1-4da8-8a72-d2cd90102a85",
          "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "successful" : true,
          "mergeCommitHash" : "8e05a10fbad7b6aebb297a1b95fe44714a601dcd",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:41.224652Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:39.885169Z",
          "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
            "createdAt" : "2026-02-26T23:16:40.375453Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:40.375453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "DiscoveryAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 6: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "result" : [ {
      "output" : "Found stuff",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "968f9915-b8f1-4da8-8a72-d2cd90102a85",
          "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "successful" : true,
          "mergeCommitHash" : "8e05a10fbad7b6aebb297a1b95fe44714a601dcd",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:41.224652Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:39.885169Z",
          "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
            "createdAt" : "2026-02-26T23:16:40.375453Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:40.375453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "DiscoveryAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 7: `workflow/discovery_dispatch`

**Request type**: `DiscoveryAgentResults`  
**Response type**: `DiscoveryAgentDispatchRouting`  

### Decorated Request (`DiscoveryAgentResults`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TZSGTD4J0H1H1RPA3TPD",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TZSGTD4J0H1H1RPA3TPD/01KJE3TZSGNTTMJJVDJD0QWSBE",
    "output" : "Found stuff",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "968f9915-b8f1-4da8-8a72-d2cd90102a85",
        "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "successful" : true,
        "mergeCommitHash" : "8e05a10fbad7b6aebb297a1b95fe44714a601dcd",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:16:41.224652Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
        "baseBranch" : "derived-AJKPVHP7",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:39.885169Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:40.375453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
        "createdAt" : "2026-02-26T23:16:40.375453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:39.885169Z",
          "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
            "createdAt" : "2026-02-26T23:16:40.375453Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "a265f946-1893-4b69-bd83-069b97e01fa3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63",
          "createdAt" : "2026-02-26T23:16:40.375453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "8c0f52c2-be82-4840-80a6-de0391b56198",
          "childWorktreeId" : "b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b5a6cf2b-79f7-4c2a-90b6-6c1b69c4ed27",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "successful" : true,
          "mergeCommitHash" : "800a2ed587a747f4a0c30372fc96911570a8883e",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:41.892175Z"
        },
        "commitMetadata" : [ ]
      },
      "merged" : true
    } ],
    "pending" : [ ]
  }
}
```

### Response (`DiscoveryAgentDispatchRouting`)

```json
{
  "collectorRequest" : {
    "goal" : "Implement feature with lib changes",
    "discoveryResults" : "discovery-results"
  }
}
```

---

## Call 8: `workflow/discovery_collector`

**Request type**: `DiscoveryCollectorRequest`  
**Response type**: `DiscoveryCollectorRouting`  

### Decorated Request (`DiscoveryCollectorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3V1P25C3PJHCQ083VG2M6",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes",
  "discoveryResults" : "discovery-results"
}
```

### Response (`DiscoveryCollectorRouting`)

```json
{
  "collectorResult" : {
    "consolidatedOutput" : "Discovery complete",
    "collectorDecision" : {
      "decisionType" : "ADVANCE_PHASE",
      "rationale" : "Advance to planning",
      "requestedPhase" : "PLANNING"
    }
  }
}
```

---

## Call 9: `workflow/planning_orchestrator`

**Request type**: `PlanningOrchestratorRequest`  
**Response type**: `PlanningOrchestratorRouting`  

### Decorated Request (`PlanningOrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes"
}
```

### Response (`PlanningOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "goal" : "Implement feature with lib changes"
    } ]
  }
}
```

---

## Call 10: `workflow/planning_agent`

**Request type**: `PlanningAgentRequest`  
**Response type**: `PlanningAgentRouting`  

### Decorated Request (`PlanningAgentRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F/01KJE3V7FPZ7G30K1VWKE7BQJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
      "createdAt" : "2026-02-26T23:16:50.558771Z",
      "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:51.213453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
      "createdAt" : "2026-02-26T23:16:51.213453Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes"
}
```

### Response (`PlanningAgentRouting`)

```json
{
  "agentResult" : {
    "output" : "Plan output"
  }
}
```

---

## Call 11: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
      "createdAt" : "2026-02-26T23:16:50.558771Z",
      "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:51.213453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
      "createdAt" : "2026-02-26T23:16:51.213453Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F/01KJE3V7FPZ7G30K1VWKE7BQJ2",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
        "baseBranch" : "derived-AJKPVHP7",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:50.558771Z",
        "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:51.213453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:51.213453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "metadata" : { }
      } ]
    },
    "goal" : "Implement feature with lib changes"
  },
  "goal" : "Implement feature with lib changes",
  "sourceAgentType" : "PLANNING_AGENT",
  "sourceRequestType" : "PlanningAgentRequest",
  "mergeDirection" : "TRUNK_TO_CHILD",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 12: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
      "createdAt" : "2026-02-26T23:16:50.558771Z",
      "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:51.213453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
      "createdAt" : "2026-02-26T23:16:51.213453Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "planningAgentResults" : [ {
      "output" : "Plan output",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "9c013772-e07a-47dc-ab3f-c8ed3bc5d2dd",
          "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "successful" : true,
          "mergeCommitHash" : "0e45facb71550783b2b5a28df496e10ebf57d157",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:51.915140Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:50.558771Z",
          "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
            "createdAt" : "2026-02-26T23:16:51.213453Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:51.213453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "PlanningAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 13: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "planningAgentResults" : [ {
      "output" : "Plan output",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "9c013772-e07a-47dc-ab3f-c8ed3bc5d2dd",
          "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "successful" : true,
          "mergeCommitHash" : "0e45facb71550783b2b5a28df496e10ebf57d157",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:51.915140Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:50.558771Z",
          "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
            "createdAt" : "2026-02-26T23:16:51.213453Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:51.213453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "PlanningAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 14: `workflow/planning_dispatch`

**Request type**: `PlanningAgentResults`  
**Response type**: `PlanningAgentDispatchRouting`  

### Decorated Request (`PlanningAgentResults`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3VA62CQ518GA19GEFC01W",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3VA62CQ518GA19GEFC01W/01KJE3VA62EBMY4HFX1101W2KE",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "9c013772-e07a-47dc-ab3f-c8ed3bc5d2dd",
        "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "successful" : true,
        "mergeCommitHash" : "0e45facb71550783b2b5a28df496e10ebf57d157",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:16:51.915140Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
        "baseBranch" : "derived-AJKPVHP7",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:50.558771Z",
        "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:51.213453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
        "createdAt" : "2026-02-26T23:16:51.213453Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:50.558771Z",
          "lastCommitHash" : "06c5e31f7e43c54fbc2e96fd5663dd6dd665bd44",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
            "createdAt" : "2026-02-26T23:16:51.213453Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "10aaeb3b-6a02-490f-ab86-a2782aed310c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3V7FP9JB8YH8434KJGG4F",
          "createdAt" : "2026-02-26T23:16:51.213453Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "4410f307-f219-4fa5-abf7-1e4f95885d5f",
          "childWorktreeId" : "2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/2a90c8a2-bbfa-4a2a-aced-091b562e675d",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "successful" : true,
          "mergeCommitHash" : "b016b6e363b5d6319ddd09edb73877a719fd93be",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:52.535974Z"
        },
        "commitMetadata" : [ ]
      },
      "merged" : true
    } ],
    "pending" : [ ]
  }
}
```

### Response (`PlanningAgentDispatchRouting`)

```json
{
  "planningCollectorRequest" : {
    "goal" : "Implement feature with lib changes",
    "planningResults" : "planning-results"
  }
}
```

---

## Call 15: `workflow/planning_collector`

**Request type**: `PlanningCollectorRequest`  
**Response type**: `PlanningCollectorRouting`  

### Decorated Request (`PlanningCollectorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3V5JA78P8TH9K3W7ZA1ZB/01KJE3VC29N6GQ6JC1324E8PZ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes",
  "planningResults" : "planning-results"
}
```

### Response (`PlanningCollectorRouting`)

```json
{
  "collectorResult" : {
    "consolidatedOutput" : "Planning complete",
    "collectorDecision" : {
      "decisionType" : "ADVANCE_PHASE",
      "rationale" : "Advance to tickets",
      "requestedPhase" : "TICKETS"
    }
  }
}
```

---

## Call 16: `workflow/ticket_orchestrator`

**Request type**: `TicketOrchestratorRequest`  
**Response type**: `TicketOrchestratorRouting`  

### Decorated Request (`TicketOrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes"
}
```

### Response (`TicketOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "ticketDetails" : "Implement feature with lib changes",
      "ticketDetailsFilePath" : "ticket-1.md"
    } ]
  }
}
```

---

## Call 17: `workflow/ticket_agent`

**Request type**: `TicketAgentRequest`  
**Response type**: `TicketAgentRouting`  

### Decorated Request (`TicketAgentRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ/01KJE3VHS038S4RJKNT1J31JBA",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
      "createdAt" : "2026-02-26T23:17:00.871775Z",
      "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:01.445706Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
      "createdAt" : "2026-02-26T23:17:01.445706Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "metadata" : { }
    } ]
  },
  "ticketDetails" : "Implement feature with lib changes",
  "ticketDetailsFilePath" : "ticket-1.md"
}
```

### Response (`TicketAgentRouting`)

```json
{
  "agentResult" : {
    "output" : "Ticket output"
  }
}
```

---

## Call 18: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
      "createdAt" : "2026-02-26T23:17:00.871775Z",
      "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:01.445706Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
      "createdAt" : "2026-02-26T23:17:01.445706Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ/01KJE3VHS038S4RJKNT1J31JBA",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
        "baseBranch" : "derived-AJKPVHP7",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:00.871775Z",
        "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:01.445706Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:01.445706Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "metadata" : { }
      } ]
    },
    "ticketDetails" : "Implement feature with lib changes",
    "ticketDetailsFilePath" : "ticket-1.md"
  },
  "goal" : "Implement feature with lib changes",
  "sourceAgentType" : "TICKET_AGENT",
  "sourceRequestType" : "TicketAgentRequest",
  "mergeDirection" : "TRUNK_TO_CHILD",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 19: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
      "baseBranch" : "derived-AJKPVHP7",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
      "createdAt" : "2026-02-26T23:17:00.871775Z",
      "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:01.445706Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
      "createdAt" : "2026-02-26T23:17:01.445706Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "ticketAgentResults" : [ {
      "output" : "Ticket output",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "674ca63f-fc97-49a1-887e-8556470d8c8d",
          "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "successful" : true,
          "mergeCommitHash" : "ce06f1c13e96ba0cf786149ec7deb16f75716607",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:17:02.126988Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:00.871775Z",
          "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
            "createdAt" : "2026-02-26T23:17:01.445706Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:01.445706Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "TicketAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 20: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "ticketAgentResults" : [ {
      "output" : "Ticket output",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "674ca63f-fc97-49a1-887e-8556470d8c8d",
          "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "successful" : true,
          "mergeCommitHash" : "ce06f1c13e96ba0cf786149ec7deb16f75716607",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:17:02.126988Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:00.871775Z",
          "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
            "createdAt" : "2026-02-26T23:17:01.445706Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:01.445706Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "TicketAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 21: `workflow/ticket_dispatch`

**Request type**: `TicketAgentResults`  
**Response type**: `TicketAgentDispatchRouting`  

### Decorated Request (`TicketAgentResults`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VM4Y99B6CJ15AJCCV759",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VM4Y99B6CJ15AJCCV759/01KJE3VM4YAE3K6HA3AN20WJQG",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "674ca63f-fc97-49a1-887e-8556470d8c8d",
        "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "successful" : true,
        "mergeCommitHash" : "ce06f1c13e96ba0cf786149ec7deb16f75716607",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:17:02.126988Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
        "baseBranch" : "derived-AJKPVHP7",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:00.871775Z",
        "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:01.445706Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
        "createdAt" : "2026-02-26T23:17:01.445706Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/",
          "baseBranch" : "derived-AJKPVHP7",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:00.871775Z",
          "lastCommitHash" : "bbeb36b9ed0b52bfeb06514bd558c2cd548b0e10",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
            "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
            "createdAt" : "2026-02-26T23:17:01.445706Z",
            "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "ed21c391-aa41-401a-a99a-ffdf000ea78e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VHS0AV300J4GDG9Y43KZ",
          "createdAt" : "2026-02-26T23:17:01.445706Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "8f6b07c5-cbb3-459f-9657-85235a30f10d",
          "childWorktreeId" : "f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f271decb-4dcc-4e5c-bbdb-618d710a0c40",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "successful" : true,
          "mergeCommitHash" : "fb51855839223e3721bc424d4cad6ccbb11a596f",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:17:02.729652Z"
        },
        "commitMetadata" : [ ]
      },
      "merged" : true
    } ],
    "pending" : [ ]
  }
}
```

### Response (`TicketAgentDispatchRouting`)

```json
{
  "ticketCollectorRequest" : {
    "goal" : "Implement feature with lib changes",
    "ticketResults" : "ticket-results"
  }
}
```

---

## Call 22: `workflow/ticket_collector`

**Request type**: `TicketCollectorRequest`  
**Response type**: `TicketCollectorRouting`  

### Decorated Request (`TicketCollectorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VFY49QVC4GY1XRH6NYTR/01KJE3VP90DT6EEGPECZ6GK7VN",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes",
  "ticketResults" : "ticket-results"
}
```

### Response (`TicketCollectorRouting`)

```json
{
  "collectorResult" : {
    "consolidatedOutput" : "Tickets complete",
    "collectorDecision" : {
      "decisionType" : "ADVANCE_PHASE",
      "rationale" : "Advance to orchestrator collector",
      "requestedPhase" : "COMPLETE"
    }
  }
}
```

---

## Call 23: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3TVBR7ME9CHHRTT11NAQY/01KJE3TX85YBW70K7XZXTZ6Z63/01KJE3TX8531C9RGH2BQ9PQ0AR/01KJE3TZ16GCF9TJTBFCHDDAYK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VTFP1PA1CHWRVEKRZW86",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553699Z",
        "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
          "baseBranch" : "derived-AJKPVHP7",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
          "createdAt" : "2026-02-26T23:16:29.553147Z",
          "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ]
    },
    "goal" : "Implement feature with lib changes",
    "phase" : "DISCOVERY"
  },
  "goal" : "Implement feature with lib changes",
  "sourceAgentType" : "ORCHESTRATOR_COLLECTOR",
  "sourceRequestType" : "OrchestratorCollectorRequest",
  "mergeDirection" : "WORKTREE_TO_SOURCE",
  "conflictFiles" : [ ]
}
```

### Response (`MergeConflictResult`)

```json
{
  "successful" : true,
  "output" : "Merge conflict validation complete.",
  "resolvedConflictFiles" : [ ],
  "notes" : [ "No further conflict action required" ]
}
```

---

## Call 24: `workflow/orchestrator_collector`

**Request type**: `OrchestratorCollectorRequest`  
**Response type**: `OrchestratorCollectorRouting`  

### Decorated Request (`OrchestratorCollectorRequest`)

```json
{
  "contextId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7/01KJE3VTFP1PA1CHWRVEKRZW86",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553699Z",
      "lastCommitHash" : "e6bc32ab6c5b127e3e7f5d03b15484a67371f4df",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
        "baseBranch" : "derived-AJKPVHP7",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
        "createdAt" : "2026-02-26T23:16:29.553147Z",
        "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "0c4284ef-614f-451f-9f7f-eb7bc79f446f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4/libs/test-sub/",
      "baseBranch" : "derived-AJKPVHP7",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "associatedNodeId" : "ak:01KJE3TJQCTMPEAHA5AJKPVHP7",
      "createdAt" : "2026-02-26T23:16:29.553147Z",
      "lastCommitHash" : "49e42afd275e3a8ff0ca9a9e997044e7583f27da",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature with lib changes",
  "phase" : "DISCOVERY",
  "mergeDescriptor" : {
    "mergeDirection" : "WORKTREE_TO_SOURCE",
    "successful" : true,
    "conflictFiles" : [ ],
    "submoduleMergeResults" : [ ],
    "mainWorktreeMergeResult" : {
      "mergeId" : "73d97f7f-eabf-400e-9252-589e9d9faff2",
      "childWorktreeId" : "3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3d2e4da3-6d27-4f67-bc9a-d7abb01fb2d4",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main3875844253910906747",
      "successful" : true,
      "mergeCommitHash" : "b54d3ec5468fded1e2c34fd5cce8ee7bef31f1fd",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T23:17:10.172111Z"
    },
    "commitMetadata" : [ ]
  }
}
```

### Response (`OrchestratorCollectorRouting`)

```json
{
  "collectorResult" : {
    "consolidatedOutput" : "Workflow complete",
    "collectorDecision" : {
      "decisionType" : "ADVANCE_PHASE",
      "rationale" : "All phases done",
      "requestedPhase" : "COMPLETE"
    }
  }
}
```

---

