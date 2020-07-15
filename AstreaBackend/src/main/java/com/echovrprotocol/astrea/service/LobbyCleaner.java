package com.echovrprotocol.astrea.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class LobbyCleaner implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(LobbyCleaner.class);


    @Override
    public void run() {
        logger.info("Starting loop for the non active game garbage collector");

        while(true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}
