package com.echovrprotocol.astrea.controllers;

public class AstreaUtility {

    public String getDiscordId(String input){
        return input.substring(input.lastIndexOf("|"));
    }
}
