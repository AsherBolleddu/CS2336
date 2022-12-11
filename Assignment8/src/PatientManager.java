/*
 * Name: Asher Bolleddu
 * Date: 12/10/2022
 * Class: CS2336.504
 *
 * Your client is a group of doctors that own their own clinic. The doctors need a system to manage patient appointments.
 * There are 3 doctors, each has its own patients.
 * The doctors want to keep track of all their patient appointments in memory and disk, using a tree as an internal program representation.
 * */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class PatientManager {
    BST<Patient> patients;
    Scanner scanner = new Scanner(System.in);
    String fileName;

    // Constructor
    public PatientManager(String fileName) {
        this.fileName = fileName;
        patients = Patient.getFromFile(fileName);
    }

    // Gets the input for the name
    String getInputFullName() {
        System.out.print("Input patient full name: ");
        return scanner.nextLine();
    }

    // Gets the input for the doctor
    String getInputDoctor() {
        System.out.println("Input patient's doctor's last name: ");
        return scanner.nextLine();
    }

    // Search's for the patient
    public Patient searchPatient(String fullName) {
        Patient e = new Patient(fullName);
        BST.TreeNode<Patient> current = patients.root; // Start from the root

        while (current != null) {
            if (patients.c.compare(e, current.element) < 0) {
                current = current.left;
            }
            else if (patients.c.compare(e, current.element) > 0) {
                current = current.right;
            }
            else // element matches current.element
                return current.element; // Element is found
        }

        return null;
    }

    // The menu
    public boolean showMenu() {
        System.out.println("1- add a new patient to the database");
        System.out.println("2- delete a patient from the database");
        System.out.println("3- edit a patient’s appointment");
        System.out.println("4- search for patients with appointments in the next week by doctor’s name");
        System.out.println("5- search for a given patient");
        System.out.println("6- search for patients that needs an annual appointment setup by doctor’s name");
        System.out.println("7- search for any patient that has an appointment in the next week");
        System.out.println("0- Exit");
        System.out.print("Input your choice [0-7]: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 0 || choice > 7) {
            return false;
        }

        String fullName;
        String doctor;
        switch (choice) {
            case 0:
                return false;
            case 1:
                fullName = getInputFullName();
                doctor = getInputDoctor();
                patients.insert(new Patient(fullName, doctor));
                break;
            case 2:
                fullName = getInputFullName();
                patients.delete(new Patient(fullName));
                break;
            case 3:
                fullName = getInputFullName();
                System.out.println("Input old appointment date [mm/dd/yy]: ");
                String oldAppointmentString = scanner.nextLine();
                System.out.println("Input new appointment date [mm/dd/yy]: ");
                String newAppointmentString = scanner.nextLine();
                searchPatient(fullName).editAppointment(new MyDate(oldAppointmentString), new MyDate(newAppointmentString));
                break;
            case 4:
                doctor = getInputDoctor();
                for (Patient patient : patients) {
                    if (Objects.equals(patient.doctor, doctor) && patient.hasAppointmentNextWeek()) {
                        patient.println();
                    }
                }
                break;
            case 5:
                fullName = getInputFullName();
                searchPatient(fullName).println();
                break;
            case 6:
                doctor = getInputDoctor();
                for (Patient patient : patients) {
                    if (Objects.equals(patient.doctor, doctor) && patient.needsAnnualAppointment()) {
                        patient.println();
                    }
                }
                break;
            case 7:
                for (Patient patient : patients) {
                    if (patient.hasAppointmentNextWeek()) {
                        patient.println();
                    }
                }
                break;
        }
        return true;
    }

    // Writes to the file inorder
    protected void inorderToFile(BST.TreeNode<Patient> root, PrintWriter printWriter) {
        if (root == null) return;
        inorderToFile(root.left, printWriter);
        printWriter.println(root.element);
        inorderToFile(root.right, printWriter);
    }

    // Saves the file
    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            inorderToFile(patients.root, printWriter);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
