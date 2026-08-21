package lk.rasheeda.bank.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String accountNo, BigDecimal requestedAmount, BigDecimal availableAmount) {
        super("Insufficient funds for account " + accountNo + ": " + requestedAmount + "but only" + availableAmount);
    }

}
