package modules;

import java.util.Scanner;

public class ConsoleInput {
	Scanner in;

	public ConsoleInput() {
		in = new Scanner(System.in);
	}
	
	public void close() {
		in.close();
	}
	
	public void pressEnter() {
		System.out.println("Press [Enter] to continue..");
		in.nextLine();
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
				res = in.nextInt();
			} catch (Exception e) {
//				System.out.println("Input a number");
			}
			try {
				in.nextLine();
			} catch (Exception e) {
				return -1;
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
			res = in.nextLine();
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
}
