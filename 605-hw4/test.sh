#!/bin/sh
# @author: Qiwen Quan, qq5575@g.rit.edu
# @author: Steve Gao, sg2369@rit.edu
# This script is used to test if the HeroStorm generates the expected output.

javac hw4/heroes/* hw4/game/*

mkdir output

java hw4/game/HeroStorm 0 0 > output/0-0.txt
java hw4/game/HeroStorm 0 1 > output/0-1.txt
java hw4/game/HeroStorm 1 1 > output/1-1.txt
java hw4/game/HeroStorm 2 2 > output/2-2.txt
java hw4/game/HeroStorm 2 3 > output/2-3.txt
java hw4/game/HeroStorm 3 3 > output/3-3.txt
java hw4/game/HeroStorm 5 5 > output/5-5.txt
java hw4/game/HeroStorm 5 7 > output/5-7.txt
java hw4/game/HeroStorm 7 7 > output/7-7.txt

echo "Program executed. Start comparing result."
echo "Difference:"

diff sample_run/0-0.txt output/0-0.txt
diff sample_run/0-1.txt output/0-1.txt
diff sample_run/1-1.txt output/1-1.txt
diff sample_run/2-2.txt output/2-2.txt
diff sample_run/2-3.txt output/2-3.txt
diff sample_run/3-3.txt output/3-3.txt
diff sample_run/5-5.txt output/5-5.txt
diff sample_run/5-7.txt output/5-7.txt
diff sample_run/7-7.txt output/7-7.txt

echo "Result comparison completed."