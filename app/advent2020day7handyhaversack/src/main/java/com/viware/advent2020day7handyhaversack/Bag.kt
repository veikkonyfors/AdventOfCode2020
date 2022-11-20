package com.viware.advent2020day7handyhaversack

class Bag constructor (val rule:String){
    val color:String
    val subBagsStringList:MutableList<String> = mutableListOf()
    val subBagsBagList:MutableList<Bag> = mutableListOf()

    init{
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

    override fun toString():String{
        return "Bag: $color, ${subBagsStringList.joinToString(",")}"
    }
}