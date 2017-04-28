package edu.ncsu.csc316.transportation_manager.list;

import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * The AdjacencyList class acts as the main data structure for storing
 * the highways in an adjacency list format
 * @author Pranesh Kamalakanthan
 *
 */
public class AdjacencyList {
	/** Main data structure */
	private DoubleList<Highway>[] data;
	/** Size of AdjacencyList */
	private int size = 0;
	
	/**
	 * Creates / sorts a new AdjacencyList object given a list of highways
	 * @param list list of highways
	 */
	public AdjacencyList(ArrayList<Highway> list ) {
		data = (DoubleList<Highway>[]) new DoubleList[list.size()];
		for(int i = 0; i < list.size(); i++ ) {
			Highway h = list.get(i);
			int city1 = h.city1();
			int city2 = h.city2();
			if( data[city1] == null) {
				data[city1] = new DoubleList<Highway>();
				data[city1].add(h);
				size++;
			}
			else {
				Node<Highway> curr = data[city1].getFirst();
				for(int j = 0; j < data[city1].size(); j++ ) {
					if(curr.getData().city1() > h.city1()) {
						data[city1].add(h, curr.getPrev(), curr);
						break;
					}
					else if(curr.getData().city1() == h.city1() ) {
						if(curr.getData().city2() > h.city2()) {
							data[city1].add(h, curr.getPrev(), curr);
							break;
						}
						else if(curr.getData().city2() == h.city2()) {
							if(curr.getData().cost() > h.cost()) {
								data[city1].add(h, curr.getPrev(), curr);
								break;
							}
							else if( curr.getData().cost() == h.cost() && curr.getData().asphalt() > h.asphalt()) {
									data[city1].add(h, curr.getPrev(), curr);
									break;
							}
						}
						data[city1].add(h, curr, curr.getNext());
						break;
					}
					else if(j == data[city1].size() - 1) {
						data[city1].add(h);
						break;
					}
					curr = curr.getNext();
				}
			}
			if( data[city2] == null) {
				data[city2] = new DoubleList<Highway>();
				data[city2].add(h);
				size++;
			}
			else {
				Node<Highway> curr = data[city2].getFirst();
				for(int j = 0; j < data[city2].size(); j++ ) {
					if(curr.getData().city1() > h.city1()) {
						data[city2].add(h, curr.getPrev(), curr);
						break;
					}
					else if(j == data[city2].size() - 1) {
						data[city2].add(h);
						break;
					}
						
					curr = curr.getNext();
				}
			}
		}
	}
	
	/**
	 * Returns the size of the AdjacencyList
	 * @return size of AdjacencyList
	 */
	public int size() { return size; }
	
	/**
	 * Returns a string representation of the AdjacencyList
	 * @return the string representation of the AdjacencyList
	 */
	@Override
	public String toString() {
		String res = "AdjacencyList[\n";
		for( int i = 0; i < size; i++ )
			res += "   City " + i + ": -> " + data[i].toString() + "\n";
		return res + "]";
	}
}
