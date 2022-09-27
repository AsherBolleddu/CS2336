/*
 * Asher Bolleddu CS2336.504
 * 09/26/2022
 * Purpose: BasicTime class that has 3 constructors, addition and subtraction (both non-static and static) of times,
 * calculations of the subtraction and addition, as well as putting a time back into a 12 hour format
 */

public class BasicTime {
     protected String time;
     protected int hour;
     protected int minutes;
     protected boolean morning;

    public BasicTime(){
        this(12, 0, true);
    } // Constructor

    public BasicTime(String time){ // Constructor
         this(Integer.parseInt(time.split(":")[0]),Integer.parseInt(time.split(":")[1].substring(0,2)),Boolean.parseBoolean(String.valueOf(time.split(":")[1].substring(2).equalsIgnoreCase("AM"))));
     }

    public BasicTime(int hour, int minutes, boolean morning){ // Constructor
        this.hour = hour;
        this.minutes = minutes;
        this.morning = morning;
        String formatMinutes = String.format("%02d",this.minutes);
        String timeOfDay;
        if (this.morning){
            timeOfDay = "AM";
        } else {
            timeOfDay = "PM";
        }
        this.time = "";
        this.time = this.hour + ":" + formatMinutes + timeOfDay;
    }

    public void printTime(){
         System.out.println(this.time);
     } // Prints the time

    public BasicTime add(BasicTime firstTime, BasicTime secondTime){ // Adds two BasicTimes
         return getAddedBasicTime(firstTime, secondTime);
     }

    public static BasicTime addTo(BasicTime firstTime, BasicTime secondTime){ // Adds two BasicTimes, but is a Static Method
         return getAddedBasicTime(firstTime, secondTime);
     }

    public BasicTime subtract(BasicTime firstTime, BasicTime secondTime){ // Subtracts two BasicTimes
        return getSubtractedBasicTime(firstTime, secondTime);
    }

    public static BasicTime subtractFrom(BasicTime firstTime, BasicTime secondTime){ // Subtracts two BasicTimes, but is a Static Method
        return getSubtractedBasicTime(firstTime, secondTime);
    }

    public static BasicTime getAddedBasicTime(BasicTime firstTime, BasicTime secondTime) { // Static Method that does that Additive calculation
        // Parses the two BasicTimes passed into Hours and Minutes and the Time of Day
        int firstTimeTotalMinutes;
        int secondTimeTotalMinutes;
        int totalMinutes;
        int firstTimeHours = getHours(firstTime);
        int firstTimeMinutes = getMinutes(firstTime);
        String firstTimeOfDay = getTimeOfDayString(firstTime);
        int secondTimeHours = getHours(secondTime);
        int secondTimeMinutes = getMinutes(secondTime);
        String secondTimeOfDay = getTimeOfDayString(secondTime);

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

        totalMinutes = firstTimeTotalMinutes + secondTimeTotalMinutes; // Add the firstTimeTotalMinutes and the secondTimeTotalMinutes

        return calculateBasicTime(totalMinutes);
    }

    public static BasicTime getSubtractedBasicTime(BasicTime firstTime, BasicTime secondTime) {
        int firstTimeTotalMinutes;
        int secondTimeTotalMinutes;
        int totalMinutes;
        int firstTimeHours = getHours(firstTime);
        int firstTimeMinutes = getMinutes(firstTime);
        String firstTimeOfDay = getTimeOfDayString(firstTime);
        int secondTimeHours = getHours(secondTime);
        int secondTimeMinutes = getMinutes(secondTime);
        String secondTimeOfDay = getTimeOfDayString(secondTime);

        if(firstTimeOfDay.equalsIgnoreCase("PM") && firstTimeHours < 12){
            firstTimeTotalMinutes = ((firstTimeHours + 12) * 60) + firstTimeMinutes;
        } else if(firstTimeOfDay.equalsIgnoreCase("AM") && firstTimeHours == 12) {
            firstTimeTotalMinutes = firstTimeMinutes;
        }
        else {
            firstTimeTotalMinutes = (firstTimeHours * 60) + firstTimeMinutes;
        }

        if(secondTimeOfDay.equalsIgnoreCase("PM") && secondTimeHours < 12){
            secondTimeTotalMinutes = ((secondTimeHours + 12) * 60) + secondTimeMinutes;
        } else if(secondTimeOfDay.equalsIgnoreCase("AM") && secondTimeHours == 12) {
            secondTimeTotalMinutes = secondTimeMinutes;
        }
        else {
            secondTimeTotalMinutes = (secondTimeHours * 60) + secondTimeMinutes;
        }

        totalMinutes = Math.abs(firstTimeTotalMinutes - secondTimeTotalMinutes);
        return calculateBasicTime(totalMinutes);
    }

    public static BasicTime calculateBasicTime(int totalMinutes) { // Does the calculation of hours and minutes after total minutes has been found
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
        return new BasicTime(hour,minutes,morning); // Calls the BasicTime constructor to create a new BasicTime
    }


    public static int getHours(BasicTime basicTime){ // Parses the hours of the string
        String basicTimeString = basicTime.toString();
        return Integer.parseInt(basicTimeString.split(":")[0]);
     }

    public static int getMinutes(BasicTime basicTime){ // Parses the minutes of the string
        String basicTimeString = basicTime.toString();
        return Integer.parseInt(basicTimeString.split(":")[1].substring(0,2));
    }


    public static String getTimeOfDayString(BasicTime basicTime){ // Parses the "AM/PM"
        String basicTimeString = basicTime.toString();
        return basicTimeString.split(":")[1].substring(2);
    }

    public String toString(){
        return this.time;
    } // Returns the String this.time
}
