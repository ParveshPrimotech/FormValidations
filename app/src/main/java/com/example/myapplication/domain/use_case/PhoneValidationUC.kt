package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.ValidationMessage
import com.example.myapplication.core.utils.ValidationResult

class PhoneValidationUC {

    private val phoneNumberLength = 10

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
            !enteredText.all { it.isDigit() } -> {
                ValidationResult(false, ValidationMessage.InvalidEntry)
            }
            enteredText.length < phoneNumberLength ->{
                ValidationResult(false, ValidationMessage.InvalidMinLength(phoneNumberLength))
            }
            enteredText.length > phoneNumberLength -> {
                ValidationResult(false, ValidationMessage.InvalidMaxLength(phoneNumberLength))
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}