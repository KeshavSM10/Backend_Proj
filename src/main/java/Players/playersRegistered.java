package Players;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class playersRegistered {
	
	public playersRegistered(int Id,String Name, String Gameplayes, String Gender) {
		super();
		ID = Id;
		name = Name;
		gameplayes = Gameplayes;
		gender = Gender;
	}
	public playersRegistered() {
		super();
	}
	
	@Id
	private int ID;
	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "gameplayes")
	private String gameplayes;
	
	@Column(name = "gender")
	private String gender;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		name = Name;
	}
	public String getGameplayes() {
		return gameplayes;
	}
	public void setGameplayes(String Gameplayes) {
		this.gameplayes = Gameplayes;
	}
	public String getGender() {
		return gender;
	}
	
	public void setGender(String Gender) {
		gender = Gender;
	}

}
