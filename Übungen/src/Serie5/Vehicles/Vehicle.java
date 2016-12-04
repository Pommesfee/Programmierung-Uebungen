package Serie5.Vehicles;

public abstract class Vehicle {

	/**
	 * P - Power
	 * in Watt
	 */
	protected double power;
	/**
	 * A - Front surface
	 * in m^2
	 */
	protected double frontSurface;
	/**
	 * cw - Drag coefficient
	 */
	protected double dragCoefficient;
	/**
	 * p - Density of surrounding element
	 */
	protected double elementDensity;
	
	public abstract int getMaximumVelocity();
	
	/**
	 * Converts the power of a car from PS to Watt.
	 * @param power Power in PS
	 * @return Power in Watt
	 */
	public static double convertPStoWatt(double power) {
		return (power * 735.49875);
	}
	
	/**
	 * Converts km/h to knots.
	 * @param speed Speed in km/h
	 * @return Speed in knots
	 */
	public static double convertKmHtoKnots(double speed) {
		return (speed / 1.85);
	}
}
