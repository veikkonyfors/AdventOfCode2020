package fi.viware.advent2020day6customcustoms

class Answer {
    var answeredQuestions:String=""
    var uniqYesAnswers:String=""
    var numberOfPeople=0

    var tmpLine:String=""

    fun add(_line:String){
        answeredQuestions+=_line
        answeredQuestions=answeredQuestions.split("").distinct().joinToString("")

        if(numberOfPeople==0) { uniqYesAnswers+=_line; numberOfPeople+=1}
        else{
            numberOfPeople+=1
            uniqYesAnswers.forEach {
                if(!_line.contains(it)) {
                    uniqYesAnswers=uniqYesAnswers.filterNot { char->
                        it==char
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return answeredQuestions
    }
}