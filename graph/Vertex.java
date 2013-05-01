//Vertex.java

package graph;
import list.*;

class Vertex {
    protected Object vertexValue;
    protected DList vertexAdjacencyList;
    protected int degree;
    protected ListNode node;
    
    Vertex(){
        vertexValue = null;
        vertexAdjacencyList = new DList();
        node = null;
        degree = 0;
    }
 
    Vertex(Object v){
        vertexValue = v;
        vertexAdjacencyList = new DList();
        node = null;
    }
   
    Object getVertex(){
        return vertexValue;
    }
    
    int getDegree(){
        return degree;
    }
    
    public DList getAdjacencyList(){
       return vertexAdjacencyList;
    }
    
    void insertEdge(Edge e){
        this.vertexAdjacencyList.insertBack(e);
        degree++;
    }
    
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
