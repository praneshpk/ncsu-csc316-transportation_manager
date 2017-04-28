package edu.ncsu.csc316.transportation_manager.highway;

/**
 * This class contains all the functions to create a Highway object
 * @author Pranesh Kamalakanthan
 *
 */
public class Highway {
	/** City1 of the highway */
	private int city1;
	/** City2 of the highway */
	private int city2;
	/** Cost of highway */
	private double cost;
	/** Asphalt amount in miles to build highway */
	private double asphalt;
	
	/**
	 * Constructs a Highway with the given information
	 * @param city1 city1 of the highway
	 * @param city2 city2 of the highway
	 * @param cost cost of building the highway
	 * @param asphalt amount (in miles) of asphalt needed to build the highway
	 */
	public Highway(int city1, int city2, double cost, double asphalt) {
		this.city1 = city1;
		this.city2 = city2;
		this.cost = cost;
		this.asphalt = asphalt;
	}
	
	/**
	 * Returns city1
	 * @return city1
	 */
	public int city1() { return city1; }
	
	/**
	 * Returns city2
	 * @return city2
	 */
	public int city2() { return city2; }
	
	/**
	 * Returns the cost of highway
	 * @return the cost of highway
	 */
	public double cost() { return cost; }
	
	/**
	 * Returns the asphalt amount
	 * @return the asphalt amount
	 */
	public double asphalt() { return asphalt; }

	/**
	 * Returns a string representation of the Highway
	 * in the format:
	 * 
	 * Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * 
	 * @return the string representation of the highway
	 */
	@Override
	public String toString() {
		return "Highway[city1=" + city1 + ", city2=" + city2
				+ ", cost=" + cost + ", asphalt=" + asphalt + "]";
	}
}
