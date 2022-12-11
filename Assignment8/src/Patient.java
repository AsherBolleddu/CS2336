/*
 * Name: Asher Bolleddu
 * Date: 12/10/2022
 * Class: CS2336.504
 *
 * Patient class to modify the Patient file
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Patient implements Comparable<Patient> {
    String fullName;
    String doctor;
    BST<MyDate> appointments;

    // Constructor
    public Patient(String fullName) {
        appointments = new BST<>();
        this.fullName = fullName;
    }

    // Constructor
    public Patient(String fullName, String doctor) {
        appointments = new BST<>();
        this.fullName = fullName;
        this.doctor = doctor;
    }

    // Deletes appointment
    public boolean deleteAppointment(MyDate appointment) {
        return appointments.delete(appointment);
    }

    // Inserts appointment
    public boolean insertAppointment(MyDate appointment) {
        return appointments.insert(appointment);
    }

    // Edit appointments
    public boolean editAppointment(MyDate oldAppointment, MyDate newAppointment) {
        appointments.delete(oldAppointment);
        return appointments.insert(newAppointment);
    }

    public boolean hasAppointmentNextWeek() {
        for (MyDate appointment : appointments) {
            if (appointment.isInNextWeek()) {
                return true;
            }
        }
        return false;
    }
    public boolean needsAnnualAppointment() {
        for (MyDate appointment: appointments) {
            if (!appointment.isPastOverAYear()) {
                return false;
            }
        }
        return true;
    }

    // Get the Patient from line
    public static Patient getFromLine(String line) {
        String[] stringArray = line.split(",\t");
        Patient patient = new Patient(stringArray[0], stringArray[1]);
        for (int i = 2; i < stringArray.length; i++) {
            patient.appointments.insert(new MyDate(stringArray[i]));
        }
        return patient;
    }

    // Get's the patient from file
    public static BST<Patient> getFromFile(String fileName) {
        BST<Patient> patients = new BST<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Patient patient = Patient.getFromLine(line);
                patients.insert(patient);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return patients;
    }

    // Sort the appointments in order
    protected void inorderAppointments(BST.TreeNode<MyDate> root) {
        if (root == null) return;
        inorderAppointments(root.left);
        System.out.print(",\t" + root.element);
        inorderAppointments(root.right);
    }

    // Sort the appointments in order into the file
    protected void inorderAppointmentsToFile(BST.TreeNode<MyDate> root, PrintWriter printWriter) {
        if (root == null) return;
        inorderAppointmentsToFile(root.left, printWriter);
        printWriter.print(",\t" + root.element);
        inorderAppointmentsToFile(root.right, printWriter);
    }
    public void println() {
        System.out.print(fullName + ",\t" + doctor);
        inorderAppointments(appointments.root);
        System.out.println();
    }
    public void printlnToFile(PrintWriter printWriter) {
        printWriter.print(fullName + ",\t" + doctor);
        inorderAppointmentsToFile(appointments.root, printWriter);
        printWriter.println();
    }

    // Compare patients
    @Override
    public int compareTo(Patient o) {
        return fullName.compareTo(o.fullName);
    }

    // Patient to string
    @Override
    public String toString() {
        return "Patient{" +
                "fullName='" + fullName + '\'' +
                ", doctor='" + doctor + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
