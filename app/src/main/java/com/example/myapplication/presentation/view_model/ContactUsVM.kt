package com.example.myapplication.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.use_case.NameValidationUC
import com.example.myapplication.domain.use_case.PhoneValidationUC
import com.example.myapplication.domain.use_case.UserNameValidationUC
import com.example.myapplication.presentation.models.screen_state.ContactUsScreenState
import com.example.myapplication.presentation.sealed_class.screen_event.ContactUsScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactUsVM @Inject constructor(
    private val userNameValidationUC: UserNameValidationUC,
    private val nameValidationUC: NameValidationUC,
    private val phoneValidationUC: PhoneValidationUC
): ViewModel() {

    private val _screenState = MutableStateFlow(ContactUsScreenState.INITIAL_STATE)
    val screenState = _screenState.asStateFlow()

    private val _isBtnEnable = MutableStateFlow(false)
    val isBtnEnable = _isBtnEnable.asStateFlow()

    init {
        _screenState.onEach {
            _isBtnEnable.value = it.firstNameValidation?.status == true
                    && it.lastNameValidation?.status == true
                    && it.phoneValidation?.status == true
                    && it.userNameValidation?.status == true
        }.launchIn(viewModelScope)
    }

    fun screenEvent(event:ContactUsScreenEvents){
        when(event){
            is ContactUsScreenEvents.UserNameChanged -> {
                _screenState.value = _screenState.value.copy(
                    userName = event.enteredText,
                    userNameValidation = userNameValidationUC(event.enteredText, screenState.value.userNameRequired)
                )
            }
            is ContactUsScreenEvents.FirstNameChanged -> {
                _screenState.value = _screenState.value.copy(
                    firstName = event.enteredText,
                    firstNameValidation = nameValidationUC(event.enteredText, screenState.value.firstNameRequired)
                )
            }
            is ContactUsScreenEvents.LastNameChanged -> {
                _screenState.value = _screenState.value.copy(
                    lastName = event.enteredText,
                    lastNameValidation = nameValidationUC(event.enteredText, screenState.value.lastNameRequired)
                )
            }
            is ContactUsScreenEvents.PhoneChanged -> {
                _screenState.value = _screenState.value.copy(
                    phone = event.enteredText,
                    phoneValidation = phoneValidationUC(event.enteredText, screenState.value.phoneRequired)
                )
            }
            ContactUsScreenEvents.SignupBtnPressed -> {

            }
        }
    }
}