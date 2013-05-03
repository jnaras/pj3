/* WUGraph.java */

package graph;
import list.*;
import dict.*;
/**
* The WUGraph class represents a weighted, undirected graph. Self-edges are
* permitted.
*/

public class WUGraph {
    
    private int numberofvertices;
    private int numberofedges;
    private HashTableChained edgeHashTable;
    private HashTableChained vertexHashTable;
    private DList vertexList;
    

/**
* WUGraph() constructs a graph having no vertices or edges.
*
* Running time: O(1).
*/
  public WUGraph(){
      numberofvertices = 0;
      numberofedges = 0;
      edgeHashTable = new HashTableChained();
      vertexHashTable = new HashTableChained();
      vertexList = new DList();
  }

/**
* vertexCount() returns the number of vertices in the graph.
*
* Running time: O(1).
*/
  public int vertexCount(){
      return numberofvertices;
  }

/**
* edgeCount() returns the number of edges in the graph.
*
* Running time: O(1).
*/
  public int edgeCount(){
      return numberofedges;
  }

/**
* getVertices() returns an array containing all the objects that serve
* as vertices of the graph. The array's length is exactly equal to the
* number of vertices. If the graph has no vertices, the array has length
* zero.
*
* (NOTE: Do not return any internal data structure you use to represent
* vertices! Return only the same objects that were provided by the
* calling application in calls to addVertex().)
*
* Running time: O(|V|).
*/
  public Object[] getVertices(){
      Object[] result = new Object[numberofvertices];
      int index = 0;
      ListNode current = vertexList.front();
      
      while(current.isValidNode()){
          try{
              result[index] = ((Vertex) current.item()).getVertex();
              current = current.next();
              index++;
           }
          catch(InvalidNodeException e){
              e.printStackTrace();
          }
      }
      return result;
   }

/**
* addVertex() adds a vertex (with no incident edges) to the graph. The
* vertex's "name" is the object provided as the parameter "vertex".
* If this object is already a vertex of the graph, the graph is unchanged.
*
* Running time: O(1).
*/
  public void addVertex(Object vertex){
      if (!isVertex(vertex)){
          Vertex newvertex = new Vertex(vertex);
          vertexHashTable.insert(vertex, newvertex);
          vertexList.insertBack(newvertex);
          newvertex.setNode(vertexList.back());
          numberofvertices++;
      }
  }

/**
* removeVertex() removes a vertex from the graph. All edges incident on the
* deleted vertex are removed as well. If the parameter "vertex" does not
* represent a vertex of the graph, the graph is unchanged.
*
* Running time: O(d), where d is the degree of "vertex".
*/
  public void removeVertex(Object vertex){
      Entry t = vertexHashTable.find(vertex);
      
      if(t == null){
          return;
      }
      Vertex v = (Vertex) t.value(); 
      ListNode current = v.getAdjacencyList().front();
      ListNode next;
      
      while(current.isValidNode()){
          try{
              Edge e = (Edge)current.item();
              next = current.next();
              this.removeEdge(e.getV1().vertexValue, e.getV2().vertexValue);
              current = next;
          }
          catch(InvalidNodeException e){
              e.printStackTrace();
          }
      }
      try{
        v.getNode().remove();
      }
      catch(InvalidNodeException e){
          e.printStackTrace();
      }
      t = vertexHashTable.remove(vertex);
      numberofvertices--;
  }

/**
* isVertex() returns true if the parameter "vertex" represents a vertex of
* the graph.
*
* Running time: O(1).
*/
  public boolean isVertex(Object vertex){
      if(vertexHashTable.find(vertex)!=null){
          return true;
      } else{
          return false;
      }
  }

/**
* degree() returns the degree of a vertex. Self-edges add only one to the
* degree of a vertex. If the parameter "vertex" doesn't represent a vertex
* of the graph, zero is returned.
*
* Running time: O(1).
*/
  public int degree(Object vertex){
      Entry newentry = vertexHashTable.find(vertex);
      if (newentry != null){
         Vertex v = (Vertex) newentry.value();
        return v.getDegree();
      }
      return 0;
  }

/**
* getNeighbors() returns a new Neighbors object referencing two arrays. The
* Neighbors.neighborList array contains each object that is connected to the
* input object by an edge. The Neighbors.weightList array contains the
* weights of the corresponding edges. The length of both arrays is equal to
* the number of edges incident on the input vertex. If the vertex has
* degree zero, or if the parameter "vertex" does not represent a vertex of
* the graph, null is returned (instead of a Neighbors object).
*
* The returned Neighbors object, and the two arrays, are both newly created.
* No previously existing Neighbors object or array is changed.
*
* (NOTE: In the neighborList array, do not return any internal data
* structure you use to represent vertices! Return only the same objects
* that were provided by the calling application in calls to addVertex().)
*
* Running time: O(d), where d is the degree of "vertex".
*/
  public Neighbors getNeighbors(Object vertex){
	  Vertex v = (Vertex) vertexHashTable.find(vertex).value();
	  if (!isVertex(vertex)){
	      return null;
	      
	  } else if(v.degree==0){
	      return null;
	      
	  } else{
	      Neighbors newNeighbors = new Neighbors();
	      newNeighbors.neighborList = new Object[v.degree];
	      newNeighbors.weightList = new int[v.degree];
	      ListNode currentnode = v.vertexAdjacencyList.front();
	      
	      for (int index=0; index<v.vertexAdjacencyList.length(); index++){
	          try{
	        	Object u = ((Edge)currentnode.item()).getV2().getVertex();
	        	newNeighbors.neighborList[index] = u;
	        	newNeighbors.weightList[index] = ((Edge)currentnode.item()).getWeight();
	        	currentnode = currentnode.next();
	          } catch (InvalidNodeException ine){
	          	ine.printStackTrace();
	          }
	      }
	      return newNeighbors;
	  }
	}

/**
* addEdge() adds an edge (u, v) to the graph. If either of the parameters
* u and v does not represent a vertex of the graph, the graph is unchanged.
* The edge is assigned a weight of "weight". If the edge is already
* contained in the graph, the weight is updated to reflect the new value.
* Self-edges (where u == v) are allowed.
*
* Running time: O(1).
*/
  public void addEdge(Object u, Object v, int weight){
      VertexPair newvertexpair = new VertexPair(u,v);
      if (isEdge(u,v)){
          ((Edge)edgeHashTable.find(newvertexpair).value()).weight = weight;
          
      } else if(!isVertex(u) || !isVertex(v)){
          return;
          
      } else{
          Vertex x = (Vertex) vertexHashTable.find(u).value();
          Vertex y = (Vertex) vertexHashTable.find(v).value();
          Edge newedge = new Edge(x,y,weight);
          edgeHashTable.insert(newvertexpair,newedge);
          numberofedges++;
      }
  }

