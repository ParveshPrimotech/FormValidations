package com.example.myapplication.presentation.sealed_class.screen_event

sealed class ContactUsScreenEvents {
    data class UserNameChanged(val enteredText:String) : ContactUsScreenEvents()
    data class FirstNameChanged(val enteredText:String) : ContactUsScreenEvents()
    data class LastNameChanged(val enteredText:String) : ContactUsScreenEvents()
    data class PhoneChanged(val enteredText:String) : ContactUsScreenEvents()
    object SignupBtnPressed : ContactUsScreenEvents()
}