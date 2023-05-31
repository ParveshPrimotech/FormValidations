package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.ValidationMessage
import com.example.myapplication.core.utils.ValidationResult

class EmailValidationUC{
    private val validEmailPattern:Regex =   "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25})+".toRegex()

    operator fun invoke(enteredText:String?,
                        isOptional: Boolean = false,
    ): ValidationResult {
        return when{
            enteredText.isNullOrEmpty() -> {
                if(isOptional){
                    ValidationResult(true,null)
                }else{
                    ValidationResult(false, ValidationMessage.NullOrEmptyValue)
                }
            }
            enteredText.contains(" ") -> {
                ValidationResult(false, ValidationMessage.CanNotContainSpace)
            }
            !validEmailPattern.matches(enteredText) -> {
                ValidationResult(false, ValidationMessage.InvalidEntry)
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}