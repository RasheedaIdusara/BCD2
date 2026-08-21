package lk.rasheeda.jta;

import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rasheeda.jta.ejb.UserBean;

import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class Test extends HttpServlet {

    @EJB
    private UserBean userBean;

//    @PersistenceContext(unitName = "JTA-PU")
//    private EntityManager em;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userBean.register("rasheeda@gmail.com","rasheeda","123");

//        EntityManager em = ManagerFactory.getEntityManager();

//        List<Object[]> resultList
//                = em.createNativeQuery("select * from user").getResultList();
//
//        resultList.forEach(r->{
//            System.out.println(r[0] + " " + r[1]);
//        });




    }
}
