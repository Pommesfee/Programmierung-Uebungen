package Serie3;

import acm.program.ConsoleProgram;

/*
 * Program that calculates the fibonacci sequence
 * The user can specify how many parts of the sequence he wants
 */
public class Fibonacci extends ConsoleProgram {

	@Override
	public void run() {

		// Ask the user to enter a number greater than 0 and save the value returned by the method "readInt" in the integer variable n
		int n = readInt("Bis zur welcher Stelle soll die Fibonacci-Serie berechnet werden ? Zahl: ");
		// Check if the user entered valid input
		if (n < 1) {
			println("Error");
		} else {
			// Declare the variables that are needed to calculate the sequence
			int temp1 = 0;  // These two variables need to have the values 0 and 1 so they produce the correct output
			int temp2 = 1;  // on the first run and can than be used to keep the last to numbers of the sequence so the can be sued in the next run of the for loop
			int result = 1; // The result variable needs to be initialized with 1 because otherwise it would give an invalid value for n = 1

			println(1); // One is printed out first because it simplifies the logic needed in the for loop
			for (int i = 1; i < n; i++) {
				result = temp1 + temp2; //First the next number of the sequence is calculated
				temp1 = temp2; // Then temp1 and temp2 are assigned the last two digits of the sequence
				temp2 = result;
				println(result); // The last number that has been calculated up to this point is then printed out. This is optional
			}
			println("Der Wert der Fibonacci-Serien an der Stelle " + n + " ist: " + result); // The program will finally print out the value of the fibonacci sequence at he user specified point n. 

		}
	}

}
