package programming.set11.art;

import java.awt.Color;
import java.util.Random;

import acm.graphics.GCompound;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * Creates some artificial art.
 * It does however not verify if the art
 * does fit pixel-perfect in a tile.
 * 
 * @author PurifyPioneer
 * @version 1.0
 * @since 1.0
 */
public class ArtificialArt extends GraphicsProgram {

	// Compound that holds all tiles
	GCompound gcomp;
	
	// Helper to get random values for tiles more easily
	private static Random rd = new Random();
	
	// rowcount of the artificial art piece
	private int rows;
	// column count of the artificial art piece
	private int columns;
	// size of each tile
	private int size;
	
	// default constructor needed so
	// we can have our test in this class
	public ArtificialArt() {
	}
	
	/**
	 * Creates a new piece of artificial art with
	 * tiles that can each have a random pattern out of 4.
	 * @param rows rowcount of art-piece
	 * @param columns columncount of art-piece
	 * @param size size of each sub-tile
	 * 			should be >=100px
	 */
	public ArtificialArt(int rows, int columns, int size) {
		
		gcomp = new GCompound();
		
		this.rows = rows;
		this.columns = columns;
		
		// makes sure that when a construcor is called it is ensured
		// that the tile-size doesn't get too small
		assert size >= 100 : "Size has to be >= 100 (in pixel)!";
		
		this.size = size;

		addTiles();
	}

