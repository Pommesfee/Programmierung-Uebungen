package Serie5.Vehicles;

/**
 * Class that represents a Steamship
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class SteamShip extends Vehicle {
	
	/**
	 * A new {@code SteamShip} will be created.
	 * @param displacementTonnage
	 * @param length
	 */
	public SteamShip(double power, double displacementTonnage, double length) {
		this.power = power;
		this.dragCoefficient = 0.3;
		this.elementDensity = 1.028;
		this.frontSurface = calculateFrontSurface(displacementTonnage, length);
	}
	
	/**
	 * Calculates the front surface of a {@code SteamShip}.
	 * @param displacementTonnage
	 * @param length
	 * @return Area of front surface
	 */
	private double calculateFrontSurface(double displacementTonnage, double length) {
		return displacementTonnage/length;
	}
	
	/**
	 * Converts km/h to knots.
	 * @param speed Speed in km/h
	 * @return Speed in knots
	 */
	private double convertKmHtoKnots(double speed) {
		return (speed / 1.85);
	}
	
	/**
	 * Returns the maximum speed of a {@code SteamShip} in knots.
	 */
	@Override
	public int getMaximumVelocity() {
		return (int) convertKmHtoKnots(Math.cbrt((2*power)/(elementDensity*frontSurface*dragCoefficient)));
	}
	
	

}
