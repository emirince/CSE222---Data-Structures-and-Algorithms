import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		this.map = _map;
    	this.key = _key.toUpperCase();
    	this.cipher_text = text.toUpperCase();
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() {
		StringBuilder ks = new StringBuilder();
		while (ks.length() < cipher_text.length()) {
			ks.append(key);
		}
		keystream = ks.substring(0, cipher_text.length());
	}
	
	private void generate_plain_text() {
		for (int i = 0; i < cipher_text.length(); i++) {
			char cipherChar = cipher_text.charAt(i);
			char keystreamChar = keystream.charAt(i);
			Map<Character, Character> columnMap = map.get(keystreamChar); // directly accessing the map with keystreamChar
	
			// Use an iterator over the keySet() of columnMap to find the plaintext character
			Iterator<Character> it = columnMap.keySet().iterator();
			while (it.hasNext()) {
				char rowChar = it.next();
				if (columnMap.get(rowChar) == cipherChar) {
					plain_text += rowChar;
					break;
				}
			}
		}
	}
	

	public String get_keystream() {
		return keystream;
	}
	
	public String get_plain_text() {
		return plain_text;
	}
}
