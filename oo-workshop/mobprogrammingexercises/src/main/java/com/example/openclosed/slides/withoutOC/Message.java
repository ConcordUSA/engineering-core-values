package com.example.openclosed.slides.withoutOC;

public class Message {
    private final String message;

    public Message(String message){
        this.message = message;
    }

    public String sendEmail(){
        return "Email Message: " + this.message;
    }

    public String sendSMS() {
        return "SMS Message: " + this.message;
    }
}

