package com.echovrprotocol.astrea.model.lfg;


import com.echovrprotocol.astrea.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@JsonFormat
public class LFGLobby {



    private long lfgLobbyId;

    private LobbyType type;

    private int maxPlayers;

    private ArrayList<User> players;

    //Getters

    //custom gets
    public User getLeader(){
        return players.get(0);
    }

    public int getPlayerCount(){
        return players.size();
    }

    //Generic Gets
    public long getLfgLobbyId() {
        return lfgLobbyId;
    }

    public LobbyType getType() {
        return type;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }



    //Setters
    public void setLfgLobbyId(long lfgLobbyId) {
        this.lfgLobbyId = lfgLobbyId;
    }

    public void setType(LobbyType type) {
        this.type = type;
    }

    public void addUser(User user){
        this.players.add(user);
    }

    public void removeUser(User user){
        this.players.remove(user);

    }
    public void setMaxPlayers(int max){
        this.maxPlayers = max;
    }



}
