package com.example.dependencyinversion.slides.withoutDIP;

public class SMS {

    public String PhoneNumber;
    public String Message;

    public void SendSMS()
    {
        //Send sms
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
