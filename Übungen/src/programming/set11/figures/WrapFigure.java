package programming.set11.figures;

import java.util.ArrayList;
import java.util.StringTokenizer;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

/**
 * Wraps text around a picture.
 * 
 * @author PurifyPioneer
 * @version 1.0
 * @since 1.0
 */
public class WrapFigure {

	// Default values for spacing
	private int border = 10;
	private int spacing = 10;
	private double lineSpacingFactor = 1.2;

	// Atrributes of each figure
	private int width;
	private String wrapFigureFilename = "tinlh01w.jpg";
	private double scale;
	private String text;
	private String font = "Serif-12";

	// Creates a new Figure with specific attributes
	public WrapFigure(int width, String wrapFigureFilename, double scale, String text) {
		this.width = width;
		this.wrapFigureFilename = wrapFigureFilename;
		this.scale = scale;
		this.text = text;
	}

	/**
	 * Adjust the font.
	 * @param s font
	 */
	public void setTextFont(String s) {
		this.font = s;
	}

	/**
	 * Set the thickness of the border.
	 * @param i thickness in pixel
	 */
	public void setBorder(int i) {
		this.border = i;
	}

	/**
	 * SPacing between text and picture.
	 * @param i spacing in pixel
	 */
	public void setSpacing(int i) {
		this.spacing = i;
	}

	/**
	 * Spacing between the seperate lines
	 * @param d spacing factor
	 */
	public void setLineSpacingFactor(double d) {
		this.lineSpacingFactor = d;
	}

	/**
	 * Renders the text around the image.
	 * @param g compound to render on
	 * @param im image to render around
	 */
	private void renderTextAround(GCompound g, GImage im) {

		// Stringtokenizer to split the input.
		// Is splitting after space and new line character so we get the output
		// the text desires. Also we include the delimeters so
		// we can handle the new line characters.
		StringTokenizer t = new StringTokenizer(text, " \n", true);

		// Positions of the labels on the x-axis
		int xPos = (int) (border + im.getWidth() + spacing);
		int xPosBelowPicture = border;

		// The maximum width a string can have
		int maxStringWidth = width - xPos - border;
		int maxStringWidthBelowPicture = width - xPosBelowPicture - border;

		// Keeps track where the next label needs to be positioned on the y-axis
		int yPos = border; // ++

		// List of labels so we can add the in one run later
		ArrayList<GLabel> labels = new ArrayList<GLabel>();

		// Variables to handle label creation
		GLabel currentLabel = new GLabel("", xPos, yPos);
		yPos += currentLabel.getAscent();
		currentLabel.setLocation(xPos, yPos);
		GLabel tempLablel = new GLabel("");
		String currentToken;
		int stringLenght;

		// Loop that handles each token the tokenizer gives us
		while (t.hasMoreTokens()) {
			currentLabel.setFont(font);
			// möglicher Fehler in der Aufgabe.. Text wird mit standard font gerendert !
			// tempLablel.setFont(font);
			currentToken = t.nextToken();

			// Check which maximum string with needs to be used
			// right of picture or below ?
			if (yPos <= (im.getHeight() + 2*border + spacing)) {
				// If we encounter a new line character we create a new, empty label
				if (currentToken.equals("\n")) {
					currentLabel.setLabel(currentLabel.getLabel().trim());
					labels.add(currentLabel);
					yPos += (currentLabel.getHeight() * lineSpacingFactor);
					currentLabel = new GLabel("", xPos, yPos);
				} else {
					// temp label used to check if current token can be added
					tempLablel.setLabel(currentLabel.getLabel() + " " + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidth) {
						// Add current token to label if it would within the
						// borders
						currentLabel.setLabel(currentLabel.getLabel() + currentToken);
					} else {
						// If it would not fit add the label to the list and
						// create a
						// new label that starts with the current token
						currentLabel.setLabel(currentLabel.getLabel().trim());
						labels.add(currentLabel);
						yPos += currentLabel.getHeight() * lineSpacingFactor;
						currentLabel = new GLabel(currentToken, xPos, yPos);
					}
				}
			} else {
				currentLabel.setLocation(xPosBelowPicture, yPos);

				// If we encounter a new line character we create a new, empty label
				if (currentToken.equals("\n")) {
					currentLabel.setLabel(currentLabel.getLabel().trim());
					labels.add(currentLabel);
					yPos += (currentLabel.getHeight() * lineSpacingFactor);
					currentLabel = new GLabel("", xPosBelowPicture, yPos);
				} else {
					
					// temp label used to check if current token can be added
					tempLablel.setLabel(currentLabel.getLabel() + " " + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidthBelowPicture) {
						// Add current token to label if it would within the
						// borders
						currentLabel.setLabel(currentLabel.getLabel() + currentToken);
					} else {
						// If it would not fit add the label to the list and
						// create a
						// new label that starts with the current token
						currentLabel.setLabel(currentLabel.getLabel().trim());
						labels.add(currentLabel);
						yPos += (currentLabel.getHeight() * lineSpacingFactor);
						currentLabel = new GLabel(currentToken, xPosBelowPicture, yPos);
					}
				}
			}
		}
		// don't forget to add last label to list
		currentLabel.setLabel(currentLabel.getLabel().trim());
		labels.add(currentLabel);

		// Add all labels in one run
		for (GLabel gLabel : labels) {
			g.add(gLabel);
		}
	}

	/**
	 * Creates a new instance with the picture and the
	 * text rendered around it using the current state of
	 * the attributes.
	 * @return a new compound
	 */
	public GCompound getCompound() {
		GCompound g = new GCompound();
		GImage im = new GImage(wrapFigureFilename);
		im.setBounds(border, border, im.getWidth() * scale, im.getHeight() * scale);
		g.add(im);
		renderTextAround(g, im);
		return g;
	}

}
