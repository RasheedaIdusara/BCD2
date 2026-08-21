package lk.rasheeda.bank.ejb.remote;

import jakarta.ejb.Local;
import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

@Local
public interface WithdrawService {
    void withdraw(String accountNo, double amount) throws InsufficientFundsException , AccountNotFoundException;
}
