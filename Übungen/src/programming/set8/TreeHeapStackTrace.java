package programming.set8;

import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.program.GraphicsProgram;

/**
 * Program that tries to show how heap and stack work.
 * 
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class TreeHeapStackTrace extends GraphicsProgram {

	// Part b:
	// To achieve that we allocate only as many stack-frames as there are GLine
	// objects
	// we would need to test if the length of tree branch is shorter than 2
	// outside of
	// the method because if we don't change this at the last run where length
	// is already shorter than 2
	// we still call the drawTree() method two times and because we are using
	// recursion this adds up
	// to doubling our number of calls we make.

	/**
	 * Part c: explanation
	 * 
	 * stack: In java the stack contains values that have a short "lifespan"
	 * like local variables. On calling a function, memory on the stack is
	 * allocated to allow the function to do it's task and after the method is
	 * finished the memory is freed up again. Due to recursion this can quickly
	 * fill up the stack because it's much smaller than the heap in general.
	 * 
	 * Example: Each time the drawTree() method is called a stack-frame is
	 * allocated and after the method finished the memory is freed up again.
	 * 
	 * heap: The heap is where objects "live". Objects are always created on the
	 * heap. When an object is not needed any longer(their is now reference to
	 * it in our program) it will be garbage collected and the memory it
	 * occupied will be freed up. This does not have to happen at the exact
	 * moment the object is no longer needed and is handle by java internally.
	 * This normally isn't a problem, because the heap is large enough.
	 *
	 * Example: Inside of the drawTree() method we create new GLine objects when
	 * the length of the tree is shorter than two.
	 *
	 * static segment: Part of the memory where static variables and other
	 * static data is stored.
	 * 
	 * Example: There are no static variables/classes used in this code. static
	 * int statVariable = 5; would be a static variable that is assigned the
	 * value 5. There can only be one instance of each static variable per class
	 * at runtime. Also static means that the variable is bound to the class
	 * itself and not to an object/Instance of a class.
	 *
	 * Recursion: When a method calls itself until a specific condition is met.
	 * This can lead, as explained above, to errors. (Stackoverflow).
	 *
	 * Mutable class: The following text was found on stackoverflow.com but I
	 * couldn't think of a better way explaining this:
	 * (http://stackoverflow.com/a/3554212/5409497 [08.01.2017:20:30]) "A
	 * mutable class is one that can change its internal state after it is
	 * created. Generally speaking, a class is mutable unless special effort is
	 * made to make it immutable."
	 *
	 * Wrapper class: Wrapper classes exist for primitive data types like int,
	 * double and so on to offer functionality that they naturally do not have.
	 * 
	 * Boxing/unboxing: The automatic conversion between a primitive and its
	 * wrapper type.
	 *
	 * Garbage collection: The so called garbage collector is active on the heap
	 * and frees up memory if it finds an object that no longer referenced by
	 * our program.
	 **/

	// Heap shrinks down
	private final int heapStartAddress = 0x100000;
	private int heapAddress = heapStartAddress;

	private int heapFootprint = 20;

	// Stack grows up
	private final int stackStartAddress = 0xffffff;
	private int stackAddress = stackStartAddress + 1; // Problem is that
														// footPrint is 28 bytes
														// long but if we want
	// the exact same output as in the task we need to start 1 address earlier.
	private int stackFootprint = 28; // In bytes
	private int maxDepthAddress = stackStartAddress;

	private int glineObjectCount = 0;
	private int drawTreeCalls = 0;
	private int depth = 1;
	private int maxDepth = 1;

	/**
	 * Method inherited from Graphics program that executes our logic.
	 */
	public void run() {
		setSize(500, 350);
		drawTree(250, 350, 100, 90); // ADJUST "TREE PARAMETERS" HERE
	}

	/**
	 * Method from the task that has been modified to show the
	 * usage of memory (heap/stack) even if it is only for educational purposes
	 * and maybe doesn't represent real circumstances.
	 * 
	 * @param x0 x-coordinate
	 * @param y0 y-coordinate
	 * @param len length
	 * @param angle angle
	 */
	public void drawTree(double x0, double y0, double len, double angle) {

		drawTreeCalls++; //tracks how often this method gets called

		//Needed to check if we have a new address that
		//is smaller than the least smallest we have until now
		stackAddress -= stackFootprint;
		if (stackAddress < maxDepthAddress) {
			maxDepthAddress = stackAddress;
		}

		println("Create drawTree() stack frame at address " + toHexString(stackAddress) + ", depth " + depth);
		depth++;

		if (len > 2) {

			if (depth > maxDepth) {
				maxDepth = depth;
			}

			double x1 = x0 + len * GMath.cosDegrees(angle);
			double y1 = y0 - len * GMath.sinDegrees(angle);
			add(new GLine(x0, y0, x1, y1));

			glineObjectCount++;
			println("Create GLine object #" + glineObjectCount + " at address " + toHexString(heapAddress));
			heapAddress += heapFootprint;

			drawTree(x1, y1, len * 0.75, angle + 30);
			drawTree(x1, y1, len * 0.66, angle - 50);
		}

		depth--;
		println("Delete stack frame at address " + toHexString(stackAddress) + ", depth " + depth);
		stackAddress += stackFootprint;

		// Code only executed on last run.. because otherwise this would never
		// be reached because of recursion or executed every time we call this method.
		if (depth == 1) {

			println();
			println("HEAP:");
			println("Created " + glineObjectCount + " GLine objects,");
			println("requiring " + (heapFootprint * glineObjectCount) + " bytes of heap space,");
			println("from address " + toHexString(heapStartAddress) + " to " + toHexString(heapAddress - 1) + ".");
			println();

			println("STACK:");
			println("Created and discarded " + drawTreeCalls + " drawTree() stack frames,");
			println("with maximal depth " + maxDepth + ",");
			println("requiring " + (maxDepth * stackFootprint) + " bytes of stack space,");
			println("from address " + toHexString(maxDepthAddress) + " to " + toHexString(stackStartAddress) + ".");
		}
	}

	/**
	 * Utility to represent an Integer as a hexadecimal string.
	 * 
	 * @param Integer value
	 * @return representation of the value as a hexadecimal string
	 */
	public String toHexString(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Input has to be positive!");
		}
		return "0x" + Integer.toHexString(i);
	}
}