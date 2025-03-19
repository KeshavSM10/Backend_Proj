package GamesPlayed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List; 

@SpringBootApplication
@Service
public class GamesAvailable {

	public GamesAvailable() {
		super();
	}
	
	private String NameOfgame;
	private int Cost;
	String NameOfPlayer;
	
	@Autowired
	public GameRepository gamerepository;

	public String getNameOfPlayer() {
		return NameOfPlayer;
	}

	public void setNameOfPlayer(String nameOfPlayer) {
		NameOfPlayer = nameOfPlayer;
	}

	public String getNameOfgame() {
		return NameOfgame;
	}

	public void setNameOfgame(String nameOfgame) {
		NameOfgame = nameOfgame;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}
	
	ArrayList<String>A = new ArrayList<String>();
	
	public ArrayList<String> Printgames() {
		
		A.add("Lawn Tennis");
		A.add("Cricket");
		A.add("Hockey");
		return A;
	}
	
	public List<GamesInDatabase> GetAllGames(){	
		return gamerepository.findAll();	
	}
	
}

//http://localhost:8080/Games&Players/Games