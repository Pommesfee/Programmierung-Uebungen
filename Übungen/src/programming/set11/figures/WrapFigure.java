package programming.set11.figures;

import java.awt.Color;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.StringTokenizer;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;

public class WrapFigure {

	private int border = 10;
	private int spacing = 10;
	private double lineSpacingFactor = 1.2;

	private int width;
	private String wrapFigureFilename = "tinlh01w.jpg";
	private double scale;
	private String text;
	private String font = "Serif-12";

	public WrapFigure(int width, String wrapFigureFilename, double scale, String text) {
		this.width = width;
		this.wrapFigureFilename = wrapFigureFilename;
		this.scale = scale;
		this.text = text;
	}

	public void setTextFont(String s) {
		this.font = s;
	}

	public void setBorder(int i) {
		this.border = i;
	}

	public void setSpacing(int i) {
		this.spacing = i;
	}

	public void setLineSpacingFactor(double d) {
		this.lineSpacingFactor = d;
	}

	private void renderTextAround(GCompound g, GImage im) {
		
		System.out.println("Rendering text around.");
		StringTokenizer t = new StringTokenizer(text);

		int xPos = (int) (border + im.getWidth() + spacing);
		int xPosBelowPicture = border;
		
	    int maxStringWidth = width - xPos - border;
		int maxStringWidthBelowPicture = width - xPosBelowPicture - border;
		
		int yPos = border; // ++
		
		GLine line = new GLine(border + spacing + im.getWidth(), 10, border + spacing + im.getWidth(), im.getHeight());
		line.setColor(Color.GREEN);
		g.add(line);
		line = new GLine(border + spacing + im.getWidth() + maxStringWidth, 10, border + spacing + im.getWidth() + maxStringWidth, im.getHeight());
		line.setColor(Color.RED);
		g.add(line);
				
		line = new GLine(border, im.getHeight() + border + spacing, border, 500);
		line.setColor(Color.GREEN);
		g.add(line);
		
		line = new GLine(border + maxStringWidthBelowPicture, im.getHeight() + border + spacing, border + maxStringWidthBelowPicture, 500);
		line.setColor(Color.RED);
		g.add(line);
		
		ArrayList<GLabel> labels = new ArrayList<GLabel>();

		// TODO When string goes under the pic and not bnesides it !

		GLabel currentLabel = new GLabel("", xPos, yPos);
		yPos += currentLabel.getAscent();
		currentLabel.setLocation(xPos, yPos); 
		GLabel tempLablel = new GLabel("");
		String currentToken;
		int stringLenght;
		
		
		while (t.hasMoreTokens()) {
			currentLabel.setFont(font);
			// möglicher Fehler in der Aufgabe..
			tempLablel.setFont(font);
			currentToken = t.nextToken();
			System.out.println("New Token [" + currentToken + "]");
			
			//TODO look at case when only one word is in label and is exceeding linewidth
			
			// Check which maximum string with needs to be used
			// right of picture or below ?
			if (yPos <= (im.getHeight() + border + spacing)) {
				
				// temp label used to check if current token can be added
				tempLablel.setLabel(currentLabel.getLabel() + " " + currentToken);
				stringLenght = tempLablel.getFontMetrics().stringWidth(tempLablel.getLabel());
				
				if (stringLenght <= maxStringWidth) {
					// Add current token to label if it would within the borders
					currentLabel.setLabel(currentLabel.getLabel() + currentToken + " ");
				} else {
					// If it would not fit add the label to the list and create a
					// new label that starts with the current token
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
		}
		labels.add(currentLabel);
		
		for (GLabel gLabel : labels) {
			g.add(gLabel);
			System.out.println("Added label [" + gLabel.getLabel() + "]");
		}
	}

	public GCompound getCompound() {
		System.out.println("Creating new compound.");
		GCompound g = new GCompound();
		GImage im = new GImage(wrapFigureFilename);
		im.setBounds(border, border, im.getWidth() * scale, im.getHeight() * scale);
		g.add(im);
		renderTextAround(g, im);
		return g;
	}

}
