package com.example.myapplication.domain.use_case

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ConfirmPasswordValidationUCTest {

    private lateinit var confirmPasswordValidationUC:ConfirmPasswordValidationUC
    private val password: String = "Hello@123"

    @Before
    fun setup(){
        confirmPasswordValidationUC = ConfirmPasswordValidationUC()
    }

    /**
     * Invalid confirm password Test cases
     * **/
    @Test
    fun `if confirm password is empty`(){
        val confirmPassword = ""
        val result = confirmPasswordValidationUC(confirmPassword,password)
        assertEquals(result.status, false)
    }


    @Test
    fun `if confirm password contains space in the start`(){
        val confirmPassword = " Abc@123"
        val result = confirmPasswordValidationUC(confirmPassword,password)
        assertEquals(result.status, false)
    }

    @Test
    fun `if confirm password contains space in the end`(){
        val confirmPassword = "Abc@123 "
        val result = confirmPasswordValidationUC(confirmPassword,password)
        assertEquals(result.status, false)
    }

    @Test
    fun `if contains all spaces`(){
        val confirmPassword = "      "
        val result = confirmPasswordValidationUC(confirmPassword,password)
        assertEquals(result.status, false)
    }

    @Test
    fun `if password and confirm password not matching`(){
        val confirmPassword = "Abc@1234"
        val result = confirmPasswordValidationUC(confirmPassword,password)
        assertEquals(result.status, false)
    }

    /**
     * Valid confirm password Test cases
     **/
    @Test
    fun `if confirm password is filled and matching with password`(){
        val confirmPassword = "Hello@123"
        val result = confirmPasswordValidationUC(confirmPassword,password)
        assertEquals(result.status, true)
    }
}