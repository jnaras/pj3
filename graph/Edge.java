
package graph;
import list.*;

class Edge {
    protected Vertex v1;
    protected Vertex v2;
    protected int weight;
    protected Edge partner;
    protected ListNode node; //keeps track of which node it's inserted into in the adjacency list
     
    
    //two constructors
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
    
    Edge(Vertex x, Vertex y, int w, Edge partner){
        weight = w;
        v1 = x;
        v2 = y;
        this.partner = partner;
        v1.insertEdge(this);
        node = v1.getAdjacencyList().back();
    }
    
    Vertex getV1(){
        return v1;
    }
    
    Vertex getV2(){
        return v2;
    }
    
    int getWeight(){
        return weight;
    }
    
    Edge getPartner(){
        return partner;
    }
    
    ListNode getNode(){
        return node;
    }
    
}
