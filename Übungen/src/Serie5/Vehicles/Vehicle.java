package Serie5.Vehicles;

public abstract class Vehicle {

	protected double P; // Power of a vehicle in Watt
	protected double A; // In m^2
	protected double cw; //dragCoefficient
	protected double p; // air/water
	
	public abstract int getMaximumVelocity();
	
}
