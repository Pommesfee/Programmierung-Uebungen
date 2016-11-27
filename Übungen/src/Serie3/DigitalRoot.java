package Serie3;

import acm.program.ConsoleProgram;

/*
 * Program that calculates the digital root of a given number
 */
public class DigitalRoot extends ConsoleProgram {

	public void run() {

		// Ask the user to enter a positive number and save the value returned by the method "readInt" in the integer variable n
		int n = readInt("Bitte gebe eine positive Zahl an, von der die iterierte Quersumme berechnet werden soll: ");
		// Variable to save the value of n (the number the user entered) because n gets changed later on
		int originalN = n;
		if (n < 0) {
			// If n is not positive "Error" will be printed out
			println("Error");
		} else {
			// If the input is valid (n >= 0), the digital root is calculated by the method "digitalRoot" and the returned value is then stored in the integer variable droot
			int droot = calculateDigitalRoot(n);
			// The digital root is then printed out. With the use of originalN the user can see which number he entered at the start
			println("Die iterierte Quersumme von " + originalN + " ist: " + droot);
		}
	}

	/*
	 * Method that calculates the digtial root of a given number
	 * The check for valid input is already done above
	 */
	private static int calculateDigitalRoot(int n) {
		do {
			n = calculateDigitalSum(n);
		} while (n >= 10); // The while loop will run until a number with only one digit is left

		return n;
	}

	/*
	 * Method that caluclates the digital sum of a given number
	 */
	private static int calculateDigitalSum(int n) {
		// This code is shown in the book/lecture 
		int dsum = 0;
		while (n > 0) {		// As long as n is greater than 0 the loop will run
			dsum += n % 10; // Here we ask for the reminder of n which is then added to the dsum
			n /= 10; 		// The reminder is then "cut off" because it isn't devisible by ten and we have an integer division.
		}					// For a one digit number n/= 10 would give 0 which would lead to a stop of the while loop
		return dsum;
	}
}
