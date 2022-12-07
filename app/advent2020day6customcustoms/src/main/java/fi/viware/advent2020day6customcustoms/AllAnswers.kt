package fi.viware.advent2020day6customcustoms

class AllAnswers constructor (val listOfAnswerLines:List<String>){

    var listOfAnswers=mutableListOf<Answer>()

    var answer:Answer=Answer()

    init{
        listOfAnswerLines.forEach {
            if (it.length == 0) { listOfAnswers.add(answer); answer=Answer() }
            else answer.add(it)
        }
        if(!listOfAnswers.contains(answer)) listOfAnswers.add(answer)
    }

    fun countAnswers():Int{
        var count=0
        listOfAnswers.forEach{
            count+=it.answeredQuestions.length
        }
        return count
    }

    fun countUniqYesAnswers():Int{
        var count=0
        listOfAnswers.forEach{
            count+=it.uniqYesAnswers.length
        }
        return count
    }

     override fun toString():String{
        return listOfAnswers.joinToString(separator=",")
    }

}