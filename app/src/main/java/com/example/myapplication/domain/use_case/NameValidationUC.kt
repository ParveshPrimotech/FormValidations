package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.ValidationMessage
import com.example.myapplication.core.utils.ValidationResult

class NameValidationUC {
    private val validNamePattern:Regex = "^[A-Za-z\\s]+$".toRegex()

    private val minLength : Int = 2
    private val maxLength : Int = 30

    /**
     * Args
     * enteredText : Text entered in name
     * isOptional : A value to ignore empty value if name field is Optional is field
     **/
    operator fun invoke(enteredText:String?,
                        isRequired: Boolean = true,
    ): ValidationResult {
        return when{
            enteredText.isNullOrEmpty() -> {
                if(isRequired){
                    ValidationResult(false, ValidationMessage.NullOrEmptyValue)
                }else{
                    ValidationResult(true,null)
                }
            }
            enteredText.contains(" ") -> {
                ValidationResult(false, ValidationMessage.CanNotContainSpace)
            }
            !validNamePattern.matches(enteredText) -> {
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