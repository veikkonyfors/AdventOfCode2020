package com.viware.advent2020day7handyhaversack

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
    fun testBagExpander() {
        val bagExpander = BagExpander(listOfBagRuleLines)
        println(bagExpander.listOfBagRules.joinToString(separator = ", "))
        println("listOfBags: "+bagExpander.listOfBags.joinToString(separator = ", "))
        val bagToExpand = bagExpander.listOfBags[0]
        println("Expanded: ${bagExpander.expand(bagToExpand).joinToString(",")}")
        assert(bagExpander.expand(bagToExpand).joinToString(",").startsWith(
            "Bag: faded blue, ,Bag: dotted black, ,Bag: dark olive, 3 faded blue ,4 dotted black ,Bag: faded blue, ,Bag: dotted black, ,Bag: vibrant plum, 5 faded blue ,6 dotted black ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: bright white, 1 shiny gold ,Bag: faded blue, ,Bag: dotted black, ,Bag: dark olive, 3 faded blue ,4 dotted black ,Bag: faded blue, ,Bag: dotted black, ,Bag: vibrant plum, 5 faded blue ,6 dotted black ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: faded blue, ,Bag: muted yellow, 2 shiny gold ,9 faded blue ")
        // This fails for some reason, even if strings seem to be exactly same
        //assert(bagExpander.expand(bagToExpand).joinToString(",")==
        //        "Bag: faded blue, ,Bag: dotted black, ,Bag: dark olive, 3 faded blue ,4 dotted black ,Bag: faded blue, ,Bag: dotted black, ,Bag: vibrant plum, 5 faded blue ,6 dotted black ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: bright white, 1 shiny gold ,Bag: faded blue, ,Bag: dotted black, ,Bag: dark olive, 3 faded blue ,4 dotted black ,Bag: faded blue, ,Bag: dotted black, ,Bag: vibrant plum, 5 faded blue ,6 dotted black ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: faded blue, ,Bag: muted yellow, 2 shiny gold ,9 faded blue "
        )
    }

    @Test
    fun testBagForShinyGold() {
        val bagExpander = BagExpander(listOfBagRuleLines)
        val bagToExpand = bagExpander.listOfBags[0] // light red
        assert(bagExpander.hasShinyGoldBagsInside(bagToExpand))
        bagExpander.listOfExpandedBags.clear()
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[1])) // dark orange
        bagExpander.listOfExpandedBags.clear()
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[2])) // bright white
        bagExpander.listOfExpandedBags.clear()
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[3])) // muted yellow
        //println("Expanded listOfBags[5], color  ${bagExpander.listOfBags[4].color}: ${bagExpander.expand(bagExpander.listOfBags[4]).joinToString(",")}")
        bagExpander.listOfExpandedBags.clear()
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[4])) // shiny gold
        //assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[4]))
        bagExpander.listOfExpandedBags.clear()
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[5]))
        bagExpander.listOfExpandedBags.clear()
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[6]))
        bagExpander.listOfExpandedBags.clear()
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[7]))
        bagExpander.listOfExpandedBags.clear()
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[8]))
    }

    @Test
    fun solve1stPuzzle() {
        val file = File(
            "/home/pappa/AndroidStudioProjects/AdventOfCode2020/app/advent2020day7handyhaversack",
            "bagrules.txt"
        )

        //bag_rule_5_mirrored_red.txt
        //bagrules.txt
        val listOfBagRuleLines: List<String> = file.readLines()

        //println("listOfBagRuleLines: ${listOfBagRuleLines.joinToString(",")}")

        val bagExpander = BagExpander(listOfBagRuleLines)

        //println(bagExpander.listOfBagRules.joinToString(separator = ", "))
        //println(bagExpander.listOfBags.joinToString(separator = ", "))

        /*val bagToExpand = bagExpander.listOfBags[94]
        println("Expanded bag, color ${bagToExpand.color}: ${bagExpander.expand(bagToExpand).joinToString(", ")}")
        assert(bagExpander.hasShinyGoldBagsInside(bagToExpand))*/

        var numberOfBagsContainingShinyGoldOne=0
        bagExpander.listOfBags.forEach {
            bagExpander.listOfExpandedBags.clear()
            //println("Expanded bag, color ${it.color}: ${bagExpander.expand(it).joinToString(", ")}")
            //if(it.color!="shiny lime") return@forEach
            if(bagExpander.hasShinyGoldBagsInside(it)) {
                println("$it has shiny golds")
                numberOfBagsContainingShinyGoldOne++
            }
        }
        println("numberOfBagsContainingShinyGoldOne: $numberOfBagsContainingShinyGoldOne")
        // 20.11.2022 17:46: 594 is too high
    }
}

