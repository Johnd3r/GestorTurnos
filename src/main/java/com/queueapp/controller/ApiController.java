package com.queueapp.controller;

import com.queueapp.model.Ticket;
import com.queueapp.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/tickets/{queue}")
    public Ticket generateTicket(@PathVariable String queue) {
        if (!queue.equals("A") && !queue.equals("C")) {
            throw new IllegalArgumentException("Invalid queue type");
        }
        return queueService.generateTicket(queue);
    }

    @PostMapping("/tickets/{queue}/next")
    public Ticket callNext(@PathVariable String queue) {
        if (!queue.equals("A") && !queue.equals("C")) {
            throw new IllegalArgumentException("Invalid queue type");
        }
        
        Ticket ticket = queueService.callNext(queue);
        if (ticket != null) {
            messagingTemplate.convertAndSend("/topic/queue/" + queue, ticket);
        }
        return ticket;
    }

    @GetMapping("/state")
    public Map<String, Object> getState() {
        return queueService.getState();
    }
}