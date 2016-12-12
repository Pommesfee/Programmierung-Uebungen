package programming.set7.sudoku;

public class Sudoku extends NumberBoard {

	public Sudoku() {
		super(9, 9);
	}

	@Override
	public void setValueAt(int row, int col, int value) {
		if(value == EMPTY || (value >= 1 && value <= 9)) {
			super.setValueAt(row, col, value);
		}
	}
	
	public boolean isValid() {
		
		//Check rows and columns
		int[] numberCount_rows = new int[9];
		int[] numberCount_columns = new int[9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(!(getValueAt(j, i) == EMPTY)) {
					numberCount_rows[getValueAt(j, i)-1]++;
				}
				if(!(getValueAt(i, j) == EMPTY)) {
					numberCount_columns[getValueAt(i, j)-1]++;
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if((numberCount_rows[i] > 1) || (numberCount_columns[i] > 1)) {
				return false;
			}
		}
		
		//Check 3x3 squares
		int[] numberCount_InSquare = new int[9];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int i1 = 0; i1 < 3; i1++) {
					for (int j1 = 0; j1 < 3; j1++) {
						if(!(getValueAt(i1 + (i* 3), j1 + (j* 3)) == EMPTY)) {
							numberCount_InSquare[getValueAt(i1 + (i* 3), j1 + (j* 3))-1]++;
						}
					}
				}
				
				for (int i2 = 0; i < 9; i++) {
					if(numberCount_InSquare[i2] > 1) {
						return false;
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
				lines[i] += Integer.toString(getValueAt(i, j));
			}
		}
		
		String finalString = "";
	
		for (int i = 0; i < lines.length; i++) {
			finalString += lines[i] + "\n";
		}
		
		return finalString;
	}
}
