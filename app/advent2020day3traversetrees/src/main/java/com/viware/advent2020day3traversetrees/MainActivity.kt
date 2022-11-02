package com.viware.advent2020day3traversetrees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.io.File

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var listOfContents:List<String>

        findViewById<TextView>(R.id.traverseButton).setOnClickListener {
            //Log.i(TAG,"In checkButton setOnClickListener lambda, BuildConfig.APPLICATION_ID: "+ BuildConfig.APPLICATION_ID)
            val file = File(applicationContext.filesDir, "adv2020_trees.txt")
            //Log.i(TAG,"In checkButton setOnClickListener lambda, file.absolutePath: "+file.absolutePath)
            listOfContents = file.readLines()

            val traverser=Traverser(listOfContents)
            findViewById<TextView>(R.id.textView).setText(traverser.getNumTrees().toString())
        }
    }

    class Traverser constructor(val listOfTrees:List<String>) {

        fun getSlot(row:Int, col:Int):Char{
            col%listOfTrees[row].length
            return listOfTrees[row][col%listOfTrees[row].length]
        }

        fun getNumCols():Int{
            return listOfTrees[0].length
        }

        fun getNumTrees(): Int {
            var c=0
            var t=0
            var i:Int

            for(index in 1..listOfTrees.size-1){
                c+=3
                i=(c)%listOfTrees[index].length
                if(listOfTrees[index][i]=='#') t++
            }
            return t
        }
    }
}