import java.util.Date;

/*
This class contains the properties color and filled and their appropriate getter and setter methods.
Assume this class also contains the dateCreated property, and the getDateCreated() and toString() methods.
The toString() method returns a string representation of the object.
 */
public class GeometricObject {
    // Attributes
    protected String color;
    protected boolean filled;
    protected Date dateCreated;

    public GeometricObject() { // Default no-arg constructor
        dateCreated = new Date();
    }

    public GeometricObject(String color, boolean filled){ // Arg constructor
        dateCreated = new Date();
        this.color = color;
        this.filled = filled;
    }

    public String getColor() { // Accessor method that returns color
        return color;
    }

    public void setColor(String color) { // Mutator method that sets the color
        this.color = color;
    }

    public boolean isFilled() { // Accessor method that returns a boolean on if the Geometric object is filled
        return filled;
    }

    public void setFilled(boolean filled) { // Mutator method that fills (or doesn't) the Geometric object
        this.filled = filled;
    }

    public Date getDateCreated() { // Accessor method that returns the date created
        return dateCreated;
    }

    @Override
    public String toString() { // To string method
        return "GeometricObject{" +
                "color='" + color + '\'' +
                ", filled= " + filled +
                ", dateCreated= " + dateCreated +
                '}';
    }
}
