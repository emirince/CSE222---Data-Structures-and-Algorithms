public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) {
		this.initial_string = str;	// Store the initial input string
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}
	
	private void capitalize() {
		// Convert the entire initial string to uppercase to standardize it
		preprocessed_string = initial_string.toUpperCase();
	}

	private void clean() {
		// Replace all characters in the string except uppercase letters
		preprocessed_string = preprocessed_string.replaceAll("[^A-Z]", "");
	}
	
	public String get_preprocessed_string() {
		// Return the cleaned and capitalized string
		return preprocessed_string;
	}
}