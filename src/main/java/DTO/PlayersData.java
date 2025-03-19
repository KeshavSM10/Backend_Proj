package DTO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import repositories.PlayersRegistered;
import repositories.PlayersRepository;

@SpringBootApplication
@Service
public class PlayersData {
	
	public PlayersData() {
		super();
	}

	private int ID;
	private String NameOfPlayer;
	private String GamesPlayerPlayes;
	private String gender;
	
	@Autowired
	public PlayersRepository playersrepository;
	
	@Autowired
	public GetDataAndInsert getdata;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNameOfPlayer() {
		return NameOfPlayer;
	}

	public void setNameOfPlayer(String nameOfPlayer) {
		NameOfPlayer = nameOfPlayer;
	}

	public String getGamesPlayerPlayes() {
		return GamesPlayerPlayes;
	}

	public void setGamesPlayerPlayes(String gamesPlayerPlayes) {
		GamesPlayerPlayes = gamesPlayerPlayes;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public List<PlayersRegistered> GetAllplayers(){
		return playersrepository.findAll();	
	}
	
	public String DataRegistration() {
		try {
            getdata.getData();
            String resultOfInsertion =  getdata.Insert();
            if(resultOfInsertion == "Success") {
            	return "Data entered Successfully";
            } else {
            	return "Unsuccessful";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during data registration: " + e.getMessage();
        }
	}
}
