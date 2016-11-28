package Serie5;

/**
 * This class represents a complex number with it's real
 * and imaginary part and supplies the functionality
 * to do basic mathematical operations like adding and
 * multiplying two complex numbers.
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class ComplexNumber {

	private double re; //The real part of the complex number
	private double im; //The imaginary part of the complex number
	
	/**
	 * Creates a new {@code ComplexNumber} where both the 
	 * real and imaginary part are initialized with zero.
	 */
	public ComplexNumber() {
		this.re = 0;
		this.im = 0;
	}
	
	/**
	 * Creates a new {@code ComplexNumber} with user specified values
	 * for it's real and imaginary part
	 * @param real The value the real part should have
	 * @param imaginary the value the imaginary part should have
	 */
	public ComplexNumber(double real, double imaginary) {
		this.re = real;
		this.im = imaginary;
	}
	
	/**
	 * Creates a new {@code ComplexNumber} that adopts
	 * the real and imaginary part from the complex {@code ComplexNumber}
	 * that is supplied as an argument.
	 * @param cn The complex number to adopt the values from
	 */
	public ComplexNumber(ComplexNumber cn) {
		this.re = cn.getReal();
		this.im = cn.getImaginary();
	}

	/**
	 * Will change a complex number of the form a + bi to a - bi.
	 * @return the conjugated complex number
	 */
	public ComplexNumber conjugate(){
		return new ComplexNumber(getReal(), (getImaginary() * -1));
	}
	
	/**
	 * Wll add two complex numbers.
	 * @param other The complex number that should be added to the complex number this mehtod is called from
	 * @return The result of the addition of both complex numbers
	 */
	public ComplexNumber add(ComplexNumber other) {
		double re = this.getReal() + other. getReal();
		double im = this.getImaginary() + other.getImaginary();
		return new ComplexNumber(re, im);
	}
	
	/**
	 * Will subtract two complex numbers.
	 * @param other The complex number that should be subtracted from the complex number this method is called from
	 * @return The result of the subtraction of both complex numbers
	 */
	public ComplexNumber subtract(ComplexNumber other) {
		double re = this.getReal() - other.getReal();
		double im = this.getImaginary() - other.getImaginary();
		return new ComplexNumber(re, im);
	}
	
	/**
	 * Multiplies two complex numbers.
	 * @param other The complex number that should be multiplyed with the complex number this method is called from
	 * @return The result of the multiplication of both complex numbers
	 */
	public ComplexNumber multiply(ComplexNumber other) {
		
		double re = ((this.getReal() * other.getReal()) - (this.getImaginary() * other.getImaginary()));
		double im = ((this.getImaginary() * other.getReal()) + (this.getReal() * other.getImaginary()));
		return new ComplexNumber(re, im);
	}
	
	/**
	 * Divides two complex numbers.
	 * @param other The complex number that should be used to divide the complex number this method is called from
	 * @return The result of the devision of both complex numbers
	 */
	public ComplexNumber divide(ComplexNumber other) {
		if(other.getReal() == 0 && other.getImaginary() == 0) {
			return null;
		}
		
		double re = (((this.getReal() * other.getReal()) + (this.getImaginary() * other.getImaginary())) / (Math.pow(other.getReal(), 2) + Math.pow(other.getImaginary(), 2))); 
		double im = (((this.getImaginary() * other.getReal()) - (this.getReal() * other.getImaginary())) / (Math.pow(other.getReal(), 2) + Math.pow(other.getImaginary(), 2)));
		return new ComplexNumber(re, im);
	}
	
	/**
	 * Method that will return the absolute value of a complex number.
	 * @return result
	 */
	public double abs() {
		return Math.sqrt(Math.pow(this.getReal(), 2) + Math.pow(this.getImaginary(), 2));
	}
	
	/**
	 * Returns the value for the real part of a complex number.
	 * @return value of real part
	 */
	public double getReal() {
		return this.re;
	}

	/**
	 * Returns the value for the imaginary part of a complex number.
	 * @return value of imaginary part
	 */
	public double getImaginary() {
		return this.im;
	}
	
	/**
	 * Returns a readable representation of a complex number
	 */
	@Override
	public String toString() {
		if (getImaginary() < 0) {
			return re + " - " + im + "i";
		} else {
			return re + " + " + im + "i";
		}
	}
}
