package com.viware.advent2020day7handyhaversack

import org.jetbrains.annotations.TestOnly

class BagExpander(val listOfBagRuleLines:List<String>) {
    val listOfBagRules:MutableList<String> = mutableListOf()
    val listOfBags:MutableList<Bag> = mutableListOf()
    val listOfExpandedBags:MutableList<Bag> = mutableListOf()
    lateinit var trimmedRule:String
    var countOfExpandedBags=0

    init{
        listOfBagRuleLines.forEach(){
            trimmedRule = it
                .replace("bags contain ", "|")
                .replace("bags, ", "|")
                .replace("bag, ", "|")
                .replace("bags.", "")
                .replace("bag.", "")
                .replace("no other ", "")
                // Bags with no other bags inside end up having a pipe in the end,
                // as if they had a sub-bag anyway.
                // Remove it, to bypass problems in Bag's constructor dealing with sub-bags.
                .replace("\\|$".toRegex(),"", )

            listOfBagRules.add(trimmedRule)
            listOfBags.add(Bag(trimmedRule))
        }

    }

    fun expand(_bagToExpand:Bag):List<Bag>{


        //Without this, numberOfBagsContainingShinyGoldOne: 211, which was the correct answer for 1st puzzle.
        // 24.11.2022: Do not count twice mod applied.
        ///*
        if(_bagToExpand.countOfBags!=0){
            //println("$_bagToExpand already expanded, countOfBags: ${_bagToExpand.countOfBags}")
            return listOfBags
        }
        //*/

        _bagToExpand.subBagsBagList.forEach{

            val subBagToExpand=listOfBags.find{ bagInList ->
                it.color.equals(bagInList.color)
            }

            //println("if(subBagToExpand!!.subBagsBagList.size==0) $subBagToExpand")
            if(subBagToExpand!!.subBagsBagList.size==0){
                //listOfExpandedBags.add(subBagToExpand)
                subBagToExpand.countOfBags=1
                //_bagToExpand.countOfBags+=subBagToExpand.countOfBags*_bagToExpand.countOfSubBags(it.color)
//                println("Added to listOfExpandedBags if: $subBagToExpand, countOfBags: ${subBagToExpand.countOfBags}")
                //println("(if) _bagToExpand.countOfBags: ${_bagToExpand.rule}: countOfBags=${_bagToExpand.countOfBags}")
            }
            else {
                expand(subBagToExpand)
                //listOfExpandedBags.add(subBagToExpand)
                //_bagToExpand.countOfBags+=subBagToExpand.countOfBags*_bagToExpand.countOfSubBags(it.color) // SubBags in this SubBag
                _bagToExpand.countOfBags+=_bagToExpand.countOfSubBags(it.color) // SubBag itself/themselves
                //println("Added to listOfExpandedBags else: $subBagToExpand, countOfBags: ${subBagToExpand.countOfBags}")
                //println("(else)_bagToExpand.countOfBags: ${_bagToExpand.rule}: countOfBags=${_bagToExpand.countOfBags}")
            }
            listOfExpandedBags.add(subBagToExpand)
            _bagToExpand.countOfBags+=subBagToExpand.countOfBags*_bagToExpand.countOfSubBags(it.color) // SubBags in this SubBag
            //println("_bagToExpand.countOfBags: ${_bagToExpand.rule}: countOfBags=${_bagToExpand.countOfBags}")
        }
        countOfExpandedBags=_bagToExpand.countOfBags
        return listOfExpandedBags
    }

    fun hasShinyGoldBagsInside(_bag:Bag):Boolean{
        var bagsInside=expand(_bag)
        bagsInside.forEach{
            if(it.color=="shiny gold") return true
        }
        return false
    }

    fun countBags(_color:String):Int{

        val shinyGoldBag=listOfBags.find{ bagInList ->
            _color.equals(bagInList.color)
        }

        val expandedBags=expand(shinyGoldBag!!)
        var countOfBags=0
        expandedBags.forEach{
            countOfBags+=it.countOfBags
            println("$it, holding ${it.countOfBags} bags, adds up to $countOfBags so far in ${shinyGoldBag.color}")
        }

        return countOfBags
    }

}