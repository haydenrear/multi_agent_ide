# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `discoveryPhase_twoAgents_conflictDetected` |
| **Started at** | 2026-02-26T22:08:48.921701Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
  "phase" : "DISCOVERY"
}
```

### Response (`OrchestratorRouting`)

```json
{
  "discoveryOrchestratorRequest" : {
    "goal" : "Discover features"
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
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features"
}
```

### Response (`DiscoveryOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "goal" : "Discover features",
      "subdomainFocus" : "Area 1"
    }, {
      "goal" : "Discover features",
      "subdomainFocus" : "Area 2"
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
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:53.343054Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.897490Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:53.897490Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
  "subdomainFocus" : "Area 1"
}
```

### Response (`DiscoveryAgentRouting`)

```json
{
  "agentResult" : {
    "output" : "Agent 1 findings"
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
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:53.343054Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.897490Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:53.897490Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.343054Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.897490Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.897490Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "metadata" : { }
      } ]
    },
    "goal" : "Discover features",
    "subdomainFocus" : "Area 1"
  },
  "goal" : "Discover features",
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

## Call 5: `workflow/discovery_agent`

**Request type**: `DiscoveryAgentRequest`  
**Response type**: `DiscoveryAgentRouting`  

### Decorated Request (`DiscoveryAgentRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4J6AMVTGX53VW2H0JS",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:54.422888Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.897396Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:54.897396Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
  "subdomainFocus" : "Area 2"
}
```

### Response (`DiscoveryAgentRouting`)

```json
{
  "agentResult" : {
    "output" : "Agent 2 findings"
  }
}
```

---

## Call 6: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:54.422888Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.897396Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:54.897396Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4J6AMVTGX53VW2H0JS",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.422888Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.897396Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.897396Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "metadata" : { }
      } ]
    },
    "goal" : "Discover features",
    "subdomainFocus" : "Area 2"
  },
  "goal" : "Discover features",
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

## Call 7: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:53.343054Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.897490Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:53.897490Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ]
    },
    "result" : [ {
      "output" : "Agent 1 findings",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "aeb39b33-c95e-40b0-9f3e-1f63957de6c8",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "successful" : true,
          "mergeCommitHash" : "b80f315e85e2ab333635aa8cd1c247d43db9c00d",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:08:55.633257Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.343054Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:53.897490Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.897490Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "metadata" : { }
        } ]
      }
    }, {
      "output" : "Agent 2 findings",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "aeb13fe0-3081-47b3-aec9-a346d40dbcc2",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "successful" : true,
          "mergeCommitHash" : "5651e45163dfccd06b037882f26d7dc38632cf00",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:08:56.452774Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.422888Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:54.897396Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.897396Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
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

## Call 8: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:54.422888Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.897396Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
      "createdAt" : "2026-02-26T22:08:54.897396Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ]
    },
    "result" : [ {
      "output" : "Agent 1 findings",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "aeb39b33-c95e-40b0-9f3e-1f63957de6c8",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "successful" : true,
          "mergeCommitHash" : "b80f315e85e2ab333635aa8cd1c247d43db9c00d",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:08:55.633257Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.343054Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:53.897490Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.897490Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "metadata" : { }
        } ]
      }
    }, {
      "output" : "Agent 2 findings",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "aeb13fe0-3081-47b3-aec9-a346d40dbcc2",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "successful" : true,
          "mergeCommitHash" : "5651e45163dfccd06b037882f26d7dc38632cf00",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:08:56.452774Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.422888Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:54.897396Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.897396Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "DiscoveryAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
  "mergeError" : "Merge conflicts detected"
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

## Call 9: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ]
    },
    "result" : [ {
      "output" : "Agent 1 findings",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "aeb39b33-c95e-40b0-9f3e-1f63957de6c8",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "successful" : true,
          "mergeCommitHash" : "b80f315e85e2ab333635aa8cd1c247d43db9c00d",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:08:55.633257Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.343054Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:53.897490Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.897490Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "metadata" : { }
        } ]
      }
    }, {
      "output" : "Agent 2 findings",
      "mergeDescriptor" : {
        "mergeDirection" : "TRUNK_TO_CHILD",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "aeb13fe0-3081-47b3-aec9-a346d40dbcc2",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "successful" : true,
          "mergeCommitHash" : "5651e45163dfccd06b037882f26d7dc38632cf00",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:08:56.452774Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.422888Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:54.897396Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.897396Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "DiscoveryAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
  "mergeError" : "Merge validation failed: parent missing child commit"
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

## Call 10: `workflow/discovery_dispatch`

**Request type**: `DiscoveryAgentResults`  
**Response type**: `DiscoveryAgentDispatchRouting`  

### Decorated Request (`DiscoveryAgentResults`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYYCMX32FAKK17HHQ4DSA",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYYCMX32FAKK17HHQ4DSA/01KJDZYYCMM1K44KXR93ADT8K4",
    "output" : "Agent 1 findings",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "aeb39b33-c95e-40b0-9f3e-1f63957de6c8",
        "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "successful" : true,
        "mergeCommitHash" : "b80f315e85e2ab333635aa8cd1c247d43db9c00d",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:08:55.633257Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.343054Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.897490Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:53.897490Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
        "metadata" : { }
      } ]
    }
  }, {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYYCMX32FAKK17HHQ4DSA/01KJDZYYCMPBB42JJGYFXJNS5N",
    "output" : "Agent 2 findings",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "aeb13fe0-3081-47b3-aec9-a346d40dbcc2",
        "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "successful" : true,
        "mergeCommitHash" : "5651e45163dfccd06b037882f26d7dc38632cf00",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:08:56.452774Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.422888Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.897396Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
        "createdAt" : "2026-02-26T22:08:54.897396Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.343054Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:53.897490Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "620d4219-d635-44da-b2a8-2b1861801995",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:53.897490Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : false,
        "conflictFiles" : [ "shared-findings.md" ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "e74e5dba-c533-4b12-ab24-59ff82c84535",
          "childWorktreeId" : "99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/99994ff5-f95b-4645-9cd8-d2653b9be6c3",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "successful" : false,
          "mergeCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "conflicts" : [ {
            "filePath" : "shared-findings.md",
            "conflictType" : "detected",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          } ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge conflicts detected (spot check)",
          "mergedAt" : "2026-02-26T22:08:57.168613Z"
        },
        "errorMessage" : "Merge conflicts detected (spot check)",
        "commitMetadata" : [ ]
      },
      "merged" : false
    } ],
    "pending" : [ ],
    "conflicted" : {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.422888Z",
          "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
            "createdAt" : "2026-02-26T22:08:54.897396Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "58fc7b72-5395-4bb8-a03b-7dfff988e055",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY",
          "createdAt" : "2026-02-26T22:08:54.897396Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : false,
        "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "4274b2ae-66c0-46ae-aa50-44894896b1ed",
          "childWorktreeId" : "b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b9b7e4e2-e019-4f6b-9426-c18b3d4ef19f",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "successful" : false,
          "mergeCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "conflicts" : [ {
            "filePath" : "shared-findings.md",
            "conflictType" : "detected",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          }, {
            "filePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
            "conflictType" : "missing-commit",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          } ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge validation failed: parent missing child commit",
          "mergedAt" : "2026-02-26T22:08:57.231138Z"
        },
        "errorMessage" : "Merge validation failed: parent missing child commit",
        "commitMetadata" : [ ]
      },
      "merged" : false
    }
  }
}
```

