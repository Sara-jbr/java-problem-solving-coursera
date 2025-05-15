package org.coursera.duke.solvingproblems.practice1;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

import java.io.File;


public class PerimeterAssignment {

	public double getPerimeter(Shape s) {
		double totalPerimeter = 0.0;
		Point prevPt = s.getLastPoint();

		for (Point currPt : s.getPoints()) {
			double currDist = prevPt.distance(currPt);
			totalPerimeter += currDist;
			prevPt = currPt;
		}

		return totalPerimeter;
	}


	public int getNumPoints(Shape s) {
		int count = 0;
		for (Point p : s.getPoints()) {
			count++;
		}
		return count;
	}

	public double getAverageLength(Shape s) {
		double perimeter = getPerimeter(s);
		int numSides = getNumPoints(s);
		return perimeter / numSides;
	}

	public void testPerimeter() {
		FileResource fr = new FileResource(); // Opens file chooser
		Shape s = new Shape(fr);
		double length = getPerimeter(s);
		System.out.println("Perimeter = " + length);

		int numPoints = getNumPoints(s);
		System.out.println("Number of points = " + numPoints);

		double avgLength = getAverageLength(s);
		System.out.println("Average side length = " + avgLength);

		double largestSide = getLargestSide(s);
		System.out.println("Largest side = " + largestSide);

		double largestX = getLargestX(s);
		System.out.println("Largest X = " + largestX);
	}

	public double getLargestSide(Shape s) {
		double largestSide = 0.0;
		Point prevPt = s.getLastPoint();  // start with the last point
		for (Point currPt : s.getPoints()) {
			double currDist = prevPt.distance(currPt);
			if (currDist > largestSide) {
				largestSide = currDist;
			}
			prevPt = currPt;
		}
		return largestSide;
	}

	public double getLargestX(Shape s) {
		double largestX = Double.NEGATIVE_INFINITY;
		for (Point p : s.getPoints()) {
			if (p.getX() > largestX) {
				largestX = p.getX();
			}
		}
		return largestX;
	}

	public void testPerimeterMultipleFiles() {
		double largestPerim = getLargestPerimeterMultipleFiles();
		System.out.println("Largest perimeter among multiple files is: " + largestPerim);
	}


	public String getFileWithLargestPerimeter() {
		DirectoryResource dr = new DirectoryResource();
		File temp = null;
		double largestPerimeter = 0.0;

		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			Shape s = new Shape(fr);
			double perimeter = getPerimeter(s);
			if (perimeter > largestPerimeter) {
				largestPerimeter = perimeter;
				temp = f;
			}
		}

		if (temp != null) {
			return temp.getName();
		} else {
			return "No file selected.";
		}
	}


	public double getLargestPerimeterMultipleFiles() {
		DirectoryResource dr = new DirectoryResource();
		double largestPerimeter = 0.0;

		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			Shape s = new Shape(fr);
			double perimeter = getPerimeter(s);
			if (perimeter > largestPerimeter) {
				largestPerimeter = perimeter;
			}
		}
		return largestPerimeter;
	}

	public static void main(String[] args) {
		PerimeterAssignment pr = new PerimeterAssignment();
		pr.testPerimeter();

		// Multiple files test
		pr.testPerimeterMultipleFiles();

		// File with the largest perimeter
		System.out.println("File with largest perimeter: " + pr.getFileWithLargestPerimeter());
	}
}
