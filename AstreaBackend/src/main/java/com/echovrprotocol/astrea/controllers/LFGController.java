package com.echovrprotocol.astrea.controllers;


import com.echovrprotocol.astrea.model.LobbyFilters;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/lfg", produces = MediaType.APPLICATION_JSON_VALUE)
public class LFGController {


    @GetMapping(value = "/lobbies", produces = "application/json")
    public JSONObject getLobbies(Authentication user, @RequestBody LobbyFilters filters){

        JSONObject response = new JSONObject();

        //TODO: Get public lobbies



        //TODO: Get group lobbies



        //TODO:Format arraylist into JsonObject


        //TODO:return

        return response;
    }


    @GetMapping(value = "/joinLobby", produces = "application/json")
    public void joinLobby(Authentication user){}

    @GetMapping(value = "/leaveLobby", produces = "application/json")
    public void leaveLobby(Authentication user){}

    @GetMapping(value = "/createLobby", produces = "application/json")
    public void createLobby(Authentication user){
        //Verify user is not in another lobby IF yes then logout
        //Verify user is not lobby leader of the lobby if Yes then logout
        //Generate lobby and assign it to user
    }

    @GetMapping(value = "/delLobby", produces = "application/json")
    public void deleteLobby(Authentication user){}

    @GetMapping(value = "/updateLobbyStatus", produces = "application/json")
    public void updateLobbyStatus(Authentication user){}

    @GetMapping(value = "/updateEchoSessionStatus", produces = "application/json")
    public void updateEchoSessionStatus(Authentication user){}




}
