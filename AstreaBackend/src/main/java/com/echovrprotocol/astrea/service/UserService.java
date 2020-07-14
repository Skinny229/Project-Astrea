package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.controllers.AstreaUtility;
import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.service.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void save(User user){
        userRepo.save(user);
    }


    public Optional<User> getUser(long id){
        return userRepo.findById(id);
    }


    public User getUser(Authentication authentication) {
        return getUser(AstreaUtility.getDiscordId(authentication.getName())).get();
    }

    public void refreshLFG(Authentication authentication) {
        User user = getUser(authentication);
        user.setLastLFGRequestUpdate(LocalDateTime.now());
        save(user);
    }
}
