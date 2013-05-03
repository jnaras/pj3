package kEdge;
/*
* This is the KEdge class used to store information in Kruskal.java. Each KEdge keeps track of 
* its weight, vertex1, vertex2, and a visited flag.
*/

public class KEdge {
	int weight;
	Object vertex1;
	Object vertex2;
	boolean visited;
	
	
	/**
	* The constructor takes in two objects to be vertices and a weight. Each KEdge is not visited
	* when it is first created.
	*/
	public KEdge(Object o1, Object o2, int weight) {
		this.vertex1 = o1;
		this.vertex2 = o2;
		this.weight = weight;
		this.visited = false;
	}

	/**
	 * Method checks whether this edge and another are merely inverses of each other, e.g. the same edge but backwards.
	 * The undirected graph does not distinguish between the two.
	 * @param edge2 edge in question to compare
	 * @return true if same edge as this, false if different edges.
	 */
	public boolean equivalent(KEdge edge2){
		if(this.vertex1 == edge2.vertex2 && this.vertex2 == edge2.vertex1 && this.weight == edge2.weight){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * vertex1()
	 * @return object in one of the vertices
	 */
	public Object vertex1(){
		return vertex1;
	}
	
	/**
	 * vertex2()
	 * @return object in other vertex
	 */
	public Object vertex2(){
		return vertex2;
	}
	
	/**
	 * weight()
	 * @return returns weight of the edge
	 */
	public int weight(){
		return this.weight;
	}
}

