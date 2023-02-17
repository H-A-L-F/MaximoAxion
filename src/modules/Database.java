package modules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

import models.user.User;

public class Database {
	private Vector<User> users;
	private static Database instance;

	private final String USER_FILE_NAME = "save.txt";
	private URI USER_FILE_URI;
	private File USER_FILE;

	private Database() {
		users = new Vector<>();

		try {
			initialize();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		loadData();
	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}
	
	private void initialize() throws URISyntaxException {
		USER_FILE_URI = getClass().getResource("/" + USER_FILE_NAME).toURI();
		USER_FILE = new File(USER_FILE_URI);
	}

	public void loadData() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(USER_FILE));
			String st;
			while ((st = br.readLine()) != null) {
				users.add(new User(st));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveDatabase() {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(USER_FILE, false));
			for (User user : users) {
				bw.write(user.serialize());
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void appendData(String str) {
		BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter(USER_FILE, false));
			bw.append(str);

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveUser(User user) {
		this.users.add(user);
	}

	public boolean findUsername(String username) {
		return users.stream().anyMatch(u -> u.getUsername().equals(username));
	}

	public Optional<User> findUser(String username, String passsword) {
		return users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(passsword))
				.findFirst();
	}

	public List<User> getTopScore(int amt) {
		return users.stream().sorted(Comparator.comparingInt(User::getDay)).limit(amt).collect(Collectors.toList());
	}
}
