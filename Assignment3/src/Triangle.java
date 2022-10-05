/*
Name: Asher Bolleddu
Date: 10/05/2022
Class: CS2336.504

Design a class named Triangle that extends GeometricObject. The class contains:

■■ Three double data fields named side1, side2, and side3 with default values 1.0 to denote three sides of a triangle.
■■ A no-arg constructor that creates a default triangle.
■■ A constructor that creates a triangle with the specified side1, side2, and
side3.
■■ The accessor methods for all three data fields.
■■ A method named getArea() that returns the area of this triangle.
■■ A method named getPerimeter() that returns the perimeter of this triangle.
■■ A method named toString() that returns a string description for the triangle.
 */

public class Triangle extends GeometricObject{
    // Attributes
    private double side1,side2,side3;

    public Triangle(){ // Default no-arg constructor
        side1 = side2 = side3 = 1.0;
    }

    public Triangle(double side1,double side2,double side3){ // Arg constructor
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Accessor methods that returns the sides
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    public double getArea(){ // Calculates the area and returns it (equation from programming exercise 2.19)
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter(){ // Calculates the perimeter and returns it
        return side1 + side2 + side3;
    }

    @Override
    public String toString() { // To string method
        return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side 3 = " + side3;
    }
}
