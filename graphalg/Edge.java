package graphalg;
import list.*;
import dict.*;

// This class is used to represent an Edge Object. This is different than the one implemented in graph package.
// It only stores the necessary variables, making it faster to work with in the Kruskal Implementation.

public class Edge{

	protected Object object1;
	protected Object object2;
	protected int weight;


	public Edge(Object u, Object v, int weight){
		object1 = u;
		object2 = v;
		this.weight = weight;
	}

	// getObject1() returns the first object. For this project we assume that the object is a vertex.
	// @return Object object1
	public Object getObject1(){
		return object1;
	}

	// getObject2() returns the second object. For this project we assume that the object is a vertex.
	// @return Object object2
	public Object getObject2(){
		return object2;
	}
	// getWeight() returns the weight of the Edge.
	// This is used for mergeSort specifically, so that Comparable class can be implemented.
	// @return Interger Object containing the weight of the Edge 
	public Object getWeight(){
		return new Integer(this.weight);
	}

	// getNumericWeight returns the int value of a weight.
	// @return int weight
	public int getNumericWeight(){
		return this.weight;
	}

	// toString helps represent the Edge as a String.
	// @return String object representing an Edge
	public String toString(){
    return "< " + object1.toString() + ", " + object2.toString() + " (" + weight + ") >";
  }
}