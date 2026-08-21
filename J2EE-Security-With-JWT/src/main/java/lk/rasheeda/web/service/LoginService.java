package lk.rasheeda.web.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import lk.rasheeda.web.entity.User;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;


@RequestScoped
public class LoginService {

    @PersistenceContext
    private EntityManager em;

    public Optional<User> findByEmail(String email){
        try{
            return Optional.of( em.createNamedQuery("User.findByEmail", User.class)
                    .setParameter("email", email)
                    .getSingleResult());

        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Transactional
    public void register(String name, String email, String password, Set<String> roles) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        em.persist(user);
    }

    public boolean validate(String email, String password) {
        return findByEmail(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    public Set<String> getRoles(String email){
       return findByEmail(email)
               .map(user -> user.getRoles()).orElse(Collections.emptySet());
    }

}

