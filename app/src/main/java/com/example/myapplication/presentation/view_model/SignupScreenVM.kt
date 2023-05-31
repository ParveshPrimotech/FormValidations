package com.example.myapplication.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.use_case.ConfirmPasswordValidationUC
import com.example.myapplication.domain.use_case.EmailValidationUC
import com.example.myapplication.domain.use_case.NameValidationUC
import com.example.myapplication.domain.use_case.PasswordValidationUC
import com.example.myapplication.presentation.models.screen_state.SignupScreenState
import com.example.myapplication.presentation.sealed_class.screen_event.SignupScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignupScreenVM @Inject constructor(
    private val nameValidationUC:NameValidationUC,
    private val emailValidationUC: EmailValidationUC,
    private val passwordValidationUC: PasswordValidationUC,
    private val confirmPasswordValidationUC: ConfirmPasswordValidationUC
) : ViewModel(){

    private val _screenState = MutableStateFlow(SignupScreenState.INITIAL_STATE)
    val screenState = _screenState.asStateFlow()

    private val _isBtnEnable = MutableStateFlow(false)
    val isBtnEnable = _isBtnEnable.asStateFlow()

    init {
        _screenState.onEach {
            _isBtnEnable.value = it.firstNameValidation?.status == true
                    && it.lastNameValidation?.status == true
                    && it.emailValidation?.status == true
                    && it.passwordValidation?.status == true
                    && it.confirmPasswordValidation?.status == true

            _screenState.value = _screenState.value.copy(
                confirmPasswordVisibility = it.passwordValidation?.status == true
            )
        }.launchIn(viewModelScope)
    }

    fun screenEvent(event:SignupScreenEvents){
        when(event){
            is SignupScreenEvents.FirstNameChanged -> {
                _screenState.value = _screenState.value.copy(
                    firstName = event.enteredText,
                    firstNameValidation = nameValidationUC(event.enteredText)
                )
            }
            is SignupScreenEvents.LastNameChanged -> {
                _screenState.value = _screenState.value.copy(
                    lastName = event.enteredText,
                    lastNameValidation = nameValidationUC(event.enteredText)
                )
            }
            is SignupScreenEvents.EmailChanged -> {
                _screenState.value = _screenState.value.copy(
                    email = event.enteredText,
                    emailValidation = emailValidationUC(event.enteredText)
                )
            }
            is SignupScreenEvents.PasswordChanged -> {
                _screenState.value = _screenState.value.copy(
                    password = event.enteredText,
                    passwordValidation = passwordValidationUC(event.enteredText)
                )
            }
            is SignupScreenEvents.ConfirmChanged -> {
                _screenState.value = _screenState.value.copy(
                    confirmPassword = event.enteredText,
                    confirmPasswordValidation = confirmPasswordValidationUC(event.enteredText,screenState.value.password)
                )
            }
            SignupScreenEvents.SignupBtnPressed -> {

            }
        }
    }
}