package com.example.myapplication.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.sealed_class.screen_event.LoginScreenEvents
import com.example.myapplication.presentation.ui.composables.asString
import com.example.myapplication.presentation.view_model.LoginScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DummyLogin(
    modifier : Modifier,
    viewModel : LoginScreenVM = hiltViewModel()
) {

    val screenState by viewModel.screenState.collectAsState()
    val isBtnEnable by viewModel.isBtnEnable.collectAsState()

    Box(
        modifier = modifier
            .verticalScroll(
                state = rememberScrollState()
            )
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                isError = true,
                value = screenState.email,
                supportingText = {
                    screenState.emailValidation?.message?.let {
                        Text(text = it.asString(fieldNameRedId = R.string.email))
                    }
                },
                onValueChange = {
                    viewModel.screenEvent(LoginScreenEvents.EmailChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                isError = true,
                value = screenState.password,
                supportingText = {
                    screenState.passwordValidation?.let {
                        it.message?.let { message ->
                            Text(text = message.asString(fieldNameRedId = R.string.password))
                        }
                    }
                },
                onValueChange = {
                    viewModel.screenEvent(LoginScreenEvents.PasswordChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.screenEvent(LoginScreenEvents.LoginBtnPressed)
                },
                enabled = isBtnEnable
            ) {
                Text(text = "Submit")
            }
        }
    }
}
