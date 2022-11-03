package com.viware.advent2020day3traversetrees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.io.File
import java.math.BigInteger

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
            // First puzzle
            findViewById<TextView>(R.id.textViewNumTree).setText(traverser.getNumTrees(3,1).toString())
            // Second puzzle
            val c11=traverser.getNumTrees(1,1).toBigInteger()
            val c31=traverser.getNumTrees(3,1).toBigInteger()
            val c51=traverser.getNumTrees(5,1).toBigInteger()
            val c71=traverser.getNumTrees(7,1).toBigInteger()
            val c12=traverser.getNumTrees(1,2).toBigInteger()
            val product:BigInteger=c11*c31*c51*c71*c12
            /*Log.i(TAG,
                "In checkButton setOnClickListener lambda, c11, c31, c51, c71, c12, product: "+
                        " $c11, $c31, $c51, $c71, $c12, $product")*/

            findViewById<TextView>(R.id.textViewProduct).setText(product.toString())

            //Log.i(TAG,"In checkButton setOnClickListener lambda, product: "+product)
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

        fun getNumTrees(right:Int, down:Int): Int {

            val toRight=right
            val toDown=down

            var c=0
            var t=0
            var i:Int

            for(index in toDown..listOfTrees.size-1 step toDown){
                c+=right
                i=(c)%listOfTrees[index].length
                if(listOfTrees[index][i]=='#') t++
                //println("$index, $i, $t, "+listOfTrees[index][i]+", \t"+listOfTrees[index])
                //println("\t\t\t\t012345678901234567890123456789")
            }
            return t
        }
    }
}