package com.example.slides.withDIP;

import java.util.List;

public class Notification {

    private List<Message> messages;

    public Notification(List<Message> messages)
    {
        this.messages = messages;
    }

    public void send()
    {
        messages.forEach(Message::sendMessage);
    }
}
