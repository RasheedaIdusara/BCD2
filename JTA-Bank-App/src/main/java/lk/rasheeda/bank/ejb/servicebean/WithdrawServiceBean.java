package lk.rasheeda.bank.ejb.servicebean;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.TransactionLogService;
import lk.rasheeda.bank.ejb.remote.WithdrawService;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.entity.TransactionType;
import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class WithdrawServiceBean implements WithdrawService {

    @EJB
    private AccountService accountService;

    @EJB
    private TransactionLogService transactionLogService;

    @Override
    public void withdraw(String accountNo, double amount) throws InsufficientFundsException,
            AccountNotFoundException {

        accountService.debitToAccount(accountNo, BigDecimal.valueOf(amount));

        Accounts accounts = accountService.findByAccountNumber(accountNo);

        transactionLogService.Log(accountNo, TransactionType.WITHDRAWAL,
                amount, accounts.getBalance(), null);

    }
}
