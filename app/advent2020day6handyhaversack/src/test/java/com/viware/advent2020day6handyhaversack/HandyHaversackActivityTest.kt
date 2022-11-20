package com.viware.advent2020day6handyhaversack

import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import java.io.File

class HandyHaversackActivityTest {

    private lateinit var listOfBagRuleLines: List<String>
    private lateinit var shorterListOfBagRuleLines: List<String>
    private lateinit var evenShorterListOfBagRuleLines: List<String>

    @Before
    fun setUp() {
        listOfBagRuleLines = listOf<String>(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.",
        )

        shorterListOfBagRuleLines = listOf<String>(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 faded blue bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.",
        )
        evenShorterListOfBagRuleLines = listOf<String>(
            "light red bags contain 1 bright white bag.",
            "bright white bags contain 1 shiny gold bag.",
            "shiny gold bags contain 1 dark olive bag, 2 faded blue bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.",
        )
//1 bright white, 1 shiny gold, 1 dark olive, 3 faded blue, 4 dotted black, 2 faded blue
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testBagRule() {
        assert(BagRule(listOfBagRuleLines[0]).color == "light red")
        assert(BagRule(listOfBagRuleLines[0]).containsBags.size == 2)
        assert(BagRule(listOfBagRuleLines[4]).color == "shiny gold")
        assert(BagRule(listOfBagRuleLines[4]).containsBags.size == 2)
        assert(BagRule(listOfBagRuleLines[8]).color == "dotted black")
        assert(BagRule(listOfBagRuleLines[8]).containsBags.size == 0)
        assert(BagRule(listOfBagRuleLines[1]).findColor("shiny gold").size == 0)
        assert(BagRule(listOfBagRuleLines[8]).findColor("shiny gold").size == 0)
        assert(BagRule(listOfBagRuleLines[2]).findColor("shiny gold").size == 1)
        assert(BagRule(listOfBagRuleLines[3]).findColor("shiny gold").size == 1)
    }

    @Test
    fun randomTest() {

        BagRule(listOfBagRuleLines[3]).findColor("shiny gold")
        val listOfBagRules: MutableList<BagRule> = mutableListOf()

        listOfBagRuleLines.forEach {
            listOfBagRules.add(BagRule(it))
        }

        listOfBagRules.forEach {
            println(it.toString())
            val bagRule = it
            if (bagRule.containsColor("shiny gold")) println("Oli")
        }
    }

    @Test
    fun testBagExpander() {
        val bagExpander = BagExpander(listOfBagRuleLines)
        println(bagExpander.listOfBagRules.joinToString(separator = ", "))
        println("listOfBags: "+bagExpander.listOfBags.joinToString(separator = ", "))
        val bagToExpand = bagExpander.listOfBags[0]
        println("Expanded: ${bagExpander.expand(bagToExpand).joinToString(",")}")
    }

    @Test
    fun testBagForShinyGold() {
        val bagExpander = BagExpander(listOfBagRuleLines)
        val bagToExpand = bagExpander.listOfBags[0]
        assert(bagExpander.hasShinyGoldBagsInside(bagToExpand))
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[1]))
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[2]))
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[3]))
        //println("Expanded listOfBags[5], color  ${bagExpander.listOfBags[4].color}: ${bagExpander.expand(bagExpander.listOfBags[4]).joinToString(",")}")
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[4]))
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[5]))
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[6]))
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[7]))
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[8]))
    }

    @Test
    fun testMirroredRed() {
        val file = File(
            "/home/pappa/AndroidStudioProjects/AdventOfCode2020/app/advent2020day6handyhaversack",
            "bag_rule_5_mirrored_red.txt"
        )

        //bag_rule_5_mirrored_red.txt
        //bagrules.txt
        val listOfBagRuleLines: List<String> = file.readLines()

        println("listOfBagRuleLines: ${listOfBagRuleLines.joinToString(",")}")

        val bagExpander = BagExpander(listOfBagRuleLines)

        println(bagExpander.listOfBagRules.joinToString(separator = ", "))
        println(bagExpander.listOfBags.joinToString(separator = ", "))

        val bagToExpand = bagExpander.listOfBags[0]
        val expandedBag=bagExpander.expand(bagToExpand)
        println("Expanded bag, color ${bagToExpand.color}: ${bagExpander.expand(bagToExpand).joinToString(", ")}")
        assert(bagExpander.hasShinyGoldBagsInside(bagToExpand))
    }

    @Test
    fun solve1stPuzzle() {
        val file = File(
            "/home/pappa/AndroidStudioProjects/AdventOfCode2020/app/advent2020day6handyhaversack",
            "bag_rule_5_mirrored_red.txt"
        )

        //bag_rule_5_mirrored_red.txt
        //bagrules.txt
        val listOfBagRuleLines: List<String> = file.readLines()

        //println("listOfBagRuleLines: ${listOfBagRuleLines.joinToString(",")}")

        val bagExpander = BagExpander(listOfBagRuleLines)

        //println(bagExpander.listOfBagRules.joinToString(separator = ", "))
        //println(bagExpander.listOfBags.joinToString(separator = ", "))

        val bagToExpand = bagExpander.listOfBags[0]
        println("Expanded bag, color ${bagToExpand.color}: ${bagExpander.expand(bagToExpand).joinToString(", ")}")
        assert(bagExpander.hasShinyGoldBagsInside(bagToExpand))
/*
        bagExpander.listOfBags.forEach {
            println("Expanded bag, color ${it.color}: ${bagExpander.expand(it).joinToString(", ")}")
            if(bagExpander.hasShinyGoldBagsInside(it)) println("$it.color has shiny golds")
        }*/
    }
}


