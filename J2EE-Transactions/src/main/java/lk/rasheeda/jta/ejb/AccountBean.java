package lk.rasheeda.jta.ejb;

public interface AccountBean {
    void creditAmount(Integer accountNumber, Double amount);
    void debitAmount(Integer accountNumber, Double amount);
}
