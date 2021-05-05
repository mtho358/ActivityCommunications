package com.example.activitiescommunication.model;

import java.io.Serializable;

public class CustomMessage implements Serializable {

    private String timeStamp;
    private String senderName;
    private String message;

    public CustomMessage(String timeStamp, String senderName, String message) {
        this.timeStamp = timeStamp;
        this.senderName = senderName;
        this.message = message;
    }

    @Override
    public String toString() {
        return timeStamp + " " + senderName + ": " + message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
