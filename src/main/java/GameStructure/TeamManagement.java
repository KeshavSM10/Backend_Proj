package GameStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import Players.playersRegistered;



@Service
@Scope("prototype")
public class TeamManagement implements Serializable{
	
	private static final long serialVersionUID = 1L;

	 List<playersRegistered> Team1 = new ArrayList<>();
	 List<playersRegistered> Team2 = new ArrayList<>();
	 int Format;
	 Scanner sc = new Scanner(System.in);
	 int NumOfGame, NumofSets;

	  public String TeamingUp() {
		  return "Please Select Game Format" + " Singles:1" + " Doubles:2";
	  }

	 
	public ResponseEntity<String> TeamingPlayersingles(int teamID, playersRegistered playerregistered) {
		
		playersRegistered player = playerregistered;
		if(teamID == 1 && Team1.size() == 0) {
			Team1.add(player);
			return ResponseEntity.ok("Player"+player.getName() +"Added Succesfully to team 1, Add Player of Team 2");
		}
		
		else if(teamID == 1 && Team1.size()==1) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Player"+player.getName() +"Already in Team 1, Add Player of Team 2");
		}
		
		if(teamID == 2 && Team2.size() == 0 && !Team1.contains(player)) {
			Team2.add(player);
			return ResponseEntity.ok("Player"+player.getName() +"Added Succesfully to team 2, Game can be started");
		}
		
		else if(teamID == 2 && Team2.size() == 0 && Team1.contains(player)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Player"+player.getName() +"Already in Team 1, Add another Player");
		}
		
		else if(teamID == 2 && Team2.size()==1) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Player"+player.getName() +"Already in Team 2, Game can be started");
		}
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Team ID. Only 1 and 2 are allowed.");
	  }

	public ResponseEntity<String> TeamingForDoubles(int teamID,playersRegistered playerregistered){
	
	    playersRegistered player = playerregistered;
		if(teamID == 1 && Team1.size()==0) {
			Team1.add(player);
			return ResponseEntity.ok("Player"+player.getName() +"Added Succesfully to team 1, Add second Player");
		}
		
		else if(teamID == 1 && Team1.size()==1 && !Team1.contains(playerregistered)) {
			Team1.add(player);
			return ResponseEntity.ok("Player"+player.getName() +"Added Succesfully to team 1, Add Players of Team 2");
		}
		
		else if(teamID == 1 && Team1.size()==1 && Team1.contains(player)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Player"+player.getName() +"Already in Team 1, Add another Player");
		}
		
		else if(teamID == 1 && Team1.size()==2) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Team already Full, Add Players to team 2");
		}
		
		if(teamID == 2 && Team2.size()==0) {
			Team2.add(player);
			return ResponseEntity.ok("Player"+player.getName() +"Added Succesfully to team 2, Add second Player");
		}
		
		else if(teamID == 2 && Team2.size()==1 && !Team1.contains(player) && !Team2.contains(player)) {
			Team2.add(playerregistered);
			return ResponseEntity.ok("Player"+player.getName() +"Added Succesfully to team 2, Game Can be started");
		}
		
	    else if(teamID == 2 && Team2.size()==1 && Team1.contains(player) && Team2.contains(player)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Player"+player.getName() +"Already in Team 2, Add another Player");
		}
		
		else if(teamID == 2 && Team2.size()==2) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Team already Full, Game Can be started");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Team ID. Only 1 and 2 are allowed.");
	}
	
	  public ResponseEntity<String> GameFormat(int games,int sets) {
		  
		  if(games%2 != 1) {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Valid Number of games, Games should be odd in Number");
		  }
		  else if(games%2 == 1){
			  NumOfGame = games;
			  return ResponseEntity.ok("Ok, number of games per set is"+games);
		  }
		  
		  if(sets%2 != 1) {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Valid Number of sets in match, sets should be odd in Number");
		  }
		  else if(sets%2 == 1){
			  NumofSets = sets;
			  return ResponseEntity.ok("Ok, number of sets per Match is"+sets);
		  }
		return null;
		}
	  
	   public ResponseEntity<String> beginGame(){
		   if(Team1.size() == 1 && Team2.size() == 1) {
			  return ResponseEntity.ok("Game Started, Add Score");
		   }
		   
		   else if(Team1.size() == 2 && Team2.size() == 2) {
			   return ResponseEntity.ok("Game Started, Add Score");
		   }
		   return ResponseEntity.status(HttpStatus.CONFLICT).body("First Complete fillling team details and game dynamics"+Team1.size()+"   "+Team2.size()+"  "+Format);
	   }

	public List<playersRegistered> getTeam1() {
		return Team1;
	}

	public void setTeam1(List<playersRegistered> team1) {
		Team1 = team1;
	}

	public List<playersRegistered> getTeam2() {
		return Team2;
	}

	public void setTeam2(List<playersRegistered> team2) {
		Team2 = team2;
	}

	public int getNumOfGame() {
		return NumOfGame;
	}

	public void setNumOfGame(int numOfGame) {
		NumOfGame = numOfGame;
	}

	public int getNumofSets() {
		return NumofSets;
	}

	public void setNumofSets(int numofSets) {
		NumofSets = numofSets;
	}
}
