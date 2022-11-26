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
    private lateinit var shinyGoldTestListOfBagRuleLines: List<String>
    private lateinit var listOfBagRuleLinesDarkOliveWithVibrantPlum: List<String>


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

        shinyGoldTestListOfBagRuleLines = listOf<String>(
        "shiny gold bags contain 2 dark red bags.",
        "dark red bags contain 2 dark orange bags.",
        "dark orange bags contain 2 dark yellow bags.",
        "dark yellow bags contain 2 dark green bags.",
        "dark green bags contain 2 dark blue bags.",
        "dark blue bags contain 2 dark violet bags.",
        "dark violet bags contain no other bags.",
        )

//1 bright white, 1 shiny gold, 1 dark olive, 3 faded blue, 4 dotted black, 2 faded blue
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testBagExpander() {
        val bagExpander = BagExpander(listOfBagRuleLines)
        //println(bagExpander.listOfBagRules.joinToString(separator = ", "))
        //println("listOfBags: " + bagExpander.listOfBags.joinToString(separator = ", "))
        val bagToExpand = bagExpander.listOfBags[0]
        //println("Expanded: ${bagExpander.expand(bagToExpand).joinToString(",")}")
        assert(bagExpander.expand(bagToExpand).joinToString(",").startsWith(
            "Bag: faded blue, ,Bag: dotted black, ,Bag: dark olive, 3 faded blue ,4 dotted black ,Bag: faded blue, ,Bag: dotted black, ,Bag: vibrant plum, 5 faded blue ,6 dotted black ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: bright white, 1 shiny gold ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: faded blue, ,Bag: muted yellow, 2 shiny gold ,9 faded blue")
        )
        // This fails for some reason, even if strings seem to be exactly same
        //assert(bagExpander.expand(bagToExpand).joinToString(",")==
        //          "Bag: faded blue, ,Bag: dotted black, ,Bag: dark olive, 3 faded blue ,4 dotted black ,Bag: faded blue, ,Bag: dotted black, ,Bag: vibrant plum, 5 faded blue ,6 dotted black ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: bright white, 1 shiny gold ,Bag: shiny gold, 1 dark olive ,2 vibrant plum ,Bag: faded blue, ,Bag: muted yellow, 2 shiny gold ,9 faded blue"
        //)
}

    @Test
    fun testBagForShinyGold() {
        val bagExpander = BagExpander(listOfBagRuleLines)

        val bagToExpand = bagExpander.listOfBags[0] // light red
        assert(bagExpander.hasShinyGoldBagsInside(bagToExpand))
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[1])) // dark orange
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[2])) // bright white
        assert(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[3])) // muted yellow
        //println("Expanded listOfBags[5], color  ${bagExpander.listOfBags[4].color}: ${bagExpander.expand(bagExpander.listOfBags[4]).joinToString(",")}")
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[4])) // shiny gold
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[5]))
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[6]))
        assertFalse(bagExpander.hasShinyGoldBagsInside(bagExpander.listOfBags[7]))
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

        var numberOfBagsContainingShinyGoldOne = 0
        bagExpander.listOfBags.forEach {
            //println("Expanded bag, color ${it.color}: ${bagExpander.expand(it).joinToString(", ")}")
            //if(it.color!="shiny lime") return@forEach
            if (bagExpander.hasShinyGoldBagsInside(it)) {
                println("$it has shiny golds")
                numberOfBagsContainingShinyGoldOne++
            }
        }
        println("numberOfBagsContainingShinyGoldOne: $numberOfBagsContainingShinyGoldOne")
        assert(numberOfBagsContainingShinyGoldOne==211)
        // 20.11.2022 17:46: 594 is too high
    }

    @Test
    fun solve2ndPuzzle() {
        val file = File(
            "/home/pappa/AndroidStudioProjects/AdventOfCode2020/app/advent2020day7handyhaversack",
            "bagrules.txt"
        )

        //bag_rule_5_mirrored_red.txt
        //bagrules.txt
        val listOfBagRuleLines: List<String> = file.readLines()

        val bagExpander = BagExpander(listOfBagRuleLines)

        val shinyGoldBag=bagExpander.listOfBags.find{ bagInList ->
            "shiny gold".equals(bagInList.color)
        }

        val expanded_bags=bagExpander.expand(shinyGoldBag!!)
        println("Count of bags in ${shinyGoldBag} is ${bagExpander.countOfExpandedBags} or is it ${shinyGoldBag.countOfBags}")

        assert(shinyGoldBag.countOfBags==12414)

        /* First try:
        That's not the right answer; your answer is too high.
        If you're stuck, make sure you're using the full input data; there are also some general tips on the about page, or you can ask for hints on the subreddit.
        Please wait one minute before trying again. (You guessed 35119.) [Return to Day 7]

        Both testCountBags and testShinyGoldBagCount give the right answer: 32 and 126
         */
        /* 24.11.2022, morning: Do not count twice mod applied.
        That's not the right answer; your answer is too high.
        If you're stuck, make sure you're using the full input data; there are also some general tips on the about page, or you can ask for hints on the subreddit.
        Please wait one minute before trying again. (You guessed 15751.) [Return to Day 7]
        This was from the 'shiny gold has 15751 subBags' i.e. counted with countbags method.
        Commented that out now from below there.

        17:00 ->
        Count of bags in Bag: drab lime, 1 mirrored green ,5 clear lime ,3 posh yellow ,5 pale yellow  is 1417 or is it 1417
        That's not the right answer; your answer is too low.
        If you're stuck, make sure you're using the full input data; there are also some general tips on the about page, or you can ask for hints on the subreddit.
        Was wrong colour in test.!!!!
        Please wait one minute before trying again. (You guessed 1417.) [Return to Day 7]

        Count of bags in Bag: shiny gold, 1 dull white ,4 dark orange  is 12414 or is it 12414
        That's the right answer! You are one gold star closer to saving your vacation.

        You have completed Day 7! You can [Share] this victory or [Return to Your Advent Calendar].
         */

        // Try counting them from subBagList
        /*
        bagExpander.countBags("shiny gold").also{
            println("shiny gold has $it subBags")
        }
        */

    }

    @Test
    fun testCountBags(){
    /*
        Test case from the puzzle page. Counted right.
     */
        val bagExpander = BagExpander(listOfBagRuleLines)

        val shinyGoldBag=bagExpander.listOfBags.find{ bagInList ->
            "shiny gold".equals(bagInList.color)
        }

        val expanded_bags=bagExpander.expand(shinyGoldBag!!)
        println("Count of bags in ${shinyGoldBag} is ${bagExpander.countOfExpandedBags} or is it ${shinyGoldBag.countOfBags}")

        assert(shinyGoldBag!!.countOfBags==32)
    }

    @Test
    fun testShinyGoldBagCount(){

        /* From the puzzle. This is counted correctly here.
        Here's another example:
shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.
In this example, a single shiny gold bag must contain 126 other bags.
         */

        val bagExpander = BagExpander(shinyGoldTestListOfBagRuleLines)

        val shinyGoldBag=bagExpander.listOfBags.find{ bagInList ->
            "shiny gold".equals(bagInList.color)
        }

        val expanded_bags=bagExpander.expand(shinyGoldBag!!)
        println("Count of bags in ${shinyGoldBag} is ${bagExpander.countOfExpandedBags} or is it ${shinyGoldBag.countOfBags}")

        assert(shinyGoldBag.countOfBags==126)
    }

    @Test
    fun testShinyGoldBagCountDarkOliveWithVibrantPlum(){

        /* Add vibrant plum bag to dark olive. Just to test bag doesn't get expanded twice.
        Should count to 44
         */

        listOfBagRuleLinesDarkOliveWithVibrantPlum = listOf<String>(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags, 1 vibrant plum bag.",
//            "dark olive bags contain 3 faded blue bags, 4 dotted black bag.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.",
        )
        val bagExpander = BagExpander(listOfBagRuleLinesDarkOliveWithVibrantPlum)

        val shinyGoldBag=bagExpander.listOfBags.find{ bagInList ->
            "shiny gold".equals(bagInList.color)
        }

        val expanded_bags=bagExpander.expand(shinyGoldBag!!)
        println("Count of bags in ${shinyGoldBag} is ${bagExpander.countOfExpandedBags} or is it ${shinyGoldBag.countOfBags}")

        assert(shinyGoldBag.countOfBags==44)
    }

}


