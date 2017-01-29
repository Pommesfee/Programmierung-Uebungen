package programming.set11.figures;

import java.awt.Color;
import java.util.ArrayList;
import java.util.StringTokenizer;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;

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
	 * Auch nach überarbeitung des wrapping-algorythmus. Der Test sag, dass er
	 * eine gewisse Zeile nicht finden kann/diese in der Ausgabe des Programms
	 * anders ist als gewünscht. Lasse ich mir die labels alle ausgeben (am Ende
	 * der "rendertextAround"-Methode) stelle Ich fest, dass das vom Test
	 * gewünschte Label existiert und auch genau dem entspricht was der Test
	 * erwartet. zum Beispiel sind keine unerwünschten Leerzeichen vorhanden.
	 * 
	 * Der Fehler, dass zu wenig Labels genutzt werden (14 statt 15) mag wohl
	 * aus dem ersten Fehler resultieren. Temporäre fix auch am ender der oben
	 * genannent methode ist das hinzufügen eines leeren labels..
	 *
	 * //EDIT: Anscheinend gibt die Fehlermeldung nicht den vom Test erwarteten
	 * Text/Label aus, sondern den, welchen ich erzeugt habe. Anhand dessen
	 * fällt mir aber kein Weg ein, wie ich herausfinden kann wie meine Ausgabe
	 * sich von der gewünschten Ausgabe unterscheidet um den Fehler zu
	 * beseitigen. Denn wenn ich die meine Ausgabe mit der default Konfiguration
	 * mit dem Beispiel vergleiche kann ich keine Unterschiede feststellen.
	 * 
	 * //EDIT2: Wenn größere Werte für die width genommen werden kann es zu
	 * einer überschreitung der maximalen string breite kommen. Jedoch kann ich
	 * im code keinen grund dafür erkennen. 
	 * --> Fehler wird erzeugt, da der text mit einer anderen Schriftart gerendert wird
	 * 	als mit der das wrapping berechnet wird.
	 * 
	 * 
	 * Schlussfolgerung:
	 * Wenn bestimmt wird wie viele Token auf ein Label passen wird dazu !nicht! die Schriftart
	 * genutzt mit der am Ende gezeichnet wird, weshalb es aber zu Überschreitungen der maximalen Breite kommen kann.
	 * Jedoch scheint dies nötig zu sein, da meine Ausgabe sonst von der Beispiel-Ausgabe abweicht.
	 * Das hat zur folge, dass dann der Test fehlschlägt und sagt es sind zu wenig Label vorhanden und er kann gleich das
	 * erste Label nicht finden.
	 * 
	 * Wenn ich den Text/Label mit der korrekten Schriftart rendere, erhalte ich ein Wrapping ohne Überschreitungen der maximalen Breite.
	 * Dann kommt es aber dzu, dass der Test trotzdem fehlschlägt, da er im Text ein anderes Wrapping erwartet,
	 * das die Methode des Renderns mit der falschen Schriftart geben würde. Zumindest stimmt hier die Anzahl der verwendeten
	 * Labels.
	 * 
	 * Dabei Handelt es sich um Zeile 188 (tempLablel.setFont(font);)
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
		int yPos = border;

		//draws two lines that indicate the maximum space available for text
		GLine line = new GLine(border + spacing + im.getWidth(), 10, border + spacing + im.getWidth(), im.getHeight());
		line.setColor(Color.GREEN);
		g.add(line);
		line = new GLine(border + spacing + im.getWidth() + maxStringWidth, 10,
		border + spacing + im.getWidth() + maxStringWidth, im.getHeight());
		line.setColor(Color.RED);
		g.add(line);

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
				tempLablel.setFont(font); //test seemingly determines if a
				// string will fit with the default font
				currentToken = currentTokenizer.nextToken();

				// Wrapping Logic						// If we want to be more like the example
				if (yPos <= (im.getHeight() + border /* + currentLabel.getHeight() */+ spacing)) {

					// temp label used to check if current token can be added
					tempLablel.setLabel(currentLabel.getLabel() + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidth) {
						// Add current token to label if it would within the
						// borders
						currentLabel.setLabel(currentLabel.getLabel() + currentToken + " ");
					} else {
						// If it would not fit add the label to the list and
						// create a
						// new label that starts with the current token
						if (!currentLabel.getLabel().equals("")) {
							currentLabel.setLabel(currentLabel.getLabel().trim());
							labels.add(currentLabel);
							yPos += (currentLabel.getHeight() * lineSpacingFactor);
						}
						currentLabel = new GLabel(currentToken + " ", xPos, yPos);
					}
					// If label goes below the picture
				} else {

					currentLabel.setLocation(xPosBelowPicture, yPos);
					// temp label used to check if current token can be added
					tempLablel.setLabel(currentLabel.getLabel() + currentToken);
					stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());

					if (stringLenght <= maxStringWidthBelowPicture) {
						// Add current token to label if it would within the
						// borders
						currentLabel.setLabel(currentLabel.getLabel() + currentToken + " ");
					} else {
						// If it would not fit add the label to the list and
						// create a
						// new label that starts with the current token
						if (!currentLabel.getLabel().equals("")) {
							currentLabel.setLabel(currentLabel.getLabel().trim());
							labels.add(currentLabel);
							yPos += (currentLabel.getHeight() * lineSpacingFactor);
						}
						currentLabel = new GLabel(currentToken + " ", xPosBelowPicture, yPos);
					}
				}

			}
			// If the last sentence (\n has not been reached)
			// create a new empty label (line)
			if (i != tokenizers.size() - 1) {

				// bugfix
				currentLabel.setFont(font);

				// Empty label next to picture
				if (yPos <= (im.getHeight() + border + currentLabel.getHeight() + spacing)) {
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
			
			if (gLabel.getY() <= (im.getHeight() + border + currentLabel.getHeight() + spacing)) {
				System.out.print(gLabel.getFontMetrics().stringWidth(gLabel.getLabel()) + "/" + maxStringWidth);
				System.out.println(" " + gLabel.getLabel());
			} else {
				System.out.print(gLabel.getFontMetrics().stringWidth(gLabel.getLabel()) + "/" + maxStringWidthBelowPicture);
				System.out.println(" " + gLabel.getLabel());
			}
			//System.out.println(gLabel.getFont().getFontName());
			
			g.add(gLabel);
		}
		
		System.out.println(currentLabel.getFontMetrics().stringWidth(" "));
		System.out.println(currentLabel.getFontMetrics().stringWidth("the"));
		System.out.println(currentLabel.getFontMetrics().stringWidth("heavy"));
		System.out.println(currentLabel.getFontMetrics().stringWidth("The alley was unusually dark"));
		System.out.println(currentLabel.getFontMetrics().stringWidth("The alley was unusually"));

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