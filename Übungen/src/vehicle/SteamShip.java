package vehicle;

/**
 * Class that represents a Steamship.
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class SteamShip extends Vehicle {
	
	private double displacementTonnage;
	private double length;
	
	/**
	 * A new {@code SteamShip} will be created.
	 * @param displacementTonnage The amount of water the ship displaces
	 * @param length the length of the ship
	 */
	public SteamShip(String description, double power, double displacementTonnage, double length) {
		this.description = description;
		this.power = power;
		this.dragCoefficient = 0.3;
		this.elementDensity = (1.028 * 1000); //*1000 because the ships formula for p(elementDensity) calculates with tons per m^3 and if we use the car formula we need to have kg/m^3.
		this.frontSurface = calculateFrontSurface(displacementTonnage, length);
		this.displacementTonnage = displacementTonnage;
		this.length = length;
	}
	
	/**
	 * Calculates the front surface of a {@code SteamShip}.
	 * @param displacementTonnage The amount of water the ship displaces
	 * @param length The length of the ship
	 * @return Area of front surface
	 */
	private double calculateFrontSurface(double displacementTonnage, double length) {
		return displacementTonnage/length;
	}
	
	@Override
	public String toString() {
		return this.description +  " (" + this.power + " PS, "+ this.displacementTonnage + "m^3, " + this.length + "m): " + this.getMaximumVelocity() + "km/h (" + Vehicle.convertKmHtoKnots(this.getMaximumVelocity()) + "kts)";
	}
}
