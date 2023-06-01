package com.example.myapplication.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.sealed_class.screen_event.ContactUsScreenEvents
import com.example.myapplication.presentation.ui.composables.CustomTextField
import com.example.myapplication.presentation.view_model.ContactUsVM

@Composable
fun DummyContactUs(
    modifier: Modifier,
    viewModel: ContactUsVM = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    val isBtnEnable by viewModel.isBtnEnable.collectAsState()

    Box(
        modifier = modifier
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                labelResId = R.string.user_name,
                text = screenState.userName,
                validationResult = screenState.userNameValidation,
                onValueChange = {
                    viewModel.screenEvent(ContactUsScreenEvents.UserNameChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                labelResId = R.string.first_name,
                text = screenState.firstName,
                validationResult = screenState.firstNameValidation,
                onValueChange = {
                    viewModel.screenEvent(ContactUsScreenEvents.FirstNameChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                labelResId = R.string.last_name,
                text = screenState.lastName,
                validationResult = screenState.lastNameValidation,
                onValueChange = {
                    viewModel.screenEvent(ContactUsScreenEvents.LastNameChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                labelResId = R.string.phone,
                text = screenState.phone,
                maxLength = 10,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                validationResult = screenState.phoneValidation,
                onValueChange = {
                    viewModel.screenEvent(ContactUsScreenEvents.PhoneChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.screenEvent(ContactUsScreenEvents.SignupBtnPressed)
                },
                enabled = isBtnEnable
            ) {
                Text(text = "Submit")
            }
        }
    }
}