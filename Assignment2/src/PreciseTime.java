/*
 * Asher Bolleddu CS2336.504
 * 09/26/2022
 * Purpose: subclass PreciseTime, which inherits from superclass BasicTime. It has 3 constructors,
 * addition and subtraction (both non-static and static) of times, calculations of the subtraction and addition,
 * as well as putting  a time back into a 12 hour format
 */
public class PreciseTime extends BasicTime {
    private int seconds;

    public PreciseTime(int hour, int minutes, int seconds, boolean morning){
        super(hour, minutes, morning);
        this.seconds = seconds; // HH:MM:SSXX
        String formatSeconds = String.format("%02d",this.seconds);
        if(this.time.length() == 7){ // Since you have to account for two digit hours, then the format changes
            this.time = this.hour + ":" + this.time.substring(3,5) + ":" + formatSeconds + this.time.substring(5);
        } else { // Use this format for non two digit hours
            this.time = this.hour + ":" + this.time.substring(2,4) + ":" + formatSeconds + this.time.substring(4);
        }
    }

    public PreciseTime(String time){ // Constructor
        this(Integer.parseInt(time.split(":")[0]),Integer.parseInt(time.split(":")[1].substring(0,2)),Integer.parseInt(time.split(":")[2].substring(0,2)),Boolean.parseBoolean(String.valueOf(time.split(":")[2].substring(2).equalsIgnoreCase("AM"))));
    }

    public PreciseTime() { // Constructor
        this(12,0,0,true);
    }

    public PreciseTime add(PreciseTime pt1, PreciseTime pt2){ // Adds two PreciseTimes
        return getAddedPreciseTime(pt1,pt2);
    }

    public static PreciseTime addTo(PreciseTime pt1, PreciseTime pt2){ // Adds two PreciseTimes, but is a Static Method
        return getAddedPreciseTime(pt1,pt2);
    }

    public PreciseTime subtract(PreciseTime pt1, PreciseTime pt2){ // Subtracts two PreciseTimes
        return getSubtractedPreciseTime(pt1,pt2);
    }

    public static PreciseTime subtractFrom(PreciseTime pt1, PreciseTime pt2){ // Subtracts two PreciseTimes, but is a Static Method
        return getSubtractedPreciseTime(pt1,pt2);
    }

    public static PreciseTime getAddedPreciseTime(PreciseTime pt1, PreciseTime pt2){ // Static Method that does that Additive calculation
        // Parses the two PreciseTimes passed into Hours and Minutes and Seconds and the Time of Day
        int firstTimeTotalMinutes;
        int secondTimeTotalMinutes;
        int totalMinutes;
        int totalSeconds;
        int firstTimeHours = BasicTime.getHours(pt1);
        int firstTimeMinutes = BasicTime.getMinutes(pt1);
        int firstTimeSeconds = PreciseTime.getSeconds(pt1);
        String firstTimeOfDay = PreciseTime.getTimeOfDayString(pt1);
        int secondTimeHours = BasicTime.getHours(pt2);
        int secondTimeMinutes = BasicTime.getMinutes(pt2);
        int secondTimeSeconds = PreciseTime.getSeconds(pt2);
        String secondTimeOfDay = PreciseTime.getTimeOfDayString(pt2);



        if(firstTimeOfDay.equalsIgnoreCase("PM") && firstTimeHours < 12){ // If time of day is PM and hour is less than 12, do the minutes calculation
            firstTimeTotalMinutes = ((firstTimeHours + 12) * 60) + firstTimeMinutes;
        } else if(firstTimeOfDay.equalsIgnoreCase("AM") && firstTimeHours == 12) { // If time of day is AM and hour is equal to 12, total minutes is equal to the minutes
            firstTimeTotalMinutes = firstTimeMinutes;
        }
        else { // In all other scenarios, default to this calculation
            firstTimeTotalMinutes = (firstTimeHours * 60) + firstTimeMinutes;
        }


        if(secondTimeOfDay.equalsIgnoreCase("PM") && secondTimeHours < 12){ // If time of day is PM and hour is less than 12, do the minutes calculation
            secondTimeTotalMinutes = ((secondTimeHours + 12) * 60) + secondTimeMinutes;
        } else if(secondTimeOfDay.equalsIgnoreCase("AM") && secondTimeHours == 12) { // If time of day is AM and hour is equal to 12, total minutes is equal to the minutes
            secondTimeTotalMinutes = secondTimeMinutes;
        }
        else { // In all other scenarios, default to this calculation
            secondTimeTotalMinutes = (secondTimeHours * 60) + secondTimeMinutes;
        }


        totalSeconds = firstTimeSeconds + secondTimeSeconds; // Get the TotalSeconds by adding both firstTimeSeconds and secondTimeSeconds
        int totalSecondsInMinutes = totalSeconds / 60; // Convert the seconds into minutes
        int remainingSeconds = totalSeconds % 60; // Find the remaining seconds using %

        totalMinutes = firstTimeTotalMinutes + secondTimeTotalMinutes + totalSecondsInMinutes;  // Calculate totalMinutes by adding the Minutes related variables

        return calculatePreciseTime(totalMinutes,remainingSeconds); // Pass it to calculatePreciseTime
    }

