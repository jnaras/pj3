package graph;
import list.*;

/*
* The Edge class represents our internal representation of an Edge object.
* An edge object keeps track of its two vertices, its weight and its partner edge.
* All its fields are protected.
*/
class Edge {
    protected Vertex v1;
    protected Vertex v2;
    protected int weight;
    protected Edge partner;
    protected ListNode node; //keeps track of which node it's inserted into in the adjacency list
     
    
    /* Edge has two constructors*/

   /* First constructor takes two vertices and a weight as input
    * @param Vertex x is the first vertex.
    * @param Vertex y is the second vertex.
    * @param integer representing the weight of the edge.
    * @return Edge constructed with appropriate end points and weight.
    */
    
    Edge(Vertex x, Vertex y, int w){
        weight = w;
        v1 = x;
        v2 = y;
        if(v1.equals(v2)){
            this.partner = this;
        }else{
            this.partner = new Edge(v2, v1, w, this);
        }
        v1.insertEdge(this);
        node = v1.getAdjacencyList().back();
    }
    
    /* 
     * Four parameter constructor for Edge class that creates an Edge after
     * taking in an input of two vertices, a weight and the edge's partner.
     * @param vertex x is the first vertex of the edge
     * @param vertex y is the second vertex of the edge
     * @param integer representing the weight of the edge
     * @param Edge representing the Edge's partner
     */
    Edge(Vertex x, Vertex y, int w, Edge partner){
        weight = w;
        v1 = x;
        v2 = y;
        this.partner = partner;
        v1.insertEdge(this);
        node = v1.getAdjacencyList().back();
    }
    
    /*
     * get V1() returns the first vertex of an edge.
     * @return Vertex representing the first vertex of the given edge.
     */
    Vertex getV1(){
        return v1;
    }
    
     /*
     * get V2() returns the second vertex of an edge.
     * @return Vertex representing the second vertex of the given edge.
     */
    Vertex getV2(){
        return v2;
    }
    
     /*
     * getWeight() returns the weight of an edge.
     * @return integer representing the weight of the given edge.
     */
    int getWeight(){
        return weight;
    }
    
     /*
     * getPartner() returns the edge's partner.
     * @return Edge representing the partner of the given edge.
     */
    Edge getPartner(){
        return partner;
    }
    
    ListNode getNode(){
        return node;
    }
    
}
