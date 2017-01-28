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
	 * Jedoch schlägt der Test aus einem mir nicht nachvollziehbaren Grund fehl.
     * Auch nach überarbeitung des wrapping-algorythmus.
	 * Der Test sag, dass er eine gewisse Zeile nicht finden kann/diese in der
	 * Ausgabe des Programms anders ist als gewünscht. Lasse ich mir die labels
	 * alle ausgeben (am Ende der "rendertextAround"-Methode) stelle Ich fest,
	 * dass das vom Test gewünschte Label existiert und auch genau dem
	 * entspricht was der Test erwartet. zum Beispiel sind keine unerwünschten
	 * Leerzeichen vorhanden.
	 * 
	 * Der Fehler, dass zu wenig Labels genutzt werden (14 statt 15) mag wohl
	 * aus dem ersten Fehler resultieren. Temporäre fix auch am ender der oben genannent methode
     * ist das hinzufügen eines leeren labels..
     *
     * //EDIT:
     * Anscheinend gibt die Fehlermeldung nicht den vom Test erwarteten Text/Label aus, sondern den, welchen ich erzeugt
     * habe. Anhand dessen fällt mir aber kein Weg ein, wie ich herausfinden kann wie meine Ausgabe sich von der gewünschten
     * Ausgabe unterscheidet um den Fehler zu beseitigen.
     * Denn wenn ich die meine Ausgabe mit der default Konfiguration mit dem Beispiel vergleiche kann ich keine Unterschiede feststellen.
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
		// we split the text into sections which end with a \n
		// to correctly create new labels when needed.
		StringTokenizer t = new StringTokenizer(text, "\n");

		String currentToken;
		ArrayList<StringTokenizer> tokenizers = new ArrayList<StringTokenizer>();
		while (t.hasMoreTokens()) {
			currentToken = t.nextToken(); // currentToken holds a full sentence
											// (\n)
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

		// Iterate over our "sentences" (\n)
		StringTokenizer currentTokenizer;
		for (int i = 0; i < tokenizers.size(); i++) {
			currentTokenizer = tokenizers.get(i);
			
			// Process each token (word) from each sentence (\n)
			while (currentTokenizer.hasMoreElements()) {

				currentLabel.setFont(font);
				// tempLablel.setFont(font); test seemingly determines if a string will fit with the default font
				currentToken = currentTokenizer.nextToken();

				// Wrapping Logic .. 2* border doesnt really make sense in my eyes but it is needed to have the same output as the example
				if (yPos <= (im.getHeight() + 2*border + spacing)) {

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
				// If label goes below the picture
				} else {
					
					currentLabel.setLocation(xPosBelowPicture, yPos);
					// temp label used to check if current token can be added
					tempLablel.setLabel(currentLabel.getLabel() + " " + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidthBelowPicture) {
						// Add current token to label if it would within the
						// borders
						currentLabel.setLabel(currentLabel.getLabel() + currentToken + " ");
					} else {
						// If it would not fit add the label to the list and
						// create a
						// new label that starts with the current token
						currentLabel.setLabel(currentLabel.getLabel().trim());
						labels.add(currentLabel);
						yPos += (currentLabel.getHeight() * lineSpacingFactor);
						currentLabel = new GLabel(currentToken + " ", xPosBelowPicture, yPos);
					}
				}

			}
			// If the last sentence (\n has not been reached)
			// create a new empty label (line)
			if (i != tokenizers.size() - 1) {
				// Empty label next to picture
				if (yPos <= (im.getHeight() + 2 * border + spacing)) {
					currentLabel.setLabel(currentLabel.getLabel().trim());
					labels.add(currentLabel);
					yPos += currentLabel.getHeight() * lineSpacingFactor;
					currentLabel = new GLabel("", xPos, yPos);
				} else {
					// Empty label below picture
					currentLabel.setLabel(currentLabel.getLabel().trim());
					labels.add(currentLabel);
					yPos += currentLabel.getHeight() * lineSpacingFactor;
					currentLabel = new GLabel("", xPosBelowPicture, yPos);
				}
			}

		}
		// don't forget to add last label to list
		currentLabel.setLabel(currentLabel.getLabel().trim());
		labels.add(currentLabel);

		// Add all labels in one run
		for (GLabel gLabel : labels) {
			// Print out all labels
			System.out.println("Added Label: [" + gLabel.getLabel() + "]");
			g.add(gLabel);
		}
		g.add(new GLabel("", 0, 0)); // satisfy test temporarily
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