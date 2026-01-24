When dispatching sub-agents in the dispatch agent the blackboard needs to be copied over (branched) and collected afterward to help with the locking. Otherwise if you're really confident then go ahead and just pass in that BlackboardHistory to all the sub-agents with existing locking mechanism. 

