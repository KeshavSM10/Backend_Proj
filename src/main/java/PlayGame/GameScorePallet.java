package PlayGame;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import GamesPlayed.GameRepository;
import GamesPlayed.GamesInDatabase;

@SpringBootApplication
public class GameScorePallet {

	@Autowired
	GameRepository games_repository;
	
	Scanner sc = new Scanner(System.in);
	
	String Gamename;
	int gameID;
	public void getGameToPlay(){
		
		System.out.println("Select Game You would like to start scoring");
		
		List<GamesInDatabase> games = games_repository.findAll();
		
		for(GamesInDatabase game : games) {
			System.out.printf("%d %s %d",game.getID(),game.getName(), game.getCost());
			System.out.println(" ");
		}
		
		gameID = sc.nextInt();
		System.out.println("OK");
	}
}
