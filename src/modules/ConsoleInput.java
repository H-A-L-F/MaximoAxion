package modules;

import java.util.Scanner;

public class ConsoleInput {
	Scanner in;
	
	private static ConsoleInput instance;

	private ConsoleInput() {
		in = new Scanner(System.in);
	}
	
	public static ConsoleInput getInstance() {
		if(instance == null) instance = new ConsoleInput();
		return instance;
	}
	
	public void pressEnter() {
		System.out.println("Press [Enter] to continue..");
		try {
			in.nextLine();
		} catch (Exception e) {
			in = new Scanner(System.in);
			in.nextLine();
		}
	}

	public int getInt(int min, int max) {
		int res = -1;
		do {
			try {
				res = in.nextInt();
			} catch (Exception e) {
				System.out.println("Input a number");
			}
			in.nextLine();
		} while (res < min || res > max);
		return res;
	}

	public boolean getBoolFromChar(char yes, char no) {
		char ch;
		do {
			ch = in.next().charAt(0);
		} while (ch != yes && ch != no);
		return ch == yes;
	}

	public int getIntWMSG(String msg, int min, int max) {
		int res = -1;
		do {
			System.out.printf(msg);
			try {
				res = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				in = new Scanner(System.in);
				res = -1;
				res = Integer.parseInt(in.nextLine());
			}
		} while (res < min || res > max);
		return res;
	}

	public boolean getBoolFromCharWMSG(String msg, char yes, char no) {
		char ch;
		do {
			System.out.printf(msg);
			ch = in.next().charAt(0);
		} while (ch != yes && ch != no);
		return ch == yes;
	}

	public String getStr(int min, int max) {
		String res = "";
		int len = res.length();
		do {
			res = in.nextLine();
			len = res.length();
		} while (len < min || len > max);
		return res;
	}

	public String getStrWMSG(String msg, int min, int max) {
		String res = "";
		int len = res.length();
		do {
			System.out.printf(msg);
			try {
				res = in.nextLine();
			} catch (Exception e) {
				in = new Scanner(System.in);
			}
			len = res.length();
		} while (len < min || len > max);
		return res;
	}

	public String getOptsStrWMSG(String msg, String[] opts) {
		String res = "";
		boolean flag = true;
		while (flag) {
			System.out.printf(msg);
			res = in.nextLine();
			for (String opt : opts) {
				if (res.equals(opt))
					flag = false;
			}
		}
		return res;
	}
	
	public int getIntWMSG(String msg) {
		while(true) {
			try {
				System.out.printf(msg);
				int res = Integer.parseInt(in.nextLine());
				return res;
			} catch (Exception e) {
			}
		}
	}
	
	public String getStrWMSG(String msg) {
		while(true) {
			try {
				System.out.printf(msg);
				String res = in.nextLine();
				return res;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
