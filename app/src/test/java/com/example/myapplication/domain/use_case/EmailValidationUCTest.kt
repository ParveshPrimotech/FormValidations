package com.example.myapplication.domain.use_case

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class EmailValidationUCTest {

    private lateinit var emailValidationUC:EmailValidationUC

    @Before
    fun setup(){
        emailValidationUC = EmailValidationUC()
    }

    /**
     * InValid Email Test Cases
     **/
    @Test
    fun `test if email empty`(){
        val email = ""
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }


    @Test
    fun `test if email is contain spaces in the start & less chars`(){
        val email = " test@test.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if email is contain spaces in the end & less chars`(){
        val email = "test@test.com "
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if email is complete spaces`(){
        val email = "         "
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 1 invalid email pattern`(){
        val email = "test"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 2 invalid email pattern`(){
        val email = "test@"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 3 invalid email pattern`(){
        val email = "test@test"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 4 invalid email pattern`(){
        val email = "test@test."
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 5 invalid email pattern`(){
        val email = "test@test+99.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 6 invalid email pattern`(){
        val email = "test#test@test.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 7 invalid email pattern`(){
        val email = "@a.b"
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    @Test
    fun `test 8 invalid email pattern`(){
        val email = "@."
        val result = emailValidationUC(email)
        assertEquals(result.status, false)
    }

    /**
     * Valid Email Test Cases
     **/
    @Test
    fun `test valid email`(){
        val email = "test@test.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, true)
    }

    @Test
    fun `test 2 valid email`(){
        val email = "test+11@test99.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, true)
    }

    @Test
    fun `test 3 valid email`(){
        val email = "test_test@test99.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, true)
    }

    @Test
    fun `test 4 valid email`(){
        val email = "test.test@test99.com"
        val result = emailValidationUC(email)
        assertEquals(result.status, true)
    }
}