package com.viware.advent2020day7handyhaversack

class BagExpander(val listOfBagRuleLines:List<String>) {
    val listOfBagRules:MutableList<String> = mutableListOf()
    val listOfBags:MutableList<Bag> = mutableListOf()
    val listOfExpandedBags:MutableList<Bag> = mutableListOf()
    lateinit var trimmedRule:String

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

        //listOfExpandedBags.clear()

        _bagToExpand.subBagsBagList.forEach{

            val subBagToExpand=listOfBags.find{ bagInList ->
                it.color.equals(bagInList.color)
            }

            //println("if(subBagToExpand!!.subBagsBagList.size==0) $subBagToExpand")
            if(subBagToExpand!!.subBagsBagList.size==0){
                //println("Adding to listOfExpandedBags if: $it")
                listOfExpandedBags.add(it)
            }
            else {
                //val subBagToExpand=Bag(it)
                //println("expand(subBagToExpand).forEach: $subBagToExpand")
                expand(subBagToExpand).forEach{
               }
                //println("Adding to listOfExpandedBags else: $subBagToExpand")
                listOfExpandedBags.add(subBagToExpand)
            }
        }
        return listOfExpandedBags
    }

    fun hasShinyGoldBagsInside(_bag:Bag):Boolean{
        var bagsInside=expand(_bag)
        bagsInside.forEach{
            if(it.color=="shiny gold") return true
        }
        return false
    }

}