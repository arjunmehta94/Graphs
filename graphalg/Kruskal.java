/* Kruskal.java */

package graphalg;

import graph.*;
import set.*;
import list.*;
import dict.*;


/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g){
  	WUGraph t = new WUGraph();
    HashTableChained vertexHashtable = new HashTableChained(g.vertexCount());
    DisjointSets vertexSet = new DisjointSets(g.vertexCount());
  	LinkedQueue edgeQueue = new LinkedQueue();
  	Object[] vertices = g.getVertices();

  	for(int i = 0; i<vertices.length; i++){
  		t.addVertex(vertices[i]);
      vertexHashtable.insert(vertices[i], new Integer(i));
  		Neighbors neighbor = g.getNeighbors(vertices[i]);

      if(neighbor!= null){
        for(int x = 0; x<neighbor.neighborList.length; x++){
        
        edgeQueue.enqueue(new Edge(vertices[i], neighbor.neighborList[x], neighbor.weightList[x]));
        }
      }
  	}

  	
  	ListSorts.mergeSort(edgeQueue);//sorts the queue using mergeSort

  	//kruskals algorithm
    //loop through sorted queue of edges
    SListNode front = null;
    try{
      front = edgeQueue.frontNode();
    }
    catch(QueueEmptyException e){
      System.out.println(e);
    }
    
    Edge edge = null;
    Object object1 = null, object2 = null;
    int j= 0, val1 = 0, val2 = 0;
    while(j!=edgeQueue.size()){//iterate through the queue of edges
      
      edge = ((Edge)front.item);
      object1 = edge.getObject1();
      object2 = edge.getObject2();
      val1 = ((Integer)((Entry)vertexHashtable.find(object1)).value()).intValue();
      val2 = ((Integer)((Entry)vertexHashtable.find(object2)).value()).intValue();

      if(object1 != object2){ //not self edges
        if(t.isEdge(object1, object2)){ //if that edge exists in the graph, do nothing
          
        }
        else{// if the edge does not exist in graph
          if(vertexSet.find(val1) == vertexSet.find(val2)){//if they have the same parent, path already exists

          }
          else{//the 2 objects do not have the same parent, add the edge to the graph
            t.addEdge(object1, object2, edge.getNumericWeight());
            vertexSet.union(vertexSet.find(val1), vertexSet.find(val2));
          }
         
        }
      }
      else{ // if 2 objects are equal, i.e. self edges
        if(t.isEdge(object1, object2)){//if self edge exists in graph, do nothing

        }
        else{//if the self edge does not exist in graph
          t.addEdge(object1, object2, edge.getNumericWeight());
         
        }
      }

      front = front.next;
      j++;
    }
    return t;


  }

  //public static void main(String[] args){}

}
