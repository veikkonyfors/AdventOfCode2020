package fi.viware.advent2020day6customcustoms

class Answer {
    var answeredQuestions:String=""

    fun add(_line:String){
        answeredQuestions+=_line
        answeredQuestions=answeredQuestions.split("").distinct().joinToString("")
    }

    override fun toString(): String {
        return answeredQuestions
    }
}