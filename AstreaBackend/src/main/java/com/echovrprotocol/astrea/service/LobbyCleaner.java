package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Component
public class LobbyCleaner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(LobbyCleaner.class);

    private final LFGLobbyService lfgLobbyService;
    private final UserService userService;

    @Autowired
    public LobbyCleaner(LFGLobbyService lfgLobbyService, UserService userService) {
        this.lfgLobbyService = lfgLobbyService;
        this.userService = userService;
    }

    @Override
    public void run() {
        logger.info("Starting loop for the non active game garbage collector");

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LocalDateTime timeCheckStart = LocalDateTime.now();
            for (LFGLobby lobby : lfgLobbyService.getPublicLobbies()) {
                ArrayList<User> toRemove = new ArrayList<>();
                for (User user : lobby.getPlayers()) {
                    double timeDifference =  ChronoUnit.SECONDS.between(user.getLastLFGRequestUpdate(), timeCheckStart);
                    if(Math.abs(timeDifference) > 10) {
                        toRemove.add(user);
                        logger.info(String.format("Removing [%s] from [%s] with time difference %ss",user.getDiscordName(),lobby.getLfgLobbyId(),timeDifference));
                    }
                }
                for(User user : toRemove)
                {
                    lobby.removeUser(user);
                    lfgLobbyService.save(lobby);
                }
                if(lobby.getPlayers().size() == 0) {
                    logger.info("Deleting lobby");
                    lfgLobbyService.deleteLobby(lobby.getLfgLobbyId());
                }
            }


        }
    }
}
