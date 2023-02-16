package modules;

import java.util.Vector;

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
}
