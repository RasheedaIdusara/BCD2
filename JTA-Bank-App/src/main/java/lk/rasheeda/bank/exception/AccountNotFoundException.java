package lk.rasheeda.bank.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accountNumber) {

        super("No such account with account number " + accountNumber);
    }
}
