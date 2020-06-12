package com.echovrprotocol.astrea.model.lfg;


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

    private String player1Name;
    private String player2Name;
    private String player3Name;
    private String player4Name;
    private String player5Name;
    private String player6Name;
    private String player7Name;
    private String player8Name;
    private String player9Name;
    private String player10Name;


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

    public int getPlayerMaxCount() {
        return playerMaxCount;
    }

    public void setPlayerMaxCount(int playerMaxCount) {
        this.playerMaxCount = playerMaxCount;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public String getPlayer3Name() {
        return player3Name;
    }

    public void setPlayer3Name(String player3Name) {
        this.player3Name = player3Name;
    }

    public String getPlayer4Name() {
        return player4Name;
    }

    public void setPlayer4Name(String player4Name) {
        this.player4Name = player4Name;
    }

    public String getPlayer5Name() {
        return player5Name;
    }

    public void setPlayer5Name(String player5Name) {
        this.player5Name = player5Name;
    }

    public String getPlayer6Name() {
        return player6Name;
    }

    public void setPlayer6Name(String player6Name) {
        this.player6Name = player6Name;
    }

    public String getPlayer7Name() {
        return player7Name;
    }

    public void setPlayer7Name(String player7Name) {
        this.player7Name = player7Name;
    }

    public String getPlayer8Name() {
        return player8Name;
    }

    public void setPlayer8Name(String player8Name) {
        this.player8Name = player8Name;
    }

    public String getPlayer9Name() {
        return player9Name;
    }

    public void setPlayer9Name(String player9Name) {
        this.player9Name = player9Name;
    }

    public String getPlayer10Name() {
        return player10Name;
    }

    public void setPlayer10Name(String player10Name) {
        this.player10Name = player10Name;
    }
}
