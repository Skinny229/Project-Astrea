package com.echovrprotocol.astrea.model.lfg;


import com.echovrprotocol.astrea.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.UUID;

@JsonFormat
public class LFGLobby {



    private UUID lfgLobbyId;

    private LobbyType type;

    private int maxPlayers;

    private final ArrayList<User> players = new ArrayList<>();




    public LFGLobby(LFGLobbySettings settings){
        this.type = settings.getType();
        this.maxPlayers = settings.maxPlayers;
        this.lfgLobbyId = UUID.randomUUID();

    }

    private LFGLobby(){}

    //Getters

    //custom gets
    public User getLeader(){
        return players.get(0);
    }

    public int getPlayerCount(){
        return players.size();
    }

    //Generic Gets
    public UUID getLfgLobbyId() {
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
    public void setLfgLobbyId(UUID lfgLobbyId) {
        this.lfgLobbyId = lfgLobbyId;
    }

    public void setType(LobbyType type) {
        this.type = type;
    }

    public void addUser(User user){
        this.players.add(user);
    }

    public void removeUser(User toDelete){

        players.removeIf(user -> user.getDiscordId() == toDelete.getDiscordId());

    }
    public void setMaxPlayers(int max){
        this.maxPlayers = max;
    }



}
