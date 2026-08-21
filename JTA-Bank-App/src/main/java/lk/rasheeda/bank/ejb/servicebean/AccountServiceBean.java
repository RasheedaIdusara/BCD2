package lk.rasheeda.bank.ejb.servicebean;

import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.LoginService;
import lk.rasheeda.bank.entity.AccountType;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.entity.TransactionType;
import lk.rasheeda.bank.entity.User;
import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Stateless
public class AccountServiceBean implements AccountService {

    @PersistenceContext(unitName = "JTA-Bank-PU")
    private EntityManager em;

    @EJB
    LoginService loginService;


    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void creditToAccount(String accountNumber, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        try {

             lk.rasheeda.bank.entity.Accounts accounts = em.createNamedQuery("Account.findByAccountNo", Accounts.class).
                    setParameter("accountNo", accountNumber).
                    getSingleResult();

             accounts.setBalance(accounts.getBalance() + amount.doubleValue());

             em.merge(accounts);

        }catch (NoResultException e) {
            throw new EJBException("Account not found" + accountNumber, e);
        }

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void debitToAccount(String accountNumber, BigDecimal amount) throws InsufficientFundsException {

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        try {

            lk.rasheeda.bank.entity.Accounts accounts = em.createNamedQuery("Account.findByAccountNo",Accounts.class).
                    setParameter("accountNo",accountNumber).
                    getSingleResult();

            if(accounts.getBalance() <  amount.doubleValue()){
                throw new InsufficientFundsException(accountNumber,amount,BigDecimal.valueOf(accounts.getBalance()));
            }

            accounts.setBalance(accounts.getBalance() - amount.doubleValue());
            em.merge(accounts);

        }catch (NoResultException e) {
            throw new EJBException("Account not found" + accountNumber, e);
        }

    }

    @Override
    public Accounts findByAccountNumber(String accountNumber) throws AccountNotFoundException {

        try {

           return em.createNamedQuery("Account.findByAccountNo", Accounts.class).
                    setParameter("accountNo", accountNumber).
                    getSingleResult();

        } catch (NoResultException e) {
            throw new AccountNotFoundException(accountNumber);
        }


    }

    @Override
    public List<Accounts> findUserByUserEmail(String email) {
        return em.createNamedQuery("Account.findByUserEmail", Accounts.class).
                setParameter("email", email).
                getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Accounts createAccount(String email, AccountType type, BigDecimal openbalance) {
        User user = loginService.findByEmail(email);
        if (user == null) {
            throw new EJBException("Cannot find user with email " + email);
        }

        Accounts account = new Accounts();
        account.setAccountType(type);
        account.setAccNo(generateAccountNumber(AccountType.SAVINGS));
        account.setBalance(openbalance.doubleValue());
        account.setUser(user);
        em.persist(account);

        return account;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String generateAccountNumber(AccountType type) {

        int branchCode = ThreadLocalRandom.current().nextInt(1, 999);
        int typeCode = (type == AccountType.CURRENT) ? 2 : 1;
        long serial = System.currentTimeMillis() % 1000_000_000L;

        return String.format("%03d%d%d08d", branchCode, typeCode, serial);
    }
}
