package com.viware.advent2020day6handyhaversack

/**
 * BagExpander, handles given bagrules.
 * When instantiated with the list of bagrules, creates listOfBagRules and listOfBags,
 * containing trimmed rule line containing the essentials
 */

class BagExpander(val listOfBagRuleLines:List<String>) {
    val listOfBagRules:MutableList<String> = mutableListOf()
    val listOfBags:MutableList<Bag> = mutableListOf() // all bags like 'shiny lime |3 muted magenta |3 clear cyan'
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

    /**
     * Expands listOfBags subbags into  listOfExpandedBags
     * Bags in listOfBags will get their countOfBags property updated to number of subBags in this bag.
     */
    fun expand(_bagToExpand:Bag):List<Bag>{


        //Without this, numberOfBagsContainingShinyGoldOne: 211, which was the correct answer for 1st puzzle.
        // 24.11.2022: Do not count twice mod applied.
        // This had to be inserted for the second puzzle, how many bags gold shiny one holds.
        // Unfortunately it meshed up the first puzzle, as some of the bags remained unexpanded.
        // Solution was to call reset() before each individual bag expansion.
        // 1st puzzle, iterate through all the bags in listOfBags and call hasShinyGoldBagsInside,
        // Which in turn expands this bag and checks, whether it has shiny gold one inside.
        // If reset() was not called, expand() returned previous listOfBags, which meshed up
        // counting.
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

    /**
     * Expands the given bag and test whether "shiny gold" one is found.
     * Before expanding, BagExpander object needs to be reset, i.e.
     * - listOfExpandedBags cleared and
     * - for all bags in listOfBags it's countOfBags will be set to zero.
     * That's is due to the unfortunate case, that counting bags for each bag in listOfBags
     * is carried out at the same time of expanding.
     * Expanding and counting of bags should had been implemented distinctly to start with.
     */
    fun hasShinyGoldBagsInside(_bag:Bag):Boolean{
        reset()
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

    fun reset(){
        listOfExpandedBags.clear() // ?
        listOfBags.forEach{
            it.countOfBags=0  // for expand() to process this bag
        }

    }

}