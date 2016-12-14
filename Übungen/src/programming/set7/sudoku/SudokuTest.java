package programming.set7.sudoku;

public class SudokuTest {

	public static void main(String[] args) {
		
		Sudoku sudoku = new Sudoku();
		
		sudoku.setValueAt(0, 0, 5);
		sudoku.setValueAt(0, 1, 5);
		sudoku.setValueAt(0, 2, 5);
		sudoku.setValueAt(0, 3, 5);
		sudoku.setValueAt(0, 4, 5);
		sudoku.setValueAt(0, 5, 5);
		sudoku.setValueAt(3, 3, 5);
		sudoku.setValueAt(2, 4, 5);
		
		System.out.println(sudoku);
		
		System.out.println(sudoku.isValid());	
	}
}
