package lk.rasheeda.ejb;

import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;
import jakarta.ejb.Stateless;

@Stateless
public class AutoTimerSessionBean {

    @Schedule(second = "0",minute = "0",hour = "*")
    public void checkSystemHealth(){
        System.out.println("Checking System Health...");
    }

    @Schedule(hour = "0",minute = "0",second = "0")
    public void purgeOldLogs(){
        System.out.println("Purging old logs...");
    }

    @Schedule(dayOfWeek = "Mon-Fri",hour = "8-17")
    public void generateHourlyReport(){
        System.out.println("Generating hourly reports...");
    }

    //@Schedule(dayOfMonth = "Last",hour = "23",minute = "59")
    @Schedule(dayOfMonth = "-1",hour = "23",minute = "59")
    public void processMonthlyBilling(){
        System.out.println("Processing monthly billing...");
    }

    @Schedule(dayOfMonth = "Last Fri")
    public void runOnLastFriday(){
        System.out.println("Running on the last friday...");
    }

    @Schedules({
            @Schedule(dayOfWeek = "Mon-Fri",hour = "8-17"),
            @Schedule(dayOfWeek = "Sat-Sun",hour = "12-17")

    })
    public void executeTask(){
        System.out.println("Executing task...");
    }

}
