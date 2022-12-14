package com.viware.advent2020day3traversetrees

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.manipulation.Ordering
import java.io.File

class TraverserTest {

    lateinit var treeLocations:List<String>
    lateinit var listOfContents:List<String>

    @Before
    fun setUp() {
        treeLocations=listOf(
            "....##..#........##...#.#..#.##",
            ".#.#..#....##....#...#..##.....",
            "##.#..##..#...#..........##.#..",
            ".#.##.####..#......###.........",
            "#.#.#...........#.....#...#....",
            "#.......#....#.#.##..###..##..#",
            ".#...#...##....#.........#.....",
            "..........##.#.#.....#....#.#..",
            ".......##..##...#.#.#...#......",
            ".#.#.#...#...##...#.##.##..#...",
            "........##.#.#.###.........##..",
            "#.#..#.#.#.....#...#...#......#",
            ".#.#.#...##......#...#.........",
            ".#..##.##.#...#...##....#.#....",
            ".##...#..#..#......##.###....##",
            ".....#...#.###.....#.#.........",
            "#.##..#....#.#.#.#.............",
            "........#...#......#...#..#....",
            "##..##...##.##...#...#.###...##",
            "#.#....##.#...###......#..#.#..",
            "..#.....#.##......#..........#.",
            "#.......#..#......#.....#....#.",
            ".....###...........#....#.##...",
            "#.#........##.......#.#...#.##.",
            ".#.#.#........#........#.#.....",
            "#..#..##.....#.###..#.#.#.##..#",
            "..#.#...#..##.#.#.#.......###..",
            "........#........#..#..#...#...",
            "##............#...#..##.##...#.",
            "#....#.#.....##...#............",
            "............#...#..#.#.#....#..",
            "#.#.#...##.##.#....#....#......",
            "................###.....#.....#",
            "##.#####.#..#...###..#...###...",
            "...#.....#...#.#....#...#..#...",
            ".......#....##.##.#.##.........",
            "..#..#..##.....#...#.#.....#...",
            "...#...#.#.##.#..###.......#...",
            "...#...........#.#####..##..#..",
            "#.#...#........####..#......#.#",
            "#..#.##...........#.#......#.##",
            "#.#..#....##..#..##.#..........",
            ".....#..#.....#........##..#...",
            "...###.......#.##.......#......",
            "...##..#..#...#....#.###...#...",
            "....####....#........#.##.#.#.#",
            "#....#.....#.###.##...##..##.##",
            ".##.#...#.##.#......#..##.#....",
            "...#.............#.............",
            "..##..##.#.....#........##....#",
            "#.....#....#.......####...#..#.",
            "..#...#..#...#...##.#....##....",
            ".#...##....#....#..#....#......",
            "##..#.#...##......#..#.......##",
            "..#...#.##..#.....#.#...#..#.#.",
            "#..##....#..........#..........",
            ".#........#..#......#......#.#.",
            "...##.#.........#.#....#.#...#.",
            "#.....#.#..#...#...#..#...#...#",
            "#.........#.#.........##.......",
            ".#.......#......#.........###.."
        )

        /*val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        val file = File(applicationContext.filesDir, "adv2020_trees.txt")
        //Log.i(TAG,"In checkButton setOnClickListener lambda, file.absolutePath: "+file.absolutePath)
        listOfContents = file.readLines()*/

    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetSlot() {
        assertTrue(MainActivity.Traverser(treeLocations).getSlot(0,0)=='.')
        assertFalse(MainActivity.Traverser(treeLocations).getSlot(0,0)=='#')
    }

    @Test
    fun getNumCols(){
        assertTrue(MainActivity.Traverser(treeLocations).getNumCols()==31)
        assertFalse(MainActivity.Traverser(treeLocations).getNumCols()==0)
    }

    @Test
    fun getNumTrees(){
        val numTrees=MainActivity.Traverser(treeLocations).getNumTrees()
        assertTrue(numTrees==35)
    }

    @Test
    fun getNumTreesRightDown(){
        var treeLocations2=
        listOf(
        "....##..#",
        ".#.#..#..",
        "##.#..##.",
        ".#.##.###",
        "#.#.#....",
        "#.......#",
        ".#...#...",
        ".........",
        ".......##",
        ".#.#.#...",
        "........#")

        assertTrue(MainActivity.Traverser(treeLocations).getNumTrees(3,1)==35)
        //assertTrue(MainActivity.Traverser(treeLocations2).getNumTrees(1,1)==4)
        //assertTrue(MainActivity.Traverser(treeLocations2).getNumTrees(1,2)==2)
    }

    @Test
    fun getNumTreesRightDownProduct(){

        fillListOfContents()

        assertTrue(MainActivity.Traverser(listOfContents).getNumTrees(1,1)==93)
        assertTrue(MainActivity.Traverser(listOfContents).getNumTrees(3,1)==164)
        assertTrue(MainActivity.Traverser(listOfContents).getNumTrees(5,1)==82)
        assertTrue(MainActivity.Traverser(listOfContents).getNumTrees(7,1)==91)
        assertTrue(MainActivity.Traverser(listOfContents).getNumTrees(1,2)==44)
    }

    fun fillListOfContents() {
        listOfContents=listOf(
            "....##..#........##...#.#..#.##",
            ".#.#..#....##....#...#..##.....",
            "##.#..##..#...#..........##.#..",
            ".#.##.####..#......###.........",
            "#.#.#...........#.....#...#....",
            "#.......#....#.#.##..###..##..#",
            ".#...#...##....#.........#.....",
            "..........##.#.#.....#....#.#..",
            ".......##..##...#.#.#...#......",
            ".#.#.#...#...##...#.##.##..#...",
            "........##.#.#.###.........##..",
            "#.#..#.#.#.....#...#...#......#",
            ".#.#.#...##......#...#.........",
            ".#..##.##.#...#...##....#.#....",
            ".##...#..#..#......##.###....##",
            ".....#...#.###.....#.#.........",
            "#.##..#....#.#.#.#.............",
            "........#...#......#...#..#....",
            "##..##...##.##...#...#.###...##",
            "#.#....##.#...###......#..#.#..",
            "..#.....#.##......#..........#.",
            "#.......#..#......#.....#....#.",
            ".....###...........#....#.##...",
            "#.#........##.......#.#...#.##.",
            ".#.#.#........#........#.#.....",
            "#..#..##.....#.###..#.#.#.##..#",
            "..#.#...#..##.#.#.#.......###..",
            "........#........#..#..#...#...",
            "##............#...#..##.##...#.",
            "#....#.#.....##...#............",
            "............#...#..#.#.#....#..",
            "#.#.#...##.##.#....#....#......",
            "................###.....#.....#",
            "##.#####.#..#...###..#...###...",
            "...#.....#...#.#....#...#..#...",
            ".......#....##.##.#.##.........",
            "..#..#..##.....#...#.#.....#...",
            "...#...#.#.##.#..###.......#...",
            "...#...........#.#####..##..#..",
            "#.#...#........####..#......#.#",
            "#..#.##...........#.#......#.##",
            "#.#..#....##..#..##.#..........",
            ".....#..#.....#........##..#...",
            "...###.......#.##.......#......",
            "...##..#..#...#....#.###...#...",
            "....####....#........#.##.#.#.#",
            "#....#.....#.###.##...##..##.##",
            ".##.#...#.##.#......#..##.#....",
            "...#.............#.............",
            "..##..##.#.....#........##....#",
            "#.....#....#.......####...#..#.",
            "..#...#..#...#...##.#....##....",
            ".#...##....#....#..#....#......",
            "##..#.#...##......#..#.......##",
            "..#...#.##..#.....#.#...#..#.#.",
            "#..##....#..........#..........",
            ".#........#..#......#......#.#.",
            "...##.#.........#.#....#.#...#.",
            "#.....#.#..#...#...#..#...#...#",
            "#.........#.#.........##.......",
            ".#.......#......#.........###..",
            ".#..#..##...........#.....#..#.",
            ".#....................#.....#..",
            ".##.....#....#....#.###.....#..",
            "...##.#.....#.#....#.........#.",
            ".........##.....#.....#.##..#..",
            "......#......#.#..#..###...#..#",
            "..##...#.#..#...#.#....#.......",
            "..#..##.###.#..#..#..#......#..",
            ".....##...##.........#...##...#",
            "###.#..##....##...##.#..###....",
            "#...#.#..##......##...#.#..#...",
            "..........#....###....#........",
            "#.#.#.#.#.....#..##.##.....#...",
            ".##.....#...#.....#......#.....",
            ".#..........#.#.............#..",
            ".#..##..#.#..##...#....#.##...#",
            "..#.#..........#...#..........#",
            ".#.......#.......#...#..#.....#",
            "##.#...##...#......#.#..#......",
            "#####..#....#..#...#...#.#.....",
            "....#.......#.#..#.............",
            "#..#..#.#.####...#....#....##..",
            "#..#.##.#......#...#......#....",
            "#...##.##...#....#..........##.",
            "..#..#.......##.#....#...#.#...",
            ".....#.##..............##.....#",
            "..##.##...##.....#.........#.#.",
            ".#....##...##...##..#....##..#.",
            ".#...#....#..#......#.#........",
            "#....#.#.#..............#....##",
            "..##..#..#....#####.#....#.#.##",
            "#....#...#.##..#.##.........###",
            "#..#..#....#...............#.#.",
            "#....##...##........##.##.#.##.",
            "......#......##....##.....#.###",
            "#...##..#..#.....#.#........##.",
            "..#.#.##...#...#....#..###...#.",
            "#..##..#.###..##.#.#....#......",
            "..###..#.##........###.....#...",
            "#.............#.............#..",
            ".#.##....#..##.#...#....#.#####",
            "###.....###.#......##..#..##...",
            ".#.#......##.#....#....#.#..#..",
            "###..#..#....#......###.##.....",
            "......#.......#......#..##..##.",
            "..#..#...#..#....#.##....#.#..#",
            ".......##..#........#.#.##.....",
            ".#...#..#........#..#.#..#..#.#",
            ".#..#.##.......#......#...#.#..",
            ".##..##......##.#...#......####",
            ".....#....#......#.....#......#",
            "..........#.#.#...##.#..#.#....",
            "...#.......##......#..#.#.##...",
            ".###..#.#.#....................",
            "##...#...#.##............#.....",
            "....#....#.##...#..#.#...##....",
            "..#.#....#.###...#...#.#.####.#",
            "..#..#.#...#.#......##.........",
            "#..#..####.##.#.#..###....#...#",
            "....#..........#.##.#..#.#.#.#.",
            "#.#.##.........#.....##...#..##",
            "#......#...#.##.#......#..#.#..",
            "#...#........#..#..#...##...#..",
            ".....#.####..##..#.#.##..#...#.",
            "#..#........#........#...#....#",
            "...........#..#.....#.#.#.#....",
            "....#......#....#...#....##....",
            ".#.#..#...#.#....#..#.#....##.#",
            "....#...#...#.##..#...#..##...#",
            "#######...............##.....##",
            ".#.#..............#....#..#.###",
            "#......#.#......###....###.....",
            "##..#...#.##..##..##.#....#....",
            "#....##..#..#...#.#.#...#......",
            "..........#..#.##..##.##.#..##.",
            "....#.#.#.....##........#..#...",
            "..###...#.....##.##.....##..##.",
            "....#.#..#.#.......#.......#...",
            "..##.#..#.....##...###...#...#.",
            "..#.........#...##...#...#..#..",
            "..#..#..#..#..##.#...##..#.#...",
            "...##..#..##..#..####...#.....#",
            "............#............###...",
            ".#.#.###.#.....#.#.#..#.###..#.",
            "...#.........#.....####........",
            "....##.#..##.#.............#...",
            "....#.##.....#..#.....#......##",
            "..........#.............#...##.",
            "#..#.....#.......##..###.......",
            "..##.#...........#.......#..#..",
            "...#...#.#.##.###....#.#..#....",
            "...#..........##..#..#..#...###",
            ".........#.....#..##.....#..#..",
            "#........#...#...#..#.#....##..",
            ".#.#.....####..#.##.#..........",
            "###.......##..#.##...#.....#...",
            "..###.##.#..#..#..#.....##...#.",
            ".........#.....##.#..#..##.....",
            "#..#..##...###..............#..",
            "#....#.#....#..#.....#..####...",
            "####..#.....##...#..#.#.#.#...#",
            "...#....#.....#.##.#.#.#....##.",
            "..........#...#.....###....#.##",
            "#....#.#.#....#..#..#.....#....",
            ".....#.#...#......#....#......#",
            ".####....##...#...#......##..#.",
            ".#...#..#....#..#..............",
            "##.#...##...#.##..#......#.....",
            "..####.##..#....#.#......#.#.##",
            "........#.....##...#.#..##....#",
            "....#.#.#.#.###...#.#...##...##",
            "#.....#...####.#....#.#........",
            "..#.....#...##.........###.....",
            "....#....#....#..#......#####.#",
            "###.....#..#.#.#......#.##.#...",
            "....#.##......#..#.#...........",
            ".#....#....#.#..#.......#...##.",
            "...................#.#.#..#....",
            "##...#.....#........#....#...#.",
            "........##......#...##.#..#.#.#",
            "#.#..###...#....#.#...#.......#",
            "#..........##......#..#..#.....",
            ".............#...##.#...#......",
            "#..#....##..#.........#..#.###.",
            ".....#..........#....##.#...##.",
            "###....................#.#.##..",
            "#........##...##......#....###.",
            "#....#.............#....#...#..",
            "##.......##.#.......#....#..#..",
            "##...#............#..#.#....##.",
            "..#.#..#...#####..........#....",
            "..#.........##.....#.#...####..",
            "....#..............#...........",
            "..#...#.#.#..#.......##.#.#.#..",
            "....#.##.....##..#.....#..####.",
            "#...#...#...#.......#.........#",
            "......#..#.####...###.#.#.....#",
            ".......#..#..#.....#.#..##.#..#",
            ".#......##..#............#.....",
            ".#........#.#....#....#........",
            ".....#.#..#.##.#..##....#..#...",
            "#.#...........#....##.....#....",
            "..#..#..##.###..##..#..###.#.##",
            "##.#..#...##.#.........#...#.#.",
            "......#..#..##...#....#...#.##.",
            "#.##.......................#...",
            ".......#..#..#.##..##......#...",
            "..#.#...#.....#..###....#...#..",
            "##..#.....#..#..#.##.....#...##",
            "#...##...###...#.#..###....#...",
            "...#.#.#..####.....#...##....#.",
            ".#.#..##.....#..#.....##..##..#",
            "#...#..........#.##.#.#........",
            "..##....#.##....#..##......#...",
            "....#..........###.....####..##",
            "...........###....##.#.#.#.#...",
            "...#......................####.",
            "#.#.#...#.#.#.#.#......#.....##",
            "..###...#.####...#..##..#....#.",
            "....#....#.......#...#.........",
            ".#.###.............##..#...#...",
            "....#.#....##...#.....#.##.....",
            "#.......##.....#.#.....#....##.",
            "....##.....###..#.#..#....#...#",
            "......#..##...#......#.....#.##",
            ".#.....#.##.###....#.....#..###",
            "...#..#.###.#....#..#..#...##.#",
            "...##..#...#..#.#.#..#...#.....",
            "##.####...##..#.#.#....#.......",
            "..##..#.#.......##.#......##.#.",
            "....##....#....#..#....#..##.#.",
            "..##..........##....#...#.#..#.",
            "#.#...#.#.###.#.#..##.#...#....",
            ".....#..#.............#...#...#",
            "....#.#..#...##...#....#.##....",
            "#..#...#.###.....#...#.....#.#.",
            "#####....#....#....#.......#.##",
            "#...##....##.#.#...#.....##.#..",
            "#.......#...#..#..#...#....#...",
            "....#......#.#..........#....##",
            "#.###...#.#..##..#.##........#.",
            "#..#.....##.......#..#..#.#....",
            "...#...#.##...#....#.#.#.#...#.",
            "...##..#.#....#......###......#",
            "#.#....#...#..#..#....#........",
            "..#..#.#...##..........#.###...",
            "#..........#...#..#....#....###",
            "..#..#.#....#..............#...",
            "...#........#...#.#....###.#..#",
            "....#.#.#................#..#.#",
            "..#........##.#....#.#..#......",
            "...##..#..#.......#..#......#.#",
            "..#..#....#.........#....#.##..",
            "#.....#....###.#..#..#...#...#.",
            "..#..##.###.#..##....#.###.....",
            "...#...####..#........###.#....",
            ".........#.#...#..#..#.#.......",
            ".##.........##.#..............#",
            "..#.#.#.....###........#.#.#..#",
            "....##..#....#....#.#..#.......",
            "#.#.....#...#........##........",
            ".##.#.#..#..#.#.#.........#....",
            "#.....#..#.##...#...#..........",
            "##..#....#....##.#..#.........#",
            "................#.##.#......#.#",
            "..#..#.#........##...###..#...#",
            "##........#.......#...##.##..#.",
            "##....#.....#..##....#.......#.",
            "#.#....#.#........#..#.........",
            "......##......#...#.....#.##...",
            "###....#..........##.#.#......#",
            "......#...###.........###..#...",
            ".####....#...##..#.#.....#...#.",
            ".##...#...###....#...#.#..###..",
            "#..#......##...#.###..###...#..",
            "#....#.#.#..#....##...#.##..#..",
            "..#.....#...#..........#.##.###",
            "#.....#....###.......##..##.#..",
            "#..##...#..#....#.###......#...",
            "#..#........##..#.....#.#.#....",
            "#.##.#.#..#....#.#.............",
            ".#...............#....##.......",
            ".#.##......##........#...#..#.#",
            ".#...#....#....#...#..#...##...",
            ".....#..###...##........#.#....",
            "...#.......#....##..#..#....#..",
            "...###....#........#..#.###.#..",
            "......##..##..............###.#",
            ".......#.####..##....#.#....#..",
            "#...#......#...#..#.....##....#",
            ".#..#..###....#..##.##.#.......",
            "#......##......#..##....#..##..",
            ".....#..#.#......##.##..##.....",
            "...#..#.......#......#.........",
            "....#..####......#..#....#...#.",
            "..#.#..#...#....#....#.......#.",
            "####..#........#.###...##.#.#.#",
            ".......##........#.#.#...##....",
            "...#.........#..#.#..##....#...",
            ".....#..#...#.#....#...#.#.##.#",
            "#..##.....#.....##.......#...#.",
            ".......##.#.#.....#....#......#",
            "...#...#.##...#......#....#....",
            "..#..#.#...#..#.....#...###.#..",
            ".........#...#..#.......##.....",
            "..##...................#..#.###",
            ".##.##..#.#...#.#....#.....##..",
            "#.#...##...#...#...##..#......#",
            "....#..#...#.....##.#.....#..##",
            "##.#..........###..#...#..#....",
            "...##....#.##....#......#......",
            ".....#.........#....#.#.......#",
            ".......#............#.#.....#..",
            "..#..#...#..#####..#....##.....",
            "...##......##...#.#........##..",
            ".....#..###...##.#.#.##.#...#..",
            "..#.#.#..##..#.##...##.#.#.....",
            "......##...#..##......#.#......",
            "......................#........",
            "#...#..#....#..#.#.##.#.....#.#",
            ".#......#.#....#.#.#..#....#...",
            ".#..#.#.#..#....#..............")
    }
}