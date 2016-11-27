package Serie4;

import acm.program.ConsoleProgram;

public class PowerSet extends ConsoleProgram {

	private static int input;

	@Override
	public void run() {

		this.setSize(500, 500);
		
		println("This program prints the power set of the set of natural numbers 0 ... N.");

		do { // Read the input from the user until the user enters a valid input
			input = readInt("Enter N (0 <= N <= 10):");
		} while ((input < 0) || (input > 10));
		calculatePowerset(input);
	}

	private void calculatePowerset(int input) {
 
		print("The powerset of { ");
		for (int i = 0; i <= input; i++) {
			if(i < (input)) {
				print(i + ", ");
			} else {
				print(i + " ");
			}
		}
		println("} is");
		
		int powersetCount = (1 << (input +1));
		print("{ ");
		for (int i = 0; i < powersetCount; i++) {

			print("{");
			boolean notFirst = false;
			for (int j = 0; j <= input; j++) {
				if (((i >> j) & 1) == 1) {

					if (notFirst) {
						print(", ");
					} else {
						notFirst = true;
					}
					print(j);
				}
			}
			
			if(i < (powersetCount -1)) {
				print("}, ");
			} else {
				print("}");
			}
			
		}
		print(" } ");
	}
}
