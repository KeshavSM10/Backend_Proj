package com.Game.Players_Database.Game.Players;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.Game.Players_Database.Game.Players","GamesPlayed","Players","PlayGame","GameStructure"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
