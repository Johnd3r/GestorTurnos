package com.queueapp.service;

import com.queueapp.model.Ticket;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QueueService {
    private final Map<String, Integer> counters = new ConcurrentHashMap<>();
    private final Map<String, Queue<Ticket>> queues = new ConcurrentHashMap<>();
    private final Map<String, Ticket> currentTickets = new ConcurrentHashMap<>();
    private final Map<String, List<Ticket>> history = new ConcurrentHashMap<>();

    public QueueService() {
        resetCounters();
        queues.put("A", new LinkedList<>());
        queues.put("C", new LinkedList<>());
        history.put("A", new ArrayList<>());
        history.put("C", new ArrayList<>());
    }

    public Ticket generateTicket(String queueType) {
        int nextNumber = counters.compute(queueType, (k, v) -> v + 1);
        String ticketId = queueType + "-" + String.format("%03d", nextNumber);
        
        Ticket ticket = new Ticket(ticketId, queueType, nextNumber);
        queues.get(queueType).offer(ticket);
        
        return ticket;
    }

    public Ticket callNext(String queueType) {
        Queue<Ticket> queue = queues.get(queueType);
        if (queue.isEmpty()) {
            return null;
        }
        
        Ticket ticket = queue.poll();
        currentTickets.put(queueType, ticket);
        
        List<Ticket> queueHistory = history.get(queueType);
        queueHistory.add(0, ticket);
        if (queueHistory.size() > 3) {
            queueHistory.remove(queueHistory.size() - 1);
        }
        
        return ticket;
    }

    public Map<String, Object> getState() {
        Map<String, Object> state = new HashMap<>();
        state.put("currentTickets", currentTickets);
        state.put("history", history);
        state.put("queueSizes", Map.of("A", queues.get("A").size(), "C", queues.get("C").size()));
        return state;
    }

    public void resetCounters() {
        counters.put("A", 0);
        counters.put("C", 0);
        queues.put("A", new LinkedList<>());
        queues.put("C", new LinkedList<>());
        currentTickets.clear();
        history.put("A", new ArrayList<>());
        history.put("C", new ArrayList<>());
    }
}