package com.queueapp.model;

public class Ticket {
    private String id;
    private String queueType;
    private int number;

    public Ticket() {}

    public Ticket(String id, String queueType, int number) {
        this.id = id;
        this.queueType = queueType;
        this.number = number;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getQueueType() { return queueType; }
    public void setQueueType(String queueType) { this.queueType = queueType; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
}