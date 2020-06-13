package com.echovrprotocol.astrea.controllers;


import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LobbyFilters;
import com.echovrprotocol.astrea.service.LFGLobbyService;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/lfg", produces = MediaType.APPLICATION_JSON_VALUE)
public class LFGController {



    final
    LFGLobbyService lfgLobbyService;

    public LFGController(LFGLobbyService lfgLobbyService) {
        this.lfgLobbyService = lfgLobbyService;
    }


    @GetMapping(value = "/lobbies", produces = "application/json")
    public JSONObject getLobbies(Authentication user, @RequestBody(required = false) LobbyFilters filters){

        JSONObject response = new JSONObject();
        ArrayList<Long> ids = new ArrayList<>();

        //Get public lobbies
        for(LFGLobby lobby : lfgLobbyService.getPublicLobbies())
            ids.add(lobby.getLfglobbyId());

        //TODO: Get password protected Lobbies

        //TODO: Get group lobbies



        //Format arraylist into JsonObject
        response.put("lobbyids", ids.toArray());

        return response;
    }


    @GetMapping(value = "/joinLobby", produces = "application/json")
    public void joinLobby(Authentication user){}

    @GetMapping(value = "/leaveLobby", produces = "application/json")
    public void leaveLobby(Authentication user){
        //
    }

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
