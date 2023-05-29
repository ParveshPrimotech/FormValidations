package com.example.myapplication.domain.use_case

import com.example.myapplication.R
import com.example.myapplication.core.utils.UiText
import com.example.myapplication.core.utils.ValidationResult

class EmailValidationUC{
    private val validEmailPattern:Regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    operator fun invoke(enteredText:String?,
                        isOptional: Boolean = false,
    ): ValidationResult {
        return when{
            enteredText.isNullOrBlank() -> {
                if(isOptional){
                    ValidationResult(true,null)
                }else{
                    ValidationResult(false, UiText.StringResource(R.string.form_null_or_empty_value))
                }
            }
            !validEmailPattern.matches(enteredText) -> {
                ValidationResult(false, UiText.StringResource(R.string.form_invalid_entry))
            }
            else -> {
                ValidationResult(true, null)
            }
        }
    }
}