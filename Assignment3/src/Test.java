/*
Name: Asher Bolleddu
Date: 10/05/2022
Class: CS2336.504

Write a test program that prompts the user to enter three
sides of the triangle, a color, and a Boolean value to indicate whether the triangle
is filled. The program should create a Triangle object with these sides and set
the color and filled properties using the input. The program should display
the area, perimeter, color, and true or false to indicate whether it is filled or not.
*/
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // Create scanner object to take in input
        Scanner scanner = new Scanner(System.in);

        // Get input for three sides of triangle
        System.out.println("Please enter the three sides of the triangle, separated by space: ");
        double side1 = scanner.nextDouble();
        double side2 = scanner.nextDouble();
        double side3 = scanner.nextDouble();

        // Get input for color of triangle
        System.out.println("Please enter the color of the triangle: ");
        String color = scanner.next();

        // Get input for if the triangle is filled or not
        System.out.println("Is the triangle filled or not (Enter 'true' or 'false'): ");
        boolean filled = scanner.nextBoolean();

        // Create the triangle object
        Triangle triangle = new Triangle(side1,side2,side3);
        triangle.setColor(color);
        triangle.setFilled(filled);

        // Print out the statements
        System.out.println("The area of the triangle is " + triangle.getArea() + " units.");
        System.out.println("The perimeter of the triangle is " + triangle.getPerimeter() + " units.");
        System.out.println("The color of the triangle is " + triangle.getColor() + ".");
        if (triangle.isFilled()){
            System.out.println("The triangle is filled.");
        } else {
            System.out.println("The triangle is not filled.");
        }

    }
}
