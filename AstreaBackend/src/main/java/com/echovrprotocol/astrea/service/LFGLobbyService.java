package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.controllers.AstreaUtility;
import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.model.echovr.EchoSession;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LFGLobbySettings;
import com.echovrprotocol.astrea.model.lfg.LobbyType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;


@Service
public class LFGLobbyService {

    private static HashMap<Long, EchoSession> echoSessions = new HashMap<>();
    public static HashMap<Long, LFGLobby> LFGLobbies = new HashMap<>();

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
    }

    public boolean isUserInLobby(Authentication authentication) {
        return false;
    }

    public void addUserToLobby(Authentication authentication, long id) {
    }

    public void createLobby(Authentication authentication, LFGLobbySettings settings) {
    }

    public LFGLobby getLobbyFromId(long id) {
        return LFGLobbies.get(id);
    }
}
