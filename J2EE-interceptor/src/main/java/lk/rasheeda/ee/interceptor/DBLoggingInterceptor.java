package lk.rasheeda.ee.interceptor;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.rasheeda.ee.annotation.Logged;

@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION+2)
public class DBLoggingInterceptor {
    @AroundInvoke
    public Object Intercept(InvocationContext ctx) throws Exception {
        System.out.println("Logging Intercepter start...");
        Object proceed = ctx.proceed();
        System.out.println("Logging Intercepter end...");
        return proceed;
    }
}
