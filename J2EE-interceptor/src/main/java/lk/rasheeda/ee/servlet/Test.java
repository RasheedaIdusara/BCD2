package lk.rasheeda.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.ee.ejb.UserSessionBean;

import java.io.IOException;

@WebServlet("/test")
public class Test extends HttpServlet {

    @EJB
    private UserSessionBean userSessionBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet start...");
        java.lang.String action = userSessionBean.doAction("Rasheeeda", 23);
        System.out.println("TestServlet end..." + action);
    }
}
