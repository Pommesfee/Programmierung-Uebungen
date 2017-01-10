package programming.set9.morse;

public class MorseCoder {

	private static char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
								'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

	private static String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
									".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
									".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----"};
	
	/**
	 * Returns a new string which is the morse code version of the input string.
	 * Each word is on a separate line. The morse representation of each
	 * character of the input string is separated from the next by a space
	 * character in the output string.
	 * 
	 * @param input
	 *            the input string.
	 * @return the morse code version of the input string, ignoring all
	 *         characters in the input string that cannot be represented in
	 *         international morse code.
	 */
	public static String encode(String input) {
		
		String result = "";
	    String[] words = input.split("[ \\n]+");
	    for (String w: words) {
	    	char[] chars = w.toCharArray();
	    	for (char c : chars) {
				result += getCharInMorse(c) + " ";
			} result += "\n";
	    }
	    return result;
	}
	
	/**
	 * Returns a new string which is the natural-language version of the input
	 * string, which is assumed to be in morse code format as produced by the
	 * encode method.
	 * 
	 * @param input
	 *            morse code input string.
	 * @return natural language version or {@code null} if the input string
	 *         could not be properly parsed.
	 */
	public static String decode(String input) {
		String result = "";
	    String[] morseCode = input.split("\\n");
	    for (String m: morseCode) {
	    	String[] subCode = m.split(" +");
	    	for (String c : subCode) {
				if (0 != getMorseAsChar(c)) {
					result += Character.toString(getMorseAsChar(c));
				} else {
					return null;
				}
			} result += " ";
	    } 
	    return result;
	}
	
	private static String getCharInMorse(char c) {
		for (int i = 0; i < letters.length; i++) {
			if (c == letters[i]) {
				return morseCode[i];
			}
		}
		return "";
	}
	
	private static char getMorseAsChar(String s) {
		for (int i = 0; i < morseCode.length; i++) {
			if (s.equalsIgnoreCase(morseCode[i])) {
				return letters[i];
			}
		}
		return 0;
	}
}
