package com.example.myapplication.presentation.models.screen_state

import com.example.myapplication.core.utils.ConfirmPasswordValidationResult
import com.example.myapplication.core.utils.PasswordValidationResult
import com.example.myapplication.core.utils.ValidationResult

data class SignupScreenState(
    val firstName:String,
    val firstNameValidation: ValidationResult?,
    val lastName:String,
    val lastNameValidation: ValidationResult?,
    val email:String,
    val emailValidation: ValidationResult?,
    val password:String,
    val passwordValidation: PasswordValidationResult?,
    val confirmPassword:String,
    val confirmPasswordValidation: ConfirmPasswordValidationResult?,
    val confirmPasswordVisibility: Boolean,
    val isLoading: Boolean
){
    companion object {
        val INITIAL_STATE = SignupScreenState(
            firstName = "",
            firstNameValidation = null,
            lastName = "",
            lastNameValidation = null,
            email = "",
            emailValidation = null,
            password="" ,
            passwordValidation = null,
            confirmPassword = "",
            confirmPasswordValidation = null,
            confirmPasswordVisibility = false,
            isLoading = false
        )
    }
}