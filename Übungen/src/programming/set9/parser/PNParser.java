package programming.set9.parser;

public class PNParser {

	/* WARNING:
	 * I couldn't get this working for some reasons even if i think
	 * that i have a pretty good understanding of how the tree data-structure works
	 * and how you can (at least manually) generate the different notations from it
	 * and also the other way round.
	 * 
	 * My problem is, that because i tried to solve it recursively, when I am in the left most
	 * branch of the tree and added the number to the left AND(!) right of the tree (previous operator)
	 * and i then try to parse the next operator or number it would have to go two steps/nodes further back 
	 * in the tree where the right-field is not yet set. I couldn't think of a way to do this and in the end got confused
	 * with recursion. I hope it is understandable what my problem was.
	 * 
	 * The code to decide whether parenthesis are needed is also missing
	 * but should be relatively easy to implement because they are only needed
	 * if the next operator in the tree has a lower precedence than the one before.
	 */
	
	String[] result;
	String finalString = "";
	SimpleTree tree;

	/**
	 * The constructor expecting the expression in polish notation.
	 */
	public PNParser(String pNExpression) {
		expressionToSimpleTree(pNExpression);
	}

	/**
	 * Parses a string and returns it's representation as a {@link SimpleTree}.
	 * 
	 * @param expression
	 *            The string that will be parsed
	 * @return String as SimpleTree
	 */
	private SimpleTree expressionToSimpleTree(String expression) {

		result = expression.split("\\s");
		// DEBUG
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]);
		}
		System.out.println();

		// 1. Iterate over string and put operators in left trees until you find
		// a number.
		// 2. Put the number also in left and go back to last root.
		// 3. Put next value in right and continue from start until end of
		// string is reached.

		tree = generateTreeRecusrive(new SimpleTree(result[0]), 1);

		return null;
	}

	private SimpleTree generateTreeRecusrive(SimpleTree t, int stringIndex) {
		
		if (stringIndex == result.length) {
			return t;
		}

		System.out.println(result[stringIndex]);
		
		switch (result[stringIndex]) {
			case "*":
			case "+":
				System.out.println("Adding OP to tree [" + result[stringIndex] + "]");
				if (t.getLeft() == null) {
					t.setLeft(generateTreeRecusrive(new SimpleTree(result[stringIndex]), stringIndex + 1));
				} else if (t.getRight() == null) {
					t.setRight(generateTreeRecusrive(new SimpleTree(result[stringIndex]), stringIndex + 1));
				} else {
					return t;
				}
				break;
			case "0": // Couldn't get regex working. Maybe because of switch.
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				
				if (result[stringIndex].equals("5")) {
					System.out.println("Con: " + t.getContent());
					System.out.println("L: " + t.getLeft().getContent());
					System.out.println("R: " + t.getRight().getContent());
				}
			
				System.out.println("Adding NUM to tree [" + result[stringIndex] + "]");
				if (t.getLeft() == null) {System.out.println("Added left" + t.getContent());
					t.setLeft(new SimpleTree(result[stringIndex]));
					return generateTreeRecusrive(t, stringIndex + 1);
					
				} else if (t.getRight() == null) {System.out.println("Added right" + t.getContent());
					t.setRight(new SimpleTree(result[stringIndex]));
					return generateTreeRecusrive(t , stringIndex + 1);
				} else {
					
				}
			default:
				break;
		}
		return t;
	}

	private SimpleTree traverseTreeRecusively(SimpleTree t) {
		
		if (t == null){
			return t;
		}
		
		switch (t.getContent()) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				System.out.println("NUM case" + t.getContent());
				finalString += t.getContent();
				return t;
			case "+":
			case "*":
				System.out.println("OP case" + t.getContent());
				traverseTreeRecusively(t.getLeft());
				finalString += t.getContent();
				traverseTreeRecusively(t.getRight());
				return t;
			default:
				return t;
		}
	}

	/**
	 * @return the tree in infix notation as string.
	 */
	@Override
	public String toString() {
		traverseTreeRecusively(tree);
		return finalString + " fini";
	}
}
