package com.example.myapplication.presentation.sealed_class.screen_event

sealed class SignupScreenEvents {
    data class FirstNameChanged(val enteredText:String) : SignupScreenEvents()
    data class LastNameChanged(val enteredText:String) : SignupScreenEvents()
    data class EmailChanged(val enteredText:String) : SignupScreenEvents()
    data class PasswordChanged(val enteredText:String) : SignupScreenEvents()
    data class ConfirmChanged(val enteredText:String) : SignupScreenEvents()
    object SignupBtnPressed : SignupScreenEvents()
}