### Response (`DiscoveryAgentDispatchRouting`)

```json
{
  "collectorRequest" : {
    "goal" : "Discover features",
    "discoveryResults" : "discovery-results"
  }
}
```

---

## Call 11: `workflow/discovery_collector`

**Request type**: `DiscoveryCollectorRequest`  
**Response type**: `DiscoveryCollectorRouting`  

### Decorated Request (`DiscoveryCollectorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZZ0FCHRWRJKJG107XG55K",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
  "discoveryResults" : "discovery-results"
}
```

### Response (`DiscoveryCollectorRouting`)

```json
{
  "collectorResult" : {
    "consolidatedOutput" : "Discovery complete with conflicts",
    "collectorDecision" : {
      "decisionType" : "ADVANCE_PHASE",
      "rationale" : "Advance despite conflicts",
      "requestedPhase" : "PLANNING"
    }
  }
}
```

---

## Call 12: `workflow/planning_orchestrator`

**Request type**: `PlanningOrchestratorRequest`  
**Response type**: `PlanningOrchestratorRouting`  

### Decorated Request (`PlanningOrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features"
}
```

### Response (`PlanningOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "goal" : "Discover features"
    } ]
  }
}
```

---

## Call 13: `workflow/planning_agent`

**Request type**: `PlanningAgentRequest`  
**Response type**: `PlanningAgentRouting`  

### Decorated Request (`PlanningAgentRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR/01KJDZZ6AVT8ZYMHFNF6BYGWY5",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
      "createdAt" : "2026-02-26T22:09:05.832801Z",
      "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:06.309085Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
      "createdAt" : "2026-02-26T22:09:06.309085Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features"
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

