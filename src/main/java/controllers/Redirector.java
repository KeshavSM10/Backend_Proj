package controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.view.RedirectView;

import DTO.GamesAvailable;
import DTO.PlayersData;
import PlayGame.GameScorePallet;
import repositories.GamesInDatabase;
import repositories.PlayersRegistered;
import repositories.PlayersRepository;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/GamesAndPlayers")
public class Redirector {

	@Autowired
	GamesAvailable p;
	
	@GetMapping("/Games")
	public List<GamesInDatabase> GetListOfGames() {
		return p.GetAllGames();
	}
	
	@GetMapping("/PlayGame/LawnTennis")
	public RedirectView redirectToLawnTennis() {
		
		return new RedirectView("/LawnTennis/Startgame");
	}
	
	@Autowired
	PlayersData pd;
	
	@Autowired
	PlayersRepository repo;
	
	@GetMapping("/Players")
	public List<PlayersRegistered> GetListOfPlayers(){
		return repo.findAll();
	}
	
	@PostMapping("/AddPlayer")
	public int AddPlayerdata(@RequestBody PlayersRegistered Player) {
		
		int id = repo.getLastID()+1;
		Player.setID(id);
		repo.save(Player);
		return repo.getLastID();
		//pd.DataRegistration();
	}
	
	@Autowired
	GameScorePallet game_to_play;
	
	@GetMapping("/PlayGame")
	public void DisplaygameToPlay() {
		game_to_play.getGameToPlay();
	}
	
	@GetMapping("/Chat/{Entry}")
	public ResponseEntity<String> chatwithai(@PathVariable("Entry") String entry){
		
		  try {
		    
		        WebClient webClient = WebClient.create();

		        String response = webClient.post()
		            .uri("https://api-inference.huggingface.co/models/openai-community/gpt2")
		            .header("Authorization", "Bearer API-key")
		            .contentType(MediaType.APPLICATION_JSON)
		            .bodyValue(Map.of("inputs", entry)) 
		            .retrieve() 
		            .bodyToMono(String.class) 
		            .block();

		        return ResponseEntity.ok(response);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                             .body("Error occurred while processing the request."+e.getMessage());
		    }
	}
	
}
