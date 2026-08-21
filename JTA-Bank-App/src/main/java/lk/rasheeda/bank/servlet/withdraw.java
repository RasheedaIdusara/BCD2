package lk.rasheeda.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.WithdrawService;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.exception.AccountNotFoundException;
import lk.rasheeda.bank.exception.InsufficientFundsException;

import java.io.IOException;
import java.util.List;

@WebServlet("/withdraw")
public class withdraw extends HttpServlet {

    @EJB
    private AccountService accountService;

    @EJB
    private WithdrawService withdrawService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getSession().getAttribute("user").toString();
        List<Accounts> accounts = accountService.findUserByUserEmail(email);
        req.setAttribute("accounts",accounts);
        req.getRequestDispatcher("withdraw.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNo = req.getParameter("accountNo");
        double amount = Double.parseDouble(req.getParameter("ämount"));

        try {

            withdrawService.withdraw(accountNo, amount);
            resp.sendRedirect(req.getContextPath()+"/dashboard.jsp");

        }catch (InsufficientFundsException | AccountNotFoundException e){

            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("withdraw.jsp").forward(req,resp);

        }

    }
}
