/*
 * Name: Asher Bolleddu
 * Date: 12/10/2022
 * Class: CS2336.504
 *
 * Just the main class, all the implementation is in PatientManager.java
 * */
public class Main {
    public static void main(String[] args) {
        PatientManager patientManager = new PatientManager("patient.txt");
        while (true) {
            boolean isContinue = patientManager.showMenu();
            if (!isContinue) {
                break;
            }
        }
        patientManager.save();
    }
}