package programming.set7.statistics;

import java.util.LinkedList;

public class StatisticsCollector {

	LinkedList<Double> numbers = new LinkedList<Double>();
	
	public StatisticsCollector(double... items) {
		for (int i = 0; i < items.length; i++) {
			numbers.add(items[i]);
		}
	}
	
	public void addItem(double item) {
		numbers.add(item);
	}
	
	public int getCount() {
		return numbers.size();
	}
	
	public double getSum() {
		double sum = 0;
		for (int i = 0; i < numbers.size(); i++) {
			sum += numbers.get(i);
		}
		return sum;
	}
	
	public double getMinimum() {
		double min = Double.MAX_VALUE;
		if (numbers.size() == 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i) < min) {
					min = numbers.get(i);
				}
			}
			return min;
		}
	}
	
	public double getMaximum() {
		double max = Double.MIN_VALUE;
		if (numbers.size() == 0) {
			return Double.NEGATIVE_INFINITY;
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i) > max) {
					max = numbers.get(i);
				}
			}
			return max;
		}
	}
	
	public double getAverage() {
		if (numbers.size() == 0) {
			return 0;
		} else {
			return getSum() / numbers.size();
		}
	}
				  
	public double getStandardDeviation() {
		if (numbers.size() == 0) {
			return 0;
		} else {
			double x= getAverage();
			double variance = 0;
			for (int i = 0; i < numbers.size(); i++) {
				variance += Math.pow((numbers.get(i) - x), 2);
			}
			variance /= numbers.size();
			return Math.sqrt(variance);
		}
	}
}
