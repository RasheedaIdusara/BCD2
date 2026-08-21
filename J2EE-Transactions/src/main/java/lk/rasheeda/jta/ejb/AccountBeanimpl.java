package lk.rasheeda.jta.ejb;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.*;
import lk.rasheeda.jta.entity.Account;

@Stateless
public class AccountBeanimpl implements AccountBean {

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Override
    public void creditAmount(Integer accountNumber, Double amount) {


        EntityTransaction transaction = em.getTransaction();
        System.out.println("creditamount : " + System.identityHashCode(transaction));

        try {

            Account account = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber",
                    Account.class).setParameter("accountNumber", accountNumber).getSingleResult();


            account.setBalance(account.getBalance() + amount);

        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public void debitAmount(Integer accountNumber, Double amount) {


        EntityTransaction transaction = em.getTransaction();
        System.out.println("debitamount : " + System.identityHashCode(transaction));

        try {

            Account account = (Account) em.createNamedQuery("Account.findbyAccountNo")
                    .setParameter("accountNumber", accountNumber).getSingleResult();

//            if(account.getBalance() < amount){
//                throw new RuntimeException("Insufficient Balance");
//            }

            account.setBalance(account.getBalance() - amount);

        } catch (NoResultException e) {
            e.printStackTrace();
        }

    }
}
