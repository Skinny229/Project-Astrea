package com.echovrprotocol.astrea.controllers;


import com.echovrprotocol.astrea.model.echovr.EchoSession;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LFGLobbySettings;
import com.echovrprotocol.astrea.model.lfg.LobbyFilters;
import com.echovrprotocol.astrea.model.lfg.LobbyType;
import com.echovrprotocol.astrea.service.LFGLobbyService;
import com.echovrprotocol.astrea.service.UserService;
import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/lfg", produces = MediaType.APPLICATION_JSON_VALUE)
public class LFGController {


    final
    LFGLobbyService lfgLobbyService;

    final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LFGController.class);


    @Autowired
    public LFGController(LFGLobbyService lfgLobbyService, UserService userService) {
        this.lfgLobbyService = lfgLobbyService;
        this.userService = userService;
    }


    @GetMapping(value = "/lobbies", produces = "application/json")
    public JSONObject getLobbies(Authentication authentication, @RequestBody(required = false) LobbyFilters filters) {

        JSONObject response = new JSONObject();
        ArrayList<UUID> ids = new ArrayList<>();
        logger.info("Generating LFG Lobby Id Array for: " + authentication.getName());
        //Get public lobbies
        for (LFGLobby lobby : lfgLobbyService.getPublicLobbies())
            ids.add(lobby.getLfgLobbyId());

        //TODO: Get password protected Lobbies
        //TODO: Get group lobbies

        //TODO: Request
        //Format arraylist into JsonObject
        response.put("lobbyids", ids);
        return response;
    }

    @GetMapping(value = "/lobbydata", produces = "application/json")
    public JSONObject getLobbyData(Authentication authentication, @RequestParam UUID id) {
        JSONObject response = new JSONObject();
        LFGLobby lobby = lfgLobbyService.getLobbyFromId(id);
        logger.info("Generating lobby data for UUID: " + id);
        if (!authentication.isAuthenticated() || lobby == null)
            return response;

        response.put("id", lobby.getLfgLobbyId());
        response.put("avatar", lobby.getLeader().getDiscordProfilePic());
        response.put("name", lobby.getLeader().getDiscordName());
        response.put("nickname", "WIP for v0.3.0");
        response.put("currentPlayers", lobby.getPlayerCount());
        response.put("maxPlayers", 8);
        response.put("players", lobby.getPlayers());
        response.put("status", "WIP for v0.3.0");


        return response;
    }


    @PostMapping("/linkEchoSession")
    public void linkEchoSession() {
        //Make sure no link has been done
    }


    @GetMapping(value = "/joinlobby", produces = "application/json")
    public JSONObject joinLobby(Authentication authentication, @RequestParam UUID id) {
        //register user with the lobby
        logger.info(String.format("User [%s] is attempting to join lobby with ID [%s]", authentication.getName(), id));
        lfgLobbyService.addUserToLobby(authentication, id);
        //return lobby details
        return lobbyStatus(authentication);
    }

    @PostMapping(value = "/leaveLobby", produces = "application/json")
    public void leaveLobby(Authentication authentication) {

        lfgLobbyService.removeFromLobbies(authentication);
    }

    @GetMapping(value = "/createlobby", produces = "application/json")
    public JSONObject createLobby(Authentication authentication, @RequestBody(required = false) LFGLobbySettings settings) {
        logger.info("Starting Lobby Creation Process for " + authentication.getName());
        //Verify user is not in another lobby IF yes then logout
        leaveLobby(authentication);
        //create lobby
        if (settings == null)
            settings = new LFGLobbySettings(8, LobbyType.PUBLIC);
        lfgLobbyService.createLobby(authentication, settings);
        //Generate lobby and assign it to user
        // return "lobbyStatus(authentication)";

        return lobbyStatus(authentication);

    }

    @PostMapping(value = "/delLobby", produces = "application/json")
    public void deleteLobby(Authentication authentication) {
        //If user is host of the lobby remove him and eliminate session
    }


    @GetMapping(value = "/lobbystatus", produces = "application/json")
    public JSONObject lobbyStatus(Authentication authentication, @RequestBody (required = false) LFGLobby lobbyInput){
        //verify user is with the lobby
        if (!lfgLobbyService.isUserInALobby(authentication)) {
            return (JSONObject) new JSONObject().put("status", "BAD");
        }

        JSONObject status = new JSONObject();
        userService.refreshLFG(authentication);
        LFGLobby lobby = lfgLobbyService.getLobbyFromUser(authentication);

        status.put("lobby", lobby);
        status.put("status", "OK");

        return status;
    }


    @GetMapping(value = "/echoSessionStatus", produces = "application/json")
    public void echoSessionStatus(Authentication authentication, @RequestBody EchoSession echoSession) {
        //Return details concerning echo session status
    }

    private JSONObject lobbyStatus(Authentication authentication) {
        return lobbyStatus(authentication, lfgLobbyService.getLobbyFromUser(authentication));
    }


}
