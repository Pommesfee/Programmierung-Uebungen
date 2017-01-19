package programming.set10.ciphers;

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
		char[] textCharArray = text.toCharArray();
		char c;
		int numValue;

		if (mode == CryptMode.ENCRYPT) {
			System.out.println("ENCRYPTING...");
			
			for (int i = 0; i < textCharArray.length; i++) {
				c = textCharArray[i];
				numValue = (int)c;

				if (c >= 'A' && c <= 'Z') { // capital letter
					
					int off = 0;
					if (numValue + cryptKey > 'Z') { // wrap around upper border (positive key)
						off = 'Z' - numValue;
						c = (char) ('A' + (cryptKey - off) -1);
					} else if (numValue + cryptKey < 'A') { // wrap around lower border (negative key)
						off = numValue - 'A';
						c = (char) ('Z' - (cryptKey + off));
					} else { // no wrap around (key in boarders)
						c = (char) (numValue + cryptKey);
					}

				} else if (c >= 'a' && c <= 'z') {// lower case letter

					
					int off = 0;
					if (numValue + cryptKey > 'z') { // wrap around upper border (positive key)
						off = 'z' - numValue;
						c = (char) ('a' + (cryptKey - off) -1);
					} else if (numValue + cryptKey < 'a') { // wrap around lower border (negative key)
						off = numValue - 'a';
						c = (char) ('z' - (cryptKey + off));
					} else { // no wrap around (key in boarders)
						c = (char) (numValue + cryptKey);	}

				} // else do nothing (eg.: punctuation characters)
				
				textCharArray[i] = c;
			}

		} else if (mode == CryptMode.DECRYPT) {
			System.out.println("DECRYPTING...");
			/*
			 * Situation:
			 * (positive) Key needs to be subtracted from
			 */
			
			for (int i = 0; i < textCharArray.length; i++) {
				c = textCharArray[i];
				numValue = (int)c;
				System.out.print("DEC: " + c);
				if (c >= 'A' && c <= 'Z') { // capital letter
					
					
					int off = 0;
					if (numValue - cryptKey > 'Z') { // wrap around (negative key)
						off  = 'Z' - numValue;
						c = (char) ('A' - (cryptKey + off));
					} else if (numValue - cryptKey < 'A') { // wrap around (positive key)
						off =  numValue - 'A';
						c = (char) ('Z' - (cryptKey - off));
					} else {
						c = (char) (numValue - cryptKey);
					}
					

				} else if (c >= 'a' && c <= 'z') {// lower case letter

					
					int off = 0;
					if (numValue - cryptKey > 'z') { // wrap around (negative key)
						off  = 'z' - numValue;
						c = (char) ('a' - (cryptKey + off));
					} else if (numValue - cryptKey < 'a') { // wrap around (positive key)
						off =  numValue - 'a';
						c = (char) ('z' - (cryptKey - off));
					} else {
						c = (char) (numValue - cryptKey);
					}

				} // else do nothing (eg.: punctuation characters)
				
				textCharArray[i] = c;
			}

			
		}
		return new String(textCharArray);
	}

}
