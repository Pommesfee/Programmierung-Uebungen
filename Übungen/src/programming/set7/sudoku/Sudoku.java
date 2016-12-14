package programming.set7.sudoku;

public class Sudoku extends NumberBoard {

	private static final int MODE_COMPLETE = 1;
	private static final int MODE_SELECTION = 2;

	public Sudoku() {
		super(9, 9);
	}

	@Override
	public void setValueAt(int row, int col, int value) {
		if (value == EMPTY || (value >= 1 && value <= 9)) {
			super.setValueAt(row, col, value);
		}
	}

	public boolean isValid() {

		if (isValid(MODE_COMPLETE)) {
			if (isValid(MODE_SELECTION)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValid(int mode) {

		if (mode == MODE_COMPLETE) {
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
			return true;
		}
		if (mode == MODE_SELECTION) {

			int[] numberCount = new int[9];
			int valueAt;

			for (int startX = 0; startX < 3; startX++) {
				for (int startY = 0; startY < 3; startY++) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							valueAt = getValueAt(i + (startX * 3), j + (startY * 3));
							if (valueAt != EMPTY) {
								numberCount[valueAt - 1]++;
							}
						}
					}
					for (int i = 0; i < numberCount.length; i++) {
						if (numberCount[i] > 1) {
							return false;
						}
					}
					numberCount = new int[9]; // Resets numberCount array after
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
		
		for (int i = 0; i < 9; i++) {
			lines[i] = "";
			for (int j = 0; j < 9; j++) {
				
				valueAt = getValueAt(i, j);
				
				if (valueAt == EMPTY) {
					stringFromValue = " ";
				} else {
					stringFromValue = Integer.toString(getValueAt(i, j));
				}

				lines[i] += stringFromValue + " ";
			}
		}

		// new lines
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
