package com.viware.advent2020day2passwordcheck

import PasswordChecker
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import android.widget.TextView
import java.io.File

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.checkButton).setOnClickListener {
            //Log.i(TAG,"In checkButton setOnClickListener lambda, BuildConfig.APPLICATION_ID: "+BuildConfig.APPLICATION_ID)
            val file = File(applicationContext.filesDir, "adv2020_password_2_check.txt")
            //Log.i(TAG,"In checkButton setOnClickListener lambda, file.absolutePath: "+file.absolutePath)
            val listOfContents:List<String> = file.readLines()
            var valid=0

            for(line in listOfContents) {
                val checkPassword=PasswordChecker(line)
                //Log.i(TAG,"In checkButton setOnClickListener lambda, listOfContents[$i]: "+listOfContents[i++]+" "+checkPassword.isOk())
                if(checkPassword.isOk()) valid++
            }
            //Log.i(TAG,"In checkButton setOnClickListener lambda, valid: "+valid)

            findViewById<TextView>(R.id.textView).setText(valid.toString())
        }
    }
}