## Call 14: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
      "createdAt" : "2026-02-26T22:09:05.832801Z",
      "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:06.309085Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
      "createdAt" : "2026-02-26T22:09:06.309085Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR/01KJDZZ6AVT8ZYMHFNF6BYGWY5",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:05.832801Z",
        "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:06.309085Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:06.309085Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "metadata" : { }
      } ]
    },
    "goal" : "Discover features"
  },
  "goal" : "Discover features",
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

## Call 15: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
      "createdAt" : "2026-02-26T22:09:05.832801Z",
      "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:06.309085Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
      "createdAt" : "2026-02-26T22:09:06.309085Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
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
          "mergeId" : "69a59860-2bf0-4e23-b686-435b8054d009",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61",
          "successful" : true,
          "mergeCommitHash" : "6f1cb1b57a47299daee48e0c88a3fd415a1092e8",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:06.946284Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:05.832801Z",
          "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
            "createdAt" : "2026-02-26T22:09:06.309085Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:06.309085Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "PlanningAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
  "mergeError" : "Merge conflicts detected"
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

## Call 16: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
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
          "mergeId" : "69a59860-2bf0-4e23-b686-435b8054d009",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61",
          "successful" : true,
          "mergeCommitHash" : "6f1cb1b57a47299daee48e0c88a3fd415a1092e8",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:06.946284Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:05.832801Z",
          "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
            "createdAt" : "2026-02-26T22:09:06.309085Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:06.309085Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "PlanningAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
  "mergeError" : "Merge validation failed: parent missing child commit"
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

## Call 17: `workflow/planning_dispatch`

**Request type**: `PlanningAgentResults`  
**Response type**: `PlanningAgentDispatchRouting`  

### Decorated Request (`PlanningAgentResults`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ8NM45PTRG97CV3GV86A",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ8NM45PTRG97CV3GV86A/01KJDZZ8NMZ62S2JET7083JZGW",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "69a59860-2bf0-4e23-b686-435b8054d009",
        "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61",
        "successful" : true,
        "mergeCommitHash" : "6f1cb1b57a47299daee48e0c88a3fd415a1092e8",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:09:06.946284Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:05.832801Z",
        "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:06.309085Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
        "createdAt" : "2026-02-26T22:09:06.309085Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ ],
    "pending" : [ ],
    "conflicted" : {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:05.832801Z",
          "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
            "createdAt" : "2026-02-26T22:09:06.309085Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "7567deb7-4783-43a7-8e9e-6a04e4e24174",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZ6AVK024JJMNQQR5BMWR",
          "createdAt" : "2026-02-26T22:09:06.309085Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : false,
        "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "63d113cd-e2f6-47e3-bb58-b04608600ab7",
          "childWorktreeId" : "fad73fae-0312-4b17-8411-0fcb127f8b61",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fad73fae-0312-4b17-8411-0fcb127f8b61",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "successful" : false,
          "mergeCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "conflicts" : [ {
            "filePath" : "shared-findings.md",
            "conflictType" : "detected",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          }, {
            "filePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
            "conflictType" : "missing-commit",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          } ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge validation failed: parent missing child commit",
          "mergedAt" : "2026-02-26T22:09:07.759085Z"
        },
        "errorMessage" : "Merge validation failed: parent missing child commit",
        "commitMetadata" : [ ]
      },
      "merged" : false
    }
  }
}
```

### Response (`PlanningAgentDispatchRouting`)

```json
{
  "planningCollectorRequest" : {
    "goal" : "Discover features",
    "planningResults" : "planning-results"
  }
}
```

---

## Call 18: `workflow/planning_collector`

**Request type**: `PlanningCollectorRequest`  
**Response type**: `PlanningCollectorRouting`  

### Decorated Request (`PlanningCollectorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZ4DNM964PHCAZC54B14N/01KJDZZAM5Y3XZ4G0DWHNYCTV2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
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

## Call 19: `workflow/ticket_orchestrator`

**Request type**: `TicketOrchestratorRequest`  
**Response type**: `TicketOrchestratorRouting`  

