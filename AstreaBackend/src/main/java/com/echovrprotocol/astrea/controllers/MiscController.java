package com.echovrprotocol.astrea.controllers;


import com.echovrprotocol.astrea.model.User;
import com.echovrprotocol.astrea.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/misc")
public class MiscController {


    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(MiscController.class);

    @Autowired
    public MiscController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/onlogin")
    public void onLogin(Authentication authentication, @RequestParam String name,
                        @RequestParam String picture){
        long possibleId = AstreaUtility.getDiscordId(authentication.getName());
        Optional<User> possibleUser = userService.getUser(possibleId);

        logger.info(String.format("Attempting to set/update User [%s] with Id[%s]",name,possibleId));

        User user;
        ////If the user is authenticated and is not registered then add him to the DB
        if( !possibleUser.isPresent() )
            user = new User();
        else
            user = possibleUser.get();

        user.setDiscordId(possibleId);
        user.setDiscordName(name);
        user.setDiscordProfilePic(picture);
        userService.save(user);

    }


}
