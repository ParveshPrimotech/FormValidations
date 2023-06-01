package com.example.myapplication.presentation.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.myapplication.core.utils.ValidationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    @StringRes labelResId:Int,
    text:String,
    validationResult:ValidationResult?,
    keyboardOptions : KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        capitalization  = KeyboardCapitalization.Sentences,
        imeAction = ImeAction.Next,
    ),
    maxLength: Int = Int.MAX_VALUE,
    singleLine : Boolean = true,
    maxLines : Int = 1,
    onValueChange : (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = stringResource(id = labelResId))
        },
        isError = true,
        value = text,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        supportingText = {
            Text(text = validationResult?.message?.asString(labelResId) ?: "")
        },
        onValueChange = {
            if(it.length <= maxLength){
                onValueChange(it)
            }
        }
    )
}