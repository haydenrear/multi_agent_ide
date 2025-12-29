# Goal Oriented Agent Planning (GOAP)

Currently we have so many agents as invocations - but this will be changed soon so that the return values
of the agents cause routing to occur within the sub-graphs. And then we'll use the parameters implicitly to
determine how the routing works and the dependencies. We'll still use the computation graph, but the sub-graphs
will be created by embabel - one for each @Agent - and then in each agent there are so many actions. They will then
route to each other based on the return types.

Here's an example in Kotlin - we'll be using Java.

```kotlin
// Must implement the SomeOf interface
data class FrogOrDog(
    val frog: Frog? = null,
    val dog: Dog? = null,
) : SomeOf

@Agent(description = "Illustrates use of the SomeOf interface")
class ReturnsFrogOrDog {
    @Action
    fun frogOrDog(): FrogOrDog {
        return FrogOrDog(frog = Frog("Kermit"))
    }

    // This action will only run if frog field was set
    @AchievesGoal(description = "Create a prince from a frog")
    @Action
    fun toPerson(frog: Frog): PersonWithReverseTool {
        return PersonWithReverseTool(frog.name)
    }

    @AchievesGoal(description = "Walk a dog")
    @Action
    fun toDog(dog: Dog): WalkDog {
        return WalkWog(dog);
    }
}
```

So we'll be doing something similar. Managing the routing across agents as well using the asAction transformation or
subAgent. The key here to make sure we're doing everything with the UI is that we'll add event listeners and emit
events the same.
