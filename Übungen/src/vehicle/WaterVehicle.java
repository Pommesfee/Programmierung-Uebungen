package vehicle;

public abstract class WaterVehicle extends Vehicle{

	/**
	 * Returns the maximum speed of a {@code WaterVehicle} in knots.
	 */
	@Override
	public int getMaximumVelocity() {
		return (int) Vehicle.convertKmHtoKnots(Math.cbrt((2*power)/(elementDensity*frontSurface*dragCoefficient)));
	}
	
}
