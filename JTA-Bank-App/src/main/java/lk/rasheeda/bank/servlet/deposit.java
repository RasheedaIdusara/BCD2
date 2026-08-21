package lk.rasheeda.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.ejb.remote.DepositService;
import lk.rasheeda.bank.entity.Accounts;
import lk.rasheeda.bank.exception.AccountNotFoundException;

import java.io.IOException;
import java.util.List;

@WebServlet("/deposit")
public class deposit extends HttpServlet {

    @EJB
    private AccountService accountService;

    @EJB
    private DepositService depositService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getSession().getAttribute("user").toString();

        List<Accounts> accounts = accountService.findUserByUserEmail(user);
        req.setAttribute("accounts",accounts);
        req.getRequestDispatcher("deposit.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNo = req.getParameter("accountNo");
        double amount = Double.parseDouble(req.getParameter("amount"));

        try {

            depositService.deposit(accountNo,amount);
            resp.sendRedirect(req.getContextPath() + "/dashboard");

        }catch (AccountNotFoundException e){

            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("deposit.jsp").forward(req,resp);

        }
    }
}
