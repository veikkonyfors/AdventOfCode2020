package com.viware.advent2020day2passwordcheck2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.io.File

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.checkButton).setOnClickListener {
            //Log.i(TAG,"In checkButton setOnClickListener lambda, BuildConfig.APPLICATION_ID: "+BuildConfig.APPLICATION_ID)
            val file = File(applicationContext.filesDir, "adv2020_password_2_check.txt")
            Log.i(TAG,"In checkButton setOnClickListener lambda, file.absolutePath: "+file.absolutePath)
            val listOfContents:List<String> = file.readLines()
            var valid=0

            for(line in listOfContents) {
                val checkPassword= PasswordChecker(line)
                //Log.i(TAG,"In checkButton setOnClickListener lambda, listOfContents[$i]: "+listOfContents[i++]+" "+checkPassword.isOk())
                if(checkPassword.isOk()) valid++
            }
            //Log.i(TAG,"In checkButton setOnClickListener lambda, valid: "+valid)

            findViewById<TextView>(R.id.textView).setText(valid.toString())
        }

    }

    class PasswordChecker(var password:String) {
        var  c:Char=' '
        var  min:Int=-1
        var  max:Int=-1

        init{
            val listOfCheckAndPassword=password.split(':')
            val listOfMinMaxChar=listOfCheckAndPassword[0].split(' ')
            c=listOfMinMaxChar[1].toCharArray()[0]
            val listOfMinMax=listOfMinMaxChar[0].split('-')
            min=listOfMinMax[0].toInt()
            max=listOfMinMax[1].toInt()
            password=listOfCheckAndPassword[1]
        }

        fun isOk():Boolean{
            if(password[min]==c && password[max]==c) return false
            if(password[min]==c) return true
            if(password[max]==c) return true
            return false

        }

        override fun toString():String {
            return "c=$c, min=$min, max=$max, password=$password"
        }
    }

}