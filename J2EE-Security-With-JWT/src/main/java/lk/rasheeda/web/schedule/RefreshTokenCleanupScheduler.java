package lk.rasheeda.web.schedule;

import jakarta.ejb.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.logging.Logger;

public class RefreshTokenCleanupScheduler {

    private static final Logger LOGGER
            = Logger.getLogger(RefreshTokenCleanupScheduler.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Schedule(hour = "23",minute = "59",persistent = false)
    @Transactional
    public void ScheduleCleanup(){

        int deleted = em.createNamedQuery("RefreshToken.deleteExpiredToken")
                .setParameter("now", Instant.now())
                .executeUpdate();

        LOGGER.info( "Refresh token cleanup :" +deleted+" refresh token has been deleted");

    }

}
