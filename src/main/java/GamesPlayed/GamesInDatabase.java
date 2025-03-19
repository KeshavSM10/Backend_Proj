package GamesPlayed;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "games")
@Entity
public class GamesInDatabase {

	public GamesInDatabase() {
		super();
	}
	
	@Id
    private int ID;
	private String name;
	private int Cost;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return Cost;
	}
	public void setCost(int cost) {
		Cost = cost;
	}
}

