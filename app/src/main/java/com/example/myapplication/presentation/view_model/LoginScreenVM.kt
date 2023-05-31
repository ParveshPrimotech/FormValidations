package com.example.myapplication.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.use_case.EmailValidationUC
import com.example.myapplication.domain.use_case.PasswordValidationUC
import com.example.myapplication.presentation.models.screen_state.LoginScreenState
import com.example.myapplication.presentation.sealed_class.screen_event.LoginScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginScreenVM @Inject constructor(
    private val emailValidationUC: EmailValidationUC,
    private val passwordValidationUC: PasswordValidationUC
): ViewModel() {
    private val _screenState = MutableStateFlow(LoginScreenState.INITIAL_STATE)
    val screenState = _screenState.asStateFlow()

    private val _isBtnEnable = MutableStateFlow(false)
    val isBtnEnable = _isBtnEnable.asStateFlow()

    init {
        _screenState.onEach {
            _isBtnEnable.value = it.emailValidation?.status == true && it.passwordValidation?.status == true
        }.launchIn(viewModelScope)
    }

    fun screenEvent(event:LoginScreenEvents){
        when(event){
            is LoginScreenEvents.EmailChanged -> {
                _screenState.value = _screenState.value.copy(
                    email = event.enteredText,
                    emailValidation = emailValidationUC(event.enteredText)
                )
            }
            is LoginScreenEvents.PasswordChanged -> {
                _screenState.value = _screenState.value.copy(
                    password = event.enteredText,
                    passwordValidation = passwordValidationUC(event.enteredText)
                )
            }
            LoginScreenEvents.LoginBtnPressed -> {

            }
        }
    }
}