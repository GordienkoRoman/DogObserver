package com.example.dog_observer.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(
): ViewModel() {
    val str = "loginviewmodel"
    init {
        if (!userLoggedIn) {
        }
    }
    val userLoggedIn: Boolean
        get() = false
    // TODO: Implement the ViewModel
}