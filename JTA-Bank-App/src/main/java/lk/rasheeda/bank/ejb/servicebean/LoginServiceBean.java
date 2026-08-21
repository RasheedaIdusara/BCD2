package lk.rasheeda.bank.ejb.servicebean;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.rasheeda.bank.ejb.remote.LoginService;
import lk.rasheeda.bank.entity.User;

@Stateless
public class LoginServiceBean implements LoginService {

    @PersistenceContext(unitName = "JTA-Bank-PU")
    private EntityManager em;


    @Override
    public boolean login(String email, String password) {

        try {

            em.createNamedQuery("User.findByEmailAndPassword"
                            , User.class).setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();

            return true;

        } catch (Exception e) {
            return false;
        }


    }

    @Override
    public User findByEmail(String email) {

        try {

            return

                    em.createNamedQuery("User.findByEmail"
                                    , User.class).setParameter("email", email)
                            .getSingleResult();


        } catch (Exception e) {
            return null;
        }


    }
}
