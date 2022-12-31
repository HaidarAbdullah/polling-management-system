package com.example.raiseyourvoice.model;

public class CreatePollingRoomRequest {
    String room_identifier;
    String open_time;
    String close_time;
    String title;
    boolean is_conditional;
    String condition_description;

    public CreatePollingRoomRequest(String room_identifier, String open_time, String close_time, String title, boolean is_conditional, String condition_description) {
        this.room_identifier = room_identifier;
        this.open_time = open_time;
        this.close_time = close_time;
        this.title = title;
        this.is_conditional = is_conditional;
        this.condition_description = condition_description;
    }

    public String getRoom_identifier() {
        return room_identifier;
    }

    public void setRoom_identifier(String room_identifier) {
        this.room_identifier = room_identifier;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIs_conditional() {
        return is_conditional;
    }

    public void setIs_conditional(boolean is_conditional) {
        this.is_conditional = is_conditional;
    }

    public String getCondition_description() {
        return condition_description;
    }

    public void setCondition_description(String condition_description) {
        this.condition_description = condition_description;
    }
}
