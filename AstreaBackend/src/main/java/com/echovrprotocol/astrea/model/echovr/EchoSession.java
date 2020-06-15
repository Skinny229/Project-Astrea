package com.echovrprotocol.astrea.model.echovr;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
public class EchoSession {

    private long sessionid;

    private String game_clock_display;
    private String game_status;

    private String blue_points;
    private String orange_points;

    private Player[] players;


    //Getters
    public long getSessionid() {
        return sessionid;
    }

    public String getGame_clock_display() {
        return game_clock_display;
    }

    public String getGame_status() {
        return game_status;
    }

    public String getBlue_points() {
        return blue_points;
    }

    public String getOrange_points() {
        return orange_points;
    }

    public Player[] getPlayers() {
        return players;
    }

    //Setters
    public void setSessionid(long sessionid) {
        this.sessionid = sessionid;
    }

    public void setGame_clock_display(String game_clock_display) {
        this.game_clock_display = game_clock_display;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }

    public void setBlue_points(String blue_points) {
        this.blue_points = blue_points;
    }

    public void setOrange_points(String orange_points) {
        this.orange_points = orange_points;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
