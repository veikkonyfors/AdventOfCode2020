package com.viware.advent2020passwordcheck

import PasswordChecker
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class CheckPasswordTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isOk() {
        assertFalse(PasswordChecker( "1-1 c: abcdefgc").isOk())
        assert(PasswordChecker("1-2 c: abcdefgc").isOk())
        assert(PasswordChecker("1-3 c: abcdefgc").isOk())
        assert(!PasswordChecker("3-5 c: abcdefgc").isOk())
    }

    @Test
    fun testToString() {
        assertTrue(PasswordChecker("1-3 c: abcdefgc").toString().equals("c=c, min=1, max=3, password= abcdefgc"))
    }
}