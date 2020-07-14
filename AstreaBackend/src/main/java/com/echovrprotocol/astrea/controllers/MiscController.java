package com.echovrprotocol.astrea.controllers;


import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/misc")
public class MiscController {


    private final UserService userService;

    @Autowired
    public MiscController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/onlogin")
    public void onLogin(Authentication authentication){
        long possibleId = AstreaUtility.getDiscordId(authentication.getName());
        Optional<User> possibleUser = userService.getUser(possibleId);

        ////If the user is authenticated and is not registered then add him to the DB
        if(authentication.isAuthenticated() && possibleUser.isEmpty() ){
            User newUser = new User();
            newUser.setDiscordId(possibleId);
            //newUser.setDiscordProfilePic();
            userService.save(newUser);
            System.out.println("yay login");
        }

    }


}
