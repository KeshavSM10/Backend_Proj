package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameDynamics {
	
	 @Autowired
	 TeamManagement match;
	 
	 int Format;
	 int NumofSets, NumofGame, gameScoreTeam1 = 0, gameScoreTeam2 = 0,setScoreTeam1 = 0,setScoreTeam2 = 0,matchScoreTeam1 = 0, matchScoreTeam2 = 0;
	 int server,reciever, Team1, Team2;
	 ResponseEntity<String> re;
	 
	 public GameDynamics(TeamManagement match) {
	        this.match = match;
	        this.Format = match.Format;
	        this.NumofSets = match.getNumofSets();
	        this.NumofGame = match.getNumOfGame();
	    }
	 
	 public GameDynamics() {
		super();
	}

	public ResponseEntity<String> Toss() {
		 if(Math.random()>0.5) {
			 return ResponseEntity.ok("Heads");
		 }
		 return ResponseEntity.ok("Tails");
	 }
	 
	 public ResponseEntity<String> Server1(int teamID) {
		 
		 if(teamID<3 && teamID>0) {
			 server = teamID;
			 setScoreTeam1 = server;
			 reciever = 3-teamID;
			 setScoreTeam2 = reciever;
			 Team1 = server;
			 Team2 = reciever;
			 return ResponseEntity.ok("Server Team is" + teamID);
		 }
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Team ID");		 
	 }
	
	 
	 
	 public ResponseEntity<String> ScoreAdd(int scoringteam){
		 System.out.println("ScoreAdd method called with team ID: " + scoringteam);
		 System.out.println("Format value: " + Format);
		 boolean update = false;
		 if(Format == 1) {
			 if((setScoreTeam1+setScoreTeam2)%2 == 0) {
				 int gamescoreTOshow1 = 0,gamescoreTOshow2 = 0;
				 if(scoringteam == server) {
				    gameScoreTeam1++;
				 }
				 else {
					 gameScoreTeam2++;
				 }
				 if(gameScoreTeam1 == 1) {
					 gamescoreTOshow1 = 15;
				 }
				 else if(gameScoreTeam1 == 2) {
					 gamescoreTOshow1 = 30;
				 }
				 else if(gameScoreTeam1 == 3) {
					 gamescoreTOshow1 = 40;
				 }
				 if(gameScoreTeam2 == 1) {
					 gamescoreTOshow2 = 15;
				 }
				 else if(gameScoreTeam2 == 2) {
					 gamescoreTOshow2 = 30;
				 }
				 else if(gameScoreTeam2 == 3) {
					 gamescoreTOshow2 = 40;
				 }
				 else {
					 gamescoreTOshow1 = 40;
					 gamescoreTOshow2 = 40;
				 }
				 if(gamescoreTOshow1+gamescoreTOshow2 == 0) {
					 return ResponseEntity.status(HttpStatus.ACCEPTED).body("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2);
				 }
				 if(gamescoreTOshow1+gamescoreTOshow2<80) {
				   re = ResponseEntity.status(HttpStatus.ACCEPTED).body("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2);                                     
			    }
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1 == gameScoreTeam2){
					 re =  ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Duece");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1>gameScoreTeam2 && setScoreTeam1 == setScoreTeam2){
					 re =  ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ "Advantage"+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Advantage to server");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1<gameScoreTeam2 && setScoreTeam1 == setScoreTeam2){
					 re =  ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+"Advantage"+" Advantage to reciever");                                     
				}
				 
				 if(gameScoreTeam1 == 4 && gameScoreTeam2<3) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re =  ResponseEntity.ok("Game won by team "+Team1+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
				 else if(gameScoreTeam2 == 4 && gameScoreTeam1<3 && update == false) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re =  ResponseEntity.ok("Game won by team "+Team2+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
				 else if(gameScoreTeam1-gameScoreTeam2 == 2 && gameScoreTeam1+gameScoreTeam2>8 && update == false) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re =  ResponseEntity.ok("Game won by team "+Team1+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
				 else if(gameScoreTeam2-gameScoreTeam1 == 2 && gameScoreTeam1+gameScoreTeam2>8 && update == false) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re =  ResponseEntity.ok("Game won by team "+Team2+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
			 }
			 else {
				 int gamescoreTOshow1 = 0,gamescoreTOshow2 = 0;
				 if(scoringteam == reciever) {
				    gameScoreTeam1++;
				 }
				 else {
					 gameScoreTeam2++;
				 }
				 if(gameScoreTeam1 == 1) {
					 gamescoreTOshow1 = 15;
				 }
				 else if(gameScoreTeam1 == 2) {
					 gamescoreTOshow1 = 30;
				 }
				 else if(gameScoreTeam1 == 3) {
					 gamescoreTOshow1 = 40;
				 }
				 if(gameScoreTeam2 == 1) {
					 gamescoreTOshow2 = 15;
				 }
				 else if(gameScoreTeam2 == 2) {
					 gamescoreTOshow2 = 30;
				 }
				 else if(gameScoreTeam2 == 3) {
					 gamescoreTOshow2 = 40;
				 }
				 else {
					 gamescoreTOshow1 = 40;
					 gamescoreTOshow2 = 40;
				 }
				 if(gamescoreTOshow1+gamescoreTOshow2<80) {
				   re =  ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam2+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2);                                     
			    }
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1 == gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam2+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Duece");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1>gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam2+"; Game score is:"+ "Advantage"+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Advantage to server");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1<gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam2+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+"Advantage"+" Advantage to reciever");                                     
				}
				 
				 if(gameScoreTeam1 == 4 && gameScoreTeam2<=3) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team1);
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
				 else if(gameScoreTeam2 == 4 && gameScoreTeam1<=3 && update == false) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team2); 
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
				 else if(gameScoreTeam1-gameScoreTeam2 == 2 && gameScoreTeam1+gameScoreTeam2>8 && update == false) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team1);
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
				 else if(gameScoreTeam2-gameScoreTeam1 == 2 && gameScoreTeam1+gameScoreTeam2>8 && update == false) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team2);
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
					 update = true;
				 }
			 }
			  
			 if(setScoreTeam1 == (NumofGame/2)+1 && setScoreTeam2<(NumofGame/2)){
				 matchScoreTeam1++;
				 re = ResponseEntity.ok("Set won by team "+Team1);
				 gameScoreTeam1 = 0;
				 gameScoreTeam2 = 0;
				 setScoreTeam1 = 0;
				 setScoreTeam2 = 0;
			 }
			 
			 else if(setScoreTeam1 == (NumofGame/2)+1 && setScoreTeam2<(NumofGame/2)) {
				 matchScoreTeam2++;
				 re = ResponseEntity.ok("Set won by team "+Team2);
				 gameScoreTeam1 = 0;
				 gameScoreTeam2 = 0;
				 setScoreTeam1 = 0;
				 setScoreTeam2 = 0;
			 }
		 if(matchScoreTeam1 == (NumofSets/2)+1 && matchScoreTeam2<(NumofSets/2)){
			 re = ResponseEntity.ok("Match won by team "+Team1);
			 gameScoreTeam1 = 0;
			 gameScoreTeam2 = 0;
			 setScoreTeam1 = 0;
			 setScoreTeam2 = 0;
		 }
		 
		 else if(matchScoreTeam2 == (NumofSets/2)+1 && matchScoreTeam1<(NumofGame/2)) {
			 re = ResponseEntity.ok("Match won by team "+Team2);
			 gameScoreTeam1 = 0;
			 gameScoreTeam2 = 0;
			 setScoreTeam1 = 0;
			 setScoreTeam2 = 0;
			 
		 }
		}
		 
		 else if(Format == 2) {
			 if((setScoreTeam1+setScoreTeam2)%2 == 0) {
				 server = Team1;
				 reciever = Team2;
				 int gamescoreTOshow1 = 0,gamescoreTOshow2 = 0;
				 if(scoringteam == server) {
				    gameScoreTeam1++;
				 }
				 else {
					 gameScoreTeam2++;
				 }
				 if(gameScoreTeam1 == 1) {
					 gamescoreTOshow1 = 15;
				 }
				 else if(gameScoreTeam1 == 2) {
					 gamescoreTOshow1 = 30;
				 }
				 else if(gameScoreTeam1 == 3) {
					 gamescoreTOshow1 = 40;
				 }
				 if(gameScoreTeam2 == 1) {
					 gamescoreTOshow2 = 15;
				 }
				 else if(gameScoreTeam2 == 2) {
					 gamescoreTOshow2 = 30;
				 }
				 else if(gameScoreTeam2 == 3) {
					 gamescoreTOshow2 = 40;
				 }
				 else {
					 gamescoreTOshow1 = 40;
					 gamescoreTOshow2 = 40;
				 }
				 if(gamescoreTOshow1+gamescoreTOshow2<80) {
				   re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2);                                     
			    }
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1 == gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Duece");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1>gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ "Advantage"+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Advantage to server");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1<gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+"Advantage"+" Advantage to reciever");                                     
				}
				 
				 if(gameScoreTeam1 == 4 && gameScoreTeam2<3) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team1+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
				 else if(gameScoreTeam2 == 4 && gameScoreTeam1<3) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team2+" Change Sides of Court"); 
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
				 else if(gameScoreTeam1-gameScoreTeam2 == 2 && gameScoreTeam1+gameScoreTeam2>8) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team1+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
				 else if(gameScoreTeam2-gameScoreTeam1 == 2 && gameScoreTeam1+gameScoreTeam2>8) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team2+" Change Sides of Court");
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
			 }
			 else {
				 int gamescoreTOshow1 = 0,gamescoreTOshow2 = 0;
				 if(scoringteam == server) {
				    gameScoreTeam1++;
				 }
				 else {
					 gameScoreTeam2++;
				 }
				 if(gameScoreTeam1 == 1) {
					 gamescoreTOshow1 = 15;
				 }
				 else if(gameScoreTeam1 == 2) {
					 gamescoreTOshow1 = 30;
				 }
				 else if(gameScoreTeam1 == 3) {
					 gamescoreTOshow1 = 40;
				 }
				 if(gameScoreTeam2 == 1) {
					 gamescoreTOshow2 = 15;
				 }
				 else if(gameScoreTeam2 == 2) {
					 gamescoreTOshow2 = 30;
				 }
				 else if(gameScoreTeam2 == 3) {
					 gamescoreTOshow2 = 40;
				 }
				 else {
					 gamescoreTOshow1 = 40;
					 gamescoreTOshow2 = 40;
				 }
				 if(gamescoreTOshow1+gamescoreTOshow2<80) {
				   re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2);                                     
			    }
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1 == gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Duece");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1>gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ "Advantage"+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+gamescoreTOshow2+" Advantage to server");                                     
				}
				 else if(gamescoreTOshow1+gamescoreTOshow2>=80 && gameScoreTeam1<gameScoreTeam2){
					 re = ResponseEntity.ok("Set Score: Team " +server+ " "+setScoreTeam1+"; Game score is:"+ gamescoreTOshow1+ "\n Set Score: Team " +reciever+ " "+setScoreTeam1+"; Game score is:"+"Advantage"+" Advantage to reciever");                                     
				}
				 
				 if(gameScoreTeam1 == 4 && gameScoreTeam2<=3) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team1);
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;;
				 }
				 else if(gameScoreTeam2 == 4 && gameScoreTeam1<=3) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team2); 
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
				 else if(gameScoreTeam1-gameScoreTeam2 == 2 && gameScoreTeam1+gameScoreTeam2>8) {
					 setScoreTeam1++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team1);
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
				 else if(gameScoreTeam2-gameScoreTeam1 == 2 && gameScoreTeam1+gameScoreTeam2>8) {
					 setScoreTeam2++;
					 server = reciever;
					 reciever = 3-server;
					 re = ResponseEntity.ok("Game won by team "+Team2);
					 gameScoreTeam1 = 0;
					 gameScoreTeam2 = 0;
				 }
			 }
			 
			 if(setScoreTeam1 == (NumofGame/2)+1 && setScoreTeam2<(NumofGame/2)){
				 matchScoreTeam1++;
				 re = ResponseEntity.ok("Set won by team "+Team1);
				 gameScoreTeam1 = 0;
				 gameScoreTeam2 = 0;
				 setScoreTeam1 = 0;
				 setScoreTeam2 = 0;
			 }
			 
			 else if(setScoreTeam1 == (NumofGame/2)+1 && setScoreTeam2<(NumofGame/2)) {
				 matchScoreTeam2++;
				 re = ResponseEntity.ok("Set won by team "+Team2);
				 gameScoreTeam1 = 0;
				 gameScoreTeam2 = 0;
				 setScoreTeam1 = 0;
				 setScoreTeam2 = 0;
			 }
		 if(matchScoreTeam1 == (NumofSets/2)+1 && matchScoreTeam2<(NumofSets/2)){
			 re = ResponseEntity.ok("Match won by team "+Team1);
			 gameScoreTeam1 = 0;
			 gameScoreTeam2 = 0;
			 setScoreTeam1 = 0;
			 setScoreTeam2 = 0;
		 }
		 
		 else if(setScoreTeam1 == (NumofSets/2)+1 && matchScoreTeam2<(NumofGame/2)) {
			 re = ResponseEntity.ok("Match won by team "+Team2);
		 }
		}
		 
		 return re;
	 }

	public int getFormat() {
		return Format;
	}

	public void setFormat(int format) {
		Format = format;
	}

	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}

	public int getTeam1() {
		return Team1;
	}

	public void setTeam1(int team1) {
		Team1 = team1;
	}

	public int getTeam2() {
		return Team2;
	}

	public void setTeam2(int team2) {
		Team2 = team2;
	}
}
