package programming.set11.figures;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class WrapFigure {
	
	private int border;
	private int spacing;
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
	
	private void renderTextAround(GCompound g, int imageWidth) {
		//TODO
	}
	
	public GCompound getCompound() {
		GCompound g = new GCompound();
		GImage im = new GImage(wrapFigureFilename);
		im.setBounds(border, border, im.getWidth()*scale, im.getHeight()*scale);
		g.add(im);
		return g;
	}
	
}
