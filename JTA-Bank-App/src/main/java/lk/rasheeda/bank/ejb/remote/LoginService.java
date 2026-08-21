package lk.rasheeda.bank.ejb.remote;

import jakarta.ejb.Local;
import lk.rasheeda.bank.entity.User;

@Local
public interface LoginService {

    boolean login(String email, String password);
    User findByEmail(String email);

}
