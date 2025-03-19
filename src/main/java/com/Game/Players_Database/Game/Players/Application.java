package com.Game.Players_Database.Game.Players;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.Game.Players_Database.Game.Players","GamesPlayed","Players","PlayGame","GameStructure"})
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
