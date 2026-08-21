package lk.rasheeda.ee.ejb;

import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.interceptor.Interceptors;
import lk.rasheeda.ee.interceptor.TimerIntercepter;

@Stateless
public class TimerSessionBean {
    @Schedule(hour = "*",minute = "*",info = "Generating Report")
    @Interceptors({TimerIntercepter.class})
    public void generateReport() {
        System.out.println("TimerSessionBean : generateReport...");
    }
}
