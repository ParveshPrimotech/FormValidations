package com.example.myapplication.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

data class ConfirmPasswordValidationResult(
    val status: Boolean,
    val message: ConfirmPasswordValidationMessage? = null,
)

sealed class ConfirmPasswordValidationMessage{
    object NullOrEmptyValue:ConfirmPasswordValidationMessage(){
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_entry_null_or_empty_value, fieldName)
        }
    }
    object CanNotContainSpace : ConfirmPasswordValidationMessage() {
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_entry_can_not_contain_space, fieldName)
        }
    }
    object PasswordMismatch:ConfirmPasswordValidationMessage(){
        @Composable
        fun asString(fieldName: String, passwordFieldName:String): String {
            return stringResource(id = R.string.form_confirm_password_not_matching, fieldName, passwordFieldName)
        }
    }
}