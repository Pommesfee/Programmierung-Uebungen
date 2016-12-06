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
	 * - super: Keyword for calling constructors and method of the direct superclass. 
	 * 			super() or super(type argument) would call a constructor of a superclass.
	 * 			super.methodOfSuperClass() would call a method of a super class.
	 * 
	 * - this: Used to reference instance variables and constructors of the current object.
	 * 			in the constructor of a class you could call another constructor of the same class with this().
	 * 			this.x would specifically reference the instance variable of a class to avoid shadowing with local variables.
	 * 
	 * - static: Declares that a method or variable can be accessed outside of object context. no instance of an object is needed.
	 * 			static int i; would we a static variable that can be accessed without needing an instance of the class i is defined in.
	 * 			static int getI(); the same principle as above applies with getI() being a static method.
	 * 
	 * - null: "Placeholder" that is used when a variable does not hold a reference to an object
	 * 			Object o; when the variable o is declared, because we do not initialize it with new, it would be initialized with null instead,
	 * 			because it does not hold a reference to an object.
	 * 
	 * - Local variable: Variable that is declared inside a code-block and lives on the stack until the end of the block is reached.
	 * 					 The scope of the local variable extends to all code blocks that lie within the code block the variable is declared in.
	 * 					 public void someMethod() {
	 * 						int someLocalVariable;
	 * 					 }
	 * 
	 * - Instance variable: Variable that each instance/object of a class has its own "version" of. They are declared directly inside the class.
	 * 						public Class TestClass {
	 * 							int someInstanceVariable;
	 * 						}
	 * 
	 * - Class variable: A static variable that has no relation to an instance of the class, only to the class itself. 
	 * 					 Class variables are initialized when the class is first used during the runtime of a program.
	 * 						public Class TestClass {
	 * 							static int someInstanceVariable;
	 * 						} 
	 * 
	 * - Constructor: Instantiates a new object from the "blueprint" of a class. Every class has a default constructor with
	 * 				  an empty parameter list if no constructor is explicitly defined.
	 * 						public Class TestClass {
	 * 							public TestClass() {
	 * 								//when the constructor is called
	 * 								//we could do things to give the object a
	 * 								//specific state
	 * 							}
	 * 							public TestClass(int i) {
	 * 								// also a constructor of TestClass. Constructors can be overloaded like methods.
	 * 							}
	 * 						} 		  
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
	/**
	 * @see java.lang.Object#toString()
	 */
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
