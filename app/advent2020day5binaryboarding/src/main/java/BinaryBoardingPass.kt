package com.viware.advent2020day5binaryboarding

import kotlin.system.exitProcess

private const val TAG = "BinaryBoardingPass"
val NUMBER_OF_ROWS=128
val NUMBER_OF_COLUMNS=8

class BinaryBoardingPass constructor(val fblr:String) {



    fun isValidFBLR():Boolean{
        if(fblr.filter({!"FBLR".contains(it)}).isNotEmpty()) return false
        if(fblr.length!=10) return false
        return true
    }

    /*
    fun String.substring(startIndex: Int, endIndex: Int): String
    Returns the substring of this string starting at the startIndex and ending right before the endIndex.
    */
    fun getRow():Int { return BinaryBoardingPassRows(fblr.substring(0,7)).getRow()}

    fun getColumn():Int { return BinaryBoardingPassColumns(fblr.substring(7,10)).getColumn()}//

    fun getSeat():Int { return getRow()*8+getColumn()}

    override fun toString():String{ return fblr}

    class BinaryBoardingPassRows(val fb:String){

        fun getRow():Int{
            var firstRow=0
            var lastRow=NUMBER_OF_ROWS-1
            for(fb:Char in fb){
                when(fb){
                    'F' -> lastRow=(lastRow+firstRow+1)/2-1
                    'B' -> firstRow=(lastRow+firstRow+1)/2
                    else -> { System.err.println("$fb contains non-FB chars. Exit 1"); exitProcess(1)}
                }
            }
            return firstRow
        }
    }

    class BinaryBoardingPassColumns(val lr:String){

        fun getColumn():Int{
            var firstColumn=0
            var lastColumn=NUMBER_OF_COLUMNS-1
            for(lr:Char in lr){
                when(lr){
                    'L' -> lastColumn=(lastColumn+firstColumn+1)/2-1
                    'R' -> firstColumn=(lastColumn+firstColumn+1)/2
                    else -> { System.err.println("$lr contains non-LR chars. Exit 1"); exitProcess(1)}
                }
            }
            return firstColumn
        }
    }

}