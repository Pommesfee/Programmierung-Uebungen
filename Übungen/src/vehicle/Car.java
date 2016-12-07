package vehicle;

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
	public Car(String description, double power) {
		this.description = description;
		this.power = Vehicle.convertPStoWatt(power);
		this.frontSurface = 2.5;
		this.dragCoefficient = 0.35;
		this.elementDensity = 1.3;
	}
	
	@Override
	public String toString() {
		return this.description +  " (" + Vehicle.convertWatttoPS(this.power) + "): " + this.getMaximumVelocity() + "km/h";
	}

}
