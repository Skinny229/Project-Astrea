package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LobbyType;
import com.echovrprotocol.astrea.service.repos.LFGLobbyRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class LFGLobbyService {


    private final LFGLobbyRepo lfgLobbyRepo;


    public LFGLobbyService(LFGLobbyRepo lfgLobbyRepo) {
        this.lfgLobbyRepo = lfgLobbyRepo;
    }


    public ArrayList<LFGLobby> getPublicLobbies(){
        ArrayList<LFGLobby> result = new ArrayList<>();

        for(LFGLobby lobby : lfgLobbyRepo.findAll())
            if(lobby.getType() == LobbyType.PUBLIC)
                result.add(lobby);

        return result;
    }


    public void save(LFGLobby lobby){
        lfgLobbyRepo.save(lobby);
    }




}
