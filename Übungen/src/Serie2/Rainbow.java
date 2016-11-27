package Serie2;

import java.awt.Color;

import acm.graphics.GArc;
import acm.program.GraphicsProgram;

/*
 * Programm, dass einen Regenbogen zeichnet
 */
public class Rainbow extends GraphicsProgram {

    public void run() {
        
    	//Anpassen der größe des Applets
    	this.setSize(500, 500);
    	//Setzen der Farbe auf den in der Übung gegeben Wert
        this.setBackground(new Color(157, 217, 237));
        
        /*
         * Erstellen der einzelnen Bögen, welche durch ihe Koordinaten und größe dann einen
         * Regenbogen formen.
         * Der Bogen arcSkyCheat sorgt für die Illusion eines durchgehenden Himmels, da die einzelnen
         * Bögen in Wirklichkeit gefüllt sind. 
         */
        
        GArc arcSkyCheat = new GArc(110, 210, 280, 280, 10, 160);
        arcSkyCheat.setFilled(true);
        arcSkyCheat.setColor(new Color(157, 217, 237));
        
        GArc arcBlue = new GArc(100, 200, 300, 300, 10, 160);
        arcBlue.setFilled(true);
        arcBlue.setColor(Color.BLUE);
        
        GArc arcLightBlue = new GArc(90, 190, 320, 320, 10, 160);
        arcLightBlue.setFilled(true);
        arcLightBlue.setColor(Color.CYAN);
        
        GArc arcGreen = new GArc(80, 180, 340, 340, 10, 160);
        arcGreen.setFilled(true);
        arcGreen.setColor(Color.GREEN);
        
        GArc arcYellow = new GArc(70, 170, 360, 360, 10, 160);
        arcYellow.setFilled(true);
        arcYellow.setColor(Color.YELLOW);
        
        
        GArc arcOrange = new GArc(60, 160, 380, 380, 10, 160);
        arcOrange.setFilled(true);
        arcOrange.setColor(Color.ORANGE);
        
        GArc arcRed = new GArc(50, 150, 400, 400, 10, 160);
        arcRed.setFilled(true);
        arcRed.setColor(Color.RED);
        
        /*
         *  Hier werden die einzelnen Objekte/Bögen hinzugefügt
         * 	Beachte die Reihenfolge, da einzelne Bögen sonst überdeckt werden könnten.
         */
        add(arcRed);
        add(arcOrange);
        add(arcYellow);
        add(arcGreen);
        add(arcLightBlue);
        add(arcBlue);
        add(arcSkyCheat);
        
    }
    
}