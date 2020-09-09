'''
file: polygons.py
description: This program draws polygons recursively. It takes two command line inputs from user, number_of_sides and fill|unfill.
Program will draw all the polygons with sides from number_of_sides to 3, and will fill the polygons accoridng to the user's input.
The bottom center of polygons with n-1 sides is connected to the corner of polygons with n sides.
Total sum of length drawn will be displayed at the console.

author: Steve Gao, sg2369@rit.edu
author: Qiwen Quan, qq5575@rit.edu
'''


import turtle as t
import sys

# --- settings ---
# canvas length and height
length = 800
height = 800
# filled or unfilled
fill = False
# initial side lengths (chosen automatically with respect to the number of sides initially supplied to avoid oversize image)
ini_length = [length/2, length/10*4, length/10*3, length/10*2.5, length/10*2, length/10*1.5]
# colors
colors = ['magenta', 'dodger blue', 'orange', "yellow", 'pink', "red"]

# Initialize the canvas and move the turtle to the center right to avoid oversize drawing.
# :pre: turtle at (0, 0), facing east, pen down
# :post: turtle at (length/5, -height/5), facing east, pen dwown
def init():
    t.speed(0)
    t.setup(length, height)
    t.penup()
    t.forward(length / 5)
    t.right(90)
    t.forward(height / 5)
    t.left(90)
    t.pendown()
    t.tracer(0,0)


# Move the turtle to and back from the starting point pf the next polygon to be drawn
# :param actions: go to / back from the starting point.
# :param sides: number of sides that current polygon has
# :param length: current side length
# :pre: turtle at corner of the previous polygon, pen down
# :post: turtle at the corner of the previous polygon, pen down
def drawHelper(action, sides, length):
    t.penup()
    if (action == "current"):
        t.left(180 + (180 - (sides-2)*180/sides)/2)
        t.forward(length/4)
    else:
        t.back(length/4)
        t.left(180 + (180 - (sides-2)*180/sides)/2)
        t.forward(length)
    t.pendown()

# Draw the polygons recursively and sum up the total length drawn. Recursion will stop when sides are less than 3.a
# :param sides: number of sides that current polygon has
# :param length: current side length
# :pre: turtle at starting position of the polygon to be drawn, pen dwown
# :post: turtle at the starting position of the polygon drawn, pen down
# :return: sum of all sides of the polygons drawn
def draw(sides, length):
    if(sides < 3):
        return 0
    else:
        t.color(colors[(sides - 3) % len(colors)])
        if fill:
            t.begin_fill()
        # draw the current polygon
        for i in range(sides):
            t.left(180 - (sides-2)*180/sides)
            t.forward(length)
        if fill:
            t.end_fill()
        sum = sides * length
        # draw the next polygons recursively
        for i in range(sides):
            drawHelper("current", sides, length)
            sum += draw(sides - 1, length / 2)
            drawHelper("next", sides, length)
        return sum


# Main function. It checks command line input and calls the recursive function to draw the polygons if the input is valid.
if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 polygons.py #_sides [fill|unfill]")
    else:
        try:
            num_of_sides = int(sys.argv[1])
            init()
            if sys.argv[2] == "fill":
                fill = True
            print("Number of sides: " + str(num_of_sides) + ", initial length: " + str(ini_length[num_of_sides - 3]))
            print(draw(num_of_sides, ini_length[num_of_sides - 3]))
            t.update()
            t.mainloop()
        except:
            print("Invalid input.")