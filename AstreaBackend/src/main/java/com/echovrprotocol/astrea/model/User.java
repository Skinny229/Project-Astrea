package com.echovrprotocol.astrea.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {


    //Discord
    @Id
    private long discordId;
    private String discordName;
    private String discordProfilePic;
    //Oculus
    private String oculusName;
    private long oculusID;
    //LFG
    private boolean inLFGLobby;
    private LocalDateTime lastLFGRequestUpdate;

    //Getters

    public long getDiscordId() {
        return discordId;
    }

    public String getDiscordName() {
        return discordName;
    }

    public String getDiscordProfilePic() {
        return discordProfilePic;
    }

    public String getOculusName() {
        return oculusName;
    }

    public long getOculusID() {
        return oculusID;
    }

    public boolean isInLFGLobby() {
        return inLFGLobby;
    }

    public LocalDateTime getLastLFGRequestUpdate() {
        return lastLFGRequestUpdate;
    }

    //Setters

    public void setDiscordId(long discordId) {
        this.discordId = discordId;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public void setDiscordProfilePic(String discordProfilePic) {
        this.discordProfilePic = discordProfilePic;
    }

    public void setOculusName(String oculusName) {
        this.oculusName = oculusName;
    }

    public void setOculusID(long oculusID) {
        this.oculusID = oculusID;
    }

    public void setInLFGLobby(boolean inLFGLobby) {
        this.inLFGLobby = inLFGLobby;
    }

    public void setLastLFGRequestUpdate(LocalDateTime lastLFGRequestUpdate) {
        this.lastLFGRequestUpdate = lastLFGRequestUpdate;
    }
}
