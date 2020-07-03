package com.echovrprotocol.astrea.controllers;


import com.echovrprotocol.astrea.model.echovr.EchoSession;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LFGLobbySettings;
import com.echovrprotocol.astrea.model.lfg.LobbyFilters;
import com.echovrprotocol.astrea.service.LFGLobbyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/lfg", produces = MediaType.APPLICATION_JSON_VALUE)
public class LFGController {



    final
    LFGLobbyService lfgLobbyService;


    @Autowired
    public LFGController(LFGLobbyService lfgLobbyService) {
        this.lfgLobbyService = lfgLobbyService;
    }


    @GetMapping(value = "/lobbies", produces = "application/json")
    public JsonObject getLobbies(Authentication authentication, @RequestBody(required = false) LobbyFilters filters){

        JsonObject response = new JsonObject();
        ArrayList<Long> ids = new ArrayList<>();

        //Get public lobbies
        for(LFGLobby lobby : lfgLobbyService.getPublicLobbies())
            ids.add(lobby.getLfgLobbyId());

        //TODO: Get password protected Lobbies
        //TODO: Get group lobbies

        //Format arraylist into JsonObject\
        JsonArray array = new JsonArray();
        for(long id : ids)
            array.add(id);
        response.add("lobbyids", array);
        return response;
    }


    @PostMapping("/linkEchoSession")
    public void linkEchoSession(){
        //Make sure no link has been done
    }


    @GetMapping(value = "/joinLobby", produces = "application/json")
    public JsonObject joinLobby(Authentication authentication, @RequestBody long id){
        //Check user is not in another lobby
        lfgLobbyService.removeFromLobbies(authentication);
        //register user with the lobby
        lfgLobbyService.addUserToLobby(authentication,id);
        //return lobby details
        return lobbyStatus(authentication);
    }

    @PostMapping(value = "/leaveLobby", produces = "application/json")
    public void leaveLobby(Authentication authentication){
        lfgLobbyService.removeFromLobbies(authentication);
    }

    @PostMapping(value = "/createLobby", produces = "application/json")
    public JsonObject createLobby(Authentication authentication, @RequestBody LFGLobbySettings settings){
        //Verify user is not in another lobby IF yes then logout
        lfgLobbyService.removeFromLobbies(authentication);
        //create lobby
        lfgLobbyService.createLobby(authentication,settings);
        //Generate lobby and assign it to user
        return lobbyStatus(authentication);
    }

    @PostMapping(value = "/delLobby", produces = "application/json")
    public void deleteLobby(Authentication authentication){
        //If user is host of the lobby remove him and eliminate session
    }

    @GetMapping(value = "/lobbyStatus", produces = "application/json")
    public JsonObject lobbyStatus(Authentication authentication, @RequestBody (required = false) LFGLobby lobby){
        //verify user is with the lobby
        if(!lfgLobbyService.isUserInLobby(authentication))
            return null;

        if(lobby == null)
            try {
                Gson gson = new Gson();
                return new JsonParser().parse(gson.toJson(lfgLobbyService.getLobbyFromUser(authentication))).getAsJsonObject();
            } catch (Exception ignored){}
        //return lobby details + echosessionid
        return null;
    }


    @GetMapping(value = "/echoSessionStatus", produces = "application/json")
    public void echoSessionStatus(Authentication authentication, @RequestBody EchoSession echoSession){
        //Return details concerning echo session status
    }

    private JsonObject lobbyStatus(Authentication authentication){
        return lobbyStatus(authentication, lfgLobbyService.getLobbyFromUser(authentication));
    }




}
