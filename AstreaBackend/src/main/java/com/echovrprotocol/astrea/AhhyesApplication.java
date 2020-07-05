package com.echovrprotocol.astrea;

import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import com.echovrprotocol.astrea.model.lfg.LobbyType;
import com.echovrprotocol.astrea.service.LFGLobbyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AhhyesApplication {

	public static void main(String[] args) {
		LFGLobby lobby = new LFGLobby();
		lobby.setLfgLobbyId(1231232);
		lobby.setType(LobbyType.PUBLIC);
		User mhm = new User();
		mhm.setDiscordId(123123123);
		mhm.setDiscordName("Skinnay");
		//mhm.setDiscordProfilePic("");
		lobby.addUser(mhm);
		lobby.addUser(new User());
		LFGLobbyService.LFGLobbies.put(lobby.getLfgLobbyId(), lobby);

		SpringApplication.run(AhhyesApplication.class, args);
	}

}
