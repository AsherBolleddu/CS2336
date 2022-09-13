public class BankOperations {

    public static void main(String[] args) {
        Account account = new Account(1122,20000); // Create new Account object "account"
        account.setAnnualInterestRate(4.5); // Sets annual interest rate to 4.5%
        account.withdraw(2500); // Withdraws 2500 from account
        account.deposit(3000); // Deposits 3000 to account

        // Print statements

        System.out.println("Your balance is: $" + account.getBalance());
        System.out.println("Your monthly interest is: " + account.getMonthlyInterestRate());
        System.out.println("This account was created on: " + account.getDateCreated());
    }
}
