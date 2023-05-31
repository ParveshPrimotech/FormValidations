package com.example.myapplication.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
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
import com.example.myapplication.presentation.sealed_class.screen_event.SignupScreenEvents
import com.example.myapplication.presentation.ui.composables.asString
import com.example.myapplication.presentation.view_model.SignupScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DummySignup(
    modifier: Modifier,
    viewModel: SignupScreenVM = hiltViewModel()
) {
    
    val screenState by viewModel.screenState.collectAsState()
    val isBtnEnable by viewModel.isBtnEnable.collectAsState()

    Box(
        modifier = modifier
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.first_name))
                },
                isError = true,
                value = screenState.firstName,
                supportingText = {
                    screenState.firstNameValidation?.message?.let {
                        Text(text = it.asString( R.string.first_name))
                    }
                },
                onValueChange = {
                    viewModel.screenEvent(SignupScreenEvents.FirstNameChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.last_name))
                },
                isError = true,
                value = screenState.lastName,
                supportingText = {
                    screenState.lastNameValidation?.message?.let {
                        Text(text = it.asString(R.string.last_name))
                    }
                },
                onValueChange = {
                    viewModel.screenEvent(SignupScreenEvents.LastNameChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                isError = true,
                value = screenState.email,
                supportingText = {
                    screenState.emailValidation?.message?.let {
                        Text(text = it.asString( R.string.email))
                    }
                },
                onValueChange = {
                    viewModel.screenEvent(SignupScreenEvents.EmailChanged(it))
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
                    viewModel.screenEvent(SignupScreenEvents.PasswordChanged(it))
                }
            )

            AnimatedVisibility(
                visible = screenState.confirmPasswordVisibility,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 800,
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 800,
                    )
                )
            ) {
                Column {
                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = stringResource(id = R.string.confirm_password))
                        },
                        isError = true,
                        value = screenState.confirmPassword,
                        supportingText = {
                            screenState.confirmPasswordValidation?.message?.let { message ->
                                Text(
                                    text = message.asString(
                                        fieldNameResId = R.string.confirm_password,
                                        passwordFieldResId = R.string.password
                                    )
                                )
                            }
                        },
                        onValueChange = {
                            viewModel.screenEvent(SignupScreenEvents.ConfirmChanged(it))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.screenEvent(SignupScreenEvents.SignupBtnPressed)
                },
                enabled = isBtnEnable
            ) {
                Text(text = "Submit")
            }
        }
    }
}