package com.example.dog_observer.login

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.dog_observer.MainActivity
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(): ViewModel() {
    init {
        if (!userLoggedIn) {
        } else {
        }
    }

    private val _navigation = MutableLiveData<NavDirections>()
    val navigation: MutableLiveData<NavDirections> = _navigation

    val userLoggedIn: Boolean
        get() = false

    private fun navigateTo(desination: NavDirections) {
        _navigation.value = desination
    }
    public fun login(login: String,password:String)
    {
      //
    }
    // TODO: Implement the ViewModel
}