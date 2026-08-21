package lk.rasheeda.bank.ejb.servicebean;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.transaction.*;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.TransactionLogService;
import lk.rasheeda.bank.ejb.remote.TransferService;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.entity.TransactionType;
import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransferServiceBean implements TransferService {

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private AccountService accountService;

    @EJB
    private TransactionLogService transactionLogService;

    @Override
    public void transferAmount(String sourceAccountNo, String destinationAccountNo, double amount)
            throws InsufficientFundsException, AccountNotFoundException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        if (sourceAccountNo.equals(destinationAccountNo)) {
            throw new IllegalArgumentException("Source account no can't be the destination account no.");
        }

        accountService.findByAccountNumber(sourceAccountNo);
        accountService.findByAccountNumber(destinationAccountNo);

        try {
            userTransaction.begin();
            try {

                accountService.debitToAccount(sourceAccountNo, BigDecimal.valueOf(amount));
                accountService.creditToAccount(destinationAccountNo, BigDecimal.valueOf(amount));
                userTransaction.commit();

            } catch (InsufficientFundsException e) {
                safeRollback();
                throw e;
            }catch (RuntimeException e) {
                safeRollback();
                throw e;
            }
        }catch (NotSupportedException | SystemException e) {
            throw new RuntimeException("Unable to perform transfer operation.",e);
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            throw new RuntimeException("Transfer Commit Failed",e);
        }

        Accounts source = accountService.findByAccountNumber(sourceAccountNo);
        Accounts destination = accountService.findByAccountNumber(destinationAccountNo);

        transactionLogService.Log(sourceAccountNo, TransactionType.DEBITED,amount,source.getBalance(),destinationAccountNo);
        transactionLogService.Log(destinationAccountNo, TransactionType.CREDITED,amount,destination.getBalance(),sourceAccountNo);


    }

    private void safeRollback() {
        try {
            userTransaction.rollback();
        } catch (SystemException e) {
            throw new RuntimeException("Rollback failed after Transaction error", e);
        }
    }
}
