package lk.rasheeda.bank.ejb.remote;

import jakarta.ejb.Local;
import lk.rasheeda.bank.entity.AccountType;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.DuplicateEmailException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.List;

@Local
public interface AccountService {

    void creditToAccount(String accountNumber, BigDecimal amount );
    void debitToAccount(String accountNumber, BigDecimal amount ) throws InsufficientFundsException;
    Accounts findByAccountNumber(String accountNumber) throws AccountNotFoundException;
    List<Accounts> findUserByUserEmail(String email);
    Accounts createAccount(String email, AccountType type , BigDecimal openbalance);
    String generateAccountNumber(AccountType type);
}
