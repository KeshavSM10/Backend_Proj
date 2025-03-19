package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.PlayersData;
import repositories.PlayersRegistered;
import repositories.PlayersRepository;
import services.GameDynamics;
import services.TeamManagement;
import services.GameService;

@SpringBootApplication
@RestController
@RequestMapping("/LawnTennis")
public class LawnTennis {
	
	@Autowired
	TeamManagement teammanagement;
	
	@Autowired
	PlayersRepository repo;
	
	@Autowired
	PlayersData insert;
	
	@Autowired
	GameDynamics gamedynamics;
	
	@Autowired 
	GameService gameservice;
	
	@GetMapping("/Startgame")
	public ResponseEntity<String> startgame() {
		
		return gameservice.startNewGame();
		
	}
	

 @GetMapping("/GameFormat")
  public String TeamingUp() {
	  return "Please Select Game Format" + " Singles:1" + " Doubles:2";
  }
 
 @PatchMapping("/Game/{gameID}/game-format/{format}")
 public ResponseEntity<String> FormatSetting(@PathVariable("gameID") String gameid, @PathVariable("format") int format) {
	return gameservice.updateFormat(gameid, format);
 }
 
  @PatchMapping("/teamForSingles/game/{gameID}/{teamID}/player/{IDofPlayer}")
  public ResponseEntity<String> TeamingPlayersingles( @PathVariable("teamID") int teamID, @PathVariable("IDofPlayer") int IDofPlayer, @PathVariable("gameID") String gameID) {
	
	  return gameservice.SinglesTeaming(gameID, teamID, IDofPlayer);
  }

  @PatchMapping("teamForDoubles/game/{gameID}/{teamID}/player/{PlayerID}")
  public ResponseEntity<String> TeamingForDoubles(@PathVariable("teamID") int teamID, @PathVariable("PlayerID") int PlayerID, @PathVariable("gameID") String gameID){
	
     return gameservice.DoublesTeaming(gameID, teamID, PlayerID);
  }

   @PostMapping("/player-addition")
   public ResponseEntity<PlayersRegistered> AddPlayer(@RequestBody PlayersRegistered Player) {
	
	   int id = repo.getLastID()+1;
	   Player.setID(id);
	   repo.save(Player);
	   return ResponseEntity.status(HttpStatus.CREATED).body(Player);
   }
  
  @PatchMapping("/Game/{gameID}/games/{numofgames}/sets/{numofsets}")
  public ResponseEntity<String> GameFormat(@PathVariable("numofgames") int games, @PathVariable("numofsets") int sets, @PathVariable("gameID") String gameID) {
	  
	  return gameservice.GameDimension(gameID, games, sets);
  }
  
   @GetMapping("/game-initializing/{gameID}")
   public ResponseEntity<String> beginGame(@PathVariable("gameID") String gameid){

	   return gameservice.GameBegin(gameid);
	}
   
   @GetMapping("/{gameID}/team/toss")
   public ResponseEntity<String> ScoreAdder(@PathVariable("gameID") String gameid){
	   return gameservice.Toss(gameid);
   }
   
   @PutMapping("/{gameID}/team/server/{server}")
   public ResponseEntity<String> DeclareServer(@PathVariable("server") int server, @PathVariable("gameID") String gameid){
	   return gameservice.Server(gameid, server);
   }
   
   @PatchMapping("/{gameID}/team/{scoringteam}")
   public ResponseEntity<String> AddScore(@PathVariable("scoringteam") int scoringteam, @PathVariable("gameID") String gameid){
	   return gameservice.addscoreto(gameid,scoringteam);
   }
}
