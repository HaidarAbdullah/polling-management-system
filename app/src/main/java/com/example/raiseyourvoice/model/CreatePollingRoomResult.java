package com.example.raiseyourvoice.model;

public class CreatePollingRoomResult {
    boolean success;
    String message;
    int room_id;

    public CreatePollingRoomResult(boolean success, String message, int room_id) {
        this.success = success;
        this.message = message;
        this.room_id = room_id;
    }

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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
