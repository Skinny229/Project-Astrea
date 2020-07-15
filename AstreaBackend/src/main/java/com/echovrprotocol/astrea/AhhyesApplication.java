package com.echovrprotocol.astrea;

import com.echovrprotocol.astrea.service.LobbyCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AhhyesApplication {



//	final LobbyCleaner cleaner;
//
//	@Autowired
//	public AhhyesApplication(LobbyCleaner cleaner) {
//		this.cleaner = cleaner;
//	}

	public static void main(String[] args) {
		SpringApplication.run(AhhyesApplication.class, args);
	}

}
