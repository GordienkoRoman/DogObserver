package com.example.dog_observer.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import restAPI.DogApiFactsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(
): ViewModel() {
    val str = "loginviewmodel"
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