package com.example.myapplication.core.di

import com.example.myapplication.domain.use_case.EmailValidationUC
import com.example.myapplication.domain.use_case.NameValidationUC
import com.example.myapplication.domain.use_case.PasswordValidationUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideEmailValidationUC() : EmailValidationUC = EmailValidationUC()

    @Provides
    fun provideNameValidationUC() : NameValidationUC = NameValidationUC()

    @Provides
    fun providePasswordValidationUC() : PasswordValidationUC = PasswordValidationUC()
}