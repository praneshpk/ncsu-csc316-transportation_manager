package edu.ncsu.csc316.transportation_manager.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import edu.ncsu.csc316.transportation_manager.heap.MinHighwayHeap;
import edu.ncsu.csc316.transportation_manager.highway.Highway;
import edu.ncsu.csc316.transportation_manager.list.*;

/**
 * The TransportationManager class contains all the functions / variables for
 * to create / access the transportation system
 * @author Pranesh Kamalakanthan
 *
 */
public class TransportationManager {
	/** List of all the highways */
	private ArrayList<Highway> list;
	/** Adjacency list of highway */
	private AdjacencyList alist;
	
	/**
	 * Constructs a new TransportationManager
	 * 
	 * @param pathToFile the path to the file that contains the set of highways in the graph
	 */
	public TransportationManager(String pathToFile)
	{
		list = new ArrayList<>();
		try( Scanner in = new Scanner( new FileInputStream( pathToFile ), "UTF8") ) {
			while(in.hasNext()) {
				try(Scanner line = new Scanner(in.nextLine())) {
					int arg1 = line.nextInt();
					int arg2 = line.nextInt();
					double arg3 = line.nextDouble();
					double arg4 = line.nextDouble();
					if( arg1 < 0 || arg2 < 0 || arg3 < 0 || arg4 < 0)
						throw new IllegalArgumentException();
					list.addLast( new Highway(arg1, arg2, arg3, arg4 ) );
				} catch( Exception e ) {
					System.out.println("Error: Invalid file!");
					throw new IllegalArgumentException();
				}
			}
		} catch(IOException e) {
			System.out.println("Error: File " + pathToFile + " not found");
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < list.size(); i++ )
			for(int j = i + 1; j < list.size(); j++ )
				if(list.get(i).city1() == list.get(j).city1()
					&& list.get(i).city2() == list.get(j).city2()) {
					System.out.println("Error: Road from and to same city!");
					throw new IllegalArgumentException();
				}
		alist = new AdjacencyList(list);
	}
		
	/**
	 * Returns a string representation of the AdjacencyList
	 * in the following format, where (for each city) the Highways are
	 * in sorted order by city1, then city2, then cost, then asphalt:
	 * 
	 * AdjacencyList[
	 *    City 0: -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 *    City 1: -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 *    City 2: -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 *    City 3: -> Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * ]
	 * 
	 * @return the string representation of the AdjacencyLists
	 */
	public String getAdjacencyList()
	{
		return alist.toString();
	}
		
	/**
	 * Returns a string representation of the list of Highways contained in the 
	 * minimum spanning heaset of Highways. The returned string is in the following format,
	 * where the Highways are in sorted order by city1, city2, then cost, then asphalt:
	 * 
	 * List[
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * ]
	 * 
	 * @param type the type ("COST" or "ASPHALT") of field to minimize
	 * @return a string representation of the minimum spanning set of Highways
	 */
	public String getMinimumHighways(String type)
	{
		MinHighwayHeap heap = new MinHighwayHeap(type);
		for(int i = 0; i < list.size(); i++ )
			heap.insert(list.get(i));
		System.out.println(heap);
		UpTree tree = new UpTree(alist.size());
		for(int i = 0; i < heap.size(); i++ )
			tree.union(heap.get(i).city1(), heap.get(i).city2());
		DoubleList<Highway> temp = null;
		for(int i = 0; i < tree.size(); i++ ) {
			Highway h = heap.deleteMin();
			if(temp == null) {
				temp = new DoubleList<>();
				temp.add(h);
				
			}
			else {
				Node<Highway> curr = temp.getFirst();
				for(int j = 0; j < temp.size(); j++ ) {
					if(curr.getData().city1() > h.city1()) {
						temp.add(h, curr.getPrev(), curr);
						break;
					}
					else if(curr.getData().city1() == h.city1() ) {
						if(curr.getData().city2() > h.city2()) {
							temp.add(h, curr.getPrev(), curr);
							break;
						}
						else if(curr.getData().city2() == h.city2()) {
							if(curr.getData().cost() > h.cost()) {
								temp.add(h, curr.getPrev(), curr);
								break;
							}
							else if( curr.getData().cost() == h.cost() && curr.getData().asphalt() > h.asphalt()) {
									temp.add(h, curr.getPrev(), curr);
									break;
							}
						}
						temp.add(h, curr, curr.getNext());
						break;
					}
					else if(j == temp.size() - 1) {
						temp.add(h);
						break;
					}
					curr = curr.getNext();
				}
			}
			
		}
		String res = "List[\n";
		Node<Highway> curr = temp.getFirst();
		for(int i = 0; i < temp.size() - 1; i++ ) {
			res += "   " + curr.getData().toString() + ",\n";
			curr = curr.getNext();
		}
		return res + "   " + curr.getData().toString() + "\n]";
		
	}
}
