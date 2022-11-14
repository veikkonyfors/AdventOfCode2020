package com.viware.advent2020day5binaryboarding

import BinaryBoardingPass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.io.File

private const val TAG = "ValidPassportActivity"

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

            for(line in listOfBoardingPasses) {
                val seatNumber= BinaryBoardingPass(line).getSeat()
                //Log.i(TAG,"In checkButton setOnClickListener lambda, listOfContents[$i]: "+listOfContents[i++]+" "+checkPassword.isOk())
                if(highestSeatNumber<seatNumber)  highestSeatNumber=seatNumber
            }
            //Log.i(TAG,"In checkButton setOnClickListener lambda, valid: "+valid)

            findViewById<TextView>(R.id.textView).setText(highestSeatNumber.toString())

            //findViewById<TextView>(R.id.textViewIsValidFields).setText(hasValidFields.toString())
        }
    }
}