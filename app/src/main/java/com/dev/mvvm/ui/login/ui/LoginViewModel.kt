package com.dev.mvvm.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel(){

    //field email
    private  val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    //field password
    private  val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password
    //field enabled login
    private  val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable
    //field is Loading
    private  val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

   suspend fun onLoginSelected() {
       _isLoading.value = true
        delay(4000)
       _isLoading.value = false
    }



}