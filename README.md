# java-problem-solving-coursera

#  Perimeter Calculator

This project reads a set of 2D points from a text file, constructs a shape from those points, and calculates the **perimeter** of that shape. The final result is the total length around the shape.


##  How It Works

Each `.txt` file contains coordinates of 2D points (one point per line). These points are interpreted as the vertices of a polygon. The program calculates the perimeter by summing the distances between each pair of consecutive points — and finally between the last point and the first.

###  Distance Formula
To calculate the distance between two points `(x1, y1)` and `(x2, y2)`:

`distance = √((x2 - x1)² + (y2 - y1)²)`

##  Features

- Calculates total perimeter of a shape
- Determines average side length
- Finds the longest side
- Counts the number of points
- Identifies the file with the shape having the largest perimeter (when multiple files are provided)

##  How to Run

Use **BlueJ**, **IntelliJ**, or any Java IDE:

1. Clone/download this repository.
2. Open the project in your IDE.
3. Run `PerimeterAssignment.java`.
4. Choose an input `.txt` file when prompted.



 Note: This project was completed as part of the Coursera course “Java Programming: Solving Problems with Software” by Duke University.