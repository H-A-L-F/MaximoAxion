package modules;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

import models.user.User;

public class Database {
	private Vector<User> users;
	private static Database instance;
	
	private static final String FILE_NAME = "save.txt";
	
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
	
	public static void appendData(String str) {
	    BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
		    bw.append(str);
		    
		    bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
