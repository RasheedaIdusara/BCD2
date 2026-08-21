package lk.rasheeda.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.bank.ejb.remote.RegisterService;
import lk.rasheeda.bank.exception.DuplicateEmailException;

import java.io.IOException;
import java.rmi.RemoteException;

@WebServlet("/register")
public class Register extends HttpServlet {

    private static final double DEFAULT_OPENING_BALENCE = 1000.00;

    @EJB
    private RegisterService registerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            registerService.register(name, email, password,DEFAULT_OPENING_BALENCE);
            req.setAttribute("message","Register Successfully");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }catch(DuplicateEmailException e){
            req.setAttribute("message","Register Failed");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }

    }
}
