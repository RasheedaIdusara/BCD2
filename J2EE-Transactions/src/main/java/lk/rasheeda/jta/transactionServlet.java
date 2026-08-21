package lk.rasheeda.jta;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.jta.ejb.UserBean;

import java.io.IOException;

@WebServlet("/transaction")
public class transactionServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userBean.transferMoney(101010,111111, 5000.0);
    }
}
