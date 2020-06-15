package com.echovrprotocol.astrea.model.lfg;


import com.echovrprotocol.astrea.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@JsonFormat
public class LFGLobby {



    private long lfgLobbyId;
    private String lfgLobbyName;
    LobbyType type;
    private String associatedEchoLobbyId;
    private int playerMaxCount;
    private final HashSet<User> players = new HashSet<>();




    public long getLfgLobbyId() {
        return lfgLobbyId;
    }

    public void setLfgLobbyId(long lfglobbyId) {
        this.lfgLobbyId = lfglobbyId;
    }

    public LobbyType getType() {
        return type;
    }

    public void setType(LobbyType type) {
        this.type = type;
    }

    public String getAssociatedEchoLobbyId() {
        return associatedEchoLobbyId;
    }

    public void setAssociatedEchoLobbyId(String associatedEchoLobbyId) {
        this.associatedEchoLobbyId = associatedEchoLobbyId;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public HashSet<User> getPlayers(){
        return  players;
    }

    public void addPlayer(User user){
        players.add(user);
    }
    public void removePlayer(User user){
        players.remove(user);

    }






}
