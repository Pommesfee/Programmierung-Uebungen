package programming.set11.art;

import java.awt.Color;
import java.util.Random;

import acm.graphics.GCompound;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

public class ArtificialArt extends GCompound {

	private static Random rd = new Random();
	
	private int rows;
	private int columns;
	private int size;

	public ArtificialArt(int rows, int columns, int size) {
		this.rows = rows;
		this.columns = columns;
		this.size = size;

		addTiles();
	}

	private void addTiles() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				add(generateTile(), i * size, j * size);
			}
		}
	}

	private GCompound generateTile() {

		GCompound tile = new GCompound();

		GRect ground = new GRect(size, size);
		ground.setFillColor(getRandomColor());
		ground.setFilled(true);
		tile.add(ground);
		
//		int key = rd.nextInt(2);
//		switch (key) {
//			case 0:
//				ground.setFillColor(Color.WHITE);
//				generateRectanlges(tile);
//				break;
//			case 1:
//				generateRotatedRectangles(tile);
//				break;
//			case 2:
//				
//				break;
//			default:
//				break;
//		}
		
		
		
//		// Circles
//		GOval ov;
//		GRect re;
//		int lastSize = size;
//		int lastOffset = 0;
//
//		for (int i = 0; i < 5; i++) {
//			System.out.println(lastSize);
//			if (i % 2 == 88888) {
//				ov = new GOval(lastSize - 1, lastSize - 1);
//				ov.setColor(getRandomColor());
//				ov.setFilled(true);
//				tile.add(ov);
//			} else {
//				GLine gl = new GLine(size/2, size/2, lastSize, size/2);
//				gl.movePolar(0, 0);
//				tile.add(gl);
//				lastOffset = (int) (size - gl.getEndPoint().getX());
//				lastSize = size - (2* lastOffset);
//				re = new GRect(lastOffset, lastOffset, lastSize, lastSize);
//				tile.add(re);
//			}
//		}

		return tile;
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

}
