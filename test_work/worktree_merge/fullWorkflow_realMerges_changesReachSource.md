# QueuedLlmRunner Call Log

| Field | Value |
|-------|-------|
| **Test class** | `WorkflowAgentWorktreeMergeIntTest` |
| **Test method** | `fullWorkflow_realMerges_changesReachSource` |
| **Started at** | 2026-02-26T22:10:23.365915Z |

---

## Call 1: `workflow/orchestrator`

**Request type**: `OrchestratorRequest`  
**Response type**: `OrchestratorRouting`  

### Decorated Request (`OrchestratorRequest`)

```json
{
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
      "createdAt" : "2026-02-26T22:10:27.893254Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:28.365178Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
      "createdAt" : "2026-02-26T22:10:28.365178Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
      "createdAt" : "2026-02-26T22:10:27.893254Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:28.365178Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
      "createdAt" : "2026-02-26T22:10:28.365178Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
        "baseBranch" : "derived-S3HZRN31",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:27.893254Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:28.365178Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:28.365178Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
      "createdAt" : "2026-02-26T22:10:27.893254Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:28.365178Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
      "baseBranch" : "discovery-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
      "createdAt" : "2026-02-26T22:10:28.365178Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
          "mergeId" : "d65a9950-11fd-452a-aaf3-a569a53a5f5d",
          "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "successful" : true,
          "mergeCommitHash" : "c38b95d4d640df01c67e832f11dce80bafb8cf4c",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:29.006596Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:27.893254Z",
          "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
            "createdAt" : "2026-02-26T22:10:28.365178Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:28.365178Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
          "mergeId" : "d65a9950-11fd-452a-aaf3-a569a53a5f5d",
          "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "successful" : true,
          "mergeCommitHash" : "c38b95d4d640df01c67e832f11dce80bafb8cf4c",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:29.006596Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:27.893254Z",
          "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
            "createdAt" : "2026-02-26T22:10:28.365178Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:28.365178Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01RG5QXEGMJ8GMCC7JTPR",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "result" : [ {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01RG5QXEGMJ8GMCC7JTPR/01KJE01RG5PJ308GSDBPVT3895",
    "output" : "Found stuff",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "d65a9950-11fd-452a-aaf3-a569a53a5f5d",
        "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "successful" : true,
        "mergeCommitHash" : "c38b95d4d640df01c67e832f11dce80bafb8cf4c",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:10:29.006596Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
        "baseBranch" : "derived-S3HZRN31",
        "derivedBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:27.893254Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:28.365178Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
        "baseBranch" : "discovery-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
        "createdAt" : "2026-02-26T22:10:28.365178Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:27.893254Z",
          "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
            "baseBranch" : "discovery-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
            "createdAt" : "2026-02-26T22:10:28.365178Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "108cd085-7972-4692-86cd-de724dd37654",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f/libs/test-sub/",
          "baseBranch" : "discovery-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB",
          "createdAt" : "2026-02-26T22:10:28.365178Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "5f7fb494-8212-40bb-a5d4-29a25b965f0d",
          "childWorktreeId" : "fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/fcb517bf-8757-4a85-88cc-d41c7600d47f",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "successful" : true,
          "mergeCommitHash" : "36500e994d07c03bd1aff71fb28ca0f97cf82efe",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:29.484907Z"
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01TDVV67WGH5G6HV3ZYR2",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR/01KJE0204VEQVQ0G5B1S9SRPQG",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
      "createdAt" : "2026-02-26T22:10:37.849808Z",
      "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:38.359093Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
      "createdAt" : "2026-02-26T22:10:38.359093Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
      "createdAt" : "2026-02-26T22:10:37.849808Z",
      "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:38.359093Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
      "createdAt" : "2026-02-26T22:10:38.359093Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR/01KJE0204VEQVQ0G5B1S9SRPQG",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
        "baseBranch" : "derived-S3HZRN31",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:37.849808Z",
        "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:38.359093Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:38.359093Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
      "createdAt" : "2026-02-26T22:10:37.849808Z",
      "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:38.359093Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
      "baseBranch" : "planning-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
      "createdAt" : "2026-02-26T22:10:38.359093Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
          "mergeId" : "ad9af41f-0fb8-44a2-af7a-ebe8500160bd",
          "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "successful" : true,
          "mergeCommitHash" : "dd1284b5a831a4f3f4837af219e6e3fbeefed757",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:39.052884Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:37.849808Z",
          "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
            "createdAt" : "2026-02-26T22:10:38.359093Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:38.359093Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
          "mergeId" : "ad9af41f-0fb8-44a2-af7a-ebe8500160bd",
          "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "successful" : true,
          "mergeCommitHash" : "dd1284b5a831a4f3f4837af219e6e3fbeefed757",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:39.052884Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:37.849808Z",
          "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
            "createdAt" : "2026-02-26T22:10:38.359093Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:38.359093Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE022B50N6SWHHE86MYKHDF",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "planningAgentResults" : [ {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE022B50N6SWHHE86MYKHDF/01KJE022B54ZTGTGYMX3GPQCBH",
    "output" : "Plan output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "ad9af41f-0fb8-44a2-af7a-ebe8500160bd",
        "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "successful" : true,
        "mergeCommitHash" : "dd1284b5a831a4f3f4837af219e6e3fbeefed757",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:10:39.052884Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
        "baseBranch" : "derived-S3HZRN31",
        "derivedBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:37.849808Z",
        "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:38.359093Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
        "baseBranch" : "planning-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
        "createdAt" : "2026-02-26T22:10:38.359093Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:37.849808Z",
          "lastCommitHash" : "cab4ecc45150b1868901be75e5026bb6417258af",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
            "baseBranch" : "planning-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
            "createdAt" : "2026-02-26T22:10:38.359093Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "16785e95-6c74-4556-84b9-b0d2286633ad",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759/libs/test-sub/",
          "baseBranch" : "planning-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0204V4S3G4KS8YDDGGERR",
          "createdAt" : "2026-02-26T22:10:38.359093Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "8f64d4d9-8a50-4b92-8448-88f4a7f4056c",
          "childWorktreeId" : "6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/6f04d17a-2be5-4428-9ede-4cfd14fca759",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "successful" : true,
          "mergeCommitHash" : "a37cfd93b76ac291d6724a95adc20a50791dc71e",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:39.578653Z"
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01Y7V3RQC0HJ029DDD6DB/01KJE0249VF505CHH2QEA4EB1F",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X/01KJE02A18MDB96JC4YJHJF9D7",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
      "createdAt" : "2026-02-26T22:10:47.977687Z",
      "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:48.452195Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
      "createdAt" : "2026-02-26T22:10:48.452195Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
      "createdAt" : "2026-02-26T22:10:47.977687Z",
      "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:48.452195Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
      "createdAt" : "2026-02-26T22:10:48.452195Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X/01KJE02A18MDB96JC4YJHJF9D7",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
        "baseBranch" : "derived-S3HZRN31",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:47.977687Z",
        "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:48.452195Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:48.452195Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
      "baseBranch" : "derived-S3HZRN31",
      "derivedBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
      "createdAt" : "2026-02-26T22:10:47.977687Z",
      "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:48.452195Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
      "baseBranch" : "ticket-1-ak-01KJE",
      "status" : "ACTIVE",
      "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
      "createdAt" : "2026-02-26T22:10:48.452195Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
          "mergeId" : "fb7f9737-b90f-4005-9c36-06c0a942868c",
          "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "successful" : true,
          "mergeCommitHash" : "562440f425b09c8b62d6f41063f69c6e77897324",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:49.150949Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:47.977687Z",
          "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
            "createdAt" : "2026-02-26T22:10:48.452195Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:48.452195Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
          "mergeId" : "fb7f9737-b90f-4005-9c36-06c0a942868c",
          "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "successful" : true,
          "mergeCommitHash" : "562440f425b09c8b62d6f41063f69c6e77897324",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:49.150949Z"
        },
        "commitMetadata" : [ ]
      },
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:47.977687Z",
          "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
            "createdAt" : "2026-02-26T22:10:48.452195Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:48.452195Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02C7RRWHWJH1E5XM2276K",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "ticketAgentResults" : [ {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02C7RRWHWJH1E5XM2276K/01KJE02C7RAEW2CG4NEK8NG1PR",
    "output" : "Ticket output",
    "mergeDescriptor" : {
      "mergeDirection" : "TRUNK_TO_CHILD",
      "successful" : true,
      "conflictFiles" : [ ],
      "submoduleMergeResults" : [ ],
      "mainWorktreeMergeResult" : {
        "mergeId" : "fb7f9737-b90f-4005-9c36-06c0a942868c",
        "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "successful" : true,
        "mergeCommitHash" : "562440f425b09c8b62d6f41063f69c6e77897324",
        "conflicts" : [ ],
        "submoduleUpdates" : [ ],
        "mergeMessage" : "Merge successful",
        "mergedAt" : "2026-02-26T22:10:49.150949Z"
      },
      "commitMetadata" : [ ]
    },
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
        "baseBranch" : "derived-S3HZRN31",
        "derivedBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:47.977687Z",
        "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:48.452195Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
        "baseBranch" : "ticket-1-ak-01KJE",
        "status" : "ACTIVE",
        "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
        "createdAt" : "2026-02-26T22:10:48.452195Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
        "metadata" : { }
      } ]
    }
  } ],
  "mergeAggregation" : {
    "merged" : [ {
      "agentResultId" : "unknown",
      "worktreeContext" : {
        "mainWorktree" : {
          "worktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/",
          "baseBranch" : "derived-S3HZRN31",
          "derivedBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:47.977687Z",
          "lastCommitHash" : "5a639b9ee2ab2b88c4717f21806ae2a31a8a7e17",
          "hasSubmodules" : true,
          "submoduleWorktrees" : [ {
            "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
            "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
            "baseBranch" : "ticket-1-ak-01KJE",
            "status" : "ACTIVE",
            "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
            "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
            "createdAt" : "2026-02-26T22:10:48.452195Z",
            "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
            "submoduleName" : "libs/test-sub",
            "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
            "metadata" : { }
          } ],
          "metadata" : { }
        },
        "submoduleWorktrees" : [ {
          "worktreeId" : "3de1b4f7-0290-4597-b0cf-da9c3bd3cb0e",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0/libs/test-sub/",
          "baseBranch" : "ticket-1-ak-01KJE",
          "status" : "ACTIVE",
          "parentWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02A18NS7CAGJ9Y52TJ20X",
          "createdAt" : "2026-02-26T22:10:48.452195Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "metadata" : { }
        } ]
      },
      "mergeDescriptor" : {
        "mergeDirection" : "CHILD_TO_TRUNK",
        "successful" : true,
        "conflictFiles" : [ ],
        "submoduleMergeResults" : [ ],
        "mainWorktreeMergeResult" : {
          "mergeId" : "0656d628-a8cf-40de-96b2-54535f9ca9b1",
          "childWorktreeId" : "1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/1eb43d93-8e2f-4227-9f96-555402e3f3d0",
          "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "successful" : true,
          "mergeCommitHash" : "00cfbc7746fe1559a59b1121bc3b49854e15f028",
          "conflicts" : [ ],
          "submoduleUpdates" : [ ],
          "mergeMessage" : "Merge successful",
          "mergedAt" : "2026-02-26T22:10:49.708360Z"
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE0282Q4W24GK6WHYB1DJ0A/01KJE02E96VX77YG936FY9BDF4",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE01MFSH9498KH9N88VDT0N/01KJE01PERHMB6TJY7XG87AJPB/01KJE01PERH9YX4GT4VMNRKGXJ/01KJE01QXN8JXATHR11B002PCT",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "metadata" : { }
    } ]
  },
  "routedFromRequest" : {
    "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE02J2V9Z2ZPJAKVS175TSJ",
    "worktreeContext" : {
      "mainWorktree" : {
        "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
        "baseBranch" : "main",
        "derivedBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.628248Z",
        "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
        "hasSubmodules" : true,
        "submoduleWorktrees" : [ {
          "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
          "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
          "baseBranch" : "derived-S3HZRN31",
          "status" : "ACTIVE",
          "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
          "createdAt" : "2026-02-26T22:10:17.627641Z",
          "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
          "submoduleName" : "libs/test-sub",
          "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
          "metadata" : { }
        } ],
        "metadata" : { }
      },
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
  "contextId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31/01KJE02J2V9Z2ZPJAKVS175TSJ",
  "worktreeContext" : {
    "mainWorktree" : {
      "worktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/",
      "baseBranch" : "main",
      "derivedBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.628248Z",
      "lastCommitHash" : "c221dea15d8924b5a5a8a0f2c49661cfbb663991",
      "hasSubmodules" : true,
      "submoduleWorktrees" : [ {
        "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
        "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
        "baseBranch" : "derived-S3HZRN31",
        "status" : "ACTIVE",
        "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
        "createdAt" : "2026-02-26T22:10:17.627641Z",
        "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
        "submoduleName" : "libs/test-sub",
        "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
        "metadata" : { }
      } ],
      "metadata" : { }
    },
    "submoduleWorktrees" : [ {
      "worktreeId" : "83a17a4d-7a8c-4aec-b868-6ad9c097a107",
      "worktreePath" : "file:///var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6/libs/test-sub/",
      "baseBranch" : "derived-S3HZRN31",
      "status" : "ACTIVE",
      "parentWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "associatedNodeId" : "ak:01KJE01C0E3QJ3GJXKS3HZRN31",
      "createdAt" : "2026-02-26T22:10:17.627641Z",
      "lastCommitHash" : "842cfa933559f0461a67d53a54958c4e123734c9",
      "submoduleName" : "libs/test-sub",
      "mainWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
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
      "mergeId" : "b1faf7de-3916-4665-8e09-14c667d0293b",
      "childWorktreeId" : "ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "parentWorktreeId" : "source",
      "childWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/multi-agent-ide-merge-test-worktrees/ede3f23a-4e32-4efc-b87d-912d38f08db6",
      "parentWorktreePath" : "/var/folders/b7/rsz3g6wn4hg8zl2bwmdx61q00000gn/T/test-main5985082194145170485",
      "successful" : true,
      "mergeCommitHash" : "9d51e4bc38586244055da40c7d9a4ab2723d065b",
      "conflicts" : [ ],
      "submoduleUpdates" : [ ],
      "mergeMessage" : "Final merge to source successful",
      "mergedAt" : "2026-02-26T22:10:56.584572Z"
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