### Decorated Request (`TicketOrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features"
}
```

### Response (`TicketOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "ticketDetails" : "Discover features",
      "ticketDetailsFilePath" : "ticket-1.md"
    } ]
  }
}
```

---

## Call 20: `workflow/ticket_agent`

**Request type**: `TicketAgentRequest`  
**Response type**: `TicketAgentRouting`  

### Decorated Request (`TicketAgentRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q/01KJDZZGETQTWGPHAYEHXNNWM5",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
      "createdAt" : "2026-02-26T22:09:16.362534Z",
      "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.981037Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
      "createdAt" : "2026-02-26T22:09:16.981037Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "metadata" : { }
    } ]
  },
  "ticketDetails" : "Discover features",
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

## Call 21: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
      "createdAt" : "2026-02-26T22:09:16.362534Z",
      "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.981037Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
      "createdAt" : "2026-02-26T22:09:16.981037Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q/01KJDZZGETQTWGPHAYEHXNNWM5",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.362534Z",
        "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.981037Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.981037Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "metadata" : { }
      } ]
    },
    "ticketDetails" : "Discover features",
    "ticketDetailsFilePath" : "ticket-1.md"
  },
  "goal" : "Discover features",
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

## Call 22: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
      "baseBranch" : "derived-XJVC938H",
      "derivedBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
      "createdAt" : "2026-02-26T22:09:16.362534Z",
      "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.981037Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
      "createdAt" : "2026-02-26T22:09:16.981037Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
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
          "mergeId" : "ff3d2653-e5f0-4524-a3f9-177d9e004068",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "successful" : true,
          "mergeCommitHash" : "94c9eb8777ffcfb475f8e701f8da2eb8136ae0ac",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:17.669945Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.362534Z",
          "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
            "createdAt" : "2026-02-26T22:09:16.981037Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.981037Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "TicketAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
  "mergeError" : "Merge conflicts detected"
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

## Call 23: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
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
          "mergeId" : "ff3d2653-e5f0-4524-a3f9-177d9e004068",
          "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "successful" : true,
          "mergeCommitHash" : "94c9eb8777ffcfb475f8e701f8da2eb8136ae0ac",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:17.669945Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.362534Z",
          "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
            "createdAt" : "2026-02-26T22:09:16.981037Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.981037Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "metadata" : { }
        } ]
      }
    } ]
  },
  "sourceAgentType" : "ALL",
  "sourceRequestType" : "TicketAgentResults",
  "mergeDirection" : "CHILD_TO_TRUNK",
  "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
  "mergeError" : "Merge validation failed: parent missing child commit"
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

## Call 24: `workflow/ticket_dispatch`

**Request type**: `TicketAgentResults`  
**Response type**: `TicketAgentDispatchRouting`  

### Decorated Request (`TicketAgentResults`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZK17F59YWH9MY6JYC2SV",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZK17F59YWH9MY6JYC2SV/01KJDZZK17A9TJTJZ1KEPZ6PR0",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "ff3d2653-e5f0-4524-a3f9-177d9e004068",
        "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "successful" : true,
        "mergeCommitHash" : "94c9eb8777ffcfb475f8e701f8da2eb8136ae0ac",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:09:17.669945Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
        "baseBranch" : "derived-XJVC938H",
        "derivedBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.362534Z",
        "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.981037Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
        "createdAt" : "2026-02-26T22:09:16.981037Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ ],
    "pending" : [ ],
    "conflicted" : {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/",
          "baseBranch" : "derived-XJVC938H",
          "derivedBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.362534Z",
          "lastCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
            "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
            "createdAt" : "2026-02-26T22:09:16.981037Z",
            "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "d4ad30a9-d3c2-4d43-b082-2c4bad47a7a9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZGETQ7PCCH0JFBGMW66Q",
          "createdAt" : "2026-02-26T22:09:16.981037Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : false,
        "conflictFiles" : [ "shared-findings.md", "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc" ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "ef2af797-c841-45f9-9f40-639502daebd2",
          "childWorktreeId" : "3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3513538c-c95a-4f7a-9c60-b6d822694f4c",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "successful" : false,
          "mergeCommitHash" : "7d4fe2c4b9d2f828df07ad14bf9cc9c360a4af09",
          "conflicts" : [ {
            "filePath" : "shared-findings.md",
            "conflictType" : "detected",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          }, {
            "filePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
            "conflictType" : "missing-commit",
            "baseContent" : "",
            "oursContent" : "",
            "theirsContent" : ""
          } ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge validation failed: parent missing child commit",
          "mergedAt" : "2026-02-26T22:09:18.370889Z"
        },
        "errorMessage" : "Merge validation failed: parent missing child commit",
        "commitMetadata" : [ ]
      },
      "merged" : false
    }
  }
}
```

### Response (`TicketAgentDispatchRouting`)

```json
{
  "ticketCollectorRequest" : {
    "goal" : "Discover features",
    "ticketResults" : "ticket-results"
  }
}
```

---

## Call 25: `workflow/ticket_collector`

**Request type**: `TicketCollectorRequest`  
**Response type**: `TicketCollectorRouting`  

### Decorated Request (`TicketCollectorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZEJ27H2QAHRF209ZBD4R/01KJDZZMXZMZW1EKYQXJ0E7V7W",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
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

