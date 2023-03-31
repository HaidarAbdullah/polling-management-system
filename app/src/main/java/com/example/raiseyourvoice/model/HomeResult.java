package com.example.raiseyourvoice.model;

import java.util.ArrayList;

public class HomeResult {
boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<PollingRoom> getPollingRooms() {
        return pollingRooms;
    }

    public void setPollingRooms(ArrayList<PollingRoom> pollingRooms) {
        this.pollingRooms = pollingRooms;
    }

    String message;
ArrayList<PollingRoom> pollingRooms = new ArrayList<PollingRoom>();

    public HomeResult(boolean success, String message, ArrayList<PollingRoom> pollingRooms) {
        this.success = success;
        this.message = message;
        this.pollingRooms = pollingRooms;
    }
}
