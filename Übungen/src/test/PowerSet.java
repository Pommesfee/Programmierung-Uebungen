package test;

import acm.program.ConsoleProgram;

public class PowerSet extends ConsoleProgram {
	/*
	 * This method calculates a set based on the binary of the variable o
	 */
	private void calcset(int o, int n) {
		int l;

		/*
		 * This counts the loops until it reaches the first 1 of the binary, at
		 * which point it stops and prints the number according to the count of
		 * loops
		 */
		for (l = 0; o % 2 == 0; l++) {
			o /= 2;
		}
		o /= 2;
		print(l);

		/*
		 * Now a second loop starts where the first loop stopped and calculates
		 * the remaining 1s and print the corresponding loop number
		 */
		for (int j = l + 1; j <= n; j++) {
			if (o % 2 == 1) {
				print(", " + j);
			}
			o /= 2;
		}
	}

	public void run() {
		println("This program prints the power set of the set of natural numbers 0 ... N.");

		int n;
		do {
			n = readInt("Enter N (0 <= N <= 10): ");
		} while (n < 0 || n > 10);

		double k = Math.pow(2, n + 1);
		print("The powerset of {");
		for (int m = 0; m < n; m++) {
			print(m + ", ");
		}
		print(n + "} :");
		print("\n { {}, {");

		for (int i = 1; i < k - 2; i++) {
			int o = i;

			calcset(o, n);

			print("}, {");
		}
		int o = (int) (k - 1); // Warum der loop ? "o = k - 1" müsste doch das selbe bewirken ?
//		while (0 < k - 1) {
//			o++;
//			if (o == k - 1)
//				break;
//		}

		calcset(o, n);
		print("} }");
	}

}