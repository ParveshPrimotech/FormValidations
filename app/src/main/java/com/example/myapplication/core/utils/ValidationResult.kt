package com.example.myapplication.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

data class ValidationResult(
    val status: Boolean,
    val message: ValidationMessage? = null
)

sealed class ValidationMessage {
    object InvalidEntry : ValidationMessage() {
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_invalid_entry, fieldName)
        }
    }
    object NullOrEmptyValue:ValidationMessage(){
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_entry_null_or_empty_value, fieldName)
        }
    }
    object CanNotContainSpace : ValidationMessage() {
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_entry_can_not_contain_space, fieldName)
        }
    }
    data class InvalidMinLength(val validMinLength: Int):ValidationMessage(){
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_field_invalid_min_length, fieldName, validMinLength)
        }
    }
    data class InvalidMaxLength(val validMaxLength: Int):ValidationMessage(){
        @Composable
        fun asString(fieldName: String): String {
            return stringResource(id = R.string.form_field_invalid_max_length, fieldName, validMaxLength)
        }
    }
}
