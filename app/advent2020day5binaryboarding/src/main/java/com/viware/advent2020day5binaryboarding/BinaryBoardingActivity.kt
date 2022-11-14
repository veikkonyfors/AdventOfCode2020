package com.viware.advent2020day5binaryboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.viware.advent2020day5binaryboarding.R
import java.io.File

private const val TAG = "BinaryBoardingActivity"

class BinaryBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binaryboarding)

        findViewById<MaterialButton>(R.id.checkButton).setOnClickListener {
            //Log.i(TAG,"In checkButton setOnClickListener lambda, BuildConfig.APPLICATION_ID: "+BuildConfig.APPLICATION_ID)
            val file = File(applicationContext.filesDir, "boardingpasses.txt")
            Log.i(TAG,"In checkButton setOnClickListener lambda, file.absolutePath: "+file.absolutePath)
            val listOfBoardingPasses:List<String> = file.readLines()
            var highestSeatNumber:Int=0
            var hasValidFields=0
            val seatsList:MutableList<Int> = mutableListOf()

            for(line in listOfBoardingPasses) {
                val seatNumber= BinaryBoardingPass(line).getSeat()
                //Log.i(TAG,"In checkButton setOnClickListener lambda, seatNumber: "+seatNumber)
                seatsList.add(seatNumber)
                if(highestSeatNumber<seatNumber)  highestSeatNumber=seatNumber
            }
            seatsList.sort()
            var previousSeat=7
            var currentSeat=7
            var nextSeat=0
            seatsList.forEach {
                previousSeat=currentSeat
                currentSeat=it
                if(currentSeat!=previousSeat+1) println("Missing seat: ${currentSeat-1}, previousSeat: $previousSeat")
            }
            //Log.i(TAG,"In checkButton setOnClickListener lambda, seatsList: "+seatsList.sort())

            findViewById<TextView>(R.id.textView).setText(highestSeatNumber.toString())

            //findViewById<TextView>(R.id.textViewIsValidFields).setText(hasValidFields.toString())
        }
    }
}