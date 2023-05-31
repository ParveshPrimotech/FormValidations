package com.example.myapplication.presentation.sealed_class.screen_event

sealed class LoginScreenEvents {
    data class EmailChanged(val enteredText:String) : LoginScreenEvents()
    data class PasswordChanged(val enteredText:String) : LoginScreenEvents()
    object LoginBtnPressed : LoginScreenEvents()
}