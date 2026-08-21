package lk.rasheeda.ee.interceptor;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.interceptor.AroundConstruct;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Constructor;

public class TestInterceptor {

    @AroundConstruct
    public void AroundConstruct(InvocationContext ic) throws Exception {
        System.out.println("TestInterceptor AroundConstruct");
        Constructor<?> constructor = ic.getConstructor();
        System.out.println("TestIntercepterConstructor AroundConstruct :"+constructor);
        ic.proceed();
    }

    public TestInterceptor() {
        System.out.println("TestInterceptor constructor");
    }

    @PostConstruct
    public void init(InvocationContext invocationContext) throws Exception {
        System.out.println("TestInterceptor init");
        invocationContext.proceed();
    }

    @AroundInvoke
    Object m(InvocationContext invocationContext) throws Exception {
        System.out.println("TestInterceptor m() start...");

        Object[] parameters = invocationContext.getParameters();
        for (Object parameter : parameters) {
            System.out.println("Parameter:"+parameter);
        }

        parameters[0]="supun";
        invocationContext.setParameters(parameters);

        Object proceed = invocationContext.proceed();
        System.out.println("TestInterceptor m() end...");
        return proceed;
    }

    @PreDestroy
    public void preDestroy(InvocationContext ic) throws Exception {
        System.out.println("TestInterceptor preDestroy");
    }

}
