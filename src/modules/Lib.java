package modules;

import java.util.Random;

public class Lib {
	private static Random rand = new Random();
	
	public static String intToBar(int amt, int tot) {
		StringBuilder res = new StringBuilder();
		
		// BAR LENGTH = 20
		// BAR SIZE = TOT / LENGTH
		int size = tot / 20;
		int len = amt / size;
		
		for (int i = 0; i < len; i++) {
			res.append('|');
		}
		
		return res.toString();
	}
	
	public static int getPercent(int amt, int tot) {
		double res = amt * 100 / tot;
		return (int) res;
	}
	
	public static void clear() {
		for(int i = 0; i < 50; i++) System.out.println();
	}
	
	public static boolean RNG(int chance) {
		int res = rand.nextInt(100);
		if(res <= chance) return true;
		else return false;
	}
	
	public static int RNG(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
}
