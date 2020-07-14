package com.echovrprotocol.astrea.controllers;

import org.springframework.security.core.Authentication;

public class AstreaUtility {

    public static long getDiscordId(String input){
        return Long.parseLong(input.substring(input.lastIndexOf("|")+1));
    }
}
