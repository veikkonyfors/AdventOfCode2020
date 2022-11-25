package com.viware.advent2020day6handyhaversack

class Bag constructor (val rule:String){
    val color:String
    val subBagsStringList:MutableList<String> = mutableListOf()
    val subBagsBagList:MutableList<Bag> = mutableListOf()
    var countOfBags:Int

    init{

        countOfBags=0
        val splittedRule=rule.split("|")
        color = splittedRule[0].trim()

        val listOfSubBags=splittedRule.drop(1)
        listOfSubBags.forEach{
            subBagsStringList.add(it)
            val localColor=it.substring(2)
            val bagToAdd=Bag(localColor)
            subBagsBagList.add(bagToAdd)
        }
    }

    constructor(_bag:Bag):this(_bag.rule){

    }

    fun countOfSubBags(_subBagColor:String):Int{
        var count=0

        val splittedRule=rule.split("|")
        val subBagRules=splittedRule.drop(1)
        subBagRules.forEach{
            if(it.contains(_subBagColor)){
                val splittedSubBagRule=it.split(" ")
                count=splittedSubBagRule[0].toInt()
            }
        }
        return count
    }

    override fun toString():String{
        return "Bag: $color, ${subBagsStringList.joinToString(",")}"
    }
}