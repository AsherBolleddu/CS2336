/*
Name: Asher Bolleddu
Date: 10/29/2022
Class: CS2336.504

Design a class named Complex for representing complex numbers and the methods
add, subtract, multiply, divide, and abs for performing complex number
operations, and override toString method for returning a string representation
for a complex number. The toString method returns (a + bi) as a string. If b
is 0, it simply returns a. Your Complex class should also implement the
Cloneable interface.

Provide three constructors Complex(a, b), Complex(a), and Complex(). Complex()
creates a Complex object for number 0 and Complex(a) creates a Complex object
with 0 for b. Also provide the getRealPart() and getImaginaryPart() methods
for returning the real and imaginary part of the complex number, respectively.
*/
public class Complex implements Cloneable, Comparable<Complex> {
    double a,b; // Attributes

    public Complex(){ // Constructor
        this(0,0);
    }

    public Complex(double a){ // Constructor
        this.a = a;
        this.b = 0;
    }

    public Complex(double a, double b){ // Constructor
        this.a = a;
        this.b = b;
    }

    public Complex add(Complex obj){ // Adds the complex objects
        return new Complex(a+obj.a,b+obj.b);
    }

    public Complex subtract(Complex obj){ // Subtracts the complex objects
        return new Complex(a-obj.a,b-obj.b);
    }

    public Complex multiply(Complex obj){ // (a*obj.a) - (b*obj.b) "+" (b*obj.a) + (a*obj.b) --> Multiplies the complex objects
        return new Complex((a*obj.a) - (b*obj.b),(b*obj.a)+ (a*obj.b));
    }

    public Complex divide(Complex obj){ // ((a*obj.a) + (b*obj.b)) / ((Math.pow(obj.a, 2) + Math.pow(obj.b, 2)) "+" ((b*obj.a) - (a*obj.b)) / ((Math.pow(obj.a, 2) + Math.pow(obj.b, 2)) --> Divides the complex objects
        return new Complex(((a*obj.a) + (b*obj.b)) / ((Math.pow(obj.a, 2) + Math.pow(obj.b, 2))),((b*obj.a) - (a*obj.b)) / ((Math.pow(obj.a, 2) + Math.pow(obj.b, 2))));
    }

    public double abs(){ // Calculates the absolute value
        return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }

    public double getRealPart() { // Gets a
        return a;
    }


    public double getImaginaryPart() { // Gets b
        return b;
    }

    @Override
    public String toString() { // To string method
        return this.a + " + " + this.b + "i";
    }

    @Override
    public int compareTo(Complex o) { // Compares the two complex objects
        // 0: if (this.abs()==o.abs())
        // -1: if (this.abs() < o.abs())
        // 1: if (this.abs() > o.abs())
        return Double.compare(this.abs(), o.abs());
    }
}
