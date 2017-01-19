package programming.set10.ciphers;

/**
 * Implementation of the "Caesar Cipher".
 * 
 * @author PurifyPioneer
 * @version 1.0
 * @since 1.0
 */
public class CaesarCipher {

	private CryptMode mode;
	private int cryptKey;

	public CaesarCipher(CryptMode mode, int cryptKey) {
		this.mode = mode;
		this.cryptKey = cryptKey % 26;
	}

	/**
	 * Encrypts or decrypts the given text, depending on the mode of operation
	 * this cypher was created for.
	 * 
	 * @param text
	 *            the text to encode.
	 * @return encryted or decrypted version of the text.
	 */
	public String encode(String text) {
		//Text to char array so we can use character arithmetic
		char[] textCharArray = text.toCharArray(); 
		char c;
		int numValue; // easier readability
		// We calculate the off set when we have a key that would
		// generate  a letter outside of our bounds so we can wrap around
		int offset = 0;
		
		// We need the loop and logic in both en- and decrypt, because it's a little different each time
		// You could safe one loop if you decide in the for loop on each run wheter we need to en- or decrypt
		// But i think this is a little bit more readable.
		if (mode == CryptMode.ENCRYPT) {
			
			// Iterate over char array
			for (int i = 0; i < textCharArray.length; i++) {
				c = textCharArray[i];
				numValue = (int)c;

				// If we have an upper case letter
				if (c >= 'A' && c <= 'Z') {
					
					if (numValue + cryptKey > 'Z') { // wrap around upper border (positive key)
						offset = 'Z' - numValue;
						c = (char) ('A' + (cryptKey - offset) -1);
					} else if (numValue + cryptKey < 'A') { // wrap around lower border (negative key)
						offset = numValue - 'A';
						c = (char) ('Z' - (cryptKey + offset));
					} else { // no wrap around (key in boarders)
						c = (char) (numValue + cryptKey);
					}

				// If we have a lower case letter
				} else if (c >= 'a' && c <= 'z') {

					if (numValue + cryptKey > 'z') { // wrap around upper border (positive key)
						offset = 'z' - numValue;
						c = (char) ('a' + (cryptKey - offset) -1);
					} else if (numValue + cryptKey < 'a') { // wrap around lower border (negative key)
						offset = numValue - 'a';
						c = (char) ('z' - (cryptKey + offset) - 1);
					} else { // no wrap around (key in boarders)
						c = (char) (numValue + cryptKey);	}

				} // else do nothing (eg.: punctuation characters)
				
				textCharArray[i] = c;
			}

		} else if (mode == CryptMode.DECRYPT) {
			
			for (int i = 0; i < textCharArray.length; i++) {
				c = textCharArray[i];
				numValue = (int)c;
				
				// If we have an upper case letter
				if (c >= 'A' && c <= 'Z') {

					if (numValue - cryptKey > 'Z') { // wrap around (negative key)
						offset  = 'Z' - numValue;
						c = (char) ('A' - (cryptKey + offset));
					} else if (numValue - cryptKey < 'A') { // wrap around (positive key)
						offset =  numValue - 'A';
						c = (char) ('Z' - (cryptKey - offset) + 1);
					} else {
						c = (char) (numValue - cryptKey);
					}
					
				// If we have a lower case letter
				} else if (c >= 'a' && c <= 'z') {


					if (numValue - cryptKey > 'z') { // wrap around (negative key)
						offset  = 'z' - numValue;
						c = (char) ('a' - (cryptKey + offset) - 1);
					} else if (numValue - cryptKey < 'a') { // wrap around (positive key)
						offset =  numValue - 'a';
						c = (char) ('z' - (cryptKey - offset) + 1);
					} else {
						c = (char) (numValue - cryptKey);
					}

				} // else do nothing (eg.: punctuation characters)
				
				textCharArray[i] = c;
			}

			
		}
		// Return a new string that is generated from the
		// manipulated char array.
		return new String(textCharArray);
	}

}
