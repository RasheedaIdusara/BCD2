package lk.rasheeda.ee.interceptor;

import jakarta.annotation.PostConstruct;
import jakarta.interceptor.AroundConstruct;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Constructor;

public class AInterceptor {

    @AroundConstruct
    public Object aroundConstruct(InvocationContext ctx) throws Exception {
       java.lang.reflect.Constructor constructor =  ctx.getConstructor();
        System.out.println("AInterceptor AroundConstruct:"+constructor);

        return constructor.newInstance();
    }

    @PostConstruct
    public void init(InvocationContext ic) throws Exception {
        System.out.println("AInterceptor init");
        ic.proceed();
    }

    @AroundInvoke
    public Object m(InvocationContext ic) throws Exception {

        System.out.println("AInterceptor m start");
        java.lang.Object proceed = ic.proceed();
        System.out.println("AInterceptor m end");
        return proceed;

    }

}
