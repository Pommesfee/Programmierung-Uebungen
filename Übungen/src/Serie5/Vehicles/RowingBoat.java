package Serie5.Vehicles;

public class RowingBoat extends Vehicle {
	
	private static final int powerPerRower = 100; //Watt
	
	public RowingBoat(int rowerCount, double width, double drought) { 
		this.P = calculatePower(rowerCount);
		this.cw = 0.3;
		this.p = 1.028;
		this.A = calculateFrontSurface(width, drought);
	}
	
	private double calculatePower(double rowerCount) {
		return rowerCount * powerPerRower;
	}
	
	private double calculateFrontSurface(double width, double drought) {
		return (0.5 * width * drought);
	}
	
	/**
	 * Converts km/h to knots.
	 * @param speed Speed in km/h
	 * @return Speed in knots
	 */
	private double convertKmHtoKnots(double speed) {
		return (speed / 1.85);
	}
	
	@Override
	public int getMaximumVelocity() {
		return (int) convertKmHtoKnots(Math.cbrt((2*P)/(p*A*cw)));
	}

}
