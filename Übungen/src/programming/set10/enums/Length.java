package programming.set10.enums;

/**
 * Enum that can be used to
 * convert different length units.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public enum Length {

	ARSHIN("Arshin", 0.71),
	FINGER("Finger", 0.022225),
	METRE("Metre", 1),
	HORSE_LENGTH("Horse Length", 2.4),
	PARSEC("Parsec", 30856776000000000d),
	PLUTO_RADIUS("Pluto Radius", 1186000);
	
	String name;
	double lengthInMetres;
	
    private Length (String name, double lengthInMeters) {
        this.name = name;
        this.lengthInMetres = lengthInMeters;
    }
	
	/**
	 * Returns how much one of this unit is in metres.
	 * 
	 * @return one unit in metres.
	 */
	public double getUnitLengthInMetres() {
	    return lengthInMetres;
	}
	
	/**
	 * Converts the given amount measured in the current length unit to how much it would be
	 * in the target unit.
	 * 
	 * @param targetLength
	 *            the target unit of length.
	 * @param amount
	 *            the length to convert to the target length.
	 * @return the corresponding length in the target unit.
	 */
	public double convertTo(Length targetLength, double amount) {
		// If current unit and target unit are the same
		// we do not need to convert
	    if (this == targetLength) {
	    	return amount;
	    } else if (this == METRE) { // If the current unit is metre we only need to convert
	    	return (amount / targetLength.lengthInMetres);
	    } else { // Else we would need to convert to metre first
	    	double toMeter = amount * lengthInMetres;
	    	return (toMeter / targetLength.lengthInMetres);
	    }
	}
}
