package com.example.myapplication.domain.use_case

import com.example.myapplication.core.utils.PasswordStrength
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class PasswordValidationUCTest {

    private lateinit var passwordValidationUC: PasswordValidationUC

    @Before
    fun setUp(){
        passwordValidationUC = PasswordValidationUC()
    }

    //InValid Passwords Test
    @Test
    fun `test if password empty`(){
        val password = ""
        val result = passwordValidationUC(password)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if password length less than 8`(){
        val password = "abcde"
        val result = passwordValidationUC(password)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if password length greater than 20`(){
        val password = "abcdcdefghijklmnopqrstuvwxyz"
        val result = passwordValidationUC(password)
        assertEquals(result.status, false)
    }

    //Valid Passwords Test
    @Test
    fun `test if password length is valid but password strength is weak`(){
        val password = "abcdcdefg"
        val result = passwordValidationUC(password)
        assertEquals(result.status && result.passwordStrength == PasswordStrength.WEAK, true)
    }


    @Test
    fun `test if password length is valid but password strength is Medium`(){
        val password = "Abcdcd1fg"
        val result = passwordValidationUC(password)
        assertEquals(result.status && result.passwordStrength == PasswordStrength.MEDIUM, true)
    }

    @Test
    fun `test if password length is valid and password strength is Strong`(){
        val password = "Abc@1234"
        val result = passwordValidationUC(password)
        assertEquals(result.status && result.passwordStrength == PasswordStrength.STRONG, true)
    }

}