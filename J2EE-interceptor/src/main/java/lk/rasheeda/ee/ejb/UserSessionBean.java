package lk.rasheeda.ee.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.PostActivate;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.Interceptors;
import jakarta.interceptor.InvocationContext;
import lk.rasheeda.ee.annotation.Logged;
import lk.rasheeda.ee.interceptor.AInterceptor;
import lk.rasheeda.ee.interceptor.TestInterceptor;

@Logged
@Singleton
public class UserSessionBean {

    @PostConstruct
    public void init() {
        System.out.println("sessionbean init");
    }

    public String doAction(String name, int age) {

        System.out.println("UserSessionBean doAction Start...");

        System.out.println("UserSessionBean doAction : " + name + " | " + age);

        System.out.println("UserSessionBean doAction End...");


        return "Success";

    }


//    @AroundInvoke
//    public Object intercepter(InvocationContext ic) throws Exception {
//
//        System.out.println("UserSessionBeanIntercepter  start");
//        java.lang.Object proceed = ic.proceed();
//        System.out.println("UserSessionBeanIntercepter  end");
//        return proceed;
//
//    }
//
//    @AroundInvoke
//    public Object intercepter1(InvocationContext ic) throws Exception {
//
//        System.out.println("UserSessionBeanIntercepter 1 start");
//        java.lang.Object proceed = ic.proceed();
//        System.out.println("UserSessionBeanIntercepter 1 end");
//        return proceed;
//
//    }


}
