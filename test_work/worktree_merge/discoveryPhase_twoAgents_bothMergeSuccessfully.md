# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `discoveryPhase_twoAgents_bothMergeSuccessfully` |
| **Started at** | 2026-02-26T22:09:37.229515Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:41.765594Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.277066Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:42.277066Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:41.765594Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.277066Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:42.277066Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:41.765594Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.277066Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.277066Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099QYFCDMHZ5H1B781G9",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:42.739445Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:43.222679Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:43.222679Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:42.739445Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:43.222679Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:43.222679Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099QYFCDMHZ5H1B781G9",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.739445Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:43.222679Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:43.222679Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:41.765594Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.277066Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:42.277066Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "69d922ee-f800-4b01-9c29-55e9d200fc8c",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "successful" : true,
          "mergeCommitHash" : "130dc3b54f1fb0b606fd48ca400af9726148a762",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:43.878519Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:41.765594Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:42.277066Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.277066Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
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
          "mergeId" : "7814b5e5-77f2-4b9e-9481-e58a2069884d",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea",
          "successful" : true,
          "mergeCommitHash" : "74fd468cad542902d481bff016429b33777e17d6",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:44.685025Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.739445Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:43.222679Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:43.222679Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:42.739445Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:43.222679Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
      "createdAt" : "2026-02-26T22:09:43.222679Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "69d922ee-f800-4b01-9c29-55e9d200fc8c",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "successful" : true,
          "mergeCommitHash" : "130dc3b54f1fb0b606fd48ca400af9726148a762",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:43.878519Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:41.765594Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:42.277066Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.277066Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
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
          "mergeId" : "7814b5e5-77f2-4b9e-9481-e58a2069884d",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea",
          "successful" : true,
          "mergeCommitHash" : "74fd468cad542902d481bff016429b33777e17d6",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:44.685025Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.739445Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:43.222679Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:43.222679Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
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

## Call 9: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "69d922ee-f800-4b01-9c29-55e9d200fc8c",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "successful" : true,
          "mergeCommitHash" : "130dc3b54f1fb0b606fd48ca400af9726148a762",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:43.878519Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:41.765594Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:42.277066Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.277066Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
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
          "mergeId" : "7814b5e5-77f2-4b9e-9481-e58a2069884d",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea",
          "successful" : true,
          "mergeCommitHash" : "74fd468cad542902d481bff016429b33777e17d6",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:44.685025Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.739445Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:43.222679Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:43.222679Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
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

## Call 10: `workflow/discovery_dispatch`

**Request type**: `DiscoveryAgentResults`  
**Response type**: `DiscoveryAgentDispatchRouting`  

