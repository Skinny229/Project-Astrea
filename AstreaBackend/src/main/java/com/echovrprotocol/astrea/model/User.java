package com.echovrprotocol.astrea.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    private long discordId;

    private String discordName;

    private String oculusName;
    private long oculusID;

    private boolean inLFGLobby;
    private LocalDateTime lastLFGRequestUpdate;


    public long getDiscordId() {
        return discordId;
    }

    public void setDiscordId(long discordId) {
        this.discordId = discordId;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public boolean isInLFGLobby() {
        return inLFGLobby;
    }

    public void setInLFGLobby(boolean inLFGLobby) {
        this.inLFGLobby = inLFGLobby;
    }
}
