package lk.rasheeda.bank.ejb.servicebean;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.DepositService;
import lk.rasheeda.bank.ejb.remote.TransactionLogService;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.entity.TransactionType;
import lk.rasheeda.bank.exception.AccountNotFoundException;

import java.math.BigDecimal;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DepositServiceBean implements DepositService {

    @EJB
    private AccountService accountService;

    @EJB
    private TransactionLogService transactionLogService;

    @Override
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        accountService.creditToAccount(accountNumber, BigDecimal.valueOf(amount));
        Accounts accounts = accountService.findByAccountNumber(accountNumber);
        transactionLogService.Log(accountNumber, TransactionType.DEPOSIT,amount, accounts.getBalance(),null);
    }
}
