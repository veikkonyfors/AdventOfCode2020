package com.viware.advent2020day5binaryboarding

import BinaryBoardingPass
import org.junit.Assert.assertFalse
import org.junit.Test

class BinaryBoardingPassTest {

    @Test
    fun testToString(){
        assert(BinaryBoardingPass("BFFFBBFRRR").toString()=="BFFFBBFRRR")
    }

    @Test
    fun isValidFBLR(){
        assert(BinaryBoardingPass("FBFBBFFRLR").isValidFBLR())
        assertFalse(BinaryBoardingPass("FBFBBFFRLYX").isValidFBLR())
        assertFalse(BinaryBoardingPass("FBFxBFFRL").isValidFBLR())

    }

    @Test
    fun testGetRow(){
        assert(BinaryBoardingPass("FBFBBFFRLR").getRow()==44)
        assert(BinaryBoardingPass("BFFFBBFRRR").getRow()==70)
        assert(BinaryBoardingPass("FFFBBBFRRR").getRow()==14)
        assert(BinaryBoardingPass("BBFFBBFRLL").getRow()==102)
        assert(BinaryBoardingPass("FFFFFFFLLL").getRow()==0)
    }

    @Test
    fun testGetColumn(){
        assert(BinaryBoardingPass("FBFBBFFRLR").getColumn()==5)
        assert(BinaryBoardingPass("BFFFBBFRRR").getColumn()==7)
        assert(BinaryBoardingPass("FFFBBBFRRR").getColumn()==7)
        assert(BinaryBoardingPass("BBFFBBFRLL").getColumn()==4)
        assert(BinaryBoardingPass("FFFFFFFLLL").getColumn()==0)
    }

    @Test
    fun testGetSeat(){
        assert(BinaryBoardingPass("FBFBBFFRLR").getSeat()==357)
        assert(BinaryBoardingPass("BFFFBBFRRR").getSeat()==567)
        assert(BinaryBoardingPass("FFFBBBFRRR").getSeat()==119)
        assert(BinaryBoardingPass("BBFFBBFRLL").getSeat()==820)
        assert(BinaryBoardingPass("FFFFFFFLLL").getSeat()==0)
    }

    //@Test
    fun testInValidFBInGetrow(){
        assert(BinaryBoardingPass("FxFBBFFRLR").getRow()==44)
    }
}