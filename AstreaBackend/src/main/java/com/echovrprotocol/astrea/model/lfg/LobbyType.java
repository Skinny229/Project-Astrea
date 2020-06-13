package com.echovrprotocol.astrea.model.lfg;

public enum LobbyType {
    PRIVATE("private"),
    PUBLIC("public"),
    GROUPS("groups");

    private String type;

    LobbyType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
