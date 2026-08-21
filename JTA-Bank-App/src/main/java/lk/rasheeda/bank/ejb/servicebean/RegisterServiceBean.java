package lk.rasheeda.bank.ejb.servicebean;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.RegisterService;
import lk.rasheeda.bank.entity.AccountType;
import lk.rasheeda.bank.entity.User;
import lk.rasheeda.bank.exception.DuplicateEmailException;

import java.math.BigDecimal;
import java.rmi.RemoteException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RegisterServiceBean implements RegisterService {

    @PersistenceContext(unitName = "JTA-Bank-PU")
    private EntityManager em;

    @EJB
    private AccountService accountService;

    @Override
    public void register(String name, String email, String password,double openningbalance) throws DuplicateEmailException {

        long existing = em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList()
                .size();

        if (existing > 0) {
            throw new DuplicateEmailException(email);
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        em.persist(user);
        em.flush();

        accountService.createAccount(email, AccountType.SAVINGS, new BigDecimal(openningbalance));


    }
}
