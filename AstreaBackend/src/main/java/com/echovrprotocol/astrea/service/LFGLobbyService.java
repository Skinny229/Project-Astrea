package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.controllers.AstreaUtility;
import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.model.echovr.EchoSession;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LFGLobbySettings;
import com.echovrprotocol.astrea.model.lfg.LobbyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


@Service
public class LFGLobbyService {

    private static final Logger logger = LoggerFactory.getLogger(LFGLobbyService.class);

    private static HashMap<Long, EchoSession> echoSessions = new HashMap<>();
    public static HashMap<UUID, LFGLobby> LFGLobbies = new HashMap<>();

    private final UserService userService;

    @Autowired
    public LFGLobbyService(UserService userService) {
        this.userService = userService;
    }

    public ArrayList<LFGLobby> getPublicLobbies() {
        ArrayList<LFGLobby> result = new ArrayList<>();

        for (LFGLobby lobby : LFGLobbies.values())
            if (lobby.getType() == LobbyType.PUBLIC)
                result.add(lobby);

        return result;
    }


    public void save(LFGLobby lobby) {

        LFGLobbies.put(lobby.getLfgLobbyId(), lobby);
    }

    public void sessionExists() {
    }

    public LFGLobby getLobbyFromUser(Authentication authentication) {
        long discordId = AstreaUtility.getDiscordId(authentication.getName());

        for (LFGLobby lobby : LFGLobbies.values())
            for (User user : lobby.getPlayers())
                if (user.getDiscordId() == discordId)
                    return lobby;
        return null;
    }


    public void removeFromLobbies(Authentication authentication) {
        LFGLobby userInLobby = getLobbyFromUser(authentication);
        if (userInLobby == null)
            return;
        userInLobby.removeUser(userService.getUser(authentication));
        if(userInLobby.getPlayerCount() == 0) {
            deleteLobby(userInLobby.getLfgLobbyId());
            return;
        }
        save(userInLobby);
    }

    public boolean isUserInALobby(Authentication authentication) {
        long discordId = AstreaUtility.getDiscordId(authentication.getName());

        for (LFGLobby lobby : LFGLobbies.values())
            for (User user : lobby.getPlayers())
                if (user.getDiscordId() == discordId)
                    return true;
        return false;

    }

    public void addUserToLobby(Authentication authentication, UUID id) {
        LFGLobby lobby = LFGLobbies.get(id);
        removeFromLobbies(authentication);
        userService.refreshLFG(authentication);
        lobby.addUser(userService.getUser(authentication));
        save(lobby);
    }

    public void createLobby(Authentication authentication, LFGLobbySettings settings) {
        LFGLobby newLobby = new LFGLobby(settings);
        newLobby.setMaxPlayers(8);
        newLobby.setType(LobbyType.PUBLIC);
        userService.refreshLFG(authentication);
        newLobby.addUser(userService.getUser(authentication));
        save(newLobby);


    }

    public LFGLobby getLobbyFromId(UUID id) {
        return LFGLobbies.get(id);
    }

    public void deleteLobby(UUID lfgLobbyId) {
        LFGLobbies.remove(lfgLobbyId);
        logger.info("Lobby deleted");
    }
}
