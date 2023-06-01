package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.ValidationMessage
import com.example.myapplication.core.utils.ValidationResult

class UserNameValidationUC {

    private val validUserNameRegex : Regex = "^(?![0-9_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$".toRegex()
    private val minLength = 5
    private val maxLength = 20

    operator fun invoke(
        enteredText:String?,
        isRequired:Boolean = true
    ) : ValidationResult {
        return when {
            enteredText.isNullOrEmpty() -> {
                if (isRequired) {
                    ValidationResult(false, ValidationMessage.NullOrEmptyValue)
                } else {
                    ValidationResult(true)
                }
            }
            enteredText.contains(" ") -> {
                ValidationResult(false, ValidationMessage.CanNotContainSpace)
            }
            !validUserNameRegex.matches(enteredText) -> {
                ValidationResult(false, ValidationMessage.InvalidEntry)
            }
            enteredText.length < minLength ->{
                ValidationResult(false, ValidationMessage.InvalidMinLength(minLength))
            }
            enteredText.length > maxLength -> {
                ValidationResult(false, ValidationMessage.InvalidMaxLength(maxLength))
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}