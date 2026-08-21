package lk.rasheeda.ee.interceptor;

import jakarta.ejb.Timer;
import jakarta.interceptor.AroundTimeout;
import jakarta.interceptor.InvocationContext;

public class TimerIntercepter {

    @AroundTimeout
    public Object aroundTimeout(InvocationContext ic) throws Throwable {
        System.out.println("TestInterceptor aroundTimeout");
        Timer timer = (Timer) ic.getTimer();
        System.out.println("Timer info: " + timer.getInfo());
        System.out.println("Timer Timeout: " + timer.getNextTimeout());
        return ic.proceed();
    }

}
