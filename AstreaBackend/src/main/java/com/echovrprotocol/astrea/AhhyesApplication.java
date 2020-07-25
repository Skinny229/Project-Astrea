package com.echovrprotocol.astrea;


import com.echovrprotocol.astrea.service.LobbyCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.net.InetAddress;


@SpringBootApplication
public class AhhyesApplication {




	@Autowired
	LobbyCleaner lobbyCleaner;



    public static void main(String[] args) {
        SpringApplication.run(AhhyesApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void botStartup() {
//		logger.info("Starting with address[{}] and hostname[{}]", InetAddress.getLoopbackAddress().getHostAddress(), InetAddress.getLoopbackAddress().getHostName());
//		//Boot JDA (Discord bot)
//		try {
//			jda = new JDABuilder(DeploymentSettings.BOT_AUTH_TOKEN).build();
//		} catch (Exception e) {
//			//Exit in case of the bot not created successfully(Usually due to bad auth token
//			logger.error(e.toString());
//			logger.error("Shutting down to prevent start up without bot");
//			System.exit(-1);
//		}
//
//
//		/*
//		 *  Bot is implemented using the ListenerAdapter class
//		 * */
//		DeploymentSettings.BOT_ID = jda.getSelfUser().getId();
//		jda.addEventListener(new RedeployHandler());
//		jda.addEventListener(new OnGuildJoinHandler());
//		jda.addEventListener(new PrivateMessageHandler());
//		jda.addEventListener(new PublicMessageIntake());
//
//		logger.info("Bot Startup Completed!");
//		logger.info("Starting the LiveGameVerifier");

        Thread thread = new Thread(lobbyCleaner);
        thread.start();
    }

}
