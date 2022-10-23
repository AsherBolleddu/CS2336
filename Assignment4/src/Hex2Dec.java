/*
Name: Asher Bolleddu
Date: 10/23/2022
Class: CS2336.504

(HexFormatException) Exercise 12.6 implements the hex2Dec method to throw a
NumberFormatException if the string is not a hex string. Define a custom
exception called HexFormatException. Implement the hex2Dec method to throw a
HexFormatException if the string is not a hex string.
 */
import java.util.Scanner;

public class Hex2Dec {

    public static void main(String[] args) {
        // Main method
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.println("Enter a hex number: ");
        String hex = input.nextLine();

        try {
            System.out.println("The decimal value for hex number " + hex + " is " + hexToDecimal(hex.toUpperCase()));
        } catch (HexFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    // Converts hex to Decimal
    public static int hexToDecimal(String hex) throws HexFormatException{
        int decimalValue = 0;
        for (int i = 0; i < hex.length();i++){
            // Throws HexFormatException if string is not a hex string
            if ((hex.charAt(i) < '0' || hex.charAt(i) > '9') && (hex.charAt(i) < 'A' || hex.charAt(i) > 'F')){
                throw new HexFormatException(hex);
            }
            char hexChar = hex.charAt(i);
            decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
        }
        return decimalValue;
    }

    // Converts hex character to decimal
    public static int hexCharToDecimal(char ch) {
        if (ch >= 'A' && ch <= 'F') {
            return 10 + ch - 'A';
        } else { // ch is '0', '1', ..., or '9'
            return ch - '0';
        }
    }


}






