package com.echovrprotocol.astrea.service;

import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.service.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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






}
