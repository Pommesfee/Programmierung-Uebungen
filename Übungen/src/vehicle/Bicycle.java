package vehicle;

public class Bicycle extends Vehicle {

	public static final int OBERLENKER_RENNRAD = 0;
	public static final int UNTERLENKER_RENNRAD = 1;
	public static final int HOLLANDRAD = 2;
	
	private double a;
	private double b;
	private double cdA; // cd * A = effective headsurface, already given by task
	
	public Bicycle(String description, int mode) {
		this.description = description;
		this.power = 160;
		setValues(mode);
	}
	
	/**
	 * Method to set the vehicles for each bicycle depending
	 * on which model is selected.
	 * @param mode The model of the Bike. Use constants from bicycle class.
	 */
	private void setValues(int mode) {
		switch (mode) {
		case 0:
			a = 277.376;
			b = 3.078;
			cdA = 0.4891;
			break;
		case 1:
			a = 399.611;
			b = 4.4226;
			cdA = 0.3397;
			break;
		case 2:
			a = 181.0455;
			b = 3.3899;
			cdA = 0.7457;
			break;
		default:
			System.out.println("Error");
			return;
		}
		power = 1.2;
	}

	/**
	 *  @return The maximum speed of a {@code Bicycle} in km/h.
	 */
	@Override
	public int getMaximumVelocity() {
		return (int) ((Math.cbrt(a+Math.sqrt(Math.pow(a, 2) + Math.pow(b, 3))) + (Math.cbrt(a-Math.sqrt(Math.pow(a, 2) + Math.pow(b, 3)))) - ((2/3.) * (0.1/cdA*elementDensity))) * 3.6);
	}
	
	@Override
	public String toString() {
		return  "Bicycle (" + this.description + "): " + this.getMaximumVelocity() + "km/h";
	}
	
}