    public static PreciseTime getSubtractedPreciseTime(PreciseTime pt1, PreciseTime pt2){
        int firstTimeTotalMinutes;
        int secondTimeTotalMinutes;
        int totalMinutes;
        int totalSeconds;
        int firstTimeHours = BasicTime.getHours(pt1);
        int firstTimeMinutes = BasicTime.getMinutes(pt1);
        int firstTimeSeconds = PreciseTime.getSeconds(pt1);
        String firstTimeOfDay = PreciseTime.getTimeOfDayString(pt1);
        int secondTimeHours = BasicTime.getHours(pt2);
        int secondTimeMinutes = BasicTime.getMinutes(pt2);
        int secondTimeSeconds = PreciseTime.getSeconds(pt2);
        String secondTimeOfDay = PreciseTime.getTimeOfDayString(pt2);


        if(firstTimeOfDay.equalsIgnoreCase("PM") && firstTimeHours < 12){ // If time of day is PM and hour is less than 12, do the minutes calculation
            firstTimeTotalMinutes = ((firstTimeHours + 12) * 60) + firstTimeMinutes;
        } else if(firstTimeOfDay.equalsIgnoreCase("AM") && firstTimeHours == 12) { // If time of day is AM and hour is equal to 12, total minutes is equal to the minutes
            firstTimeTotalMinutes = firstTimeMinutes;
        }
        else { // In all other scenarios, default to this calculation
            firstTimeTotalMinutes = (firstTimeHours * 60) + firstTimeMinutes;
        }

        if(secondTimeOfDay.equalsIgnoreCase("PM") && secondTimeHours < 12){ // If time of day is PM and hour is less than 12, do the minutes calculation
            secondTimeTotalMinutes = ((secondTimeHours + 12) * 60) + secondTimeMinutes;
        } else if(secondTimeOfDay.equalsIgnoreCase("AM") && secondTimeHours == 12) { // If time of day is AM and hour is equal to 12, total minutes is equal to the minutes
            secondTimeTotalMinutes = secondTimeMinutes;
        }
        else { // In all other scenarios, default to this calculation
            secondTimeTotalMinutes = (secondTimeHours * 60) + secondTimeMinutes;
        }

        totalSeconds = Math.abs(firstTimeSeconds - secondTimeSeconds); // Get the TotalSeconds by taking the absolute value of subtracting both firstTimeSeconds and secondTimeSeconds
        int totalSecondsInMinutes = totalSeconds / 60; // Convert the seconds into minutes
        int remainingSeconds = totalSeconds % 60; // Find the remaining seconds using %

        totalMinutes = Math.abs(firstTimeTotalMinutes - secondTimeTotalMinutes - totalSecondsInMinutes); // Calculate totalMinutes by taking the absolute value of subtracting the Minutes related variables
        return calculatePreciseTime(totalMinutes,remainingSeconds); // Pass it to calculatePreciseTime
    }

    public static int getSeconds(PreciseTime preciseTime){ // Parsing seconds
        String preciseTimeString = preciseTime.toString();
        return Integer.parseInt(preciseTimeString.split(":")[2].substring(0,2));
    }

    public static String getTimeOfDayString(PreciseTime preciseTime){ // Parsing "AN/PM" because it is held in a different substring now with the addition of seconds
        String preciseTimeString = preciseTime.toString();
        return preciseTimeString.split(":")[2].substring(2);
    }

    public String toString(){
        return this.time;
    } // Returns the string this.time

    public static PreciseTime calculatePreciseTime(int totalMinutes,int remainSeconds){ // Does the calculation of hours and minutes after total minutes has been found
        int hour = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        boolean morning;

        if (hour >= 24){
            hour = hour % 24;
        }
        if (hour < 12){
            if (hour == 0){
                hour = 12;
            }
            morning = true;
        } else if (hour == 12){
            morning = false;
        } else {
            hour = hour - 12;
            morning = false;
        }
        return new PreciseTime(hour,minutes,remainSeconds,morning); // Calls the PreciseTime constructor to create a new PreciseTime
    }


}
