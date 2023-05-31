package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.PasswordStrength
import com.example.myapplication.core.utils.PasswordValidationResult
import com.example.myapplication.core.utils.ValidationMessage

class PasswordValidationUC {

    private val minLength: Int = 8
    private val maxLength: Int = 20

    operator fun invoke(enteredText: String?): PasswordValidationResult {

        return when{
            enteredText.isNullOrEmpty() -> {
                PasswordValidationResult(false,ValidationMessage.NullOrEmptyValue)
            }
            enteredText.contains(" ") -> {
                PasswordValidationResult(false, ValidationMessage.CanNotContainSpace)
            }
            enteredText.length < minLength ->{
                PasswordValidationResult(false, ValidationMessage.InvalidMinLength(minLength))
            }
            enteredText.length > maxLength ->{
                PasswordValidationResult(false, ValidationMessage.InvalidMaxLength(maxLength))
            }
            else -> {
                var result = PasswordValidationResult(true)

                val hasUppercase = enteredText.matches(".*[A-Z].*".toRegex())
                val hasLowercase = enteredText.matches(".*[a-z].*".toRegex())
                val hasDigit = enteredText.matches(".*\\d.*".toRegex())
                val hasSpecialChar = enteredText.matches(".*[@$!%*?&].*".toRegex())


                when {
                    enteredText.all { it.isUpperCase() } || enteredText.all { it.isLowerCase() } || enteredText.all { it.isDigit() } -> {
                        result = result.copy(
                            passwordStrength = PasswordStrength.WEAK
                        )
                    }
                    !hasUppercase || !hasLowercase || !hasDigit || !hasSpecialChar -> {
                        result = result.copy(
                            passwordStrength = PasswordStrength.MEDIUM
                        )
                    }
                    else -> {
                        result = result.copy(
                            passwordStrength = PasswordStrength.STRONG
                        )
                    }
                }

                result
            }
        }
    }
}