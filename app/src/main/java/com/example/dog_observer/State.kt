package com.example.dog_observer

sealed class State {
    class LoadingState : State()
    class DefaultState : State()
    class ErrorState : State()
    class LoadedState<T>(val data: MutableList<T>) : State()
}
