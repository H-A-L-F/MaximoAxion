package modules;

public class Lib {
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
		double res = amt / tot;
		return (int) res * 100;
	}
}
