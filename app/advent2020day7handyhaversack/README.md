# Advent of Code 2020 day 6, Handy Haversacks

Solution for day's both puzzles. 

There's a problem with testBagForShinyGold and hasShinyGoldBagsInside still.
Not counting correctly in all cases. Might debug it at some point.
Also, refactoring package to day 6 caused a problem with R not found in import. Needed toput day 7 on that import.

One needs to store puzzle input into File(applicationContext.filesDir, "bagrules.txt").
