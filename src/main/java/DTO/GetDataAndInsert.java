package DTO;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PlayersRegistered;
import repositories.PlayersRepository;

@Service
public class GetDataAndInsert {

	int id;
	String Name;
	String Games;
	String gender;
	
	@Autowired
	public PlayersRepository playerrepo;
	
	public GetDataAndInsert() {
		super();		
	}
	
	public void getData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter data here");
		
		Name = sc.nextLine();
		Games = sc.nextLine();
		gender = sc.nextLine();
		id = playerrepo.getLastID()+1;
		
	}
	
	public String Insert() {
		PlayersRegistered NovelPlayer = new PlayersRegistered(id,Name, Games, gender);
		try {
			playerrepo.save(NovelPlayer);
			System.out.println(id);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "data not registered";
		}
	}
}
