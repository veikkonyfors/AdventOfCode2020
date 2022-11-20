package com.viware.advent2020day7handyhaversack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.viware.advent2020day7handyhaversack.R
import java.io.File

private const val TAG = "HandyHaversackActivity"

class HandyHaversackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handyhaversack)

        findViewById<MaterialButton>(R.id.checkButton).setOnClickListener {
            // Funny thing here. applicationContext.filesDir seems to be
            // /data/user/0/advent2020day6handyhaversack
            // still, even if advent2020day6handyhaversack was refactored to
            // advent2020day7handyhaversack. Had to do various kinds of cleaning, rebuilding, invalidating
            // editing . . . to make it work so far. But didn't find how could i change this filesdir
            // to point to 7. Thus, there is both directories on the emulator.
            val file = File(applicationContext.filesDir, "bagrules.txt")
            val listOfBagRuleLines:List<String> = file.readLines()

            val bagExpander = BagExpander(listOfBagRuleLines)

            var numberOfBagsContainingShinyGoldOne=0
            bagExpander.listOfBags.forEach {
                bagExpander.listOfExpandedBags.clear()
                if(bagExpander.hasShinyGoldBagsInside(it)) {
                    numberOfBagsContainingShinyGoldOne++
                }
            }

            findViewById<TextView>(R.id.textView).setText(numberOfBagsContainingShinyGoldOne.toString())


        }
    }
}