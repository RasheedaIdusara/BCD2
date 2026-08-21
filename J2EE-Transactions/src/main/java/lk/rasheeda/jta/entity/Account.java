package lk.rasheeda.jta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@NamedQueries({
        @NamedQuery(name = "Account.findbyAccountNo",query = "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accountNumber;
    private String name;
    private double balance;

    public Account(int id, int accountNumber, String name, double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
