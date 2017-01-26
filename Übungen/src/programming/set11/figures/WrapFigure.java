package programming.set11.figures;

import java.awt.Dimension;
import java.util.StringTokenizer;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class WrapFigure {
	
	private int border = 10;
	private int spacing = 10;
	private double lineSpacingFactor;
	
	private int width;
	private String wrapFigureFilename = "tinlh01w.jpg";
	private double scale;
	private String text;
	private String font = "Serif-12";
	
	WrapFigure(int width, String wrapFigureFilename, double scale, String text) {
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
		StringTokenizer t = new StringTokenizer(text);
		
		int stringSpaceWidth = (int) (width - im.getWidth() - (2*border));
		
		GLabel l = new GLabel("");
		while (t.hasMoreTokens()) {
			l.setLabel(l.getLabel() + t);
			int i = l.getFontMetrics().stringWidth(l.getLabel());
			if() {
				//TODO
				//Check how many words fit in a line
				//after that create a new glabel and start again until no tokens are left
				//List of glabels ?
			}
		}
		
	}
	
	public GCompound getCompound() {
		GCompound g = new GCompound();
		GImage im = new GImage(wrapFigureFilename);
		im.setBounds(border, border, im.getWidth()*scale, im.getHeight()*scale);
		g.add(im);
		renderTextAround(g, im);
		return g;
	}
	
}
