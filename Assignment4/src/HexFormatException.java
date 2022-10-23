/*
Name: Asher Bolleddu
Date: 10/23/2022
Class: CS2336.504

HexFormatException is a custom exception class that occurs when an attempt
is made to convert a string with improper format into a hexadecimal format
 */

public class HexFormatException extends NumberFormatException {
    private String hex;

    // Constructor
    public HexFormatException(String hex){
        super(hex + " is not a hex string");
        this.hex = hex;
    }

}
