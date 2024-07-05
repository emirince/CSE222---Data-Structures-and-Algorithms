import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		this.map = _map;
   	    this.key = _key.toUpperCase();
    	this.plain_text = text.toUpperCase();
	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	private void generate_keystream() {
		StringBuilder ks = new StringBuilder();
		while (ks.length() < plain_text.length()) {
			ks.append(key);
		}
		keystream = ks.substring(0, plain_text.length());
	}
	
	private void generate_cipher_text() {
		for (int i = 0; i < plain_text.length(); i++) {
			char plainChar = plain_text.charAt(i);
			char keystreamChar = keystream.charAt(i);
			cipher_text += map.get(plainChar).get(keystreamChar);
		}
	}

	public String get_keystream() {
		return keystream;
	}
	
	public String get_cipher_text() {
		return cipher_text;
	}
}
