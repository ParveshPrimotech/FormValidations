package com.example.myapplication.domain.use_case

import com.example.myapplication.R
import com.example.myapplication.core.utils.PasswordStrength
import com.example.myapplication.core.utils.PasswordValidationResult
import com.example.myapplication.core.utils.UiText

class PasswordValidationUC {

    /**
     * In case of change in these values please make
     * appropriate changes in related error message strings
     * written below
     */
    private val minLength: Int = 8
    private val maxLength: Int = 20

    operator fun invoke(enteredText: String): PasswordValidationResult {

        return when{
            enteredText.length < minLength ->{
                PasswordValidationResult(false, UiText.StringResource(R.string.form_password_invalid_min_length))
            }
            enteredText.length > maxLength ->{
                PasswordValidationResult(false, UiText.StringResource(R.string.form_password_invalid_max_length))
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