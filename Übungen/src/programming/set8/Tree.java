package programming.set8;

import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.program.GraphicsProgram;

public class Tree extends GraphicsProgram {

	//Heap shrinks down
	private static int heapStartAddress = 0x100000;
	private int heapAddress = 0x100000;

	private int heapBytes = 0;

	//Stack grows up
	private int stackAddress = 0xffffe4;

	private int stackBytes = 0;

	private int glineObjectCount = 0;
	private int drawTreeCalls = 0;
	private int depth = 0;
	private int maxDepth = 0;

	public void run() {
		setSize(500, 350);
		drawTree(250, 350, 3.5, 90); // ADJUST "TREE PARAMETERS" HERE
	}

	public void drawTree(double x0, double y0, double len, double angle) {

		depth++;
		drawTreeCalls++;
		println("Create drawTree() stack frame at adress " + toHexString(stackAddress) + ", depth " + depth);
		stackAddress -= 28;
		
		if (len > 2) {
			
			// Check is made here because on the last run where we wouldn't draw, depth is
			// still incremented.
			if (depth > maxDepth) {
				maxDepth = depth;
			}

			double x1 = x0 + len * GMath.cosDegrees(angle);
			double y1 = y0 - len * GMath.sinDegrees(angle);
			add(new GLine(x0, y0, x1, y1));
			
			glineObjectCount++;
			println("Create GLine object #" + glineObjectCount + " at address " + toHexString(heapAddress));
			heapAddress += 20;
			
			drawTree(x1, y1, len * 0.75, angle + 30);
			drawTree(x1, y1, len * 0.66, angle - 50);
		}
		
		depth--;
		stackAddress += 28;
		println("Delete stack frame at address " + toHexString(stackAddress) + ", depth " + depth);

		// Code only executed on last run.. because otherwise this would never be reached because of recursion.
		if (depth == 1) {
			int stackMaxDepthAddress = 0xffffff - (drawTreeCalls * 28);
			
			println("HEAP:");
			println("Created " + glineObjectCount + " GLine objects,");
			println("requiring " + heapBytes + " bytes of heap space,");
			println("from address " + toHexString(heapStartAddress) + " to " + toHexString(heapAddress) + ".");
			println();
			println("STACK:");
			println("Created and discarded " + drawTreeCalls + " drawTree() stack frames,");
			println("with maximal depth " + maxDepth + ",");
			println("requiring " + stackBytes + " bytes of stack space,");
			println("from " + toHexString(stackMaxDepthAddress) + " to 0xffffff.");
		}
	}

	public String toHexString(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Input has to be positive!");
		}
		return "0x" + Integer.toHexString(i);
	}
}