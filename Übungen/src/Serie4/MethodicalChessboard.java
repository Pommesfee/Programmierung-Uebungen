package Serie4;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class MethodicalChessboard extends GraphicsProgram {

    /* Explanation of terms from assignment:
     * 
     * A method is defined in a class and part of an object and describes what the object
     * do.
     * 
     * A parameter is specified in the definition of the method. A method could have an
     * empty parameterlist or as many parameters as the developer wishes to have.
     * 
     * There are the primitive types ("datatypes") like int, double and so on.
     * Each class that we write "creates" it's own datatype. 
     * Example: we have a class Vehicle class and we create a new instance/object from it. This
     * object would have the type Vehicle.
     * 
     * Arguments are the values that fill up the parameterlist of a method during runtime, when calling a method with
     * an non empty parameterlist.
     * 
     * The scope of an variable is also called visibility, which means where we can access a variable.
     * A variable is visible in the code block it is defined in and all code blocks within this block.
     * You have to keep this in mind to avoid effects like Shadowing.
     * 
     * Control statements like if can be used to check if user input is valid.
     */
    
    //General constants that control the behahaviour of our applet/how the cheesboard is drawn
    private static final int OFFSET_X = 50;
    private static final int OFFSET_Y = 50;
    private static final int SIZE = 500;
    private static final int TILES = 8;
    private static final int TILESIZE = SIZE / TILES;

    //Constants to identify each individual piece, so our code doesn't get messy
    private static final int PAWN = 0;
    private static final int KNIGHT = 1;
    private static final int BISHOP = 2;
    private static final int ROOK = 3;
    private static final int QUEEN = 4;
    private static final int KING = 5;

    //Same as above. Makes the code much more readable
    private static final int PLAYER_DARK = 1;
    private static final int PLAYER_WHITE = 2;

    @Override
    public void run() {
        
        this.setSize(SIZE + 2 * OFFSET_X, SIZE + 2 * OFFSET_Y);

        drawChessboard();
        drawPieces();
    }

    /**
     * Method that will draw the chess
     * pieces for both players onto the board.
     */
    private void drawPieces() {

        drawPiece(0, 0, ROOK, PLAYER_DARK);
        drawPiece(1, 0, KNIGHT, PLAYER_DARK);
        drawPiece(2, 0, BISHOP, PLAYER_DARK);
        drawPiece(3, 0, QUEEN, PLAYER_DARK);
        drawPiece(4, 0, KING, PLAYER_DARK);
        drawPiece(5, 0, BISHOP, PLAYER_DARK);
        drawPiece(6, 0, KNIGHT, PLAYER_DARK);
        drawPiece(7, 0, ROOK, PLAYER_DARK);

        //Easier drawing for pawns.
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    drawPiece(j, 1, PAWN, PLAYER_DARK);
                } else {
                    drawPiece(j, 6, PAWN, PLAYER_WHITE);
                }
            }
        }

        drawPiece(0, 7, ROOK, PLAYER_WHITE);
        drawPiece(1, 7, KNIGHT, PLAYER_WHITE);
        drawPiece(2, 7, BISHOP, PLAYER_WHITE);
        drawPiece(3, 7, QUEEN, PLAYER_WHITE);
        drawPiece(4, 7, KING, PLAYER_WHITE);
        drawPiece(5, 7, BISHOP, PLAYER_WHITE);
        drawPiece(6, 7, KNIGHT, PLAYER_WHITE);
        drawPiece(7, 7, ROOK, PLAYER_WHITE);

    }

    /**
     * Draws a specific chess {@code piece} of a {@code player} at the position
     * of a specific square identified by {@code x} and {@code y}. Pieces are: 0
     * pawn, 1 Knight, 2 Bishop, 3 Rook, 4 Queen, 5 King The player playing
     * white is identified by 0. The player playing with the black pieces is
     * identified by 1.
     * 
     * @param x
     *            file index
     * @param y
     *            rank index
     * @param piece
     *            the piece
     * @param player
     *            player 1 or player 2
     */
    public void drawPiece(int x, int y, int piece, int player) {

        //Variable declared and initialized up here
        //to be able to set the location and font so it
        //doesn't need to be done in every part of the switch statements
        GLabel lblPiece = new GLabel("");
        lblPiece.setLocation(((x * TILESIZE) + OFFSET_X + 10), ((y * TILESIZE) + OFFSET_Y + 50));
        lblPiece.setFont("SansSerif-44");
        if (player == PLAYER_DARK) {

            //Switch ensures that the right sprite for the requested piece is set
            switch (piece) {
            case PAWN:
                lblPiece.setLabel("\u265F");
                break;
            case KNIGHT:
                lblPiece.setLabel("\u265E");
                break;
            case BISHOP:
                lblPiece.setLabel("\u265D");
                break;
            case ROOK:
                lblPiece.setLabel("\u265C");
                break;
            case QUEEN:
                lblPiece.setLabel("\u265B");
                break;
            case KING:
                lblPiece.setLabel("\u265A");
                break;
            default:
                println("Specified invalid piece!  [" + piece + "]");
                return; //If an invalid piece is specified the method will end and add nothing to the applet
            }
            add(lblPiece);

        } else if (player == PLAYER_WHITE) {

            //Exactly as above only for white player this time
            switch (piece) {
            case PAWN:
                lblPiece.setLabel("\u2659");
                break;
            case KNIGHT:
                lblPiece.setLabel("\u2658");
                break;
            case BISHOP:
                lblPiece.setLabel("\u2657");
                break;
            case ROOK:
                lblPiece.setLabel("\u2656");
                break;
            case QUEEN:
                lblPiece.setLabel("\u2655");
                break;
            case KING:
                lblPiece.setLabel("\u2654");
                break;
            default:
                println("Specified invalid piece!  [" + piece + "]");
                return;
            }
            add(lblPiece);
        }
    }

    /**
     * Method that draws the chessboard with
     * it's file and rank designators.
     */
    public void drawChessboard() {
        paintGrid();
        paintBoardDesignators();
    }


    /**
     * Method that will only paint the chessboard
     * with it's typical pattern but without any
     * designators.
     */
    private void paintGrid() {

        // Will calculated where a grey rectangle has to be
        // i represents the the rows and j the columns
        // So a grey rectangle will be painted when we have
        // an even row number and an odd column number
        // or when we have an odd row number and an even column number
        for (int i = 0; i < TILES; i++) {
            for (int j = 0; j < TILES; j++) {
                if (i % 2 == 0) {
                    if (!(j % 2 == 0)) {
                        drawSquare(i, j, Color.LIGHT_GRAY);
                    }
                } else {
                    if (j % 2 == 0) {
                        drawSquare(i, j, Color.LIGHT_GRAY);
                    }
                }
            }
        }
    }

    /**
     * Draws the square identified by {@code x} and {@code y} in the color
     * {@code color}.
     * 
     * @param x
     *            file index
     * @param y
     *            rank index
     * @param color
     *            chosen color
     */
    public void drawSquare(int x, int y, Color color) {
        GRect g = new GRect((TILESIZE * x) + OFFSET_X, (TILESIZE * y) + OFFSET_Y, TILESIZE, TILESIZE);
        g.setFilled(true);
        g.setFillColor(color);
        add(g);
    }

    /**
     * Method that will draw the row and rank designators around the boarders
     * of the chessboard.
     */
    private void paintBoardDesignators() {

        for (int i = 0; i < TILES; i++) {
            paintNumbers(i);
            paintLetters(i);
        }

    }

    /**
     *  Method that will paint the letters around the chessboard  
     * @param i The letter that should be drawn from 0 = A to 7 = H.
     */
    private void paintLetters(int i) {

        //Same structure as in drawPieces method
        GLabel g = new GLabel("");
        g.setLocation(OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y - 20);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
        GLabel g2 = new GLabel("");
        g2.setLocation(OFFSET_X + (((i + 1) * TILESIZE) - (TILESIZE / 2)), OFFSET_Y + SIZE + 20);
        g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));

        switch (i) {
        case 0:
            g.setLabel("A");
            g2.setLabel("A");
            break;
        case 1:
            g.setLabel("B");
            g2.setLabel("B");
            break;
        case 2:
            g.setLabel("C");
            g2.setLabel("C");
            break;
        case 3:
            g.setLabel("D");
            g2.setLabel("D");
            break;
        case 4:
            g.setLabel("E");
            g2.setLabel("E");
            break;
        case 5:
            g.setLabel("F");
            g2.setLabel("F");
            break;
        case 6:
            g.setLabel("G");
            g2.setLabel("G");
            break;
        case 7:
            g.setLabel("H");
            g2.setLabel("H");
            break;
        default:
            // If the Chessboard is set bigger than the standard 8 tiles there
            // are not enough
            // letters for the amount of columns specified and the program will
            // print an "?" instead
            g.setLabel("?");
            g2.setLabel("?");
            break;
        }
        add(g);
        add(g2);
    }

    /**
     *  Method that will paint the numbers around the chessboard  
     * @param i The number that should be drawn from 0 = 8 to 7 = 1.
     */
    private void paintNumbers(int i) {

        //Same structure as in draw pieces method and paintNumbers method
        GLabel g = new GLabel("");
        g.setLocation(OFFSET_X - 20, OFFSET_Y + (((i + 1) * TILESIZE) - (TILESIZE / 2)));
        g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
        GLabel g2 = new GLabel("");
        g2.setLocation(OFFSET_X + SIZE + 20, OFFSET_Y + (((i + 1) * TILESIZE) - (TILESIZE / 2)));
        g2.setFont(g.getFont().deriveFont(Font.BOLD, 14));

        switch (i) {
        case 0:
            g.setLabel("8");
            g2.setLabel("8");
            break;
        case 1:
            g.setLabel("7");
            g2.setLabel("7");
            break;
        case 2:
            g.setLabel("6");
            g2.setLabel("6");
            break;
        case 3:
            g.setLabel("5");
            g2.setLabel("5");
            break;
        case 4:
            g.setLabel("4");
            g2.setLabel("4");
            break;
        case 5:
            g.setLabel("3");
            g2.setLabel("3");
            break;
        case 6:
            g.setLabel("2");
            g2.setLabel("2");
            break;
        case 7:
            g.setLabel("1");
            g2.setLabel("1");
            break;
        default:
            // If the Chessboard is set bigger than the standard 8 tiles there
            // are not enough
            // letters for the amount of columns specified and the program will
            // print an "?" instead
            g.setLabel("?");
            g2.setLabel("?");
            break;
        }
        add(g);
        add(g2);
    }
}