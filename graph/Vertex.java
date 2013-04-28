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
        return vertexAdjacencyList.length();
    }
    
    DList getAdjacencyList(){
       return vertexAdjacencyList;
    }
    
    void insertEdge(Edge e){
        this.vertexAdjacencyList.insertBack(e);
    }
    
    ListNode getNode(){
        return node;
    }
    
    void setNode(ListNode d){
        node = d;
    }
}   
