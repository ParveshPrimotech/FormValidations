package com.example.myapplication.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class StringResource(val resourceId: Int) : UiText()
    data class PlainText(val text: String) : UiText()
}

@Composable
fun UiText.asString(vararg formatArgs: String): String{
    return when(this){
        is UiText.PlainText -> {
            text
        }
        is UiText.StringResource -> {
            stringResource(id = resourceId,formatArgs = formatArgs)
        }
    }
}