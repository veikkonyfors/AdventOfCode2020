package fi.viware.advent2020day6customcustoms

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class CustomCustomsTest {

    private lateinit var listOfAnswerLines: List<String>


    @Before
    fun setUp() {
        listOfAnswerLines = listOf<String>(
            "abc",
            "",
            "a",
            "b",
            "c",
            "",
            "ab",
            "ac",
            "",
            "a",
            "a",
            "a",
            "a",
            "",
            "b",
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLoadAnswers(){
        val allAnswers:AllAnswers= AllAnswers(listOfAnswerLines)
        assert(allAnswers.toString().equals("abc,abc,abc,a,b"))
    }

    @Test
    fun testCountAnswers(){
        val allAnswers:AllAnswers= AllAnswers(listOfAnswerLines)
        assert(allAnswers.countAnswers().equals(11))
    }

    @Test
    fun testPuzzle1Answer(){

        val file = File(
            "/home/pappa/AndroidStudioProjects/AdventOfCode2020/app/advent2020day6customcustoms",
            "answerlines.txt"
        )

        //bag_rule_5_mirrored_red.txt
        //bagrules.txt
        val listOfAnswerLines: List<String> = file.readLines()
        val allAnswers:AllAnswers= AllAnswers(listOfAnswerLines)
        println(allAnswers.countAnswers())
        assert(allAnswers.countAnswers().equals(6885))
    }
}