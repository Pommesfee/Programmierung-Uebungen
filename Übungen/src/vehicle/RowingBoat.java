package vehicle;

/**
 * Repesents a rowing boat.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class RowingBoat extends Vehicle {

	private static final int POWER_PER_ROWER = 100;

	private int manRowing;
	private double width;
	private double draught;
	
	/**
	 * A new {@code RowingBoat} will be created.
	 * @param manRowing The amount of rowers the boat has
	 * @param width The width of the boat
	 * @param draught the draught of the boat
	 */
	public RowingBoat(String description, int manRowing, double width, double draught) {
		this.description = description;
		this.frontSurface = calculateFrontSurface(width, draught);
		this.dragCoefficient = 0.3;
		this.elementDensity = (1.028 * 1000); //*1000 because the ships formula for p(elementDensity) calculates with tons per m^3 and if we use the car formula we need to have kg/m^3.
		this.width = width;
		this.draught = draught;
		this.manRowing = manRowing;
		this.power = (manRowing * POWER_PER_ROWER);
	}
	
	/**
	 * Calculates the front surface of a {@code RowingBoat}.
	 * @param width
	 * @param draught
	 * @return Area of front surface
	 */
	private double calculateFrontSurface(double width, double draught) {
		return ((1/2) * width * draught);
	}
	
	@Override
	public String toString() {
		return this.description +  " (" + this.manRowing + ", b = " + this.width + ", h = " + this.draught + "): " + this.getMaximumVelocity() + "km/h (" + Vehicle.convertKmHtoKnots(this.getMaximumVelocity()) + "kts)";
	}
}
