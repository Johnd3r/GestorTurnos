package com.queueapp.config;

import com.queueapp.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerConfig {

    @Autowired
    private QueueService queueService;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetCountersDaily() {
        queueService.resetCounters();
        System.out.println("Daily reset executed at midnight");
    }
}