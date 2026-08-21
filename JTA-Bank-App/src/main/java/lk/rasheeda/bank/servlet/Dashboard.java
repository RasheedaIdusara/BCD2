package lk.rasheeda.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.bank.ejb.remote.AccountService;
import lk.rasheeda.bank.entity.User;
import lk.rasheeda.bank.exception.AccountNotFoundException;

import java.io.IOException;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    @EJB
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user  = (User)req.getSession().getAttribute("user");
        java.lang.String email = user.getEmail();

        req.setAttribute("accounts",accountService.findUserByUserEmail(email));
        req.getRequestDispatcher("home.jsp").forward(req,resp);



    }
}
