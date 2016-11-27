package Serie3;

import acm.program.ConsoleProgram;

/*
 * Program that can convert Celsius to Fahrenheit and backwards.
 */
public class TemperatureConverter extends ConsoleProgram {

	private static int mode; //Static variable of type int that is later assigned the value for the mode the user wishes to enter
	private static final int MODE_CELSIUS = 1; //Constant of type int that represents the mode where Celsius is converted to Fahreheit for easier readability
	private static final int MODE_FAHRENHEIT = 2; //Constant of type int that represents the mode where Fahrenheit is converted to Celsius for easier readability
	private static final int SENTINEL = -1000; //Constant of type int that represents the value for which the while loop should terminate
	//The constant sentinel is assigned the value -1000. In this case its an expression because we have the unary operator - !
	
	public void run() {

		//Ask the user to select a mode
        println("What do you want me to do?");
        println("(1) Convert Celsius to Fahrenheit");
        println("(2) Convert Fahrenheit to Celsius");
		mode = readInt("Mode: ");

		// Check if the user input is valid
		if (!(mode == MODE_CELSIUS || mode == MODE_FAHRENHEIT)) {
			println("Error");
		} else {

			//If input is valid start converting
			double temperature = readDouble("Temperature: "); //Local variable of type double that will hold the user entered temperature/the converted temperature
			
			//While loop that repeats until the user enters a specific value
			while (temperature > SENTINEL /*Here we have an expression that evaluates if temperature is greater or smaller than our sentinel value. Precedence doesn't apply because we only have on operator*/) { //If the user enters a temperature that is equal or smaller than our sentinel value, the loop will stop and "Goodbye !" will be printed 
					println(convertTemperature(temperature)); //The entered temperature needs to be converted first otherwise the sentinel would also be converted and printed and we don't want this
					temperature = readDouble("Temperature: "); //On each loop the user is asked to enter a temperature
			}	
			println("Goodbye !");
		}

	}

	//Static method that converts Celsius to Fahrenheit or backwards depending on the selected mode
	private static double convertTemperature(double temperature) {

		// If the selected mode is Celsius this block will be executet
		if (mode == MODE_CELSIUS) {

			// Convert Celsius to Fahrenheit
			// The expression on the righthand site of the assignment statement is "(temperature * 9 / 5.) + 32".
			// The parentheses could be left out but are here for easier readability.
			// After the rules of precedence the expression is evaluated like this:
			// First temperature is multiplied by 9 and then divided by 5 (5. because we don't want an integer division).
			// this is because we have two operators of the same precedence. In this case the leftmost is evaluated first!
			// The 32 is added last because the + operator has the lowest precedence
			temperature = (temperature * 9 / 5.) + 32; //The variable temperature is assigned the value that the expression on the righthand side of the equation yields

		// If the selected mode is Fahrenheit this block will be executed
		} else if (mode == MODE_FAHRENHEIT) {

			// Convert Fahrenheit to Celsius
			// The expression on the righthand site of the assignment statement is "5 / 9. * (temperature - 32)"
			// In this case we can't leave out the parentheses because we need (temperature - 32) to be evaluated before anything else happens with it. If we didn't have the parentheses
			// temperature would be multiplied with the result of 5/9. and after that 32 would be subtracted which would leave us with a wrong result.
			// In this case the first thing to happen is the evaluation of the parentheses and then it's the same case as in the expression above:
			// The leftmost operator, in this case /, is evaluated first (we write 9. so we avoid an integer division) and then
			// the result of that is multiplied with the result of the evaluation of the parentheses that has already been evaluated.
			temperature = 5 / 9. * (temperature - 32); //The variable temperature is assigned the value that the expression on the righthand side of the equation yields

		}

		return temperature; // When the conversion is finished the temperature will be returned
	}
}
