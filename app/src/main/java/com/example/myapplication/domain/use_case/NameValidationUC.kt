package com.example.myapplication.domain.use_case

import com.example.myapplication.R
import com.example.myapplication.core.utils.UiText
import com.example.myapplication.core.utils.ValidationResult

class NameValidationUC {
    private val validNamePattern:Regex = "^[A-Za-z\\s]+$".toRegex()

    /**
     * In case of change in these values please make
     * appropriate changes in related error message strings
     * written below
     */
    private val minLength : Int = 2
    private val maxLength : Int = 30

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
            !validNamePattern.matches(enteredText) -> {
                ValidationResult(false, UiText.StringResource(R.string.form_invalid_entry))
            }
            enteredText.length < minLength ->{
                ValidationResult(false, UiText.StringResource(R.string.form_name_invalid_min_length))
            }
            enteredText.length > maxLength -> {
                ValidationResult(false, UiText.StringResource(R.string.form_name_invalid_max_length))
            }
            else -> {
                ValidationResult(true, null)
            }
        }
    }
}