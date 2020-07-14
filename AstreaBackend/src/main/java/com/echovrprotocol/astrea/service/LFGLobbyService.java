package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.controllers.AstreaUtility;
import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.model.echovr.EchoSession;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LFGLobbySettings;
import com.echovrprotocol.astrea.model.lfg.LobbyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


@Service
public class LFGLobbyService {

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
                else
                    return null;
        return null;
    }


    public void removeFromLobbies(Authentication authentication) {
        LFGLobby userInLobby = getLobbyFromUser(authentication);
        if (userInLobby == null)
            return;
        userInLobby.removeUser(userService.getUser(authentication));
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
        if(isUserInALobby(authentication) && lobby.getLfgLobbyId() != getLobbyFromUser(authentication).getLfgLobbyId())
            removeFromLobbies(authentication);
        lobby.addUser(userService.getUser(authentication));
        save(lobby);
    }

    public void createLobby(Authentication authentication, LFGLobbySettings settings) {
        LFGLobby newLobby = new LFGLobby(settings);
        newLobby.setMaxPlayers(8);
        newLobby.setType(LobbyType.PUBLIC);
        save(newLobby);
        addUserToLobby(authentication, newLobby.getLfgLobbyId());

    }

    public LFGLobby getLobbyFromId(UUID id) {
        return LFGLobbies.get(id);
    }
}
