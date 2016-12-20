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

	/* Explanation of terms: TODO Completed Comments
	 * 
	 * Array: 
	 * Can be thought of as a "List". In memory the elements of an array a stored directly after		  
	 * each other and can be accessed by the index that is stored at the very top of the given array.
	 * Arrays are of a fixed size and can only contain objects or primitives of one type that can be chosen
	 * when declaring the array.
	 * In this program we use an array because we have a "List" of numbers of a fixed size. 
	 * The Array is of the type boolean and holds the information if a number is a prime or not.
	 * 
	 * You would declare a array as following:
	 * 
	 * type[] name = new type[size];
	 * 
	 * 
	 * List: 
	 * Similar to an array. Stores values/objects in sequence(allowing duplicates) but provides more
	 * functionality with methods like get(), set(). Lists are also objects and are generic which means,
	 * that their type is determined by the programmer.
	 * So you have one implementation of a list and can chooses its type instead of creating a list specifically for each
	 * new type you think of.
	 * 
	 * T is the type (String, Object, Integer...)
	 * 
	 * List<T> list = new List<T>;
	 * 
	 * An example for a list can be found in the StatisticsCollector class, where a LinkedList is used
	 * to store the numbers.
	 * 
	 * 
	 * Exception:
	 * Exceptions can happen when a program is executing. They are used to indicate that something has happened that we
	 * as programmer need to handle(catch) or provide the user with information that an operation might not have been successful.
	 * 
	 * There are unchecked exception(runtime exceptions) that can be thrown without needing to catch them,
	 * which means that the program could still continue after such an exception happened.
	 * 
	 * Than there are checked exceptions that we as a programmer need to take care of because if they are not handled
	 * our program would not be able to continue. Also if we throw such an exception somewhere, we need to add
	 * a throws clause to the method or constructor that this method/constructor can produce such an exception and that
	 * this possible case has to be taken care of wherever the method/constructor is called.
	 * 
	 * Example:
	 * In the solvePrimes method of this class we throw a IllegalStateException if we have no valid input.
	 * This is a unchecked exception that causes no further harm to our program.
	 * 
	 * 
	 * throw:
	 * throw is a statement that is used to throw an exception or anything that is a subclass of Throwable like Error.
	 * It is used by typing the keyword throw and accepts as an argument a throwable.
	 * 
	 * ..method or constructor body {
	 * 		throw new RuntimeException
	 * }
	 * ..method or constructor body throws Exception {
	 * 		throw new Exception
	 * }
	 *
	 * Example:
	 * Same example as for Exception.
	 */
	
	private int input; // Variable that stores user input
	private boolean[] numbers; // Array that stores the information if a number(indices intepreted as numbers) is a prime
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
