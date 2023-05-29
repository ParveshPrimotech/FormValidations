package com.example.myapplication.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.use_case.EmailValidationUC
import com.example.myapplication.domain.use_case.PasswordValidationUC
import com.example.myapplication.presentation.models.screen_state.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenVM @Inject constructor(
    private val emailValidationUC: EmailValidationUC,
    private val passwordValidationUC: PasswordValidationUC
): ViewModel() {
    private val _screenState = MutableStateFlow(LoginScreenState.INITIAL_STATE)
    val screenState = _screenState.asStateFlow()

    fun onEmailChanged(enteredText:String){
        _screenState.value = _screenState.value.copy(
            email = enteredText,
            emailValidation = emailValidationUC(enteredText).message
        )
    }

    fun onPasswordChanged(enteredText: String){
        _screenState.value = _screenState.value.copy(
            password = enteredText,
            passwordValidation = passwordValidationUC(enteredText).message
        )
    }

    fun onBtnLoginPressed(){

    }
}