package Serie4;

import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class MethodicalPyramid extends GraphicsProgram {

	private static final int BRICK_WIDTH = 50;
	private static final int BRICK_HEIGHT = 10;
	private static int input;
	private static int pyramdiHeight;
	private static int brickOffsetX;
	private static int brickOffsetY;

	@Override
	public void run() {

		this.setSize(500, 500);

		input = readInt("Please specify the number of bricks in the bottom layer:");

		drawPyramid();
	}

	private void drawPyramid() {

		// number of tiles in bottom tile == number of layers

		pyramdiHeight = input * BRICK_HEIGHT;
		brickOffsetX = BRICK_WIDTH / 2;
		brickOffsetY = pyramdiHeight;

		for (int i = 0; i < input; i++) {
			drawLayer(i, input, layerColor(i, input));
			brickOffsetX += BRICK_WIDTH / 2;
			brickOffsetY -= BRICK_HEIGHT;
		}
	}

	/**
	 * Returns the color to be used for bricks in the given layer.
	 * 
	 * @param layerIndex
	 *            index of the layer whose color to return. {@code 0} is the
	 *            bottom layer, {@code numberOfLayers - 1} is the top layer.
	 * @param numberOfLayers
	 *            the number of layers in the pyramid.
	 * @return the color to be used for the given layer, or {@code null} if
	 *         {@code layerIndex} is invalid.
	 */
	public Color layerColor(int layerIndex, int numberOfLayers) {
		if (layerIndex < 0 || layerIndex >= numberOfLayers) {
			return null;
		} else if (layerIndex == (numberOfLayers - 1)) {
			return new Color(255, 220, 220);
		} else {
			double colorPercentage = (double) layerIndex / (double) numberOfLayers;

			int green = (int) (colorPercentage * 220);
			int blue = (int) (colorPercentage * 220);
			println("CustomCOlor: " + green + " | " + blue);

			return new Color(255, green, blue);
		}
	}

	/**
	 * Draws the given layer with bricks filled with the given color. If
	 * {@code layerIndex} is invalid, no bricks at all should be drawn.
	 * 
	 * @param layerIndex
	 *            index of the layer to draw. {@code 0} is the bottom layer,
	 *            {@code numberOfLayers - 1} is the top layer.
	 * @param numberOfLayers
	 *            the number of layers in the pyramid.
	 * @param layerColor
	 *            color the layer's bricks should be filled with.
	 */
	public void drawLayer(int layerIndex, int numberOfLayers, Color layerColor) {

		GRect g = null;
		for (int i = 0; i < (numberOfLayers - layerIndex); i++) {
			g = new GRect(brickOffsetX + (i * BRICK_WIDTH), brickOffsetY, BRICK_WIDTH, BRICK_HEIGHT);
			g.setFillColor(layerColor);
			g.setFilled(true);
			add(g);
		}

	}

}
