package com.viware.advent2020day6handyhaversack

class BagRule (val rule:String){
    val trimmedRule:String
    val color:String
    val containsBags:MutableList<ContainedBag> = mutableListOf()

    init {
        trimmedRule = rule.replace("contain", "")
            .replace("bags", "|")
            .replace("bag", "|")
            .replace("|.", "")
            .replace(".", "")
            .replace(",", "")



        val splittedRule = trimmedRule.split("|")
        color = splittedRule[0].trim()

        splittedRule.drop(1).forEach {
            if(it.contains("no other"))
                return@forEach
            else {
                containsBags.add(ContainedBag(it)) // These are 1st level bags
                // Bags inside it need to be added as well, recursively
                val insideBag=BagRule(it)
                //insideBag.containsBags.forEach{
                //    containsBags.add(ContainedBag(it))
                }
            }

        }

    public fun findColor(_color:String):List<BagRule.ContainedBag>{
        var containedColorBags:MutableList<ContainedBag> = mutableListOf()
        containsBags.forEach {
            if(it.color==_color) containedColorBags.add(it)
        }

        return containedColorBags //.toList()
    }

    public fun containsColor(_color:String):Boolean{
        var containedColorBags:MutableList<ContainedBag> = mutableListOf()
        containsBags.forEach {
            if(it.color==_color) return true
            // Pitaa kayda taman varin alilaukut lapi, olisiko niissa
        }

        return false
    }

    override fun toString(): String {
        return "BagRule: color: $color, containsBags: ${containsBags.toString()}"
    }

    class ContainedBag(val numberAndColor:String) {
        val amount: Int
        val color: String

        init {
            val trimmedSplittedNumberAndColor = numberAndColor.trim().split(" ")
            amount = trimmedSplittedNumberAndColor[0].toInt()
            color = trimmedSplittedNumberAndColor.drop(1).joinToString(separator = " ")
        }

        override fun toString(): String {
            return "$amount $color"
        }
    }

    class BagRecursion(val bag:ContainedBag){
        val containsRecursiveBags:MutableList<ContainedBag> = mutableListOf()

        init{

        }

    }
}