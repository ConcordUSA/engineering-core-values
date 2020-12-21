package com.example.dependencyinversion.slides.withDIP;

public class SMS implements com.example.dependencyinversion.slides.withDIP.Message {

    public String PhoneNumber;
    public String Message;

    @Override
    public void sendMessage()
    {
        sendSMS();
    }

    private void sendSMS()
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
