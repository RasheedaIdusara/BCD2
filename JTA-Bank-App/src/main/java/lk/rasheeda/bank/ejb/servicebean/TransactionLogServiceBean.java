package lk.rasheeda.bank.ejb.servicebean;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.rasheeda.bank.ejb.remote.TransactionLogService;
import lk.rasheeda.bank.entity.Transaction;
import lk.rasheeda.bank.entity.TransactionType;

import java.util.List;

@Stateless
public class TransactionLogServiceBean implements TransactionLogService {

    @PersistenceContext(unitName = "JTA-Bank-PU")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void Log(String accountNumber, TransactionType type, double amount, double balanceAfter, String relatedAccountNo) {

       Transaction transaction = new Transaction(accountNumber,type,amount,balanceAfter,relatedAccountNo);
       em.persist(transaction);

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Transaction> history(String accountNo) {
        return em.createNamedQuery("Transaction.findByAccountNo", Transaction.class).
                setParameter("accountNo", accountNo).
                getResultList();
    }
}
