package com.echovrprotocol.astrea.model.lfg;

public class LFGLobbySettings {

        int maxPlayers;
        LobbyType type;


    public LFGLobbySettings(int maxPlayers, LobbyType type) {
        this.maxPlayers = maxPlayers;
        this.type = type;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public LobbyType getType() {
        return type;
    }

    public void setType(LobbyType type) {
        this.type = type;
    }
}
