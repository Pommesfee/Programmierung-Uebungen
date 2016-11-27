package Serie3;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/*
 * Program that can draw a Chessboard
 */
public class Chessboard extends GraphicsProgram {

	private static final int OFFSET_X = 50; // The offsets that shift the
											// playing field a little bit to the
											// middle because we need space for
											// the letters and numbers.
	private static final int OFFSET_Y = 50; // See above
	private static final int SIZE = 500; // Size in pixels that the applet
											// should have
	private static final int TILES = 8; // How many tiles the playingfield
										// should consist of
	private static final int TILESIZE = SIZE / TILES; // The size each tiles
														// has. Integer division
														// because we can't have
														// half pixels

	@Override
	public void run() {

		this.setSize(SIZE + (2 * OFFSET_X), SIZE + (2 * OFFSET_Y));
		paintGrid();
		paintBoardDesignators();
	}

	// Method that will calculated where a grey rectangle has to be
	// i represents the the rows and j the columns
	// So a grey rectangle will be painted when we have
	// an even row number and an odd column number
	// or when we have an odd row number and an even column number
	private void paintGrid() {

		for (int i = 0; i < TILES; i++) {
			for (int j = 0; j < TILES; j++) {
				if (i % 2 == 0) {
					if (!(j % 2 == 0)) {
						paintGreyRectangle(i, j);
					}
				} else {
					if (j % 2 == 0) {
						paintGreyRectangle(i, j);
					}
				}
			}
		}
	}
	
	//Mehtod that will paint the board designators
	private void paintBoardDesignators() {
		
		for (int i = 0; i < TILES; i++) {
            paintNumbers(i);
            paintLetters(i); 
		}
		
	}

	// Method that draws a grey rectangle that will automatically
	// calculate its position and size
	private void paintGreyRectangle(int i, int j) {
		GRect g = new GRect((TILESIZE * i) + OFFSET_X, (TILESIZE * j) + OFFSET_Y, SIZE / TILES, SIZE / TILES);
		g.setFilled(true);
		g.setFillColor(Color.LIGHT_GRAY);
		add(g);
	}

	// Method that will paint the letters for the
	// Columns on the top and bottom of the playing field
	private void paintLetters(int i) {

		GLabel g;
		GLabel g2;
		switch (i) {
		case 0:
			g = new GLabel("A");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("A");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 1:
			g = new GLabel("B");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("B");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 2:
			g = new GLabel("C");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("C");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 3:
			g = new GLabel("D");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("D");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 4:
			g = new GLabel("E");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("E");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 5:
			g = new GLabel("F");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("F");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 6:
			g = new GLabel("G");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("G");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		case 7:
			g = new GLabel("H");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("H");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		default:
			// If the Chessboard is set bigger than the standard 8 tiles there
			// are not enough
			// letters for the amount of columns specified and the program will
			// print an "?" instead
			g = new GLabel("?");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("?");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
			add(g2, OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
			break;
		}

	}

	// Method that will paint the numbers for the
	// rows on the sides of the playing field
	private void paintNumbers(int j) {

		GLabel g;
		GLabel g2;
		switch (j) {
		case 0:
			g = new GLabel("8");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("8");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 1:
			g = new GLabel("7");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("7");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 2:
			g = new GLabel("6");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("6");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 3:
			g = new GLabel("5");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("5");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 4:
			g = new GLabel("4");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("4");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 5:
			g = new GLabel("3");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("3");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 6:
			g = new GLabel("2");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("2");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		case 7:
			g = new GLabel("1");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("1");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		default:
			// If the Chessboard is set bigger than the standard 8 tiles there
			// are not enough
			// numbers for the amount of lines specified and the program will
			// print a "0" instead
			g = new GLabel("0");
			g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			g2 = new GLabel("0");
			g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));
			add(g, OFFSET_X - 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			add(g2, OFFSET_X + SIZE + 20, OFFSET_Y + (((j + 1) * TILESIZE) - (TILESIZE / 2)));
			break;
		}
	}

}