  /**
* removeEdge() removes an edge (u, v) from the graph. If either of the
* parameters u and v does not represent a vertex of the graph, the graph
* is unchanged. If (u, v) is not an edge of the graph, the graph is
* unchanged.
*
* Running time: O(1).
*/
  public void removeEdge(Object u, Object v){
      if (!isEdge(u,v)){
          return;
      } else{
          VertexPair nvp1 = new VertexPair(u,v);
          Vertex v1 = (Vertex) vertexHashTable.find(v).value();
          Vertex u1 = (Vertex) vertexHashTable.find(u).value();
          
          Edge e = (Edge) edgeHashTable.remove(nvp1).value();
          Edge p = e.getPartner();
          u1.removeEdge(e);
          
            if(!v.equals(u)){
                v1.removeEdge(p);
            }
          numberofedges--;
      }
  }

  /**
* isEdge() returns true if (u, v) is an edge of the graph. Returns false
* if (u, v) is not an edge (including the case where either of the
* parameters u and v does not represent a vertex of the graph).
*
* Running time: O(1).
*/
  public boolean isEdge(Object u, Object v){
      VertexPair newvp = new VertexPair(u,v);
      
      if(vertexHashTable.find(u) == null){
    	  return false;
      }
      if(vertexHashTable.find(v) == null){
    	  return false;
      }
      if(edgeHashTable.find(newvp)!=null){
          return true;
      }
      return false;
  }

  /**
* weight() returns the weight of (u, v). Returns zero if (u, v) is not
* an edge (including the case where either of the parameters u and v does
* not represent a vertex of the graph).
*
* (NOTE: A well-behaved application should try to avoid calling this
* method for an edge that is not in the graph, and should certainly not
* treat the result as if it actually represents an edge with weight zero.
* However, some sort of default response is necessary for missing edges,
* so we return zero. An exception would be more appropriate, but
* also more annoying.)
*
* Running time: O(1).
*/
  public int weight(Object u, Object v){
      if (isEdge(u,v)){
          VertexPair newpair = new VertexPair(u,v);
          Edge e = (Edge)edgeHashTable.find(newpair).value();
          return e.getWeight();
      } else{
          return 0;
      }
  }
}


