package edu.ncsu.csc316.transportation_manager.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class has all the functions for testing TransportationManager
 * @author Pranesh Kamalakanthan
 *
 */
public class TransportationManagerTest {

	/**
	 * Testing constructor and vital functions
	 */
	@Test
	public void testTransportationManager() {
		TransportationManager t = new TransportationManager("input/pt1.txt");
		assertEquals("AdjacencyList[\n"
				+ "   City 0: -> Highway[city1=0, city2=3, cost=14.0, asphalt=415.0] -> Highway[city1=1, city2=0, cost=5.0, asphalt=99.0] -> Highway[city1=2, city2=0, cost=7.0, asphalt=159.0]\n"
				+ "   City 1: -> Highway[city1=1, city2=0, cost=5.0, asphalt=99.0] -> Highway[city1=1, city2=2, cost=6.0, asphalt=72.0] -> Highway[city1=3, city2=1, cost=10.0, asphalt=112.0]\n"
				+ "   City 2: -> Highway[city1=1, city2=2, cost=6.0, asphalt=72.0] -> Highway[city1=2, city2=0, cost=7.0, asphalt=159.0] -> Highway[city1=3, city2=2, cost=12.0, asphalt=212.0]\n"
				+ "   City 3: -> Highway[city1=0, city2=3, cost=14.0, asphalt=415.0] -> Highway[city1=3, city2=1, cost=10.0, asphalt=112.0] -> Highway[city1=3, city2=2, cost=12.0, asphalt=212.0]\n"
				+ "]", t.getAdjacencyList());
		assertEquals("List[\n"
				+ "   Highway[city1=1, city2=0, cost=5.0, asphalt=99.0],\n"
				+ "   Highway[city1=1, city2=2, cost=6.0, asphalt=72.0],\n"
				+ "   Highway[city1=3, city2=1, cost=10.0, asphalt=112.0]\n"
				+ "]", t.getMinimumHighways("COST"));
		assertEquals("List[\n"
				+ "   Highway[city1=1, city2=2, cost=6.0, asphalt=72.0],\n"
				+ "   Highway[city1=1, city2=0, cost=5.0, asphalt=99.0],\n"
				+ "   Highway[city1=2, city2=0, cost=7.0, asphalt=159.0]\n"
				+ "]", t.getMinimumHighways("ASPHALT"));
		t = new TransportationManager("input/highways_small.txt");
		assertEquals("AdjacencyList[\n"
				+ "   City 0: -> Highway[city1=0, city2=3, cost=14.0, asphalt=144.0] -> Highway[city1=1, city2=0, cost=5.0, asphalt=101.0] -> Highway[city1=2, city2=0, cost=7.0, asphalt=77.0]\n"
				+ "   City 1: -> Highway[city1=1, city2=0, cost=5.0, asphalt=101.0] -> Highway[city1=1, city2=2, cost=6.0, asphalt=55.0] -> Highway[city1=3, city2=1, cost=10.0, asphalt=66.0]\n"
				+ "   City 2: -> Highway[city1=1, city2=2, cost=6.0, asphalt=55.0] -> Highway[city1=2, city2=0, cost=7.0, asphalt=77.0] -> Highway[city1=3, city2=2, cost=12.0, asphalt=122.0]\n"
				+ "   City 3: -> Highway[city1=0, city2=3, cost=14.0, asphalt=144.0] -> Highway[city1=3, city2=1, cost=10.0, asphalt=66.0] -> Highway[city1=3, city2=2, cost=12.0, asphalt=122.0]\n"
				+ "]", t.getAdjacencyList());
		assertEquals("List[\n"
				+ "   Highway[city1=1, city2=0, cost=5.0, asphalt=101.0],\n"
				+ "   Highway[city1=1, city2=2, cost=6.0, asphalt=55.0],\n"
				+ "   Highway[city1=3, city2=1, cost=10.0, asphalt=66.0]\n"
				+ "]", t.getMinimumHighways("COST"));
		assertEquals("List[\n"
				+ "   Highway[city1=1, city2=2, cost=6.0, asphalt=55.0],\n"
				+ "   Highway[city1=3, city2=1, cost=10.0, asphalt=66.0],\n"
				+ "   Highway[city1=1, city2=0, cost=5.0, asphalt=101.0]\n"
				+ "]", t.getMinimumHighways("ASPHALT"));
		t = new TransportationManager("input/invalidmap.txt");
		try {
			t = new TransportationManager("input/nonexistent.txt");
		} catch( Exception e) {
			// Supposed to happen
		}
		try {
			t = new TransportationManager("input/invalidboundarymap.txt");
		} catch( Exception e) {
			// Supposed to happen
		}
		
		
		
		
		
		
	}
}