## Call 26: `workflow/worktree_commit_agent`

**Request type**: `CommitAgentRequest`  
**Response type**: `CommitAgentResult`  

### Decorated Request (`CommitAgentRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZS09BSCX0JS8AMF02VMA/01KJDZZS0EEAGR8JAHZVAAM7RC",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZS09BSCX0JS8AMF02VMA",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ]
    },
    "goal" : "Discover features",
    "phase" : "DISCOVERY"
  },
  "goal" : "Discover features",
  "sourceAgentType" : "ORCHESTRATOR_COLLECTOR",
  "sourceRequestType" : "OrchestratorCollectorRequest",
  "commitInstructions" : "Use your toolset to inspect git status and commit pending changes in this worktree. Split into multiple focused commits when appropriate. Each commit message must include metadata trailers shown below. Before returning, git status must be clean for the target worktree (no staged, unstaged, untracked, or conflicted files).",
  "sourceResultSummary" : "Orchestrator Collector Request Context Id: ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZS09BSCX0JS8AMF02VMA Worktree Context: \tMain Worktree: \t\tId: f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc \t\tPath: /var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc \t\tBase Branch: main \t\tDerived Branch: derived-XJVC938H \t\tStatus: ACTIVE \t\tParent Worktree Id: null \t\tAssociated Node Id: ak:01KJDZYFP312KNAKC5XJVC938H \t\tCreated At: 2026-02-26T22:08:43.267930Z"
}
```

### Response (`CommitAgentResult`)

```json
{
  "successful" : false
}
```

---

## Call 27: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZYR758ZC6AKMDE0YPGNS9/01KJDZYT4J2S3BRJA4D35613SY/01KJDZYT4JHPWPRH1YW1MR3SNX/01KJDZYWP5K7YH8GHEA3W9F4H1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZS09BSCX0JS8AMF02VMA",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.267930Z",
        "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
          "baseBranch" : "derived-XJVC938H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
          "createdAt" : "2026-02-26T22:08:43.266956Z",
          "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ]
    },
    "goal" : "Discover features",
    "phase" : "DISCOVERY"
  },
  "goal" : "Discover features",
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

## Call 28: `workflow/orchestrator_collector`

**Request type**: `OrchestratorCollectorRequest`  
**Response type**: `OrchestratorCollectorRouting`  

### Decorated Request (`OrchestratorCollectorRequest`)

```json
{
  "contextId" : "ak:01KJDZYFP312KNAKC5XJVC938H/01KJDZZS09BSCX0JS8AMF02VMA",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.267930Z",
      "lastCommitHash" : "558d7a129ee960af6b95934a5f218d458228960a",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
        "baseBranch" : "derived-XJVC938H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
        "createdAt" : "2026-02-26T22:08:43.266956Z",
        "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d47cf6a7-53f4-4394-8ba0-11133b4ce011",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc/libs/test-sub/",
      "baseBranch" : "derived-XJVC938H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "associatedNodeId" : "ak:01KJDZYFP312KNAKC5XJVC938H",
      "createdAt" : "2026-02-26T22:08:43.266956Z",
      "lastCommitHash" : "d2b4bfaa9397b7970727c216a00ba3858addfce8",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "metadata" : { }
    } ]
  },
  "goal" : "Discover features",
  "phase" : "DISCOVERY",
  "mergeDescriptor" : {
    "mergeDirection" : "WORKTREE_TO_SOURCE",
    "successful" : true,
    "conflictFiles" : [ ],
    "submoduleMergeResults" : [ ],
    "mainWorktreeMergeResult" : {
      "mergeId" : "33595433-9ce2-4624-910b-74ed4339513e",
      "childWorktreeId" : "f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0cc5a7f-43a3-42de-a5cc-7d6ce92bf9dc",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main769552132067514598",
      "successful" : true,
      "mergeCommitHash" : "5e76f5f4ba65c87e657b9396d3589249a496f14b",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T22:09:25.252114Z"
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

