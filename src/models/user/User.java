package models.user;

public class User {
	private String username;
	private String password;
	private int day;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.day = 0;
	}
	
	public User(String user) {
		String arr[] = user.split("#");
		this.username = arr[0];
		this.password = arr[1];
		this.day = Integer.parseInt(arr[2]);
	}
	
	public String serialize() {
		StringBuilder res = new StringBuilder();
		
		res.append(username);
		res.append("#");
		res.append(password);
		res.append("#");
		res.append(day);
		
		return res.toString();
	}
}
