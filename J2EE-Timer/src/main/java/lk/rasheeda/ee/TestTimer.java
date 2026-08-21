package lk.rasheeda.ee;

import jakarta.ejb.EJB;
import jakarta.ejb.TimerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.ejb.Task;
import lk.rasheeda.ejb.TimerSessionBean;

import java.io.IOException;

@WebServlet("/timertest")
public class TestTimer extends HttpServlet {

    @EJB
    private TimerSessionBean timerSessionBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Task task = timerSessionBean.createTimer();

        resp.getWriter().write("Task created :" + task.getTaskId());

    }
}