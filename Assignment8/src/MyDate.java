/*
 * Name: Asher Bolleddu
 * Date: 12/10/2022
 * Class: CS2336.504
 *
 * Uses the Date class to utilize and be able to modify the dates in the patient.txt file
 * */
import java.util.Date;

public class MyDate implements Comparable<MyDate> {
    static int[] dayCountInMonth = new int[] { 29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int day;
    int month;
    int year;

    // Constructor
    public MyDate(String date) {
        setDate(date);
    }

    // Constructor
    public MyDate(Date date) {
        this.month = date.getMonth();
        this.day = date.getDay();
        this.year = date.getYear();
    }

    // Sets the date
    void setDate(String date) {
        String[] stringArray = date.split("/");
        day = Integer.parseInt(stringArray[1]);
        month = Integer.parseInt(stringArray[0]);
        year = 2000 + Integer.parseInt(stringArray[2]);
    }

    // Checks if the date is a leap year
    public static boolean isLeap(int year) {
        if (year % 4 == 0) {
            return year % 100 != 0 || year % 400 == 0;
        }
        return false;
    }

    // Gets the amount of days in a month
    public static int getDayCountInMonth(int month, int year) {
        if (month == 4 && isLeap(year)) {
            return dayCountInMonth[0];
        }
        return dayCountInMonth[month];
    }

    // Subtracts the day
    public static int subtractDay(MyDate minuend, MyDate subtrahend) {
        if (minuend.compareTo(subtrahend) < 0) {
            return -subtractDay(subtrahend, minuend);
        }
        int dayCount = -subtrahend.day;
        int month = subtrahend.month;
        int year = subtrahend.year;
        while (month <= minuend.month && year <= minuend.year) {
            dayCount += getDayCountInMonth(month, year);

            month += 1;
            if (month > 12) {
                month = 1;
                year += 1;
            }
        }

        return dayCount;
    }

    // Checks if the patient has an appointment in the next week
    public boolean isInNextWeek() {
        MyDate currentDate = new MyDate(new Date());
        int dayDifference = subtractDay(this, currentDate);
        return dayDifference >= 0 && dayDifference <= 7;
    }

    // Checks if the patient hasn't had an annual appointment
    public boolean isPastOverAYear() {
        MyDate currentDate = new MyDate(new Date());
        int dayDifference = subtractDay(currentDate, this);
        return dayDifference > 365;
    }

    // Compares the date
    @Override
    public int compareTo(MyDate o) {
        if (year < o.year) {
            return -1;
        } else if (year > o.year) {
            return 1;
        } else {
            if (month < o.month) {
                return -1;
            } else if (month > o.month) {
                return 1;
            } else {
                if (day < o.day) {
                    return -1;
                } else if (day > o.day) {
                    return 1;
                }
            }
        }
        return 0;
    }

    // To string method
    @Override
    public String toString() {
        String string = "";
        if (month < 10) {
            string += "0";
        }
        string += month + "/";
        if (day < 10) {
            string += "0";
        }
        string += day + "/";
        if (year % 100 < 10) {
            string += "0";
        }
        string += year % 100;
        return string;
    }
}
