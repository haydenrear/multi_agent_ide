# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `fullWorkflow_withSubmoduleChanges_propagateToSource` |
| **Started at** | 2026-02-26T22:11:08.364065Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
      "createdAt" : "2026-02-26T22:11:12.817084Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:13.317326Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
      "createdAt" : "2026-02-26T22:11:13.317326Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
      "createdAt" : "2026-02-26T22:11:12.817084Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:13.317326Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
      "createdAt" : "2026-02-26T22:11:13.317326Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
        "baseBranch" : "derived-E6WVTXSX",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:12.817084Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:13.317326Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:13.317326Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
      "createdAt" : "2026-02-26T22:11:12.817084Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:13.317326Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
      "createdAt" : "2026-02-26T22:11:13.317326Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
          "mergeId" : "4062e2e1-98ab-4f0d-93d9-7fa6bb1fc37e",
          "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6",
          "successful" : true,
          "mergeCommitHash" : "963baa4d9ed5174e502de196a1a2fbd47b1b1748",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:14.151182Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:12.817084Z",
          "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
            "createdAt" : "2026-02-26T22:11:13.317326Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:13.317326Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
          "mergeId" : "4062e2e1-98ab-4f0d-93d9-7fa6bb1fc37e",
          "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6",
          "successful" : true,
          "mergeCommitHash" : "963baa4d9ed5174e502de196a1a2fbd47b1b1748",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:14.151182Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:12.817084Z",
          "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
            "createdAt" : "2026-02-26T22:11:13.317326Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:13.317326Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE034QERMWFPKNWAF95MC6H",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE034QERMWFPKNWAF95MC6H/01KJE034QEF823GG14MDZ8HYAV",
    "output" : "Found stuff",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "4062e2e1-98ab-4f0d-93d9-7fa6bb1fc37e",
        "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6",
        "successful" : true,
        "mergeCommitHash" : "963baa4d9ed5174e502de196a1a2fbd47b1b1748",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:11:14.151182Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
        "baseBranch" : "derived-E6WVTXSX",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:12.817084Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:13.317326Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
        "createdAt" : "2026-02-26T22:11:13.317326Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:12.817084Z",
          "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
            "createdAt" : "2026-02-26T22:11:13.317326Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "7024f8d5-75a9-4e71-a841-a33c0c11535e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA",
          "createdAt" : "2026-02-26T22:11:13.317326Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "25f86db0-2ce7-4315-9658-8c784760c27c",
          "childWorktreeId" : "540017e1-14a5-4025-a1dc-0d94538200a6",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/540017e1-14a5-4025-a1dc-0d94538200a6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "successful" : true,
          "mergeCommitHash" : "a28a12ddc3b26069da433b363a3555666618cd49",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:14.786714Z"
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE036KP93Z9JJ4T3YKJ055T",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618/01KJE03CDQ5CX5PKWCWTKCM2XE",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
      "createdAt" : "2026-02-26T22:11:23.322025Z",
      "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.910312Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
      "createdAt" : "2026-02-26T22:11:23.910312Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
      "createdAt" : "2026-02-26T22:11:23.322025Z",
      "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.910312Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
      "createdAt" : "2026-02-26T22:11:23.910312Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618/01KJE03CDQ5CX5PKWCWTKCM2XE",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
        "baseBranch" : "derived-E6WVTXSX",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.322025Z",
        "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.910312Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.910312Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
      "createdAt" : "2026-02-26T22:11:23.322025Z",
      "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.910312Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
      "createdAt" : "2026-02-26T22:11:23.910312Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
          "mergeId" : "d58027a0-a405-43b1-8ca1-45509b68cfa1",
          "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749",
          "successful" : true,
          "mergeCommitHash" : "4d2cf78b19218478f23e2e8fc41d07affb1a5edf",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:24.588462Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.322025Z",
          "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
            "createdAt" : "2026-02-26T22:11:23.910312Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.910312Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
          "mergeId" : "d58027a0-a405-43b1-8ca1-45509b68cfa1",
          "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749",
          "successful" : true,
          "mergeCommitHash" : "4d2cf78b19218478f23e2e8fc41d07affb1a5edf",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:24.588462Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.322025Z",
          "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
            "createdAt" : "2026-02-26T22:11:23.910312Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.910312Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03EX8MJ42CKT53P5P9X5M",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03EX8MJ42CKT53P5P9X5M/01KJE03EX80KBM4K3JE79X71G3",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "d58027a0-a405-43b1-8ca1-45509b68cfa1",
        "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749",
        "successful" : true,
        "mergeCommitHash" : "4d2cf78b19218478f23e2e8fc41d07affb1a5edf",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:11:24.588462Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
        "baseBranch" : "derived-E6WVTXSX",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.322025Z",
        "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.910312Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
        "createdAt" : "2026-02-26T22:11:23.910312Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.322025Z",
          "lastCommitHash" : "6fb020e0fa43c6b1ec9895a04f0aa115397c1109",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
            "createdAt" : "2026-02-26T22:11:23.910312Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "c45d630b-5c96-4bf8-a487-37b6d2fbf760",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03CDP9PAYMH70WKK1J618",
          "createdAt" : "2026-02-26T22:11:23.910312Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "bcc23c8f-1dab-4533-bf59-3b45a23da2e8",
          "childWorktreeId" : "8f93aa67-3f57-469b-847e-595381a41749",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/8f93aa67-3f57-469b-847e-595381a41749",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "successful" : true,
          "mergeCommitHash" : "9acd36d14ca55a8b011ade8e22c932da14a78b26",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:25.212592Z"
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03AFQRV5XJJZ5P463NQ84/01KJE03GSEETVY8KGNW3J3J07Q",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH/01KJE03PT2NJ3D2KZSNB4FN364",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
      "createdAt" : "2026-02-26T22:11:34.134410Z",
      "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.738371Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
      "createdAt" : "2026-02-26T22:11:34.738371Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
      "createdAt" : "2026-02-26T22:11:34.134410Z",
      "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.738371Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
      "createdAt" : "2026-02-26T22:11:34.738371Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH/01KJE03PT2NJ3D2KZSNB4FN364",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
        "baseBranch" : "derived-E6WVTXSX",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.134410Z",
        "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.738371Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.738371Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
      "baseBranch" : "derived-E6WVTXSX",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
      "createdAt" : "2026-02-26T22:11:34.134410Z",
      "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.738371Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
      "createdAt" : "2026-02-26T22:11:34.738371Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
          "mergeId" : "697bc17e-6b8e-4572-ab59-b914dbd5c798",
          "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "successful" : true,
          "mergeCommitHash" : "2dd54d91325590d2424529626d0f897531d5ec14",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:35.450896Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.134410Z",
          "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
            "createdAt" : "2026-02-26T22:11:34.738371Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.738371Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
          "mergeId" : "697bc17e-6b8e-4572-ab59-b914dbd5c798",
          "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "successful" : true,
          "mergeCommitHash" : "2dd54d91325590d2424529626d0f897531d5ec14",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:35.450896Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.134410Z",
          "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
            "createdAt" : "2026-02-26T22:11:34.738371Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.738371Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03SH5KYMSCGCFYP45GP3K",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03SH5KYMSCGCFYP45GP3K/01KJE03SH57G5QGJPBBPPBYPPD",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "697bc17e-6b8e-4572-ab59-b914dbd5c798",
        "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "successful" : true,
        "mergeCommitHash" : "2dd54d91325590d2424529626d0f897531d5ec14",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:11:35.450896Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
        "baseBranch" : "derived-E6WVTXSX",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.134410Z",
        "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.738371Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
        "createdAt" : "2026-02-26T22:11:34.738371Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/",
          "baseBranch" : "derived-E6WVTXSX",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.134410Z",
          "lastCommitHash" : "6b89574464a54968ca8c06c03e630ce198074a6c",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
            "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
            "createdAt" : "2026-02-26T22:11:34.738371Z",
            "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "5265a33f-10e9-4d4d-a3c6-0bfafd54d9ac",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03PT2G8ZNEKZDJ3Y6N4TH",
          "createdAt" : "2026-02-26T22:11:34.738371Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "284ece21-d167-4d86-a2a9-4210610ace14",
          "childWorktreeId" : "74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/74fcfb1a-80b8-45e3-803f-9abbf92fd0ae",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
          "successful" : true,
          "mergeCommitHash" : "bfa5ede2536a42f6966dc4683a4d77731f98ccef",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:11:36.088297Z"
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03MR78R8HEJ3G9CJGAPK5/01KJE03VDGWYAS0G5259QRX6KN",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE0309XMYAPTHC924MGAQA8/01KJE0328J5MEYRK04CV61CQWA/01KJE0328JJRYEMH1D8H4KNZJJ/01KJE034037V0ZCGR2ZR21TGGT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03ZEDJB6KEHWJKQJHW11B",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.478576Z",
        "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
          "baseBranch" : "derived-E6WVTXSX",
          "status" : "ACTIVE",
          "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
          "createdAt" : "2026-02-26T22:11:02.477909Z",
          "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
  "contextId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX/01KJE03ZEDJB6KEHWJKQJHW11B",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.478576Z",
      "lastCommitHash" : "4332e75e891e4c38dc0fdbbabe577b10297318b9",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
        "baseBranch" : "derived-E6WVTXSX",
        "status" : "ACTIVE",
        "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
        "createdAt" : "2026-02-26T22:11:02.477909Z",
        "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "4f7fee1c-7aba-471b-8780-8e7c76d1309c",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346/libs/test-sub/",
      "baseBranch" : "derived-E6WVTXSX",
      "status" : "ACTIVE",
      "parentWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "associatedNodeId" : "ak:01KJE02QN8Q3WG4KCFE6WVTXSX",
      "createdAt" : "2026-02-26T22:11:02.477909Z",
      "lastCommitHash" : "00f87522fa69e82faf44b04c86f65c8790ffb7f2",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
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
      "mergeId" : "5ad22664-9fd8-4e7b-ba52-13aae2f68945",
      "childWorktreeId" : "23b7704d-1b82-4093-aea8-b561827a1346",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/23b7704d-1b82-4093-aea8-b561827a1346",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main15774572070775843449",
      "successful" : true,
      "mergeCommitHash" : "eb7ad472d158265d920a64361a84a834b3f17ec9",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T22:11:42.953488Z"
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

