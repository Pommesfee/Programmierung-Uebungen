package programming.set7.sudoku;

import java.util.Arrays;

/**
 * Creates an empty Sudoku. The balues of the fields can be
 * set by the user and the Sudoku can validate itself.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class Sudoku extends NumberBoard {

	// Constants for mode selection when checking for validation
	private static final int MODE_COMPLETE = 1;
	private static final int MODE_SELECTION = 2;

	/**
	 * Creates a new Instance of this class and because
	 * Sudokus are always 9 by 9 it calls the parents class
	 * constructor.
	 */
	public Sudoku() {
		super(9, 9);
	}

	/**
	 * Overrides parent method to guarantee that only valid numbers
	 * can be written into the udoku-board. (1-9)
	 */
	@Override
	public void setValueAt(int row, int col, int value) {
		if (value == EMPTY || (value >= 1 && value <= 9)) {
			super.setValueAt(row, col, value);
		}
	}

	/**
	 * Validate the sudoku.
	 * @return Returns true a the sudoku is valid.
	 */
	public boolean isValid() {

		if (isValid(MODE_COMPLETE)) {		// Check if the lines and rows of the whole square (9x9) are valid
			if (isValid(MODE_SELECTION)) {	// Check if all the sub squares (3x3) are valid
				return true;
			}
		}
		return false;
	}

	/**
	 * Implements actual validation logic.
	 * @param mode {@link Sudoku#MODE_COMPLETE} for checking 9x9 square. {@link Sudoku#MODE_SELECTION} for checking 3x3 sub-squares
	 * @return True if the sudoku is valid for the given mode
	 */
	private boolean isValid(int mode) {

		// Check rows and columns of the whole sudoku
		if (mode == MODE_COMPLETE) {

			// Arrays that are interpreted as a list from 1 to 9
			// that keeps track of the occurrences of a number in 
			// a specific row or column.
			int[] numberCount_rows = new int[9];
			int[] numberCount_columns = new int[9];

			// Loop over all lines and rows
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					
					// If a field is found that holds a value that is not the reserved value for an
					// empty cell its occurrence is stored in the arrays defined above.
					if (getValueAt(i, j) != EMPTY) {
						numberCount_rows[getValueAt(i, j) - 1]++;
					}
					if (getValueAt(j, i) != EMPTY) {
						numberCount_columns[getValueAt(j, i) - 1]++;
					}
					
				}
				
				// When a row and column have been evaluated we check if there
				// is a value that appears more than once. If that is the case
				// we have found an invalid sudoku and return false.
				// Otherwise we continue with our loop.
				for (int j = 0; j < numberCount_rows.length; j++) {
					if (numberCount_rows[j] > 1) {
						return false;
					}
					if (numberCount_columns[j] > 1) {
						return false;
					}
				}
				
				// Reset the arrays so a new row or column can be counted correctly
				Arrays.fill(numberCount_rows, 0);
				Arrays.fill(numberCount_columns, 0);
			}
			return true; // Return true if the sudoku is valid
		}
		if (mode == MODE_SELECTION) {

			// See arrays above..
			int[] numberCount = new int[9];
			int valueAt; // Variable initialized outside of loop to avoid allocating again memory in each loop

			// Two outer for loops are there to go over
			// the 3x3 sub squares.
			for (int startX = 0; startX < 3; startX++) {
				for (int startY = 0; startY < 3; startY++) {
					
					// Inner two for loops are there to check a given sub-square
					// for correctness
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							// Same logic as above. Counting occurrences of a number with an array
							valueAt = getValueAt(i + (startX * 3), j + (startY * 3));
							if (valueAt != EMPTY) {
								numberCount[valueAt - 1]++;
							}
						}
					}
					// Same logic as above.
					for (int i = 0; i < numberCount.length; i++) {
						if (numberCount[i] > 1) {
							return false;
						}
					}
					Arrays.fill(numberCount, 0); // Resets numberCount array after
												 // each checked field.
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String toString() {

		String[] lines = new String[9];
		int valueAt = 0;
		String stringFromValue = "";
		
		// Loop through each line
		for (int i = 0; i < 9; i++) {
			
			// Create string representation of each line
			lines[i] = "";
			for (int j = 0; j < 9; j++) {
				
				valueAt = getValueAt(i, j);
				
				// If an empty value is found it is replaced by whitespace
				// or else the value found at this position will be onverted to a string
				if (valueAt == EMPTY) {
					stringFromValue = " ";
				} else {
					stringFromValue = Integer.toString(getValueAt(i, j));
				}

				lines[i] += stringFromValue + " "; // Add value to line
			}
		}

		// Add the above created lines together to create
		// a representation of the state of the sudoku.
		String finalString = "";

		for (int i = 0; i < lines.length; i++) {
			if (i+1 == lines.length) {
				finalString += lines[i];
			} else {
				finalString += lines[i] + "\n";
			}
		}

		return finalString;
	}
}
