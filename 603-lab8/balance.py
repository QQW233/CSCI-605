'''
File: balance.py
Description: This program simulates the balance puzzle. It will ask user to input the path to the setup file.
Information will be read from this setup file and stored. If there is a missing value, the program will first compute
that missing value and then update the puzzle. Then the entire puzzle will be drawn without overlapping. This puzzle
will be checked for balance and the result will be print to the console.

Author: Steve Gao, sg2369@rit.edu
Author: Qiwen Quan, qq5575@rit.edu
'''

import turtle as t
import sys

# Set the intial click size. Change this value to change the size of the puzzle to draw.
TICK_INITIAL = 50

class Beam():
    '''
    Beam class stores the information about itself such as distance, scale and extent (both left and right) as well as 
    the weights that hang from it. It supports function such as draw and check whether this beam is balanced or not.
    '''

    __slots__ = "weights", "distance", "scale", "left_extent", "right_extent"
    '''
    weights: A list of Weight or Beam objects that hang from this beam.
    distance: The distance from the hanging point of this beam to the centerpoint of the beam that hangs this beam. 
              0 if this beam is not hung. Negative value indicates that this beam is hung to the left of the centerpoint. Positive if hung to the right.
    scale: number of pixles a tick of this beam should have to avoid overlapping. Used to draw the puzzle.
    left_extent: This beam's extent to the left of the hanging point.
    right_extent: This beam's extent to the right of the hanging point.
    '''

    def __init__(self, weights, distance = 0):
        '''
        Constructor for the Beam class. Initializes the fields and calculate the scale for each beam for future drawing.
        :param weights: A list of Weight/Beam objects that hangs from this beam.
        :param distance: The distance from the hanging point of this beam to the centerpoint of the beam that hangs this beam. 
        '''
        self.sort(weights)
        self.weights = weights
        self.distance = distance
        self.calculateScale()

    '''
    Sort a list of Weight/Beam objects according to their distance value in ascending order.
    :param weights: the list to sort.
    :pre: weights list not sorted.
    :post: wightes list sorted.
    '''
    def sort(self, weights):
        # Selection sort.
        for i in range(len(weights)-1):
            for j in range(len(weights)-1-i):
                if weights[j].distance > weights[j+1].distance:
                    temp = weights[j]
                    weights[j] = weights[j+1]
                    weights[j+1] = temp

    '''
    Calculate the scale of each beam recursively such that there will be no overlapping when drawn.
    '''
    def calculateScale(self):
        leaf = True
        # Calculate the scale of beams bottom up.
        for i in self.weights:
            if isinstance(i, Beam):
                leaf = False
                i.calculateScale()
        # Case 1 (Only weights hang from this beam), set the scale to default value and update the extent.
        if leaf:
            self.scale = TICK_INITIAL
            self.left_extent = abs(self.weights[0].distance * self.scale)
            self.right_extent = abs(self.weights[-1].distance * self.scale)
        # Case 2 (Other beams hang from this beam). Calculate the minimum scale to avoid overlapping at this beam and update the extent.
        else:
            min_scale = TICK_INITIAL
            for i in range(len(self.weights)-1):
                if (self.weights[i].right_extent + 20 + self.weights[i+1].left_extent)/(self.weights[i+1].distance - self.weights[i].distance) > min_scale:
                    min_scale = (self.weights[i].right_extent + 20 + self.weights[i+1].left_extent)/(self.weights[i+1].distance - self.weights[i].distance)
            self.scale = min_scale
            self.left_extent = abs(self.weights[0].distance * self.scale) + self.weights[0].left_extent
            self.right_extent = abs(self.weights[-1].distance * self.scale) + self.weights[-1].right_extent

    '''
    Get the total weight of a beam which is the sum of all the items that hang from it.
    :return: total weight of this beam.
    '''
    def getWeight(self):
        sum = 0
        for i in self.weights:
            sum += i.getWeight()
        return sum

    '''
    Draw this beam and all the items that hang from it. It will first draw the beam itself, and then recursively.
    call the draw() function of each item that hangs from it to finish the drawing.
    :pre: pendown, facing south
    :post: pendown, facing south
    '''
    def draw(self):
        tick = self.scale
        t.forward(40)
        t.left(90)
        # Draw the ticks.
        leftmost = self.weights[0].distance
        rightmost = self.weights[-1].distance
        for i in range(0, leftmost, -1):
            t.back(tick)
            t.right(90)
            t.forward(5)
            t.back(5)
            t.left(90)
        t.back(leftmost * tick)
        for i in range(0, rightmost, 1):
            t.forward(tick)
            t.right(90)
            t.forward(5)
            t.back(5)
            t.left(90)
        t.back(rightmost * tick)
        # Draw the weights/beams
        for i in self.weights:
            t.forward(i.distance * tick)
            t.right(90)
            t.forward(40)
            i.draw()
            t.forward(-40)
            t.left(90)
            t.back(i.distance * tick)
        t.right(90)
        t.back(40)

    '''
    Checks if this beam is balanced in terms of torque. A beam is considered balanced only if itself is balanced and
    all the beams that hang from it are balanced.
    If a missing value is detected during the process, it will be computed and filled.
    :return: A boolean value represents whether or not this beam is balanced. True if balanced. False otherwise.
    '''
    def checkBalance(self):
        balanced = True
        sum = 0
        unfinished = False
        for i in self.weights:
            if i.getWeight() == -1:
                unfinished = True
                unfinished_weight = i
                continue
            if isinstance(i, Beam):
                balanced = balanced and i.checkBalance()
                if balanced == False:
                    return False
            sum += i.getWeight() * i.distance
        if unfinished:
            answer = sum / unfinished_weight.distance * (-1)
            unfinished_weight.weight = int(answer)
            print("The missing weight is: " + str(int(answer)))
            sum += answer * unfinished_weight.distance
        if sum == 0 and balanced:
            return True
        else:
            return False


