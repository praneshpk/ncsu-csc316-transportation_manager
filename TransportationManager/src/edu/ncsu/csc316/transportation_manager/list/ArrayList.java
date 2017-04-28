package edu.ncsu.csc316.transportation_manager.list;

/**
 * The ArrayList class acts as one of the main data structures required for many
 * of the operations in the Tree classes
 * @author Pranesh Kamalakanthan
 *
 * @param <E> data contained in ArrayList
 */
public class ArrayList<E> {
	/** Default array capacity */
	public static final int CAP = 16;
	
	/** Array for storage */
	private E[] data;
	
	/** Size of list */
	private int size = 0;
	
	/**
	 * Constructs a list with default capacity
	 */
	public ArrayList() { this(CAP); }
	
	/**
	 * Constructs a list with given capacity
	 * @param capacity given capacity
	 */
	public ArrayList( int capacity ) {
		data = (E[]) new Object[capacity];
	}
	
	/**
	 * Returns the size of the list
	 * @return size of list
	 */
	public int size() { return size; }
	
	/**
	 * Returns the element at index i 
	 * @param i index
	 * @return E element
	 * @throws IndexOutOfBoundsException if index i is out of bounds
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}
	/**
	 * Inserts an element at index
	 * @param i index
	 * @param e element
	 * @throws IndexOutOfBoundsException if index i is out of bounds
	 */
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if( size == data.length)
			resize(2 * data.length);
		for(int j = size - 1; j >= i; j-- )
			data[j + 1] = data[j];
		data[i] = e;
		size++;
	}
	/**
	 * Adds an element to the end
	 * @param e element
	 */
	public void addLast( E e ) {
		add(size, e);
	}
	
	/**
	 * Removes last element in list
	 */
	public void removeLast() {
		data[size - 1] = null;
		size--;
	}
	
	/**
	 * Swaps the two elements at their given indices
	 * @param a index of element a
	 * @param b index of element b
	 */
	public void swap(int a, int b) {
		E temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
	
	/**
	 * Prints out contents of ArrayList
	 * @return contents of ArrayList
	 */
	public String toString() {
		String res = "ArrayList[";
		for( int i = 0; i < size - 1; i++ )
			res +=  data[i] + ", ";
		return res + data[size - 1] + "]";
	}
	
	/**
	 * Checks if the index is within the range [0, n)
	 * @param i index
	 * @param n max
	 * @throws IndexOutOfBoundsException
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if(i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i );
	}
	
	/**
	 * Resizes the ArrayList
	 * @param capacity new capacity
	 */
	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for(int i = 0; i < size; i++ )
			temp[i] = data[i];
		data = temp;
	}
}
