package com.example.myapplication.core.utils

data class PasswordValidationResult(
    val status: Boolean,
    val message: ValidationMessage? = null,
    val passwordStrength: PasswordStrength? = null
)

