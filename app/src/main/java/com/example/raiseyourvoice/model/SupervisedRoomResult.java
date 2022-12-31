package com.example.raiseyourvoice.model;

import java.util.ArrayList;

public class SupervisedRoomResult {
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

    public ArrayList<SupervisedRoom> getPollingRooms() {
        return supervisedRooms;
    }

    public void setPollingRooms(ArrayList<SupervisedRoom> supervisedRooms) {
        this.supervisedRooms = supervisedRooms;
    }

    String message;
    ArrayList<SupervisedRoom> supervisedRooms = new ArrayList<SupervisedRoom>();

    public SupervisedRoomResult(boolean success, String message, ArrayList<SupervisedRoom> supervisedRooms) {
        this.success = success;
        this.message = message;
        this.supervisedRooms = supervisedRooms;
    }

}
