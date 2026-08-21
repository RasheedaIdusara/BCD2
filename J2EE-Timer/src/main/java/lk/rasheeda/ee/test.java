package lk.rasheeda.ee;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(value = "/test",asyncSupported = true)
public class test extends HttpServlet {


    //ExecutorService executor = Executors.newFixedThreadPool(5); JVM Managed Pool

    @Resource
    //private ManagedExecutorService executorService; Container Managed Pool This is good option

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Start:"+Thread.currentThread().getName());
        AsyncContext async = request.startAsync();
        async.setTimeout(1000);
        async.start(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            async.complete();
        });
        System.out.println("End:"+Thread.currentThread().getName());
    }
}
