package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.ConfirmPasswordValidationMessage
import com.example.myapplication.core.utils.ConfirmPasswordValidationResult

class ConfirmPasswordValidationUC {

    /**
     * Args
     * enteredText : Text entered in confirm password
     * password : Password to match entered confirm password
    **/
    operator fun invoke(enteredText: String?, password:String?): ConfirmPasswordValidationResult {

        return when{
            enteredText.isNullOrEmpty() -> {
                ConfirmPasswordValidationResult(false, ConfirmPasswordValidationMessage.NullOrEmptyValue)
            }
            enteredText.contains(" ") -> {
                ConfirmPasswordValidationResult(false, ConfirmPasswordValidationMessage.CanNotContainSpace)
            }
            enteredText != password -> {
                ConfirmPasswordValidationResult(false, ConfirmPasswordValidationMessage.PasswordMismatch)
            }
            else -> {
                ConfirmPasswordValidationResult(true)
            }
        }
    }
}