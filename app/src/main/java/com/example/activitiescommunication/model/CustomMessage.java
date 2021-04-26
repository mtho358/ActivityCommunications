package com.example.activitiescommunication.model;

import java.io.Serializable;

public class CustomMessage implements Serializable {

    private String timeStamp;
    private String message;

    public CustomMessage(String timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "timeStamp='" + timeStamp + '\'' +
                ", message='" + message + '\'' +
                '}';
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
}
