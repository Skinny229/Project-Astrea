package com.echovrprotocol.astrea.model.lfg;

public enum LobbyType {
    PRIVATE(1),
    PUBLIC(2),
    GROUPS(3);

    private int type;

    LobbyType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
