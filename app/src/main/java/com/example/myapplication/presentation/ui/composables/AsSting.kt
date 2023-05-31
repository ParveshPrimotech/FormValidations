package com.example.myapplication.presentation.ui.composables

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.core.utils.ConfirmPasswordValidationMessage
import com.example.myapplication.core.utils.ValidationMessage

@Composable
fun ValidationMessage.asString(@StringRes fieldNameRedId:Int):String{

    val fieldName : String = stringResource(id = fieldNameRedId)

    return when(this){
        ValidationMessage.CanNotContainSpace -> {
            (this as ValidationMessage.CanNotContainSpace).asString(fieldName)
        }
        ValidationMessage.InvalidEntry -> {
            (this as ValidationMessage.InvalidEntry).asString(fieldName)
        }
        is ValidationMessage.InvalidMaxLength -> {
            this.asString(fieldName)
        }
        is ValidationMessage.InvalidMinLength -> {
            this.asString(fieldName)
        }
        ValidationMessage.NullOrEmptyValue -> {
            (this as ValidationMessage.NullOrEmptyValue).asString(fieldName)
        }
    }
}

@Composable
fun ConfirmPasswordValidationMessage.asString(@StringRes fieldNameResId:Int,@StringRes passwordFieldResId:Int):String{

    val fieldName : String = stringResource(id = fieldNameResId)
    val passwordFieldName : String = stringResource(id = passwordFieldResId)

    return when(this){
        ConfirmPasswordValidationMessage.CanNotContainSpace -> {
            (this as ConfirmPasswordValidationMessage.CanNotContainSpace).asString(fieldName)
        }
        ConfirmPasswordValidationMessage.NullOrEmptyValue -> {
            (this as ConfirmPasswordValidationMessage.NullOrEmptyValue).asString(fieldName)
        }
        ConfirmPasswordValidationMessage.PasswordMismatch -> {
            (this as ConfirmPasswordValidationMessage.PasswordMismatch).asString(fieldName,passwordFieldName)
        }
    }
}