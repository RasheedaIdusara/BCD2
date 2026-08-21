package lk.rasheeda.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.jms.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.rasheeda.bank.ejb.remote.LoginService;
import lk.rasheeda.bank.ejb.remote.RegisterService;
import lk.rasheeda.bank.entity.User;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    @EJB
    private LoginService loginService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        java.lang.String email = req.getParameter("email");
        java.lang.String password = req.getParameter("password");

        boolean login = loginService.login(email,password);

//        if(login){
//            req.setAttribute("message","Login Successfully");
//            req.getRequestDispatcher("home.jsp").forward(req,resp);
//        }else {
//            req.setAttribute("message","Login Failed");
//            req.getRequestDispatcher("login.jsp").forward(req,resp);
//        }

        if(login){

            User user = loginService.findByEmail(email);
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            session.setAttribute("username",user.getName());

            resp.sendRedirect(req.getContextPath() + "/dashboard" );

        }else {

            req.setAttribute("error","Invalid email or password");
            req.getRequestDispatcher("login.jsp").forward(req,resp);

        }

    }
}
