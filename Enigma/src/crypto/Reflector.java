package crypto;

public class Reflector {

	public static int reflect(char input) {
		return ((int)(input-'a'+13) % 26)+ 'a';

	}
	
	public static int reflect(int input) {
		return ((input+13) % 26);

	}

}
