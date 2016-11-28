package Serie5.Vehicles;

/**
 * Class that represents a Car.
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class Car extends Vehicle {
	
	/**
	 * A new {@code Car} will be created.
	 * @param power Power in PS
	 */
	public Car(double power) {
		this.power = convertPStoWatt(power);
		this.frontSurface = 2.5;
		this.elementDensity = 1.3;
		this.dragCoefficient = 0.35;
	}
	
	/**
	 * Converts the power of a car from PS to Watt.
	 * @param power Power in ps
	 * @return Power in Watt
	 */
	private double convertPStoWatt(double power) {
		return (power * 735.49875);
	}
	
	/**
	 * Returns the maximum speed of a {@code Car} in km/h.
	 */
	@Override
	public int getMaximumVelocity() {
		return (int) Math.cbrt((2*power)/(elementDensity*frontSurface*dragCoefficient));
	}

}
