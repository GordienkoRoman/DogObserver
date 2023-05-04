package com.example.dog_observer.login

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(
): ViewModel() {
    init {
        if (!userLoggedIn) {
        }
    }
    val userLoggedIn: Boolean
        get() = false
    // TODO: Implement the ViewModel
}