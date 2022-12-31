package com.example.raiseyourvoice.RVmodels;

public class Room {
    public int id ;
    public String room_identifier;
    public String open_time;
    public String close_time;
    public String title;

    public Room(int id, String room_identifier, String open_time, String close_time, String title) {
        this.id = id;
        this.room_identifier = room_identifier;
        this.open_time = open_time;
        this.close_time = close_time;
        this.title = title;
    }
}
