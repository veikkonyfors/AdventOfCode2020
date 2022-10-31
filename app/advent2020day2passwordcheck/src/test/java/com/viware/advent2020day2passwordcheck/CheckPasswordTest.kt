package com.viware.advent2020passwordcheck

import CheckPassword
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
        assertFalse(CheckPassword( "1-1 c: abcdefgc").isOk())
        assert(CheckPassword("1-2 c: abcdefgc").isOk())
        assert(CheckPassword("1-3 c: abcdefgc").isOk())
        assert(!CheckPassword("3-5 c: abcdefgc").isOk())
    }

    @Test
    fun testToString() {
        assertTrue(CheckPassword("1-3 c: abcdefgc").toString().equals("c=c, min=1, max=3, password= abcdefgc"))
    }
}