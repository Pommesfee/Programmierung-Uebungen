package Serie5.Vehicles;

public class RowingBoat extends WaterVehicle {
	
	private static final int POWER_PER_ROWER = 100;
	
	public RowingBoat(int manRowing, double width, double draught) {
		this.frontSurface = calculateFrontSurface(width, draught);
		this.dragCoefficient = 0.3;
		this.elementDensity = (1.028 * 1000); //*1000 because the ships formula for p(elementDensity) calculates with tons per m^3 and if we use the car formula we need to have kg/m^3.
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
}