### Decorated Request (`DiscoveryAgentResults`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE00DKJB72VEHQT993WH8M8",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE00DKJB72VEHQT993WH8M8/01KJE00DKJD5M2AJC5TQJFEX5Y",
    "output" : "Agent 1 findings",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "69d922ee-f800-4b01-9c29-55e9d200fc8c",
        "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "successful" : true,
        "mergeCommitHash" : "130dc3b54f1fb0b606fd48ca400af9726148a762",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:09:43.878519Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:41.765594Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.277066Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.277066Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
        "metadata" : { }
      } ]
    }
  }, {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE00DKJB72VEHQT993WH8M8/01KJE00DKJFWM0RJC2HNYB7MBF",
    "output" : "Agent 2 findings",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "7814b5e5-77f2-4b9e-9481-e58a2069884d",
        "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea",
        "successful" : true,
        "mergeCommitHash" : "74fd468cad542902d481bff016429b33777e17d6",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:09:44.685025Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:42.739445Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:43.222679Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
        "createdAt" : "2026-02-26T22:09:43.222679Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:41.765594Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:42.277066Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3a50b219-37a5-432d-ae77-38688f7d11e6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.277066Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "b590fb11-d00a-4cdc-96da-769653ac0099",
          "childWorktreeId" : "935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/935e4ccb-a36e-4556-a55b-03a33a8ea7f6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "successful" : true,
          "mergeCommitHash" : "fab2e12dc83282d8ebe255e3bc5a42e90e97d1c1",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:45.471685Z"
        },
        "commitMetadata" : [ ]
      },
      "merged" : true
    }, {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:42.739445Z",
          "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
            "createdAt" : "2026-02-26T22:09:43.222679Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "8a7e5f4e-0cc1-414a-9759-39e28f8c7b84",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H",
          "createdAt" : "2026-02-26T22:09:43.222679Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "6078ebcb-315b-4452-8661-1a9c637f8cae",
          "childWorktreeId" : "4403e020-61f4-48c9-9c02-870b962212ea",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/4403e020-61f4-48c9-9c02-870b962212ea",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "successful" : true,
          "mergeCommitHash" : "fcf2677506339d83d20bfd48373243fc960decdb",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:45.575714Z"
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE00FN5N32N6JPKGH2FY2KD",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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

## Call 12: `workflow/planning_orchestrator`

**Request type**: `PlanningOrchestratorRequest`  
**Response type**: `PlanningOrchestratorRouting`  

### Decorated Request (`PlanningOrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF/01KJE00NF0HDXSRJ25GW1VKZ1H",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
      "createdAt" : "2026-02-26T22:09:54.114283Z",
      "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.524629Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
      "createdAt" : "2026-02-26T22:09:54.524629Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
      "createdAt" : "2026-02-26T22:09:54.114283Z",
      "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.524629Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
      "createdAt" : "2026-02-26T22:09:54.524629Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF/01KJE00NF0HDXSRJ25GW1VKZ1H",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.114283Z",
        "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.524629Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.524629Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
      "createdAt" : "2026-02-26T22:09:54.114283Z",
      "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.524629Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
      "createdAt" : "2026-02-26T22:09:54.524629Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "a891b7d3-6882-4f47-8dca-fca63d9a928b",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5",
          "successful" : true,
          "mergeCommitHash" : "4fa5c46bb465d7a25abe17fe4d379dc8323dcbc8",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:55.121849Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.114283Z",
          "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
            "createdAt" : "2026-02-26T22:09:54.524629Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.524629Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
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

## Call 16: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "a891b7d3-6882-4f47-8dca-fca63d9a928b",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5",
          "successful" : true,
          "mergeCommitHash" : "4fa5c46bb465d7a25abe17fe4d379dc8323dcbc8",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:55.121849Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.114283Z",
          "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
            "createdAt" : "2026-02-26T22:09:54.524629Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.524629Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
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

## Call 17: `workflow/planning_dispatch`

**Request type**: `PlanningAgentResults`  
**Response type**: `PlanningAgentDispatchRouting`  

### Decorated Request (`PlanningAgentResults`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00QE3T9V66GW5N9WRREGR",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00QE3T9V66GW5N9WRREGR/01KJE00QE371Y0JH954DFY3WD1",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "a891b7d3-6882-4f47-8dca-fca63d9a928b",
        "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5",
        "successful" : true,
        "mergeCommitHash" : "4fa5c46bb465d7a25abe17fe4d379dc8323dcbc8",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:09:55.121849Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.114283Z",
        "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.524629Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
        "createdAt" : "2026-02-26T22:09:54.524629Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.114283Z",
          "lastCommitHash" : "5212cc9f384008b75f7493f77bbefb0896201909",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
            "createdAt" : "2026-02-26T22:09:54.524629Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "ecc9b46f-8902-41d5-b4c8-b15e12eea674",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00NF0W0JSWH4G1CWEZ5XF",
          "createdAt" : "2026-02-26T22:09:54.524629Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "f07d33e8-788c-4d72-ab36-daa813491418",
          "childWorktreeId" : "3a099066-b325-43a3-bea3-e4616b1876c5",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/3a099066-b325-43a3-bea3-e4616b1876c5",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "successful" : true,
          "mergeCommitHash" : "ec6fd97d19bdaf5c49cdbb20c2dbe0a272bb14a5",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:09:55.640170Z"
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00KHMZ2SSWJDR7APPTZ5P/01KJE00SDA9BWEWKW54J46H8SB",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N/01KJE00Z377DPS6GD78TC80WAX",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
      "createdAt" : "2026-02-26T22:10:03.967714Z",
      "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:04.409652Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
      "createdAt" : "2026-02-26T22:10:04.409652Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
      "createdAt" : "2026-02-26T22:10:03.967714Z",
      "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:04.409652Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
      "createdAt" : "2026-02-26T22:10:04.409652Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N/01KJE00Z377DPS6GD78TC80WAX",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:03.967714Z",
        "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:04.409652Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:04.409652Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
      "baseBranch" : "derived-5YMR9S0H",
      "derivedBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
      "createdAt" : "2026-02-26T22:10:03.967714Z",
      "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:04.409652Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJD",
      "status" : "ACTIVE",
      "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
      "createdAt" : "2026-02-26T22:10:04.409652Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "0904df23-ca67-4ed9-ac54-223dac746989",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "successful" : true,
          "mergeCommitHash" : "16a2493db7c31270c3e0b1b3aeac397cdf64b68f",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:05.043569Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:03.967714Z",
          "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
            "createdAt" : "2026-02-26T22:10:04.409652Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:04.409652Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
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

## Call 23: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
          "mergeId" : "0904df23-ca67-4ed9-ac54-223dac746989",
          "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "successful" : true,
          "mergeCommitHash" : "16a2493db7c31270c3e0b1b3aeac397cdf64b68f",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:05.043569Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:03.967714Z",
          "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
            "createdAt" : "2026-02-26T22:10:04.409652Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:04.409652Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
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

## Call 24: `workflow/ticket_dispatch`

**Request type**: `TicketAgentResults`  
**Response type**: `TicketAgentDispatchRouting`  

### Decorated Request (`TicketAgentResults`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE0113QWVNYGJFRJP68KR56",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE0113QWVNYGJFRJP68KR56/01KJE0113QYP9B2HFCKZECD9S5",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "0904df23-ca67-4ed9-ac54-223dac746989",
        "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "successful" : true,
        "mergeCommitHash" : "16a2493db7c31270c3e0b1b3aeac397cdf64b68f",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:10:05.043569Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
        "baseBranch" : "derived-5YMR9S0H",
        "derivedBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:03.967714Z",
        "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:04.409652Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJD",
        "status" : "ACTIVE",
        "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
        "createdAt" : "2026-02-26T22:10:04.409652Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/",
          "baseBranch" : "derived-5YMR9S0H",
          "derivedBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:03.967714Z",
          "lastCommitHash" : "2f14efa0e8d2612b2815fb553aa2c2962e2b2321",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJD",
            "status" : "ACTIVE",
            "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
            "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
            "createdAt" : "2026-02-26T22:10:04.409652Z",
            "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "d0246afe-04ef-4011-8e7d-02f37dc713fa",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJD",
          "status" : "ACTIVE",
          "parentWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE00Z37XAWBTGHTFH30069N",
          "createdAt" : "2026-02-26T22:10:04.409652Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "1dd793c4-73ae-453b-949b-cf31687ef53a",
          "childWorktreeId" : "17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/17928400-de53-43eb-b7e8-23ca4dd5ea7c",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "successful" : true,
          "mergeCommitHash" : "d198f48cff103fb5329d8444bb78424a9cdd847a",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:05.547809Z"
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
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE00X56MGC9CJE370N0ZEGW/01KJE01341K8D86HARMXCQN39X",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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

## Call 26: `workflow/worktree_merge_conflict_agent`

**Request type**: `MergeConflictRequest`  
**Response type**: `MergeConflictResult`  

### Decorated Request (`MergeConflictRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE007DNYQ496G7PSFQ965E0/01KJE0099QM7T96KJ76E5Z9Z5H/01KJE0099Q45F1RKXWHYJ5EZD1/01KJE00BTYV4Q8PJ15QTBDEMKG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE016VNM9RP6GVC9J5N82GH",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212946Z",
        "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
          "baseBranch" : "derived-5YMR9S0H",
          "status" : "ACTIVE",
          "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
          "createdAt" : "2026-02-26T22:09:31.212251Z",
          "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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

## Call 27: `workflow/orchestrator_collector`

**Request type**: `OrchestratorCollectorRequest`  
**Response type**: `OrchestratorCollectorRouting`  

### Decorated Request (`OrchestratorCollectorRequest`)

```json
{
  "contextId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H/01KJE016VNM9RP6GVC9J5N82GH",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212946Z",
      "lastCommitHash" : "93b86dc796c7b2a1dd6c8efaaf44312f7d7b7e15",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
        "baseBranch" : "derived-5YMR9S0H",
        "status" : "ACTIVE",
        "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
        "createdAt" : "2026-02-26T22:09:31.212251Z",
        "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "84646454-f70a-4280-b07c-e119fb3658b9",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911/libs/test-sub/",
      "baseBranch" : "derived-5YMR9S0H",
      "status" : "ACTIVE",
      "parentWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "associatedNodeId" : "ak:01KJDZZYHJFC84WG5F5YMR9S0H",
      "createdAt" : "2026-02-26T22:09:31.212251Z",
      "lastCommitHash" : "b1691c26822f0f17d2dd54e8caee39e784af3d96",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
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
      "mergeId" : "366496c1-4800-437b-8864-30339852c495",
      "childWorktreeId" : "405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/405f022e-9aa0-416a-b4a9-84c7b1b97911",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main17788725713551437526",
      "successful" : true,
      "mergeCommitHash" : "8d9444a122aaf4891288bbe1f03dbea722a512d8",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T22:10:12.304281Z"
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

