package lk.rasheeda.ee;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.ejb.TimerSessionBean;

import java.io.IOException;

@WebServlet("/canceltimer")
public class CancelTimer extends HttpServlet {

    @EJB
    private TimerSessionBean timerSessionBean;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String taskId = req.getParameter("taskId");


        timerSessionBean.cancelTimer(taskId);
    }

}
