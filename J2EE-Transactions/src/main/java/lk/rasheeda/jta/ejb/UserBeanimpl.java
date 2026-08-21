package lk.rasheeda.jta.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import lk.rasheeda.jta.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBeanimpl implements UserBean {

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

    @EJB
    private AccountBean accountBean;

//    @Resource
//    private UserTransaction utx;

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public boolean register(String email, String name, String password) {

        // Session session = em.unwrap(Session.class);

        //Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);

//        try {
//            utx.begin();
        em.persist(user);
//            utx.commit();
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }


        //session.save(user);

        //transaction.commit();

        return true;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public void transferMoney(Integer fromAccountNumber, Integer toAccountNumber, Double amount) {

        EntityTransaction transaction = em.getTransaction();
        System.out.println("transfer : "+System.identityHashCode(transaction));

        //transaction.commit();

        accountBean.debitAmount(fromAccountNumber,amount);
        accountBean.creditAmount(toAccountNumber,amount);

    }
}
