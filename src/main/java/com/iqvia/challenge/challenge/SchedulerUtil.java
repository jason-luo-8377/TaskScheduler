package com.iqvia.challenge.challenge;

import java.util.Date;

public class SchedulerUtil {
    public static boolean isDeliveryTimeFuture(Date deliveryTime) {
        if(System.currentTimeMillis() <= deliveryTime.getTime()) {
            return true;
        } else {
            return false;
        }
    }
}
