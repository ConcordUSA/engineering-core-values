package com.example.slides.withoutDIP;

public class Notification {

    private Email email = new Email();
    private SMS sms = new SMS();

    public void send()
    {
        email.SendEmail();
        sms.SendSMS();
    }
}




