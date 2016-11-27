package Serie4;

import acm.program.ConsoleProgram;

public class PowerSet extends ConsoleProgram {

    private static int input; //Variable that takes the input of the user (has to be a positive integer)

    @Override
    public void run() {

    	//Adjust the size of the applet
        this.setSize(500, 500);
        
        //Little hint so the user knows what the program will do
        println("This program prints the power set of the set of natural numbers 0 ... N.");

        do { // Read the input from the user until the user enters a valid input
            input = readInt("Enter N (0 <= N <= 10):");
        } while ((input < 0) || (input > 10)); 
        calculatePowerset(input);
    }

    /*
     * Method that calculates the powerset of a set of integers.
     * The set starts with 0 and includes all integers up to
     * the integer the user specified (included in the set).
     */
    private void calculatePowerset(int input) {
 
    	//Needed for correct text output
    	//Will print out all integers the set consists of and
    	//from which we will later calculate the powerset
        print("The powerset of { ");
        for (int i = 0; i <= input; i++) {
            if(i < (input)) {
                print(i + ", ");
            } else {
                print(i + " ");
            }
        }      
        println("} is");
        
        //Calculates how many powersets our set has.
        //Under the assumption that 2^n is the number of powersets for a given set.
        //If we shift 1 by (input + 1) bits to the left we will get the power of two
        //that 2^n would give us otherwise.
        //We need (input + 1) because our set has (input + 1) elements because we include zero
        //And n is the number of elements in our set .. otherwise we would have 2^(n-1) which would be wrong
        //Example for input = 2:
        //Set := {0,1,2}
        //2^3 = 8
        //(1 << (2 + 1)) == 0..0001 << 3 = 0..1000 = 8
        int powersetCount = (1 << (input + 1));
        print("{ ");
        //Loop that will be used to determine each powerset
        //Idea: the integer i can be interpreted as a powerset of our original set
        /* Example: input is the same as before.
         * Then i would be (leading zeros are ignored):
         * 
         * 000 (i = 0)
         * 001 (i = 1)
         * 010 (i = 2)
         * 011 (i = 3)
         * 100 (i = 4)
         * 101 (i = 5)
         * 110 (i = 6)
         * 111 (i = 7)
         * 
         * We would look at each value for i in binary form and interpret each bit that is set
         * as an indicator if an element from our set is included in a specific powerset.
         * This is possible be cause each representation for i is unique.
         * 
         * Example:
         * i = 3 would be 011
         * {2,1,0} -> Elements of our set
         *  0 1 1 -> Elements that are included in our powerset if the bit is the set a the same position
         * So our powerset would be {0,1} for i = 3
         */
        for (int i = 0; i < powersetCount; i++) {

            print("{");
            boolean notFirst = false; //Used to avoid wrong punctuation in our output
            //Loops through all elements of the set so it can be checked if the
            //Element is also an element of this specific powerset
            for (int j = 0; j <= input; j++) {
            	
            	//Evaluates if a bit is set at j-position in i
            	//Basically we in each loop we move i one bit after another to the right to
            	//see if the bit at the j-position is set.
            	//This can be achieved if be use the binary-and to
            	//we use it to link i with 1 which would only
            	//return true if the first bit in i is set and because
            	//we know that we shifted i, we then now know
            	//that the element at j-position has to be included in the powerset and we willll print it
                if (((i >> j) & 1) == 1) {

                	//Code only for correct textual representation
                    if (notFirst) {
                        print(", ");
                    } else {
                        notFirst = true;
                    }
                    //Print out j if condition is met
                    print(j);
                }
            }
            
            //Code only for correct textual representation
            //Will close each powerset and add a comma or
            //will not add a comma if we reached the last powerset
            if(i < (powersetCount -1)) {
                print("}, ");
            } else {
                print("}");
            }
            
        }
        print(" } ");
    }
}
