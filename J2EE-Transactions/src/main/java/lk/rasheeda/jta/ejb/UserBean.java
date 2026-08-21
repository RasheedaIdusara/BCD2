package lk.rasheeda.jta.ejb;

import jakarta.ejb.Local;

@Local
public interface UserBean {

    boolean login(String username,String password);
    boolean register(String email,String name,String password);

    void transferMoney(Integer fromAccountNumber, Integer toAccountNumber, Double amount);

}