class Weight():
    '''
    Class to represent a single weight. It stores relevant information such as weight, distance and extent (both left and right)
    and contains functions to perform relevant actions.
    '''

    __slots__ = "weight", "distance", "left_extent", "right_extent"
    '''
    weight: how much does this weight weigh.
    distance: The distance from the hanging point of this weight to the centerpoint of the beam that hangs this weight. 
              Negative value indicates that this weight is hung to the left of the centerpoint. Positive if hung to the right.
    left_extent: This weight's extent to the left of the hanging point. Set to 0.
    right_extent: This weight's extent to the right of the hanging point. Set to 0.
    '''

    '''
    Constructor of the Weight class. Initializes the fields.
    '''
    def __init__(self, distance, weight):
        self.weight = weight
        self.distance = distance
        self.left_extent = 0
        self.right_extent = 0

    '''
    Get the weight value of this weight object.
    :return: the weight value of this weight object.
    '''
    def getWeight(self):
        return self.weight

    '''
    Draw this weight object. It will write the weight value of this weight object to the end of the hanging point.
    :pre: at the end of the hanging point, pendown, facing south
    :post: at the end of the hanging point, pendown, facing south
    '''
    def draw(self):
        t.penup()
        t.forward(15)
        t.write(str(self.weight), font = ("Arial", 12, "normal"))
        t.back(15)
        t.pendown()


'''
Main function will prompt a message to the user asking for setup file path. After reading the file path the information will be read
from the file and the relevant objects(Weight/Beam) will be constructed, checked for missing value, drawn and then validated for balance.
'''
if __name__ == "__main__":
    filepath = input("Please enter the input file path: ")
    beams = {}
    with open(sys.path[0] + '/' + filepath) as fp:
        line = fp.readline()
        while line:
            data = line.split(" ")
            name = data[0]
            weights = []
            for i in range(1, len(data), 2):
                position = int(data[i])
                weight = data[i+1]
                # Decide whether it is a Weight or a Beam.
                try:
                    weight = int(weight)
                    weights.append(Weight(position, weight))
                except:
                    beam = beams[weight]
                    beam.distance = position
                    weights.append(beam)
            beams[name] = Beam(weights)
            line = fp.readline()

    # Check for missing values and balance.
    balanced = beams['B'].checkBalance()
    # Draw the puzzle. 
    t.right(90)
    t.speed(0)
    beams['B'].draw()
    # Print the check result.
    if balanced:
        print("The puzzle is balanced!")
    else:
        print("The puzzle is unbalanced!")
    t.mainloop()