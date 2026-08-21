package lk.rasheeda.bank.ejb.remote;

import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;

public interface DepositService {

    void deposit(String accountNumber, double amount) throws AccountNotFoundException;

}
