# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `fullWorkflow_realMerges_changesReachSource` |
| **Started at** | 2026-02-26T23:15:48.770598Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature",
  "phase" : "DISCOVERY"
}
```

### Response (`OrchestratorRouting`)

```json
{
  "discoveryOrchestratorRequest" : {
    "goal" : "Implement feature"
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature"
}
```

### Response (`DiscoveryOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
      "createdAt" : "2026-02-26T23:15:53.570885Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:54.072581Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
      "createdAt" : "2026-02-26T23:15:54.072581Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
      "createdAt" : "2026-02-26T23:15:53.570885Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:54.072581Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
      "createdAt" : "2026-02-26T23:15:54.072581Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:53.570885Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:54.072581Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:54.072581Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "metadata" : { }
      } ]
    },
    "goal" : "Implement feature",
    "subdomainFocus" : "Primary"
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
      "createdAt" : "2026-02-26T23:15:53.570885Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:54.072581Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
      "createdAt" : "2026-02-26T23:15:54.072581Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
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
          "mergeId" : "eaec0a09-03c3-4146-b601-f8c420a68e62",
          "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "successful" : true,
          "mergeCommitHash" : "f2b4b84bcf0e83a27c58694542c0e4d9ee91a674",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:54.718152Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:53.570885Z",
          "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
            "createdAt" : "2026-02-26T23:15:54.072581Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:54.072581Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
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
          "mergeId" : "eaec0a09-03c3-4146-b601-f8c420a68e62",
          "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "successful" : true,
          "mergeCommitHash" : "f2b4b84bcf0e83a27c58694542c0e4d9ee91a674",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:54.718152Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:53.570885Z",
          "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
            "createdAt" : "2026-02-26T23:15:54.072581Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:54.072581Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SJ95NF23PGRAJQJHCN50",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SJ95NF23PGRAJQJHCN50/01KJE3SJ95SP2ECJXA019SNE43",
    "output" : "Found stuff",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "eaec0a09-03c3-4146-b601-f8c420a68e62",
        "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "successful" : true,
        "mergeCommitHash" : "f2b4b84bcf0e83a27c58694542c0e4d9ee91a674",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:15:54.718152Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:53.570885Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:54.072581Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
        "createdAt" : "2026-02-26T23:15:54.072581Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:53.570885Z",
          "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
            "createdAt" : "2026-02-26T23:15:54.072581Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "da2ff473-fc45-4833-ac62-be57da38b6d8",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP",
          "createdAt" : "2026-02-26T23:15:54.072581Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "68d5ae6f-584f-442f-ac1d-474311db6c48",
          "childWorktreeId" : "c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/c354c4dc-4870-4094-bef1-0b6fc85051ab",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "successful" : true,
          "mergeCommitHash" : "a5cccdae7ef12a5156556feb51905a24dbf5dcf8",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:55.277330Z"
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
    "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SMJYHH91RH310D1PR2RS",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature"
}
```

### Response (`PlanningOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "goal" : "Implement feature"
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8/01KJE3STK4YT6Y2HSG2Z1YNGFM",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
      "createdAt" : "2026-02-26T23:16:04.302402Z",
      "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.786861Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
      "createdAt" : "2026-02-26T23:16:04.786861Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature"
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
      "createdAt" : "2026-02-26T23:16:04.302402Z",
      "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.786861Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
      "createdAt" : "2026-02-26T23:16:04.786861Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8/01KJE3STK4YT6Y2HSG2Z1YNGFM",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.302402Z",
        "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.786861Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.786861Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "metadata" : { }
      } ]
    },
    "goal" : "Implement feature"
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
      "createdAt" : "2026-02-26T23:16:04.302402Z",
      "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.786861Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
      "createdAt" : "2026-02-26T23:16:04.786861Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
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
          "mergeId" : "027b39cf-1420-4e2b-955e-a983997762c9",
          "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "successful" : true,
          "mergeCommitHash" : "1074b14bd4587d1a2fe72644ac1a91951b95a636",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:05.463766Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.302402Z",
          "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
            "createdAt" : "2026-02-26T23:16:04.786861Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.786861Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
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
          "mergeId" : "027b39cf-1420-4e2b-955e-a983997762c9",
          "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "successful" : true,
          "mergeCommitHash" : "1074b14bd4587d1a2fe72644ac1a91951b95a636",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:05.463766Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.302402Z",
          "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
            "createdAt" : "2026-02-26T23:16:04.786861Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.786861Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3SWS79ZPZEJEXFM78D2TE",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3SWS79ZPZEJEXFM78D2TE/01KJE3SWS7RBA1MHGMHZPJPHER",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "027b39cf-1420-4e2b-955e-a983997762c9",
        "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "successful" : true,
        "mergeCommitHash" : "1074b14bd4587d1a2fe72644ac1a91951b95a636",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:16:05.463766Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.302402Z",
        "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.786861Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
        "createdAt" : "2026-02-26T23:16:04.786861Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.302402Z",
          "lastCommitHash" : "3ee2a441f5b8c8de5e6fee430ef9c45943db084b",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
            "createdAt" : "2026-02-26T23:16:04.786861Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8ea016f0-dc3b-49c1-8ecc-2a067a6e728c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3STK4ZB8FEHM7J6K5E5P8",
          "createdAt" : "2026-02-26T23:16:04.786861Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "e04cdbd0-02d5-42d2-9b02-ab5b53a811d8",
          "childWorktreeId" : "f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/f0c0496c-f4af-41ee-b3cb-f3ccd5bcc622",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "successful" : true,
          "mergeCommitHash" : "d8639e4351d56780ad2420a4ada807c3e0d2370f",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:06.043920Z"
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
    "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SRH10EYNWJ0B2SJSX6Q9/01KJE3SYT81FK58JXJE541XJTK",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature"
}
```

### Response (`TicketOrchestratorRouting`)

```json
{
  "agentRequests" : {
    "requests" : [ {
      "ticketDetails" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW/01KJE3T4SPVDTJ6GQM3J3BF94M",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
      "createdAt" : "2026-02-26T23:16:14.754369Z",
      "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:15.235671Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
      "createdAt" : "2026-02-26T23:16:15.235671Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "metadata" : { }
    } ]
  },
  "ticketDetails" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
      "createdAt" : "2026-02-26T23:16:14.754369Z",
      "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:15.235671Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
      "createdAt" : "2026-02-26T23:16:15.235671Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW/01KJE3T4SPVDTJ6GQM3J3BF94M",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:14.754369Z",
        "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:15.235671Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:15.235671Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "metadata" : { }
      } ]
    },
    "ticketDetails" : "Implement feature",
    "ticketDetailsFilePath" : "ticket-1.md"
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
      "createdAt" : "2026-02-26T23:16:14.754369Z",
      "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:15.235671Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
      "createdAt" : "2026-02-26T23:16:15.235671Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
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
          "mergeId" : "61cbc9d0-9f09-4709-84c3-f3e194843893",
          "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "successful" : true,
          "mergeCommitHash" : "fae7500946528350078fd2176f7f61e756c5d65e",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:15.923376Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:14.754369Z",
          "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
            "createdAt" : "2026-02-26T23:16:15.235671Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:15.235671Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
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
          "mergeId" : "61cbc9d0-9f09-4709-84c3-f3e194843893",
          "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "successful" : true,
          "mergeCommitHash" : "fae7500946528350078fd2176f7f61e756c5d65e",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:15.923376Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:14.754369Z",
          "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
            "createdAt" : "2026-02-26T23:16:15.235671Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:15.235671Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T709EFFGRGAPMQG91JZA",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T709EFFGRGAPMQG91JZA/01KJE3T709VP03EGZD174GXJKQ",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "61cbc9d0-9f09-4709-84c3-f3e194843893",
        "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "successful" : true,
        "mergeCommitHash" : "fae7500946528350078fd2176f7f61e756c5d65e",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:16:15.923376Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:14.754369Z",
        "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:15.235671Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
        "createdAt" : "2026-02-26T23:16:15.235671Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:14.754369Z",
          "lastCommitHash" : "32b502889fb04a680d2a3b50f8c8066fdbfb0a80",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
            "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
            "createdAt" : "2026-02-26T23:16:15.235671Z",
            "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "c923147c-7f1b-446b-8fee-8e51f01c2d6b",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T4SP4K4C4KK8PGKBCHPW",
          "createdAt" : "2026-02-26T23:16:15.235671Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "7a37db29-2730-4e19-a1e6-cfbd5e121947",
          "childWorktreeId" : "65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/65aee8b4-d3c6-4fad-9963-f9de50beecbc",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
          "successful" : true,
          "mergeCommitHash" : "1e30406bff5013a79054f8639c6aa7d46c3927fd",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:16:16.495335Z"
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
    "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3T2QDXXWNRKX0SBXFZEX8/01KJE3T930KXKFWKAV6225JY7Y",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3SDWT3VTX6G1BSGMFJSBD/01KJE3SG32AHHHRGC75Z07VCMP/01KJE3SG32WAVS8GD0DQ6AQGNM/01KJE3SHKX2NQMTHT63SJ1RPJ2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3TCZ7YBWTCH2XDPYWTCGG",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.663550Z",
        "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
          "baseBranch" : "derived-MQ4W5RQ2",
          "status" : "ACTIVE",
          "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
          "createdAt" : "2026-02-26T23:15:42.662975Z",
          "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ]
    },
    "goal" : "Implement feature",
    "phase" : "DISCOVERY"
  },
  "goal" : "Implement feature",
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
  "contextId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2/01KJE3TCZ7YBWTCH2XDPYWTCGG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.663550Z",
      "lastCommitHash" : "a9e6ac34493bad7417b9f87c4dfb389dcdd90efa",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
        "baseBranch" : "derived-MQ4W5RQ2",
        "status" : "ACTIVE",
        "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
        "createdAt" : "2026-02-26T23:15:42.662975Z",
        "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "375901c9-c53e-4682-a86d-7a8846c7e212",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580/libs/test-sub/",
      "baseBranch" : "derived-MQ4W5RQ2",
      "status" : "ACTIVE",
      "parentWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "associatedNodeId" : "ak:01KJE3S4N361Z4RGY4MQ4W5RQ2",
      "createdAt" : "2026-02-26T23:15:42.662975Z",
      "lastCommitHash" : "34497673e2d2f2d21e78cbde6c221737d842841d",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "metadata" : { }
    } ]
  },
  "goal" : "Implement feature",
  "phase" : "DISCOVERY",
  "mergeDescriptor" : {
    "mergeDirection" : "WORKTREE_TO_SOURCE",
    "successful" : true,
    "conflictFiles" : [ ],
    "submoduleMergeResults" : [ ],
    "mainWorktreeMergeResult" : {
      "mergeId" : "5fdd5cb1-b651-4a08-91ec-473b7702174e",
      "childWorktreeId" : "5c6d7b23-fb3e-4681-b3f0-150947573580",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/5c6d7b23-fb3e-4681-b3f0-150947573580",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main9247980374116092167",
      "successful" : true,
      "mergeCommitHash" : "cef18eab1aec0ebaabcab0e7415dea744689a724",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T23:16:23.350396Z"
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

