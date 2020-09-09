import turtle

COLORS = ['red', 'green', 'blue', 'yellow']


def draw_triangles_1(length):
    '''
    Draws a red triangle.
    :param length: length of 1 side of the triangle
    :pre: turtle down, lower left, facing east
    :post： turtle down, lower left, facing east
    :return: None
    '''
    turtle.pencolor("red")
    turtle.forward(length)
    turtle.left(120)
    turtle.forward(length)
    turtle.left(120)
    turtle.forward(length)
    turtle.left(120)


def draw_triangles_2(green_length):
    '''
    Draws a green triangle.
    :param green_length: length of 1 side of the triangle
    :pre: turtle down, lower left, facing east
    :post: turtle down, lower left, facing east
    :return: None
    '''
    turtle.pencolor("green")
    for i in range(3):
        turtle.forward(green_length)
        draw_triangles_1(green_length/2)
        turtle.pencolor("green")
        turtle.left(120)

def draw_triangles_rec(length, depth):
    '''
    Draws triangles recursively
    :param length: length of 1 side of the triangle
    :param depth: amount of layers of triangles drawing will have
    :pre: turtle down, lower left, facing east
    :post: turtle down, lower left, facing east
    :return: sum of all sides of the triangle drawn
    '''
    if depth == 0:
        return 0
    else:
        sum = 0
        turtle.pencolor(COLORS[depth % len(COLORS)])
        for i in range(3):
            turtle.forward(length)
            sum += length + draw_triangles_rec(length/2, depth-1)
            turtle.pencolor(COLORS[depth % len(COLORS)])
            turtle.left(120)
        return sum

# Executes the program
turtle.speed(0)
print("Sum is: " + str(draw_triangles_rec(200, 6)))
turtle.mainloop()