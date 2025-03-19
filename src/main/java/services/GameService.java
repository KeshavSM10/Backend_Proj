package services;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import DTO.PlayersData;
import GameStructure.GameComm;
import GameStructure.GameFactory;
import repositories.PlayersRegistered;
import repositories.PlayersRepository;

@Service
public class GameService {
    
    @Autowired
    private RedisTemplate<String, GameComm> redisTemplate;
    
    @Autowired
    private GameFactory gameFactory;
    
	 @Autowired
	 PlayersRepository repo;
	 
	 @Autowired
	 PlayersData insert;

    public ResponseEntity<String> startNewGame() {
        String gameID = UUID.randomUUID().toString();
        
        GameComm newGame = gameFactory.createGame(gameID);

        redisTemplate.opsForValue().set(gameID, newGame);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body("Game initialized with ID: " + gameID);
    }
    
    public ResponseEntity<String> updateFormat(String gameID, int format) {
        GameComm game = redisTemplate.opsForValue().get(gameID);

        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameID);
        }

        System.out.println("Before update: " + game.getGameDynamics().getFormat());
        game.getGameDynamics().setFormat(format);
        redisTemplate.opsForValue().set(gameID, game);
        ResponseEntity<String> re = ResponseEntity.status(HttpStatus.ACCEPTED).body("format set to given value"+game.getGameDynamics().getFormat());
        System.out.println("After update: " + game.getGameDynamics().getFormat()+"  "+gameID);

        
        if (re.getStatusCode() == HttpStatus.OK) {
            redisTemplate.opsForValue().set(gameID, game);
        }

        return re;
    }
    
    public ResponseEntity<String> SinglesTeaming(String gameid, int teamID, int playerid){
    	
    	GameComm game = redisTemplate.opsForValue().get(gameid);

        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
        }
        
        PlayersRegistered player = repo.findById(playerid)
        	    .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerid));


        ResponseEntity<String> re = game.getTeamManagement().TeamingPlayersingles(teamID, player);
        
        redisTemplate.opsForValue().set(gameid, game);
        
        System.out.println("Updated Team1: " + game.getTeamManagement().getTeam1().size());
        System.out.println("Updated Team2: " + game.getTeamManagement().getTeam2().size());
        System.out.println("Updated Team1: " + game.getTeamManagement().getTeam1());
        System.out.println("Updated Team2: " + game.getTeamManagement().getTeam2());
        return re;
    }
    
    public ResponseEntity<String> DoublesTeaming(String gameid, int teamID, int playerid){
    	
    	    GameComm game = redisTemplate.opsForValue().get(gameid);

            if (game == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
            }
            
            PlayersRegistered player = repo.getReferenceById(playerid);

            ResponseEntity<String> re = game.getTeamManagement().TeamingForDoubles(teamID, player);
        
            redisTemplate.opsForValue().set(gameid, game);

            return re;
        }
    
    public ResponseEntity<String> GameDimension(String gameid, int numofgames, int numofsets){
    	
	    GameComm game = redisTemplate.opsForValue().get(gameid);

        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
        }

        ResponseEntity<String> re = game.getTeamManagement().GameFormat(numofgames, numofsets);
    
        redisTemplate.opsForValue().set(gameid, game);

        return re;
    }
    
    public ResponseEntity<String> GameBegin(String gameid){
    	
	        GameComm game = redisTemplate.opsForValue().get(gameid);

            if (game == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
            }

            ResponseEntity<String> re = game.getTeamManagement().beginGame();
    
            redisTemplate.opsForValue().set(gameid, game);

            return re;
        }
    
    public ResponseEntity<String> Toss(String gameid){
    	
        GameComm game = redisTemplate.opsForValue().get(gameid);

        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
        }

        ResponseEntity<String> re = game.getGameDynamics().Toss();

        redisTemplate.opsForValue().set(gameid, game);

        return re;
    }
    
    public ResponseEntity<String> Server(String gameid, int teamid){
    	
        GameComm game = redisTemplate.opsForValue().get(gameid);

        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
        }

        ResponseEntity<String> re = game.getGameDynamics().Server1(teamid);

        redisTemplate.opsForValue().set(gameid, game);

        return re;
    }
    
    public ResponseEntity<String> addscoreto(String gameid, int teamid){
    	
        GameComm game = redisTemplate.opsForValue().get(gameid);

        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
        }

        ResponseEntity<String> re = game.getGameDynamics().ScoreAdd(teamid);

        redisTemplate.opsForValue().set(gameid, game);

        return re;
    }

}
