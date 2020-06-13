package com.echovrprotocol.astrea.service.repos;

import com.echovrprotocol.astrea.model.lfg.LFGLobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LFGLobbyRepo extends CrudRepository<LFGLobby,Long> {
}
