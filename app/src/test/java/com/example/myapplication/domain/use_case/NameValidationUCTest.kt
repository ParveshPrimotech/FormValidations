package com.example.myapplication.domain.use_case

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class NameValidationUCTest {

    private lateinit var nameValidationUC: NameValidationUC

    @Before
    fun setup(){
        nameValidationUC = NameValidationUC()
    }

    /**
     * InValid Name Test Cases
     **/
    @Test
    fun `if name is empty`(){
        val name = ""
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `if name contains space at the start`(){
        val name = " test"
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `if name contains space at the end`(){
        val name = "test "
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `if name contains space in the middle`(){
        val name = "test test"
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `if name contains all spaces`(){
        val name = "    "
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if name contains special chars`(){
        val name = "test#"
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if name contains number`(){
        val name = "test8"
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }


    @Test
    fun `test if length of name is less than 2 chars`(){
        val name = "a"
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    @Test
    fun `test if length of name is more than 30 chars`(){
        val name = "abcdefghijklmnopqrstuvwxyzabcdedgf"
        val result = nameValidationUC(name)
        assertEquals(result.status, false)
    }

    /**
     * Valid Name Test Cases
     **/

    @Test
    fun `test if name is valid`(){
        val name = "test"
        val result = nameValidationUC(name)
        assertEquals(result.status, true)
    }

    @Test
    fun `test if name is valid with two chars`(){
        val name = "Om"
        val result = nameValidationUC(name)
        assertEquals(result.status, true)
    }
}