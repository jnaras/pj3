//Vertex.java

package graph;
import list.*;

/**
* The vertex class represents our internal representation of a vertex object.
* Each vertex object keeps track of its value, the vertices adjacent to it and its 
* degree.
* All fields are protected.
* / 

class Vertex {
    protected Object vertexValue;
    protected DList vertexAdjacencyList;
    protected int degree;
    protected ListNode node;
    
/*
* Zero parameter Vertex Constructor.
* returns a new vertex that has a null value, degree of zero and an empty 
* adjacency list i.e. has no connections to any other vertices.
* @return new Vertex object
*/
    Vertex(){
        vertexValue = null;
        vertexAdjacencyList = new DList();
        node = null;
        degree = 0;
    }
 
/* 
* One parameter Vertex Constructor.
* returns a new vertex with value v, degree zero and an empty adjacency list 
* i.e. has no connections to any other vertices.
* @param Object v that represents the value of the vertex.
* @return new Vertex object with value v.
    Vertex(Object v){
        vertexValue = v;
        vertexAdjacencyList = new DList();
        node = null;
    }
   
/*
* getVertex() returns the value of the given vertex.
* @return integer representing value of vertex
*/
    Object getVertex(){
        return vertexValue;
    }
    
/* 
* getDegree() returns the degree of the given vertex, representing the number 
* of vertices the vertex is connected to.
* @return integer representing degree of the given vertex.
*/
    int getDegree(){
        return degree;
    }
    
 /* 
 * getAdjacencyList() returns a list of edges that are connected to the given 
* vertex.
*  @return DList of edges
*/  
    public DList getAdjacencyList(){
       return vertexAdjacencyList;
    }

    /*
    * insertEdge takens an edge as an input, an inserts it into the graph, 
    * incrementing the degree of the vertex and adding the edge to the vertex's
    *  adjacency list.
    * @param Edge e - the edge that is being inserted into the graph.
    */
    void insertEdge(Edge e){
        this.vertexAdjacencyList.insertBack(e);
        degree++;
    }
    
/*
* 
*/
    void removeEdge(Edge e){
        try{
    		e.getNode().remove();
    		degree--;
    	}
    	catch(InvalidNodeException n){
    		n.printStackTrace();
    	}
    }
    
    ListNode getNode(){
        return node;
    }
    
    void setNode(ListNode d){
        node = d;
    }
} 
