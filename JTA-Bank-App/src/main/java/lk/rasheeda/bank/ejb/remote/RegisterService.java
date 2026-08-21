package lk.rasheeda.bank.ejb.remote;

import jakarta.ejb.Local;
import lk.rasheeda.bank.exception.DuplicateEmailException;

import java.rmi.RemoteException;

@Local
public interface RegisterService {
    void register(String username, String email, String password , double openningbalance) throws DuplicateEmailException;
}
