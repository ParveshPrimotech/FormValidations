package com.example.myapplication.presentation.models.screen_state

import com.example.myapplication.core.utils.UiText

data class LoginScreenState(
    val email:String,
    val emailValidation: UiText?,
    val password:String,
    val passwordValidation: UiText?,
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