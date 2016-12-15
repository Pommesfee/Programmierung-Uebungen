package programming.set7.optimus;

import java.util.Arrays;

import acm.program.ConsoleProgram;

/**
 * Implementation of the "Sieve of Eratosthenes" Algorithm, that will print the
 * prime numbers between 2 and N, where N is entered by the user. Be careful
 * though, because for (very) big N the program will be slow.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class SieveOfEratosthenes extends ConsoleProgram {

	private int input; // Variable that stores user input
	private boolean[] numbers; // Array that stores the information is a prime
								// number or not

	@Override
	public void run() {

		// Getting user input
		input = readInt("Please enter a number equal or greater than 2: ");
		solvePrimes();

	}

	/**
	 * Prints out all primes from 2 to N, where N is an Integer provided by the
	 * user. The "Sieve of Eratosthenes" algorithm is used.
	 */
	private void solvePrimes() {

		// Check if we have input that makes sense
		if (input < 2) {
			throw new IllegalStateException("The input cannot be smaller than 2");
		} else {

			// Initialize array
			numbers = new boolean[input + 1]; // + 1 because we otherwise we
												// would miss the last prime
												// number in some cases
			Arrays.fill(numbers, true); // Because we cross out all numbers that
										// are no primes we assume first that
										// all numbers are primes

			numbers[0] = false; // 0 and 1 are no prime numbers so they are
								// crossed
			numbers[1] = false; // out from the start.

			// Main algorithm. Start at i = 2 because we manually crossed out 0
			// and 1.
			for (int i = 2; i < numbers.length; i++) {
				if (numbers[i]) { // If a number is found that is not crossed
									// out our logic is executed by definition
									// of the algorithm
					for (int j = 2; j * i < numbers.length; j++) {
						// This crosses out all multiples of the given number
						// (numbers at index i)
						numbers[j * i] = false;
					}
				}
			}

			// Print out all numbers that have not been crossed out after the
			// algorithm is finished
			for (int i = 0; i < numbers.length; i++) {
				if (numbers[i]) {
					println(i);
				}
			}
		}

	}

}
