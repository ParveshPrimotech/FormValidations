package com.example.myapplication.presentation.models.screen_state

import com.example.myapplication.core.utils.PasswordValidationResult
import com.example.myapplication.core.utils.ValidationResult

data class LoginScreenState(
    val email:String,
    val emailValidation: ValidationResult?,
    val password:String,
    val passwordValidation: PasswordValidationResult?,
    val isLoading: Boolean
){
    companion object {
        val INITIAL_STATE = LoginScreenState(
             email = "",
             emailValidation = null,
             password="" ,
             passwordValidation = null,
             isLoading = false
        )
    }
}