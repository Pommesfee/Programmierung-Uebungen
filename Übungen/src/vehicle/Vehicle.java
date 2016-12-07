package vehicle;

/**
 * Class that defines all information 
 * that all vehicles should share and also offers
 * some utility like converting km/h to knots.
 *  
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
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
	
	/**
	 * Text that describes the model of a car for example
	 */
	protected String description;
	
	/**
	 * Returns the maximum speed of a {@code Vehicle} in km/h.
	 */
	public int getMaximumVelocity() {
		return (int) (Math.cbrt((2*power)/(elementDensity*frontSurface*dragCoefficient)) * 3.6);
	}
	
	/**
	 * Converts the power of a car from PS to Watt.
	 * @param power Power in PS
	 * @return Power in Watt
	 */
	public static double convertPStoWatt(double power) {
		return (power * 735.49875);
	}
	
	/**
	 * Converts the power of a car from Watt to PS.
	 * @param power Power in Watt
	 * @return Power in PS
	 */
	public static double convertWatttoPS(double power) {
		return (power / 735.49875);
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
