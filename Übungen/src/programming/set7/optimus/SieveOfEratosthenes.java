package programming.set7.optimus;

import acm.program.ConsoleProgram;

public class SieveOfEratosthenes extends ConsoleProgram {

	private int input;
	private int[] numbers;
	
	@Override
	public void run() {
		
		input = readInt("Please enter a number equal or greater than 2: ");
		solvePrimes();
		
	}
	
	private void solvePrimes() {
		
		if (input < 2) {
			throw new IllegalStateException("The input cannot be smaller than 2");
		} else {
			
			numbers = new int[input - 2]; //-2 because we exclude 0 and 1
			
			int prime = 0;
			
			for (int i = 0; i < numbers.length; i++) {
				if (testForPrime) {
					for (int j = 0; j < numbers.length; j++) {
						find all numbers that are multiples of prime
					}
				}
			}
			
		}
		
	}
	
}
