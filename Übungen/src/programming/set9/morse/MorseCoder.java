package programming.set9.morse;

/**
 * Utility to decode and encode sequences of morse-code/strings.
 * @author PurifyPioneer
 */
public class MorseCoder {
	
	/*
	 * We use the fact that we know exactly where in the array an item is.
	 * So if our char A is at index 0 of the letters array, the morse-code representation
	 * of A will be at index 0 in the morseCode array. And so on..
	 * 
	 */

	//A letters our alphabet consists of
	private static char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
								'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

	//The representation of our letters in morse-code
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
				if (!(getCharInMorse(c).equalsIgnoreCase(""))) {
					result += getCharInMorse(c) + " ";
				}
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
	
	/**
	 * Tries to parse a char to a string that would be it's representation
	 * in morse-code. The morse-code is stored in the morseCode array.
	 * If the char can not be parsed, an empty string is returned.
	 * @param c char to be parsed
	 * @return string (morse-code) representing input char
	 */
	private static String getCharInMorse(char c) {
		for (int i = 0; i < letters.length; i++) {
			if (Character.toUpperCase(c) == letters[i]) {
				return morseCode[i];
			}
		}
		return "";
	}
	
	/**
	 * Pries to parse morse-code to a char. The valid chars
	 * are defined in the letters array. If the parsing is not successful
	 * the char NUL will be returned.
	 * @param s string to be parsed (morse-code)
	 * @return char representing input string (morse-code)
	 */
	private static char getMorseAsChar(String s) {
		for (int i = 0; i < morseCode.length; i++) {
			if (s.equalsIgnoreCase(morseCode[i])) {
				return letters[i];
			}
		}
		return 0;
	}
}
