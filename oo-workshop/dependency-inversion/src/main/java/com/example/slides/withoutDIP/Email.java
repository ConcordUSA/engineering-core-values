package com.example.slides.withoutDIP;

public class Email {

    public String Address;
    public String Subject;
    public String Content;

    public void SendEmail()
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
