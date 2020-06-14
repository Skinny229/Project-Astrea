package com.echovrprotocol.astrea.service.repos;


import com.echovrprotocol.astrea.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
