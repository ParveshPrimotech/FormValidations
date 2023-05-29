package com.example.myapplication.core.utils

data class PasswordValidationResult(
    val status: Boolean,
    val message: UiText? = null,
    val passwordStrength: PasswordStrength? = null
)