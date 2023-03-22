sealed class State  {
    class DefaultState: State()
    class LoadingState : State()
    class LoadedItemState<T>(val item: T) : State()
    class LoadedFactsState<T>(val item: T) : State()
    class LoadedImgState<T>(val item: T) : State()
    class ErrorState : State()
    class LoadedState<T>(val data: List<T>) : State()
}
