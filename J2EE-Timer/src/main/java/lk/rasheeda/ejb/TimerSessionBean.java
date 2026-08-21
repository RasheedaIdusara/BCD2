package lk.rasheeda.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;

import java.io.Serializable;
import java.util.UUID;

@Stateless
public class TimerSessionBean{

    @Resource
    private TimerService timerService;

    public Task createTimer(){
        //System.out.println(timerService);
        //timerService.createTimer(10000L,5000L,"Test Timer");
        TimerConfig timerConfig = new TimerConfig();

        String taskID = UUID.randomUUID().toString();

        Task task = new Task(taskID,"Simple Task");

        timerConfig.setInfo(task);
        timerConfig.setPersistent(true);

        //timerService.createSingleActionTimer(10000L,timerConfig);

        ScheduleExpression se = new ScheduleExpression();

        se.month("*/2");

        se.hour("*");
        se.minute("*");
        se.second("10");

        timerService.createCalendarTimer(se,timerConfig);

        return task;
    }

    @Timeout
    public void task(){
        System.out.println("Test Timer Task...");
    }

    public void cancelTimer(String taskID){
        timerService.getAllTimers().forEach((timer)->{
//
//            if(timer.getInfo().equals("Test Timer")){
//                timer.cancel();
//            }
           Serializable info = timer.getInfo();
            if(info instanceof Task && ((Task)info).getTaskId().equals(taskID)){
                timer.cancel();
            }

        });
    }



}
