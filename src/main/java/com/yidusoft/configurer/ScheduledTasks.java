package com.yidusoft.configurer;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by L on 2017/10/18.
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

   /* @Resource
    private OrderOnlineService orderOnlineService;

    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime() {
        OrderOnline orderOnline = new OrderOnline();
        orderOnline.setOrderState(0);
        orderOnline.setUserId(Security.getUserId());
        List<OrderOnline> list = orderOnlineService.getUserOderById(orderOnline);
        for (OrderOnline order : list){
            System.out.print(order.getOrderState());
        }
            System.out.println("Scheduling Tasks Examples: The time is now " + dateFormat().format(new Date()));
    }

    //每1分钟执行一次
    @Scheduled(cron = "0 1 *  * * * ")
    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
*/
}