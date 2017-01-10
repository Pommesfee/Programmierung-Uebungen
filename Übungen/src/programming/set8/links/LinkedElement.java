package programming.set8.links;

/**
 * LinkedList implementation using recursion.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class LinkedElement<T> {

	private T value; //Value of this element
	private LinkedElement<T> nextElement; //The next element in the list. null would mean this is the last element of the list.

	/**
	 * Create a new LinkedList(head element)
	 * @param t the value the head element is assigned
	 */
	public LinkedElement(T t) {
		value = t;
	}

	/**
	 * Returns the value of the i-th linked element.
	 * 
	 * @param i
	 *            0-based index of the element whose value to return.
	 * @return the i-th element's value, or {@code null} if there is no element
	 *         with that index.
	 */
	public T get(int i) {
		
		//Recursively calls this method until we've done it i times
		//which would be when we are at our desired element.
		//If we encounter an element with no next element before we called i times
		//we return null as requested by the task.
		if (i == 0) {
			return this.value;
		} else {
			if (this.nextElement == null) {
				return null;
			} else {
				return this.nextElement.get(i - 1);
			}
		}
	}

	/**
	 * Sets the value of the i-th linked element to the given value. If there is
	 * no i-th linked element, nothing happens.
	 * 
	 * @param i
	 *            0-based index of the element whose value to return.
	 * @param newVal
	 *            the new value to set.
	 */
	public void set(int i, T newVal) {
		
		//Exact principle as in the get method.
		if (i == 0) {
			this.value = newVal;
		} else if (this.nextElement != null) {
			this.nextElement.set(i - 1, newVal);
		}
	}

	/**
	 * Returns the index of the first occurrence of a linked element carrying
	 * the given value in the list.
	 * 
	 * @param val
	 *            the value to search for.
	 * @return index where the value was found, or -1 if it's not in any of the
	 *         linked elements.
	 */
	public int firstIndexOf(T val) {
		
		//Recursively compares val to the nexElement starting at the head element
		//until an equality is found or there is no nextElement left.
		//If we find an equality we "go backwards through our recursion" and return the value of our temp
		//variable that is "incremented" each time we finish a method. This continues until we are back at the start
		//and the index the element has been found at is returned.
		if (this.value == val) {
			return 0;
		}
		if (this.nextElement != null) {
			
			int temp = this.nextElement.firstIndexOf(val);
			if (temp == -1) {
				return temp;
			}
			return temp + 1;
		}

		return -1;
	}

	/**
	 * Adds a new linked element holding the given value at the end of the
	 * linked elements.
	 * 
	 * @param newVal
	 *            the new value.
	 */
	public void add(T newVal) {
		
		//Finds the last element in the list (where nextElement == null)
		//and adds the element newVal.
		if (this.nextElement == null) {
			this.nextElement = new LinkedElement<T>(newVal);
		} else {
			this.nextElement.add(newVal);
		}
	}

	/**
	 * Removes the i-th element from the linked elements. If {@code i == 0},
	 * this will effectively remove the head element. Thus, this method returns
	 * the linked element that is the new head element.
	 * 
	 * @param i
	 *            index of the element to remove.
	 * @return the new head element.
	 */
	public LinkedElement<T> remove(int i) {
		
		//Goes trough the list and replaces the value of the nextElement field of
		//the element before the one we want to remove with the element after
		//the element we want to remove or null if the element that is getting removed is
		//the last element.
		if (i == 0) {
			if (this.nextElement != null) {
				return this.nextElement;
			}
		} else {
			if (this.nextElement != null) {
				
				LinkedElement<T> temp = this.nextElement.remove(i - 1);
				if (temp != null && temp != this.nextElement) {
					this.nextElement = temp; // Fügt dem Element, dass vor dem zu entferneden  liegt das nextElement von dem zu entfernednen ein/an
				}
				return this;
			}
		}
		return null;
	}

}
