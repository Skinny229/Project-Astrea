package com.echovrprotocol.astrea.model;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LFGLobby {


    @Id
    private long lfglobbyId;

    LobbyType type;

    private String associatedEchoLobbyId;

    private int playerCount;

    private int playerMaxCount;

    private String player1Id;
    private String player2Id;
    private String player3Id;
    private String player4Id;
    private String player5Id;
    private String player6Id;
    private String player7Id;
    private String player8Id;
    private String player9Id;
    private String player10Id;


    public long getLfglobbyId() {
        return lfglobbyId;
    }

    public void setLfglobbyId(long lfglobbyId) {
        this.lfglobbyId = lfglobbyId;
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
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }

    public String getPlayer3Id() {
        return player3Id;
    }

    public void setPlayer3Id(String player3Id) {
        this.player3Id = player3Id;
    }

    public String getPlayer4Id() {
        return player4Id;
    }

    public void setPlayer4Id(String player4Id) {
        this.player4Id = player4Id;
    }

    public String getPlayer5Id() {
        return player5Id;
    }

    public void setPlayer5Id(String player5Id) {
        this.player5Id = player5Id;
    }

    public String getPlayer6Id() {
        return player6Id;
    }

    public void setPlayer6Id(String player6Id) {
        this.player6Id = player6Id;
    }

    public String getPlayer7Id() {
        return player7Id;
    }

    public void setPlayer7Id(String player7Id) {
        this.player7Id = player7Id;
    }

    public String getPlayer8Id() {
        return player8Id;
    }

    public void setPlayer8Id(String player8Id) {
        this.player8Id = player8Id;
    }

    public String getPlayer9Id() {
        return player9Id;
    }

    public void setPlayer9Id(String player9Id) {
        this.player9Id = player9Id;
    }

    public String getPlayer10Id() {
        return player10Id;
    }

    public void setPlayer10Id(String player10Id) {
        this.player10Id = player10Id;
    }
}
