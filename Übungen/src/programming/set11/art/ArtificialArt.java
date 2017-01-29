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

	GCompound gcomp;
	
	private static Random rd = new Random();
	
	private int rows;
	private int columns;
	private int size;

	public ArtificialArt() {
	}
	
	public ArtificialArt(int rows, int columns, int size) {
		
		gcomp = new GCompound();
		
		this.rows = rows;
		this.columns = columns;
		this.size = size;

		addTiles();
	}

	private void addTiles() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				gcomp.add(generateTile(), i * size, j * size);
			}
		}
	}
	
	public GCompound getCompound() {
		return this.gcomp;
	}

	private GCompound generateTile() {

		GCompound tile = new GCompound();

		GRect ground = new GRect(size, size);
		ground.setFillColor(getRandomColor());
		ground.setFilled(true);
		tile.add(ground);
		
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
				break;
		}

		return tile;
	}
	
	private void generateOverlappingBoxes(GCompound parent) {
		
		GRect rect;
		int factor = rd.nextInt(50) + 11; 
		
		for (int i = 0; i < 5; i++) {
			rect = new GRect(i*factor, i*factor, size - i*factor, size - i*factor);
			rect.setFillColor(getRandomColor());
			rect.setFilled(true);
			parent.add(rect);
		}
		
	}
	
	private void generatePyramid(GCompound parent) {
		
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
	
	private void generateRandomBars(GCompound parent) {
		
		GRect rect;
		Color c;
		int count = rd.nextInt(5) + 6;
		int barSize = size/count;
		
		for (int i = 0; i < count; i++) {
			
			rect = new GRect(i*barSize, 0, barSize, size);
			rect.setFillColor(getRandomColor());
			rect.setFilled(true);
			parent.add(rect);
		}
		
	}
	
	private void generateRandomColorEachPixel(GCompound parent) {
		
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
	 * Generates rectangles and adds them to the specific component.
	 * @param parent
	 */
	private void generateRectanlges(GCompound parent) {
		
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

	private void generateRotatedRectangles(GCompound parent) {

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

		return new Color(r, g, b);
	}

	@Override
	public void run() {
		
		ArtificialArt a = new ArtificialArt(5, 3, 200);
		setSize((int)a.getCompound().getWidth(), (int)a.getCompound().getHeight());
		
		add(a.getCompound());
	}
	
}