	/**
	 * Generates the individual tiles and adds them to the
	 * graphics-compound.
	 */
	private void addTiles() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				gcomp.add(generateTile(), i * size, j * size);
			}
		}
	}
	
	/**
	 * Returns the current piece of artificial art
	 * or throws an {@link NullPointerException} when
	 * the default constructor was used, because it should only
	 * exists to have the test logic in this class.
	 * @return
	 */
	public GCompound getCompound() {
		if (gcomp != null) {
			return this.gcomp;
		} else {
			throw new NullPointerException("Compound not initialized. Use constructor with arguments instead.");
		}
	}

	/**
	 * Generates a tile with one of the 4 available
	 * patterns.
	 * @return compound containing the artificial art
	 */
	private GCompound generateTile() {

		GCompound tile = new GCompound();

		// Constructs a tile with a random color
		// that acts as a background
		GRect ground = new GRect(size, size);
		ground.setFillColor(getRandomColor());
		ground.setFilled(true);
		tile.add(ground);
		
		// "randomly" select one out of 4 available patterns
		int key = rd.nextInt(4);
		switch (key) {
			case 0:
				generateOverlappingBoxes(tile);
				break;
			case 1:
				generateRandomBars(tile);
				break;
			case 2:
				generatePyramid(tile);
				break;
			case 3:
				generateRandomColorEachPixel(tile);
				break;
			default:
				// This case does never get executed
				// to be sure we use an assertion.
				assert false : "Non existing pattern!";
		}

		return tile;
	}
	
	/**
	 * Generates a pattern with squares or boxes
	 * that are offset from the top left corner by a random value between 10 and 50
	 * and get smaller in size. Each square has a random color.
	 * @param parent
	 */
	private void generateOverlappingBoxes(GCompound parent) {
		
		assert parent!= null : "Parent was null!";
		
		GRect rect;
		int factor = rd.nextInt(50) + 11; 
		
		for (int i = 0; i < 5; i++) {
			rect = new GRect(i*factor, i*factor, size - i*factor, size - i*factor);
			rect.setFillColor(getRandomColor());
			rect.setFilled(true);
			parent.add(rect);
		}
		
	}
	
	/**
	 * Generates a pattern that is similar to a pyramid. The pyramid can have between
	 * 4 and 8 layers. Each layer has its own random color.
	 * The pyramid however may not fit perfectly into the available space because
	 * the boxes that make up each layer can't be distributed perfectly over the size of the tile
	 * if each box should have the same size.
	 * @param parent
	 */
	private void generatePyramid(GCompound parent) {
		
		assert parent!= null : "Parent was null!";
		
		int layerCount = rd.nextInt(4) + 5;
		
		int tileSize = size/layerCount;
		int posX = size/2 - tileSize/2;
		GRect rect;
		Color c;
		int tilesPerLayer = 1;
		
		for (int i = 0; i < layerCount; i++) {
			
			c = getRandomColor();
			
			for (int j = 0; j < tilesPerLayer; j++) {
				
				rect = new GRect(posX + (j*tileSize), i*tileSize, tileSize, tileSize);
				rect.setFillColor(c);
				rect.setFilled(true);
				parent.add(rect);
				
			}
			posX -= tileSize/2;
			tilesPerLayer++;
		}
		
	}
	
	/**
	 * Generates between 5 and 10 bars that extend from top to bottom and
	 * each have a random color.
	 * The same problem as with the pyramids can occur because the available space
	 * can in some cases not be distributed evenly over all bars.
	 * @param parent
	 */
	private void generateRandomBars(GCompound parent) {
		
		assert parent!= null : "Parent was null!";
		
		GRect rect;
		int count = rd.nextInt(5) + 6;
		int barSize = size/count;
		
		for (int i = 0; i < count; i++) {
			
			rect = new GRect(i*barSize, 0, barSize, size);
			rect.setFillColor(getRandomColor());
			rect.setFilled(true);
			parent.add(rect);
		}
		
	}
	
	/**
	 * Assigns each pixel a random color by drawing many small rectangles
	 * with a random color.
	 * @param parent
	 */
	private void generateRandomColorEachPixel(GCompound parent) {
		
		assert parent!= null : "Parent was null!";
		
		GRect rect;
		Color c;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				c = getRandomColor();
				
				rect = new GRect(i, j, 1, 1);
				rect.setColor(c);
				rect.setFillColor(c);
				rect.setFilled(true);
				parent.add(rect);
			}
		}
		
	}
	
	/**
	 * SAME PATTERN AS IN TASK
	 * Generates rectangles and adds them to the specific component.
	 * @param parent
	 */
	private void generateRectanlges(GCompound parent) {
		
		assert parent!= null : "Parent was null!";
		
		int off = size / 11;
		
		for (int i = 0; i < 5; i++) {
			generateLayer(i*off, off, size - (2*i*off), parent);
		}
	}
	
	/**
	 * Component of {@code generateRectangles()}. Will be called
	 * to create the seperate layers.
	 * 
	 * @param startXY
	 * @param off
	 * @param size
	 * @param parent
	 */
	private void generateLayer(int startXY, int off, int size, GCompound parent) {
		
		assert parent!= null : "Parent was null!";
		
		GRect rect = new GRect(startXY, startXY, size - off, off);
		rect.setFillColor(getRandomColor());
		rect.setFilled(true);
		parent.add(rect);
		
		rect = new GRect(startXY + size - off, startXY, off, size - off);
		rect.setFillColor(getRandomColor());
		rect.setFilled(true);
		parent.add(rect);
		
		rect = new GRect(startXY + off, startXY + size - off, size-off, off);
		rect.setFillColor(getRandomColor());
		rect.setFilled(true);
		parent.add(rect);
		
		rect = new GRect(startXY, startXY + off, off, size - off);
		rect.setFillColor(getRandomColor());
		rect.setFilled(true);
		parent.add(rect);
		
	}

	/**
	 * SAME PATTERN AS IN TASK
	 * @param parent
	 */
	private void generateRotatedRectangles(GCompound parent) {

		assert parent!= null : "Parent was null!";
		
		GPoint[] points = new GPoint[4];
		GPolygon poly;
		GRect rect;
		int lastSize = size;
		int lastOffset = 0;

		for (int i = 0; i < 5; i++) {
			if (i % 2 == 0) {
				points[0] = new GPoint(size / 2, lastOffset);
				points[1] = new GPoint(lastOffset + lastSize, size / 2);
				points[2] = new GPoint(size / 2, lastOffset + lastSize);
				points[3] = new GPoint(lastOffset, size / 2);
				poly = new GPolygon(points);
				poly.setFillColor(getRandomColor());
				poly.setFilled(true);
				parent.add(poly);
			} else {
				lastOffset += lastSize / 4;
				lastSize = lastSize / 2;

				rect = new GRect(lastOffset, lastOffset, lastSize, lastSize);
				rect.setFillColor(Color.RED);
				rect.setFilled(true);
				parent.add(rect);
			}
		}
	}

	private Color getRandomColor() {
		int r = rd.nextInt(255);
		int g = rd.nextInt(255);
		int b = rd.nextInt(255);

		// Assert that rgb values are between 0 and 255 before we return the color
		assert (r >= 0 && r <= 255) || (g >= 0 && g <= 255) || (b >= 0 && b <= 255) : "Error while creating color";

		return new Color(r, g, b);
	}

	@Override
	public void run() {
		
		ArtificialArt a = new ArtificialArt(5, 3, 100);
		setSize((int)a.getCompound().getWidth(), (int)a.getCompound().getHeight());
		
		add(a.getCompound());
	}
	
}
