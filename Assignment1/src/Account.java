import java.util.Date;

public class Account {

    // Variables needed

    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0;
    private Date dateCreated;

    public Account(){ // No-arg Account constructor
        this.id = 0;
        this.balance = 0;
        dateCreated = new Date();
    }

    public Account(int specificID, double initialBalance){ // Account constructor
        this.id = specificID;
        this.balance = initialBalance;
        dateCreated = new Date();
    }

    // Accessor methods for id, balance, annualInterestRate, dateCreated, and MonthlyInterestRate

    public int getID(){ // Gets id
        return id;
    }

    public double getBalance() { // Gets balance
        return balance;
    }

    public double getAnnualInterestRate() { // Gets annual interest rate
        return annualInterestRate;
    }

    public Date getDateCreated() { // Gets date creation
        return dateCreated;
    }

    public double getMonthlyInterestRate(){ // Gets monthly interest rate
        return (annualInterestRate / 100) / 12;
    }

    // Mutator methods for id, balance, and annualInterestRate

    public void setId(int id) { // sets id
        this.id = id;
    }

    public void setBalance(double balance) { // sets Balance
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) { // sets annual interest rate
        this.annualInterestRate = annualInterestRate;
    }

    // Withdraw and deposit methods

    public void withdraw(double amount){ // Withdraws money from account
        this.balance -= amount;
    }

    public void deposit(double amount){ // Deposits money into account
        this.balance += amount;
    }
}
