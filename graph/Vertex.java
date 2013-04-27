//Vertex.java

package graph;

class Vertex {
    protected Object vertexValue;
    
    void vertex(){
        vertexValue = null;
    }
 
    void vertex(Object v){
        vertexValue = v;
    }
   
    Object getVertex(){
        return vertexValue;
    }
    
}   
