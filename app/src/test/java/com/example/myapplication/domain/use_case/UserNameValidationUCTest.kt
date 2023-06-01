package com.example.myapplication.domain.use_case

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class UserNameValidationUCTest {
    
    private lateinit var userNameValidationUC: UserNameValidationUC
    
    @Before
    fun setup(){
        userNameValidationUC = UserNameValidationUC()
    }

    /**
     * InValid Name Test Cases
     **/
    @Test
    fun `if username is empty`(){
        val username = ""
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `if username contains only numbers`(){
        val username = "999999999"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `if username contains numbers in start`(){
        val username = "9999test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `if username contains space at the start`(){
        val username = " test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `if username contains space at the end`(){
        val username = "test "
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `if username contains space in the middle`(){
        val username = "test test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `if username contains all spaces`(){
        val username = "    "
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains special which is not allowed at the end`(){
        val username = "test#"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains special which is not allowed at the start`(){
        val username = "#test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains special which is not allowed in middle`(){
        val username = "test#test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains _ in the start`(){
        val username = "_test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains _ in the end`(){
        val username = "test_"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains dot in the start`(){
        val username = ".test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains dot in the end`(){
        val username = "test."
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains double __ in the middle`(){
        val username = "test__test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains double dots in the middle`(){
        val username = "test..test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains under_score and dot together in the middle`(){
        val username = "test_.test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if username contains dot and under_score in the middle`(){
        val username = "test._test"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if length of username is less than 5 chars`(){
        val username = "aaa"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if length of username is more than 20 chars`(){
        val username = "abcdefghijklmnopqrstuvwxyzabcdedgf"
        val result = userNameValidationUC(username)
        assertEquals(result.status, false)
    }


    /**
     * Valid Name Test Cases
     **/
    @Test
    fun `test if username contain only chars`(){
        val username = "testname"
        val result = userNameValidationUC(username)
        assertEquals(result.status, true)
    }

    @Test
    fun `test if username contain chars and _`(){
        val username = "test_name"
        val result = userNameValidationUC(username)
        assertEquals(result.status, true)
    }

    @Test
    fun `test if username contain chars and dot`(){
        val username = "test.name"
        val result = userNameValidationUC(username)
        assertEquals(result.status, true)
    }

    @Test
    fun `test if username contain chars and _ and number at the end`(){
        val username = "test_123"
        val result = userNameValidationUC(username)
        assertEquals(result.status, true)
    }

    @Test
    fun `test if username contain chars and dot and number at the end`(){
        val username = "test.123"
        val result = userNameValidationUC(username)
        assertEquals(result.status, true)
    }
}