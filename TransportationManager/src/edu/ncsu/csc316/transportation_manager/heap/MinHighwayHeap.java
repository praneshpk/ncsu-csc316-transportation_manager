package edu.ncsu.csc316.transportation_manager.heap;

import edu.ncsu.csc316.transportation_manager.highway.Highway;
import edu.ncsu.csc316.transportation_manager.list.ArrayList;

/**
 * Contains all the functions for creating a specialized heap
 * to be used with the TransportationManager class
 * @author Pranesh Kamalakanthan
 *
 */
public class MinHighwayHeap {
	/** List containing all heap data */
	private ArrayList<Highway> list;
	/** Type of weight to consider, cost is true, asphalt is false */
	private boolean cost;
	
	/**
	* Constructs a new Highway heap
	* 
	* @param type the type of weight to consider ("COST" or "ASPHALT") when
	*         operating on the heap
	*/
	public MinHighwayHeap(String type) {
		list = new ArrayList<>();
		cost = type.equals("COST");
	}

	/**
	 * Inserts the given Highway into the minheap
	 * @param hwy the Highway to insert into the minheap
	 */
	public void insert(Highway hwy) {
		list.addLast(hwy);
		int i = list.size() - 1;
		int parent = parent(i);
		if(cost) {
			while( parent != i && list.get(i).cost() < list.get(parent).cost() ) {
				list.swap(i, parent);
				i = parent;
				parent = parent(parent);
			}
		}
		else {
			while( parent != i && list.get(i).asphalt() < list.get(parent).asphalt() ) {
				list.swap(i, parent);
				i = parent;
				parent = parent(parent);
			}
		}
		
	}
	
	/**
	 * Returns the index to parent of i
	 * @param i index that we want to know the parent of
	 * @return index to parent of i
	 */
	private int parent(int i) { return ( i % 2 == 1 ) ? i / 2 : (i - 1) / 2; }
	
	/**
	 * Returns the Highway with minimum weight in the minheap
	 * @return the Highway with minimum weight in the minheap
	 */
	public Highway deleteMin() { 
		Highway res = list.get(0);
		list.swap(0, list.size() - 1);
		list.removeLast();
		minHeap(0);
		
		return res;
	}
	
	/**
	 * Resorts minHeap after removing given index
	 * @param i index
	 */
	private void minHeap(int i) {
		int right = 2 * i + 2;
		int low = -1;
		if(cost)
			if(right <= list.size() - 1 && list.get(right).cost() < list.get(i).cost())
				low = right;
			else
				low = i;
		else
			if(right <= list.size() - 1 && list.get(right).asphalt() < list.get(i).asphalt())
				low = right;
			else
				low = i;
		
		if(low != i) {
			list.swap(i, low);
			minHeap(low);
		}
	}
	
	/**
	 * Returns the Highway at given index i
	 * @param i given index
	 * @return the Highway at i
	 */
	public Highway get(int i) { return list.get(i); }
	
	/**
	 * Returns the size of the heap
	 * @return size
	 */
	public int size() { return list.size(); }

	/**
	 * Returns a string representation of the level-order traversal 
	 * of the heap in the following format:
	 * 
	 * Heap[
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * ]
	 *
	 * @return the string representation of the minheap
	 */
	@Override
	public String toString()
	{
		String res = "Heap[\n";
		for(int i = 0; i < list.size() - 1; i++ )
			res += "   " + list.get(i).toString() + ",\n";
		return res + "   " + list.get(list.size() - 1) + "\n]";
	}
}
