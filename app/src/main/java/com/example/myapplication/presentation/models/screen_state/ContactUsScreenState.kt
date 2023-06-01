package com.example.myapplication.presentation.models.screen_state

import com.example.myapplication.core.utils.ValidationResult

data class ContactUsScreenState(
    val firstName:String,
    val firstNameValidation: ValidationResult?,
    val firstNameRequired : Boolean,
    val userName:String,
    val userNameValidation: ValidationResult?,
    val userNameRequired : Boolean,
    val lastName:String,
    val lastNameValidation: ValidationResult?,
    val lastNameRequired: Boolean,
    val phone:String,
    val phoneValidation: ValidationResult?,
    val phoneRequired: Boolean,
    val isLoading: Boolean
){
    companion object {
        val INITIAL_STATE = ContactUsScreenState(
            firstName = "",
            firstNameValidation = null,
            firstNameRequired = true,
            userName = "",
            userNameValidation = null,
            userNameRequired = true,
            lastName = "",
            lastNameValidation = null,
            lastNameRequired = false,
            phone = "",
            phoneValidation = null,
            phoneRequired = true,
            isLoading = false
        )
    }
}