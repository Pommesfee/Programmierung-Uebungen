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

	/*
	 * Das Program erzeugt augenscheinlich genau den selben Output wie im
	 * Beispiel, wenn es mit der selben Konfiguration ausgeführt wird.
	 * 
	 * Jedoch schlägt der Test aus einem mir nicht nachvollziehbaren fehl. Der
	 * Test sag, dass er eine gewisse Zeile nicht finden kann/diese in der
	 * Ausgabe des Programms anders ist als gewünscht. Lasse Ich mir die labels
	 * alle ausgeben (am Ende der "rendertextAround"-Methode) stelle Ich fest,
	 * dass das vom Test gewünschte Label existiert und auch genau dem
	 * entspricht was der Test erwartet. zum Beispiel sind keine unerwünschten
	 * Leerzeichen vorhanden.
	 * 
	 * Der Fehler, dass zu wenig Labels genutzt werden (14 statt 15) mag wohl
	 * aus dem ersten Fehler resultieren.
	 */

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
	 * 
	 * @param s
	 *            font
	 */
	public void setTextFont(String s) {
		this.font = s;
	}

	/**
	 * Set the thickness of the border.
	 * 
	 * @param i
	 *            thickness in pixel
	 */
	public void setBorder(int i) {
		this.border = i;
	}

	/**
	 * SPacing between text and picture.
	 * 
	 * @param i
	 *            spacing in pixel
	 */
	public void setSpacing(int i) {
		this.spacing = i;
	}

	/**
	 * Spacing between the seperate lines
	 * 
	 * @param d
	 *            spacing factor
	 */
	public void setLineSpacingFactor(double d) {
		this.lineSpacingFactor = d;
	}

	/**
	 * Renders the text around the image.
	 * 
	 * @param g
	 *            compound to render on
	 * @param im
	 *            image to render around
	 */
	private void renderTextAround(GCompound g, GImage im) {

		// Stringtokenizer to split the input.
		// Is splitting after space and new line character so we get the output
		// the text desires. Also we include the delimeters so
		// we can handle the new line characters.
		StringTokenizer t = new StringTokenizer(text, "\n"); // extract each
																// sentence
		String currentToken;
		ArrayList<StringTokenizer> tokenizers = new ArrayList<StringTokenizer>();

		while (t.hasMoreTokens()) {
			currentToken = t.nextToken(); // currentToken holds a full sentence
			tokenizers.add(new StringTokenizer(currentToken));

		}

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
		int stringLenght;

		StringTokenizer currentTokenizer;
		for (int i = 0; i < tokenizers.size(); i++) {
			currentTokenizer = tokenizers.get(i);

			while (currentTokenizer.hasMoreElements()) {
				
				currentLabel.setFont(font);
				currentToken = currentTokenizer.nextToken();

				// Wrapping Logic
				if (yPos <= (im.getHeight() + 2 * border + spacing)) {

					// temp label used to check if current token can be added
					tempLablel.setLabel(currentLabel.getLabel() + " " + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidth) {
						// Add current token to label if it would within the
						// borders
						currentLabel.setLabel(currentLabel.getLabel() + currentToken + " ");
					} else {
						// If it would not fit add the label to the list and
						// create a
						// new label that starts with the current token
						currentLabel.setLabel(currentLabel.getLabel().trim());
						labels.add(currentLabel);
						yPos += currentLabel.getHeight() * lineSpacingFactor;
						currentLabel = new GLabel(currentToken + " ", xPos, yPos);
					}

				} else {

					currentLabel.setLocation(xPosBelowPicture, yPos);

					tempLablel.setLabel(currentLabel.getLabel() + " " + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidthBelowPicture) {
						currentLabel.setLabel(currentLabel.getLabel() + currentToken + " ");
					} else {
						labels.add(currentLabel);
						yPos += (currentLabel.getHeight() * lineSpacingFactor);
						currentLabel = new GLabel(currentToken + " ", xPosBelowPicture, yPos);
					}
				}
				currentLabel.setLabel(currentLabel.getLabel().trim());
				labels.add(currentLabel);

			}
			
			if (yPos <= (im.getHeight() + 2 * border + spacing)) {
				yPos += currentLabel.getHeight() * lineSpacingFactor;
				currentLabel = new GLabel("", xPos, yPos);
			} else {
				yPos += currentLabel.getHeight() * lineSpacingFactor;
				currentLabel = new GLabel("", xPosBelowPicture, yPos);
			}
		}

		// Add all labels in one run
		for (GLabel gLabel : labels) {
			// Print out all labels
			System.out.println("Added Label: [" + gLabel.getLabel() + "]");
			g.add(gLabel);
		}
		// g.add(new GLabel("", 0, 0));
	}

	/**
	 * Creates a new instance with the picture and the text rendered around it
	 * using the current state of the attributes.
	 * 
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
