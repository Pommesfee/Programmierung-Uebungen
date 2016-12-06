package programming.set6.date;

/**
 * Represents a Date consisting of the year, month and day (day of the given
 * month). Offers utility to find out how far apart two dates in the same year
 * are and more. Will check if a date is valid on initialization.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class Date {

	/*
	 * Explanation:
	 *  - super 
	 *  - this
	 *  - static 
	 *  - null 
	 *  - Local variable 
	 *  - Instance variable 
	 *  - Class variable 
	 *  - Constructor
	 * 
	 */

	// Constants for easier use. No Javadoc needed.
	private static final int JANUARY = 1;
	private static final int FEBRUARY = 2;
	private static final int MARCH = 3;
	private static final int APRIL = 4;
	private static final int MAY = 5;
	private static final int JUNE = 6;
	private static final int JULY = 7;
	private static final int AUGUST = 8;
	private static final int SEPTEMBER = 9;
	private static final int OKTOBER = 10;
	private static final int NOVEMBER = 11;
	private static final int DECEMBER = 12;

	/**
	 * The year of the date
	 */
	private final int year;
	/**
	 * The month of the date
	 */
	private final int month;
	/**
	 * The day of the date (day counted for each month)
	 */
	private final int day;

	/**
	 * Will create a new {@code Date} object and check if the supplied
	 * parameters are valid.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public Date(int year, int month, int day) {
		validate(year, month, day);
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * Returns the Number of days in a given month and year.
	 * 
	 * @param year
	 *            Has to be greater or equal to Zero
	 * @param month
	 *            1 for January and 12 for December
	 * @return The days of the month in the given year
	 */
	public static int getDaysInMonth(int year, int month) {

		// Check needed because static method can be called without
		// validation check in constructor. Validation method
		// is not applicable here because we do not have a day.
		if (year < 0) {
			return 0;
		}

		// Return the number of days in a given month
		switch (month) {
		case JANUARY:
			return 31;
		case FEBRUARY:
			// Check if we have a leap year and adjust the days of February
			// accordingly
			if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
				return 29;
			} else {
				return 28;
			}
		case MARCH:
			return 31;
		case APRIL:
			return 30;
		case MAY:
			return 31;
		case JUNE:
			return 30;
		case JULY:
			return 31;
		case AUGUST:
			return 31;
		case SEPTEMBER:
			return 30;
		case OKTOBER:
			return 31;
		case NOVEMBER:
			return 30;
		case DECEMBER:
			return 31;
		default:
			return 0;
		}
	}

	/**
	 * Checks if a given date is valid. Will throw an IllegalArgumentException
	 * if date is invalid: Month not between 1 and 12. Day outside of days of
	 * the month. Or negative year.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public static void validate(int year, int month, int day) {
		if (year < 0 || (month < 1 || month > 12)) {
			throw new IllegalArgumentException("This is not a valid Date");
		} else if (getDaysInMonth(year, month) < day || day < 1) { // Else if
																	// only
																	// executed
																	// if first
																	// if
																	// returns
																	// false.
																	// (Legal
																	// year and
																	// month)
			throw new IllegalArgumentException("This is not a valid Date");
		}
	}

	/**
	 * Will return which day of the year it is. Depending on {@code Date}
	 * instance. For example, January 20th is the 20th day of the year, while
	 * May 20th is the 140th day of the year.
	 * 
	 * @return The day of the year
	 */
	public int dayOfYear() {
		int dayOFYear = 0;
		// Add up the amount of days in all month prior to the current month
		for (int i = 1; i < this.month; i++) {
			dayOFYear += getDaysInMonth(year, i);
		}
		// Then the day of the current month is added and the resul is returned
		dayOFYear += day;
		return dayOFYear;
	}

	/**
	 * Returns the difference between two days in the same year. If other lies
	 * in the future, that number is positive; otherwise, it is negative.
	 * 
	 * @param other
	 * @return
	 */
	public int sameYearDiff(Date other) {
		if (year != other.year) {
			return 0; // We only produce an output if we are in the same year
		} else {
			return other.dayOfYear() - dayOfYear();
		}
	}

	/**
	 * Getter for variable year
	 * 
	 * @return The year of the date
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Getter for variable month
	 * 
	 * @return the month of the date
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Getter for variable day
	 * 
	 * @return The day of the date
	 */
	public int getDay() {
		return day;
	}

	// Creates a string representation of the date in the form of
	// November 20, 2016.
	@Override
	public String toString() {

		String month = "";

		switch (this.month) {
		case JANUARY:
			month = "January";
			break;
		case FEBRUARY:
			month = "February";
			break;
		case MARCH:
			month = "March";
			break;
		case APRIL:
			month = "April";
			break;
		case MAY:
			month = "May";
			break;
		case JUNE:
			month = "June";
			break;
		case JULY:
			month = "July";
			break;
		case AUGUST:
			month = "August";
			break;
		case SEPTEMBER:
			month = "September";
			break;
		case OKTOBER:
			month = "Oktober";
			break;
		case NOVEMBER:
			month = "November";
			break;
		case DECEMBER:
			month = "December";
		}

		return month + " " + day + ", " + year;
	}

}
