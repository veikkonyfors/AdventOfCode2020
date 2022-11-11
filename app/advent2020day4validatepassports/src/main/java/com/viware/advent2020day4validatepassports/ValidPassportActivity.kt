package com.viware.advent2020day4validatepassports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.io.File

private const val TAG = "ValidPassportActivity"

class ValidPassportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valid_passport)

        findViewById<MaterialButton>(R.id.checkButton).setOnClickListener {
            //Log.i(TAG,"In checkButton setOnClickListener lambda, BuildConfig.APPLICATION_ID: "+BuildConfig.APPLICATION_ID)
            val file = File(applicationContext.filesDir, "credentials.txt")
            Log.i(TAG,"In checkButton setOnClickListener lambda, file.absolutePath: "+file.absolutePath)
            val listOfCredentialLines:List<String> = file.readLines()
            var isOkWithFields=0
            var hasValidFields=0
            val listOfCredentials:List<String> = CredentialBuilder(listOfCredentialLines).buildCredentials()

            for(line in listOfCredentials) {
                val passPort= Passport(line)
                //Log.i(TAG,"In checkButton setOnClickListener lambda, listOfContents[$i]: "+listOfContents[i++]+" "+checkPassword.isOk())
                if(passPort.isOk()) isOkWithFields++
                if(passPort.isValid()) hasValidFields++
            }
            //Log.i(TAG,"In checkButton setOnClickListener lambda, valid: "+valid)

            findViewById<TextView>(R.id.textViewIsOkFields).setText(isOkWithFields.toString())

            findViewById<TextView>(R.id.textViewIsValidFields).setText(hasValidFields.toString())
        }
    }
}

class CredentialBuilder (val listOfLines:List<String>){

    var listOfCredentials:MutableList<String> = mutableListOf()
    var aCredential=""

    fun buildCredentials():List<String>{
        listOfLines.forEach{
            when(it){
                "" -> { listOfCredentials.add(aCredential); aCredential=""}
                else -> aCredential=aCredential.plus(it).plus(" ")
            }
        }
        if(aCredential!="") listOfCredentials.add(aCredential)
        return listOfCredentials
    }
}

