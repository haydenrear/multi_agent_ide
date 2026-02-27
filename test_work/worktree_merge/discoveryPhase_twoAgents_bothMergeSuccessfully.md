# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `discoveryPhase_twoAgents_bothMergeSuccessfully` |
| **Started at** | 2026-02-26T23:14:58.780815Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:03.451029Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.899133Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:03.899133Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:03.451029Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.899133Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:03.899133Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.451029Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.899133Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.899133Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE8EEJHEK5HY9YZBW",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "discovery-2-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:04.391697Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.913718Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:04.913718Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "discovery-2-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:04.391697Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.913718Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:04.913718Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE8EEJHEK5HY9YZBW",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.391697Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.913718Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.913718Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:03.451029Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.899133Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:03.899133Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "230daee2-fe41-4aa4-8143-b1dc3f0b8364",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "successful" : true,
          "mergeCommitHash" : "9715437018d850d2af521257a8c296917e6052ed",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:05.654099Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.451029Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:03.899133Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.899133Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
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
          "mergeId" : "29b2d393-5cb1-4a2f-801c-57abbfa88d0f",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "successful" : true,
          "mergeCommitHash" : "f44fa703cfa3e8f618d307dbba5c72eb61deeea0",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:06.504840Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.391697Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:04.913718Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.913718Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "discovery-2-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:04.391697Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.913718Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
      "baseBranch" : "discovery-2-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
      "createdAt" : "2026-02-26T23:15:04.913718Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "230daee2-fe41-4aa4-8143-b1dc3f0b8364",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "successful" : true,
          "mergeCommitHash" : "9715437018d850d2af521257a8c296917e6052ed",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:05.654099Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.451029Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:03.899133Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.899133Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
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
          "mergeId" : "29b2d393-5cb1-4a2f-801c-57abbfa88d0f",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "successful" : true,
          "mergeCommitHash" : "f44fa703cfa3e8f618d307dbba5c72eb61deeea0",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:06.504840Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.391697Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:04.913718Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.913718Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "230daee2-fe41-4aa4-8143-b1dc3f0b8364",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "successful" : true,
          "mergeCommitHash" : "9715437018d850d2af521257a8c296917e6052ed",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:05.654099Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.451029Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:03.899133Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.899133Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
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
          "mergeId" : "29b2d393-5cb1-4a2f-801c-57abbfa88d0f",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "successful" : true,
          "mergeCommitHash" : "f44fa703cfa3e8f618d307dbba5c72eb61deeea0",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:06.504840Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.391697Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:04.913718Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.913718Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3R3J92ETJ0K3GQ9FS48B3",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3R3J92ETJ0K3GQ9FS48B3/01KJE3R3J9R5PQCHQ6G1DN4N0J",
    "output" : "Agent 1 findings",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "230daee2-fe41-4aa4-8143-b1dc3f0b8364",
        "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "successful" : true,
        "mergeCommitHash" : "9715437018d850d2af521257a8c296917e6052ed",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:15:05.654099Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.451029Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.899133Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:03.899133Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
        "metadata" : { }
      } ]
    }
  }, {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3R3J92ETJ0K3GQ9FS48B3/01KJE3R3J9TW9RTHQEYKYP4GAW",
    "output" : "Agent 2 findings",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "29b2d393-5cb1-4a2f-801c-57abbfa88d0f",
        "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "successful" : true,
        "mergeCommitHash" : "f44fa703cfa3e8f618d307dbba5c72eb61deeea0",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:15:06.504840Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.391697Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.913718Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
        "baseBranch" : "discovery-2-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
        "createdAt" : "2026-02-26T23:15:04.913718Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.451029Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:03.899133Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "131fffbe-fcc6-423d-affd-ba1e2a2d3f7a",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:03.899133Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "75bd0fc2-21f8-4d7b-9299-1336d2a59151",
          "childWorktreeId" : "e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/e23c045f-cfb7-4365-9038-ba40ea205c7e",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "successful" : true,
          "mergeCommitHash" : "c451bf7cc0520d99e37f61a303de47f2ab9d4785",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:07.348039Z"
        },
        "commitMetadata" : [ ]
      },
      "merged" : true
    }, {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.391697Z",
          "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
            "baseBranch" : "discovery-2-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
            "createdAt" : "2026-02-26T23:15:04.913718Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "97f79e5b-eee6-4313-942f-6147e8c6cdba",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef/libs/test-sub/",
          "baseBranch" : "discovery-2-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ",
          "createdAt" : "2026-02-26T23:15:04.913718Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "71a6fe26-ab0e-42cf-847b-d79851fcb993",
          "childWorktreeId" : "ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ae5a1107-5cc6-4964-b7bd-69167aabd3ef",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "successful" : true,
          "mergeCommitHash" : "fd1701b483f7ddc9649663f7c8198adcaa933a3e",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:07.446208Z"
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3R5ERV5MV8KBSBBWRJ8XP",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW/01KJE3RBWSW3K5YHWG2ANHR7F5",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
      "createdAt" : "2026-02-26T23:15:16.515934Z",
      "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:17.024476Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
      "createdAt" : "2026-02-26T23:15:17.024476Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
      "createdAt" : "2026-02-26T23:15:16.515934Z",
      "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:17.024476Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
      "createdAt" : "2026-02-26T23:15:17.024476Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW/01KJE3RBWSW3K5YHWG2ANHR7F5",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:16.515934Z",
        "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:17.024476Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:17.024476Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
      "createdAt" : "2026-02-26T23:15:16.515934Z",
      "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:17.024476Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
      "createdAt" : "2026-02-26T23:15:17.024476Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "9c7bc411-83b7-4029-993a-ff9a2ec653e1",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "successful" : true,
          "mergeCommitHash" : "da66ac9f3f883116e80fb5cf01cc0a33332f8697",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:17.687791Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:16.515934Z",
          "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
            "createdAt" : "2026-02-26T23:15:17.024476Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:17.024476Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "9c7bc411-83b7-4029-993a-ff9a2ec653e1",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "successful" : true,
          "mergeCommitHash" : "da66ac9f3f883116e80fb5cf01cc0a33332f8697",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:17.687791Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:16.515934Z",
          "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
            "createdAt" : "2026-02-26T23:15:17.024476Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:17.024476Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RE2P82AXTG04RF0HFBG0",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RE2P82AXTG04RF0HFBG0/01KJE3RE2PR6KXRJSVDY0S6PW6",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "9c7bc411-83b7-4029-993a-ff9a2ec653e1",
        "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "successful" : true,
        "mergeCommitHash" : "da66ac9f3f883116e80fb5cf01cc0a33332f8697",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:15:17.687791Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:16.515934Z",
        "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:17.024476Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
        "createdAt" : "2026-02-26T23:15:17.024476Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:16.515934Z",
          "lastCommitHash" : "456268a81ef4911158202fd4e78aaa7f08e3edd0",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
            "createdAt" : "2026-02-26T23:15:17.024476Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "b7706b2e-68f2-4a58-ac58-b26f5a6ebfbc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RBWSZ1D9TJGE41DNFVEW",
          "createdAt" : "2026-02-26T23:15:17.024476Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "3f35ac82-7823-4263-b8d2-c86129ffa569",
          "childWorktreeId" : "530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/530800f6-da6b-4ca8-88eb-cd3f972f9697",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "successful" : true,
          "mergeCommitHash" : "776d49a0c63d8095aa0cd2ca681e9627f12b55a6",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:18.219595Z"
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3R9SAF0YF4K1N5MTGPM79/01KJE3RG4C8QKTRHNG0PYT7GBP",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH/01KJE3RPBMAVFFEH6B4TZJAKJD",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
      "createdAt" : "2026-02-26T23:15:27.376494Z",
      "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.974174Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
      "createdAt" : "2026-02-26T23:15:27.974174Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
      "createdAt" : "2026-02-26T23:15:27.376494Z",
      "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.974174Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
      "createdAt" : "2026-02-26T23:15:27.974174Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH/01KJE3RPBMAVFFEH6B4TZJAKJD",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.376494Z",
        "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.974174Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.974174Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
      "baseBranch" : "derived-R9BXYFYH",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
      "createdAt" : "2026-02-26T23:15:27.376494Z",
      "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.974174Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
      "createdAt" : "2026-02-26T23:15:27.974174Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "630659fb-3d1e-4534-a810-f349e6369fe9",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "successful" : true,
          "mergeCommitHash" : "2808db36b4173ec55449e0d0a7f430b919fed2de",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:28.675563Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.376494Z",
          "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
            "createdAt" : "2026-02-26T23:15:27.974174Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.974174Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
          "mergeId" : "630659fb-3d1e-4534-a810-f349e6369fe9",
          "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "successful" : true,
          "mergeCommitHash" : "2808db36b4173ec55449e0d0a7f430b919fed2de",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:28.675563Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.376494Z",
          "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
            "createdAt" : "2026-02-26T23:15:27.974174Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.974174Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RRV9H9HK6KPRFRB8JBBS",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RRV9H9HK6KPRFRB8JBBS/01KJE3RRV9Q1ZXTJN8MAM7B4K5",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "630659fb-3d1e-4534-a810-f349e6369fe9",
        "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "successful" : true,
        "mergeCommitHash" : "2808db36b4173ec55449e0d0a7f430b919fed2de",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T23:15:28.675563Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
        "baseBranch" : "derived-R9BXYFYH",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.376494Z",
        "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.974174Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
        "createdAt" : "2026-02-26T23:15:27.974174Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/",
          "baseBranch" : "derived-R9BXYFYH",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.376494Z",
          "lastCommitHash" : "223300c228369788dcf24f862d112dab4a2115c4",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
            "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
            "createdAt" : "2026-02-26T23:15:27.974174Z",
            "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "1d735bcc-3f68-4c88-8f23-24c47e35e7cc",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RPBMVD11PHSJBNK84YNH",
          "createdAt" : "2026-02-26T23:15:27.974174Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "e4c3a6b1-18c0-44a4-8842-728cf9ec4a3b",
          "childWorktreeId" : "b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/b98185d0-aaf9-48b6-ac1d-2b1f0f5078b0",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
          "successful" : true,
          "mergeCommitHash" : "b11a6d21597c8035354a017d380e968be8ce4c91",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T23:15:29.228307Z"
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RM2G58XB6GE7EES7WZ66/01KJE3RTZ629W42H7Q92CD1H6H",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3QX7H7C8ETKAXJDF62NZV/01KJE3QZ6TDHGWWHMK9K5B7TDJ/01KJE3QZ6TE7032KW44QNKRXDA/01KJE3R1Q4Z33BGGH0HR40NY3V",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RYXBRVXXPHHWVF8NX23X",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085532Z",
        "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
          "baseBranch" : "derived-R9BXYFYH",
          "status" : "ACTIVE",
          "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
          "createdAt" : "2026-02-26T23:14:52.085082Z",
          "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
  "contextId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH/01KJE3RYXBRVXXPHHWVF8NX23X",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085532Z",
      "lastCommitHash" : "6764131dcd0656a757a8dc03b6cb034b0c469d0c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
        "baseBranch" : "derived-R9BXYFYH",
        "status" : "ACTIVE",
        "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
        "createdAt" : "2026-02-26T23:14:52.085082Z",
        "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "626872b3-9daa-4934-b48a-c18a220d6426",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99/libs/test-sub/",
      "baseBranch" : "derived-R9BXYFYH",
      "status" : "ACTIVE",
      "parentWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "associatedNodeId" : "ak:01KJE3QKFWGDHPAJV7R9BXYFYH",
      "createdAt" : "2026-02-26T23:14:52.085082Z",
      "lastCommitHash" : "96e8888f340bef72a08684279f65c51584ac6abd",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
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
      "mergeId" : "eed494e0-4b5f-4220-a09f-2823c6353c2f",
      "childWorktreeId" : "d64f11da-563b-421f-a8b0-70676cbd1e99",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/d64f11da-563b-421f-a8b0-70676cbd1e99",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main7636310972881349316",
      "successful" : true,
      "mergeCommitHash" : "b0a2822d746d900bf048f7fd2b1652b57cdccb42",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T23:15:36.463519Z"
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

