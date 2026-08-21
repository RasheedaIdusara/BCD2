package lk.rasheeda.bank.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class DuplicateEmailException extends Exception{

    public DuplicateEmailException(String email){
        super("An Account with the email "+email+" already exists");
    }

}
