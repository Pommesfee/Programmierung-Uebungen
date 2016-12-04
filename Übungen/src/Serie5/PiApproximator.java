package Serie5;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class PiApproximator extends GraphicsProgram {

	@Override
	public void run() {
		
		
	}
	
	/**
	 * Randomly generates a new point whose x and y coordinates lie between -1
	 * and 1.
	 * 
	 * @return random point.
	 */
	public GPoint randomPoint() {
		return null;
	    // Implement this method
	}

	/**
	 * Checks if the point with the given coordinates is inside the circle with
	 * radius 1 centered at the coordinate system's origin.
	 * 
	 * @param unitPoint
	 *            the point to check.
	 * @return {@code true} if the point is inside the circle, {@code false} if
	 *         it's not.
	 */
	public boolean isInCircle(GPoint unitPoint) {
		return rootPaneCheckingEnabled;
	    // Implement this method
	}
	
}
