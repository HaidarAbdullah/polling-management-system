package com.example.raiseyourvoice.model;

public class PollingRoom {
    int id;
    String room_identifier;
    int creator_id;
    String open_time;
    String close_time;
    boolean is_conditional;
    int condition_type;
    String condition_desc;
    String Title;

    public PollingRoom(int id, String room_identifier, int creator_id, String open_time, String close_time, boolean is_conditional, int condition_type, String condition_desc, String title) {
        this.id = id;
        this.room_identifier = room_identifier;
        this.creator_id = creator_id;
        this.open_time = open_time;
        this.close_time = close_time;
        this.is_conditional = is_conditional;
        this.condition_type = condition_type;
        this.condition_desc = condition_desc;
        Title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_identifier() {
        return room_identifier;
    }

    public void setRoom_identifier(String room_identifier) {
        this.room_identifier = room_identifier;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
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

    public boolean isIs_conditional() {
        return is_conditional;
    }

    public void setIs_conditional(boolean is_conditional) {
        this.is_conditional = is_conditional;
    }

    public int getCondition_type() {
        return condition_type;
    }

    public void setCondition_type(int condition_type) {
        this.condition_type = condition_type;
    }

    public String getCondition_desc() {
        return condition_desc;
    }

    public void setCondition_desc(String condition_desc) {
        this.condition_desc = condition_desc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
