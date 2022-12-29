package fi.viware.advent2020day6customcustoms.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import fi.viware.advent2020day6customcustoms.AllAnswers
import java.io.File

private const val TAG = "CustomCustomsMainViewModel"

//class CustomCustomsMainViewModel() : ViewModel() {
class CustomCustomsMainViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    // Tiedoston sojotus menee eri lailla activityssa, fragmentissa ja modelissa:
    // Ativity:?
    //private val file = File(getActivity().getApplicationContext().filesDir,"answerlines.txt")
    // muunnettuna property accees syntaksiin, kuten AOC2022_AndroidKotlin day12hillclimbing koodissa:
    // val file = File(activity?.applicationContext.filesDir ,"input_test"
    // Fragment:?
    //val file = File(requireActivity().application.filesDir,"answerlines.txt")
    // Kaikissa toimii hard koodattuna
    //private val file = File("/data/user/0/fi.viware.advent2020day6customcustoms/files","answerlines.txt"    )
    // Modelissa nain:
    private val file:File = File(getApplication<Application>().applicationContext.filesDir,"answerlines.txt")

    val listOfAnswerLines: List<String>
    val allAnswers: AllAnswers

    init{
        listOfAnswerLines = file.readLines()
        allAnswers = AllAnswers(listOfAnswerLines)
    }

    fun countAnswers():Int{return allAnswers.countAnswers()}

    fun countUniqYesAnswers():Int{return allAnswers.countUniqYesAnswers()}

}