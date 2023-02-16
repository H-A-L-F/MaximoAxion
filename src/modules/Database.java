package modules;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

import models.user.User;

public class Database {
	private Vector<User> users;
	private static Database instance;
	
	private Database() {
		users = new Vector<>();
	}
	
	public static Database getInstance() {
		if(instance == null) instance = new Database();
		return instance;
	}
	
	public void loadData() {
		
	}
	
	public static void writeData(String str) {
		
	}
	
	public boolean findUsername(String username) {
		return users.stream().anyMatch(u -> u.getUsername().equals(username));
	}
	
	public Optional<User> findUser(String username, String passsword) {
		return users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(passsword)).findFirst();
	}
	
	public List<User> getTopScore(int amt) {
		return users.stream().sorted(Comparator.comparingInt(User::getDay)).limit(amt).collect(Collectors.toList());
	}
}
