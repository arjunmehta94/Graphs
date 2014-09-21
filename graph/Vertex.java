package graph; 
import list.*;

// This class is used to represent a Vertex in a graph. It uses a given obejct and stores it as its Identity, keeps track of all the edges and the degree of the vertex.
class Vertex {

	public DList edgeList; // stores the edges, as a list, incident on the Vertex
	protected int degree; // stores the degree of a Vertex
	protected Object identity; // stores the given implementation of a vertex.

	//Constructor 
	public Vertex(Object i) {
		edgeList = new DList();
		degree = 0;
		identity = i;
	}

	// Returns the Identity of the Vertex
	// @return indentity obejct
	public Object getIdentity(){
		return identity;
	}
	

	// Returns the degree of a vertex
	// @return int degree
	public int getDegree() {
		return degree;
	}


	// Increases the degree of a vertex by 1 when an edge is added.
	public void increaseDegree(){
		degree += 1;
	}

	// Decreases the degree of a vertex by 1 when an edge is removed
	public void decreaseDegree(){
		degree -= 1;
	}

	
	// ToString method to represent a Vertex
	// @return a string representing the Vertex
	public String toString(){
		String s = new String();
		s = "[" + identity.toString() + ", " + degree + "]";
		return s;
	}

}




