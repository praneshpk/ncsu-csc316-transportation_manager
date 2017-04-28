package edu.ncsu.csc316.transportation_manager.list;

/**
 * The UpTree class acts as a general tree data structure for finding MST
 * @author Pranesh Kamalakanthan 
 */
public class UpTree {
	/** The size of the UpTree (edges) */
	private int size;
	/** Set of vertices */
	private int[] list;
	
	/**
	 * Creates a new UpTree object
	 * @param cap the capacity of the list array
	 */
	public UpTree(int cap) {
		size = 0;
		list = new int[cap];
		// Sets all vertices' parent indices to itself
		for(int i = 0; i < list.length; i++)
			list[i] = i;
	}
	
	/**
	 * Unionizes two cities if they are not part of the same cluster
	 * @param city1 first city
	 * @param city2 second city
	 * @return true if the cities were unionized, false otherwise
	 */
	public boolean union(int city1, int city2) {
		if(list[city1] != list[city2]) {
			if(list[city1] > list[city2]) {
				int temp = list[city2];
				for(int i = 0; i < list.length; i++ )
					if(list[i] == temp)
						list[i] = list[city1];
			}
			else {
				int temp = list[city1];
				for(int i = 0; i < list.length; i++)
					if(list[i] == temp)
						list[i] = list[city2];
			}
			size++;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the size of the UpTree
	 * @return size of UpTree
	 */
	public int size() { return size; }
	
}
