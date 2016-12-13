package programming.set7.sudoku;

public class Sudoku extends NumberBoard {

	public Sudoku() {
		super(9, 9);
	}

	@Override
	public void setValueAt(int row, int col, int value) {
		if (value == EMPTY || (value >= 1 && value <= 9)) {
			super.setValueAt(row - 1, col - 1, value);
		}
	}

	public boolean isValid() {

		if (isValid(1)) {
			if (!isValid(2)) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	//TODO Look at selection method.. Anton
	private boolean isValid(int mode) {

		if (mode == 1) {
			// Check rows and columns
			int[] numberCount_rows = new int[9];
			int[] numberCount_columns = new int[9];

			// Check rows ---> i repesents row-number and
			// j represents each element of the row.
			// If the value at the desired position is not empty the value
			// will be stored in the array numberCount-rows.
			// TODO adjust comments
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (getValueAt(i, j) != EMPTY) {
						numberCount_rows[getValueAt(i, j) - 1]++;
					}
					if (!(getValueAt(j, i) == EMPTY)) {
						numberCount_columns[getValueAt(j, i) - 1]++;
					}
				}
				for (int j = 0; j < numberCount_rows.length; j++) {
					if (numberCount_rows[j] > 1) {
						return false;
					}
					if (numberCount_columns[j] > 1) {
						return false;
					}
				}
				numberCount_rows = new int[9];
				numberCount_columns = new int[9];
			}
		}
		if (mode == 2) {
			
			int[] numberCount_small = new int[9];
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					
					for (int j2 = 0; j2 < 3; j2++) {
						for (int k = 0; k < 3; k++) {
							if (!(getValueAt(j2 + (i*3), k + (j*3)) == EMPTY)) {
								numberCount_small[getValueAt(j2 + (i*3), k + (j*3)) - 1]++;
							}
						}
					}
					for (int j2 = 0; j2 < numberCount_small.length; j2++) {
						if(numberCount_small[j2] > 1) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public String toString() {

		String[] lines = new String[9];

		for (int i = 0; i < 9; i++) {
			lines[i] = "";
			for (int j = 0; j < 9; j++) {
				lines[i] += Integer.toString(getValueAt(i, j)) + " ";
			}
		}

		String finalString = "";

		for (int i = 0; i < lines.length; i++) {
			finalString += lines[i] + "\n";
		}

		return finalString;
	}
}
