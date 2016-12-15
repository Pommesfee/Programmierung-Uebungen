package programming.set7.statistics;

import java.util.LinkedList;

/**
 * Offers functionality to get information/statistics about a set(list) of
 * numbers like the sum of all numbers contained in the list.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class StatisticsCollector {

	// List that holds the numbers
	private LinkedList<Double> numbers = new LinkedList<Double>();

	/**
	 * Creates a new instance and adds all numbers supplied in the parameter
	 * list to the list.
	 * 
	 * @param items
	 */
	public StatisticsCollector(double... items) { // Var-args. The user could
													// call this constructor and
													// decided how many
													// arguments (including 0)
													// he wants to provide

		for (int i = 0; i < items.length; i++) {
			numbers.add(items[i]);
		}
	}

	/**
	 * Adds an item to the numbers list.
	 * @param item Item to be added
	 */
	public void addItem(double item) {
		numbers.add(item);
	}

	/**
	 * Returns how many numbers are stored in the numbers list.
	 * @return Count of numbers in numbers list
	 */
	public int getCount() {
		return numbers.size();
	}

	/**
	 * Calculates the sum of all numbers in the list.
	 * @return The sum of all numbers in the list
	 */
	public double getSum() {
		// self-explanatory
		double sum = 0;
		for (int i = 0; i < numbers.size(); i++) {
			sum += numbers.get(i);
		}
		return sum;
	}

	/**
	 * Returns the smallest number contained in numbers list.
	 * @return Smallest number from list
	 */
	public double getMinimum() {
		double min = Double.MAX_VALUE;
		if (numbers.size() == 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i) < min) { // Checks if value at  index i is smaller than the value before
											// and if so sets the minimum value to the new value.
											// Until all values have been checked and the smallest found.
					min = numbers.get(i);
				}
			}
			return min;
		}
	}

	/**
	 * Returns the biggest number that is contained in the numbers list.
	 * @return Biggest number from list
	 */
	public double getMaximum() {
		double max = Double.MIN_VALUE;
		if (numbers.size() == 0) {
			return Double.NEGATIVE_INFINITY;
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i) > max) { // Checks if value at  index i is greater than the value before
											// and if so sets the maximum value to the new value.
											// Until all values have been checked and the biggest found.
					max = numbers.get(i);
				}
			}
			return max;
		}
	}

	/**
	 * Calculates the average value of all numbers contained in numbers list
	 * @return the average value of all numbers from the list
	 */
	public double getAverage() {
		if (numbers.size() == 0) {
			return 0;
		} else {
			return getSum() / numbers.size(); // Sum of all elements divided by the amount of elements is the average
		}
	}

	/**
	 * Determines the standard deviation of the elements  of the numbers list.
	 * @return Value representing the standard deviation
	 */
	public double getStandardDeviation() {
		if (numbers.size() == 0) {
			return 0;
		} else {
			// Calculate the variance first
			double x = getAverage();
			double variance = 0;
			for (int i = 0; i < numbers.size(); i++) {
				variance += Math.pow((numbers.get(i) - x), 2);
			}
			variance /= numbers.size();
			// and then return the standard deviation
			return Math.sqrt(variance);
		}
	}
}
