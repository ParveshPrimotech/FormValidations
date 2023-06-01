package com.example.myapplication.domain.use_case

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class PhoneValidationUCTest {

    private lateinit var phoneValidationUC: PhoneValidationUC

    @Before
    fun setup(){
        phoneValidationUC = PhoneValidationUC()
    }

    /**
     * InValid Phone number Test Cases
     **/
    @Test
    fun `if phone is empty`(){
        val phone = ""
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains space at the start`(){
        val phone = " 99999"
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains space at the end`(){
        val phone = "99999 "
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains space in the middle`(){
        val phone = "999 999"
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains all spaces`(){
        val phone = "    "
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains chars`(){
        val phone = "#999999999"
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains only digits but length is less than 10`(){
        val phone = "999999999"
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }

    @Test
    fun `if phone contains only digits but length is more than 10`(){
        val phone = "99999999999"
        val result = phoneValidationUC(phone)
        assertEquals(result.status, false)
    }


    /**
     * Valid Phone number Test Cases
     **/
    @Test
    fun `if enter phone contains only digits and length is 10`(){
        val phone = "0123456789"
        val result = phoneValidationUC(phone)
        assertEquals(result.status, true)
    }

    @Test
    fun `if enter phone is empty and not required`(){
        val phone = ""
        val result = phoneValidationUC(
            enteredText = phone,
            isRequired = false
        )
        assertEquals(result.status, true)
    }
}