package Serie5.Vehicles;

/**
 * Class that represents a Steamship
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class SteamShip extends WaterVehicle {
	
	/**
	 * A new {@code SteamShip} will be created.
	 * @param displacementTonnage
	 * @param length
	 */
	public SteamShip(double power, double displacementTonnage, double length) {
		this.power = power;
		this.dragCoefficient = 0.3;
		this.elementDensity = (1.028 * 1000); //*1000 because the ships formula for p(elementDensity) calculates with tons per m^3 and if we use the car formula we need to have kg/m^3.
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
}
