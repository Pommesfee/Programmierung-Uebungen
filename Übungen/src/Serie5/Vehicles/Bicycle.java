package Serie5.Vehicles;

public class Bicycle extends Vehicle {

	public static final int OBERLENKER_RENNRAD = 0;
	public static final int UNTERLENKER_RENNRAD = 1;
	public static final int HOLLANDRAD = 2;
	
	private double a;
	private double b;
	private double cd;
	//TODO calculate cd and A
	
	public Bicycle(int mode) {
		this.power = 160;
		setValues(mode);
	}
	
	private void setValues(int mode) {
		switch (mode) {
		case 0:
			a = 277.376;
			b = 3.078;
			break;
		case 1:
			a = 399.611;
			b = 4.4226;
			break;
		case 2:
			a = 181.0455;
			b = 3.3899;
			break;
		default:
			System.out.println("Error");
			return;
		}
		dragCoefficient = 1.2;
	}

	@Override // m/s
	public int getMaximumVelocity() {
		return (int) (Math.cbrt(a+Math.sqrt(Math.pow(a, 2) + Math.pow(b, 3))) + (Math.cbrt(a-Math.sqrt(Math.pow(a, 2) + Math.pow(b, 3)))) - ((2/3.) * (0.1/power)));
	}

	
	
}
