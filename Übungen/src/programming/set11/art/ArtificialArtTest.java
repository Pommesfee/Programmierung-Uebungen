package programming.set11.art;

import acm.program.GraphicsProgram;

public class ArtificialArtTest extends GraphicsProgram {

	@Override
	public void run() {
		
		ArtificialArt a = new ArtificialArt(5, 3, 250);
		setSize((int)a.getWidth(), (int)a.getHeight());
		
		add(a);
	}
}
