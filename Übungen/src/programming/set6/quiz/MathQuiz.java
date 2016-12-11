package programming.set6.quiz;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

/**
 * A little math quiz which is aimed at children to teach them how sums work and
 * the differences of numbers. The operands of the are between 0 and 20 while
 * the result will also be between 0 and 20. That means, that one operand and
 * the result are randomly generated (also the operator) and the second operand
 * is then calculated.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class MathQuiz extends ConsoleProgram {

	/**
	 * Used to decide which operation (+ or -) should be used or to to determine
	 * the operands
	 */
	private RandomGenerator r = RandomGenerator.getInstance();

	@Override
	public void run() {

		// Adjusting the size of the applet
		this.setSize(500, 500);

		println("Welcome. This is a little quiz that will teach \n"
				+ "you how to calculate sums and differences of numbers.");

		// Handing exactly five math problems to the user
		for (int i = 0; i < 5; i++) {
			generateMathProblem();
		}
	}

	/**
	 * Will generate a new math problem. The user than needs to enter a solution
	 * and this method will determine if the given answer is correct and print a
	 * corresponding message.
	 */
	private void generateMathProblem() {

		int randomMode = r.nextInt(0, 1); // Deciding which operation should be
											// used
		String operator = ""; // Variable that holds the operator so it can be
								// evaluated in isCorrect method

		int operand1 = r.nextInt(0, 20); // Operand1 for calculation
		int operand2 = 0; // Operand2 for calculation
		int correctResult = 0; // Result initialized in each case. see below.

		// Will generate the problem depending on which operator got rolled.
		switch (randomMode) {
		case 0: // Case for subtraction
			operator = "-";

			correctResult = r.nextInt(0, operand1); // The result is randomly
													// generated and has to be
													// between 0 and the value
													// of
													// operand1 because
													// otherwise we could get a
													// too complicated situation
													// like (5 - (-15)) = 20
													// For this specific task
													// this situation should be
													// avoided.
			operand2 = operand1 - correctResult; // The second operand is
													// determined

			print("What is " + operand1 + " - " + operand2 + "?");
			break;
		case 1: // Case for addition
			operator = "+";

			correctResult = r.nextInt(operand1, 20); // Similar case as above.
														// Because we only want
														// simple equations the
														// result has to be
														// greater
														// or equal to operand1.
			operand2 = correctResult - operand1; // The second operand is
													// determined

			print("What is " + operand1 + " + " + operand2 + "?");
			break;
		}

		int userInput = readInt(); // Takes the input of the user
		// Will print a success message if the user entered the correct result
		// or will otherwise punish the user
		if (isCorrect(operand1, operator, operand2, userInput)) {
			println(generateSuccessMessage());
		} else {
			println(generateFailMessage(correctResult));
		}
	}

	/**
	 * Checks whether the given result is really the result of the operation on
	 * the two operands.
	 * 
	 * @param operand1
	 *            the first operand.
	 * @param operator
	 *            the operator, given as a String. This must be either
	 *            {@code "+"} or {@code "-"}. Otherwise, this method will always
	 *            return {@code false}.
	 * @param operand2
	 *            the second operand.
	 * @param result
	 *            the proposed result.
	 * @return {@code true} if {@code result} is really the result of the
	 *         calculation, {@code false} otherwise.
	 */
	public boolean isCorrect(int operand1, String operator, int operand2, int result) {
		if (!(operator.equals("-") || operator.equals("+"))) {
			return false; // If the supplied string for operator is not a valid
							// operator this method will return
		} else {
			if (operator.equals("-")) { // Case for subtraction: return true if user input is valid
				if ((operand1 - operand2) == result) {
					return true;
				} else {
					return false;
				}
			} else { // Case for addition: return true if user input is valid
				if ((operand1 + operand2) == result) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Returns a message that can be displayed to the user after successfully
	 * solving a problem. There are at least four messages the method randomly
	 * chooses from to keep it interesting.
	 * 
	 * @return randomly selected success message.
	 */
	private String generateSuccessMessage() {
		int mode = r.nextInt(0, 2);

		switch (mode) {
		case 0:
			return "Yay!";
		case 1:
			return "You did it! I'd be proud of you if I wasn't a computer incapable of being proud of you.";
		case 2:
			return "Not bad, not bad. Not great either, but not bad.";
		default:
			return "Success.";
		}
	}

	/**
	 * Returns a message that can be displayed to the user after failing to
	 * solve a problem correctly. There are at least four messages the method
	 * randomly chooses from to keep it interesting. The correct result is
	 * included in the message somewhere to teach the user a lesson.
	 * 
	 * @param correctResult
	 *            the number that would have been the correct result. This
	 *            number is included somehow in the returned message.
	 * @return randomly selected fail message.
	 */
	private String generateFailMessage(int correctResult) {

		int mode = r.nextInt(0, 1);

		switch (mode) {
		case 0:
			return "Not quite. It's " + correctResult + ".";
		case 1:
			return "What's wrong with you? " + correctResult + " would have been correct.";
		default:
			return "Failure. Correct would have been: " + correctResult;
		}
	}

}
