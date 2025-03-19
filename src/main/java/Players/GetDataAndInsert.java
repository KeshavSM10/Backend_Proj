package Players;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDataAndInsert {

	int id;
	String Name;
	String Games;
	String gender;
	
	@Autowired
	public playersRepository playerrepo;
	
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
		playersRegistered NovelPlayer = new playersRegistered(id,Name, Games, gender);
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
