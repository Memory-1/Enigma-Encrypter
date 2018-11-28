package crypto;

public class tools {

	private final static String[] abc =
		{"a", "b", "c", "d", "e", "f", "g",
		"h", "i", "j", "k", "l", "m", "n",
		"o", "p", "q", "r", "s", "t", 
		"u", "v", "w", "x", "y", "z"};

	tools(){
		
	}
	
	public static int findCharacter(String[] wheel, Character input, int shift) {
		String temp = input.toString();
		for (int i = 0; i < wheel.length; i++) {
			if (temp.equals(wheel[(i+shift)%26]))
				return i;
		}
		return -1;
	}
	
	public static String[] getAbc() {
		return abc;
	}
	public static int getIndex(Character input) {
		
		return (int)input - 'a';
	}
	


}
