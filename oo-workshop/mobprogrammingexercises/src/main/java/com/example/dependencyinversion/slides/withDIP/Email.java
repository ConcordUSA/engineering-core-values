package com.example.dependencyinversion.slides.withDIP;

import com.example.dependencyinversion.slides.withDIP.Message;

public class Email implements Message {

    public String Address;
    public String Subject;
    public String Content;

    @Override
    public void sendMessage()
    {
        sendEmail();
    }

    private void sendEmail()
    {
        //Send email
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
