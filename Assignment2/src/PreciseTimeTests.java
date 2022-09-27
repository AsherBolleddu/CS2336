/*
 * Asher Bolleddu CS2336.504
 * 09/26/2022
 * Purpose: Asks the user to input two PreciseTimes, which is validated through the regex,
 * then displays the addition (both non-static and static) and subtraction (both non-static and static) of the two times
 */
import java.util.Scanner;

public class PreciseTimeTests {
    public static void main(String[] args) {
        // Values and Variables needed
        boolean isValid = false;
        boolean isValid2 = false;
        PreciseTime firstTime;
        PreciseTime secondTime;
        PreciseTime addedTime = new PreciseTime();
        PreciseTime subtractedTime = new PreciseTime();
        String time1 = null;
        String time2 = null;

        Scanner scanner = new Scanner(System.in);
        while(!isValid){ // Loop to keep asking for a proper time format that matches regex
            System.out.println("Please enter a time in 12 hour format (HH:MM:SSXX) where HH is hour, MM is minutes, SS is seconds, and XX is AM/PM");
            time1 = scanner.nextLine().trim();
            if (!validateTime(time1)){
                System.out.println("INVALID TIME, PLEASE TRY AGAIN");
            } else {
                while (!isValid2){
                    System.out.println("Please enter a second time in 12 hour format (HH:MM:SSXX) where HH is hour, MM is minutes, SS is seconds, and XX is AM/PM");
                    time2 = scanner.nextLine().trim();
                    if(!validateTime(time2)){
                        System.out.println("INVALID TIME, PLEASE TRY AGAIN");
                    } else {
                        isValid2 =true;
                        isValid = true;
                    }
                }

            }
        }
        firstTime = new PreciseTime(time1); // Initializes object firstTime
        secondTime = new PreciseTime(time2); // Initializes object secondTime

        // Display results
        System.out.println();
        System.out.print("Time 1: ");
        firstTime.printTime();
        System.out.print("Time 2: ");
        secondTime.printTime();
        System.out.println();

        System.out.println("Adding using non static: " + addedTime.add(firstTime,secondTime));
        System.out.println("Adding using static: " + PreciseTime.addTo(firstTime,secondTime));
        System.out.println();
        System.out.println("Subtracting using non static: " + subtractedTime.subtract(firstTime,secondTime));
        System.out.println("Subtracting using static: " + PreciseTime.subtractFrom(firstTime,secondTime));

    }

    public static boolean validateTime(String time){ // Regex
        return time.matches("^(([1-9])|([0-1][1-2])|(0[1-9])|([1][0-2])):([0-5][0-9]):([0-5][0-9])(([aA])|([pP]))[mM]$");
    }
}
