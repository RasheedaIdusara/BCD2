package lk.rasheeda.bank.ejb.remote;

import jakarta.ejb.Local;
import lk.rasheeda.bank.entity.Transaction;
import lk.rasheeda.bank.entity.TransactionType;
import lk.rasheeda.bank.exception.AccountNotFoundException;

import java.util.List;

@Local
public interface TransactionLogService {

    void Log(String accountNumber, TransactionType type, double amount,double balanceAfter,String relatedAccountNo);
    List<Transaction> history(String accountNo);
}
