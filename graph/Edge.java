
package graph;
import list.*;

// This class represents the Edge in a graph. Every Edge stores 2 objects are the Vertices on which the edge is incident on. It stores the weight of the Edge and the reference to the partner edge.
// It also stores the node in which the Edge is present, and the two parent nodes.
class Edge {

	protected Edge partner; 
	protected VertexPair edge;
	protected int weight;
	protected DListNode myNode;
	protected DListNode parent1;
	protected DListNode parent2;

	// Constructs a new Edge Object
	public Edge(Object u, Object v, int weight) {
		
		edge = new VertexPair(u, v);
		this.weight = weight;
	}

	// getPartner returns the partner of an Edge.
	// @return Edge object containing the partner
	public Edge getPartner(){
		return partner;
	}

	// setPartner sets the partner of "This" edge.
	public void setPartner(Edge partner) {
		this.partner = partner;
	}

	// setWeight sets the weight of "This" edge.
	public void setWeight(int weight){
		this.weight = weight;
	}

	// getWeight returns the wieght of an Edge
	// @return int weight
	public int getWeight() {
		return weight;
	}

	// getParent1 returns the DListNode which is the parent.
	// @return DListNode parent1
	public DListNode getParent1(){
		return parent1;
	}

	// getParent2 returns the DListNode which is the parent.
	// @return DListNode parent2
	public DListNode getParent2(){
		return parent2;
	}

	// getmyNode returns the DListNode which contains "this" Edge.
	// @return DListNode myNode
	public DListNode getMyNode(){
		return myNode;
	}

	// toString helps in representing an Edge as a String.
	// @return String implementation of an Edge
	public String toString(){
		return "< " + ((Vertex)parent1.item).getIdentity() + ", " + ((Vertex)parent2.item).getIdentity() + " (" + weight + ") " + " >";
	}
}