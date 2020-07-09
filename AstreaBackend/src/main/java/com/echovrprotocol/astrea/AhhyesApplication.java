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
		mhm.setDiscordProfilePic("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/e8a84afd-0014-47dd-980f-194988b2013c/dddp920-720125e2-3c64-4bb6-9a1b-84b5927f0a36.jpg/v1/fill/w_1280,h_1280,q_75,strp/patrick__pfp__by_thatrandomdude911_dddp920-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3siaGVpZ2h0IjoiPD0xMjgwIiwicGF0aCI6IlwvZlwvZThhODRhZmQtMDAxNC00N2RkLTk4MGYtMTk0OTg4YjIwMTNjXC9kZGRwOTIwLTcyMDEyNWUyLTNjNjQtNGJiNi05YTFiLTg0YjU5MjdmMGEzNi5qcGciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.wuo3MwvJjmRTiyhbL3EOsiJyalpWDiL-3nBYWG-O6Es");
		mhm.setDiscordName("Skinnay");
		//mhm.setDiscordProfilePic("");
		lobby.addUser(mhm);
		lobby.addUser(new User());
		LFGLobbyService.LFGLobbies.put(lobby.getLfgLobbyId(), lobby);

		SpringApplication.run(AhhyesApplication.class, args);
	}

}
