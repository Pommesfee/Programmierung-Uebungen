package Serie5.Vehicles;

public abstract class Vehicle {

	protected double power; // Power of a vehicle in Watt
	protected double frontSurface; // In m^2
	protected double dragCoefficient;
	protected double elementDensity; // air/water
	
	public abstract int getMaximumVelocity();
	
}
