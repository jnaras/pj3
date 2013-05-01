/* Kruskal.java */

import dict.*;
import edge.*;
import graph.*;
import set.*;
import list.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   */
  public static WUGraph minSpanTree(WUGraph g){
  
    WUGraph t = new WUGraph(); 
	  Object[] gVertices = g.getVertices();
	  
	  for(int i = 0; i < gVertices.length; i++){ // for all the vertices in the graph
		  t.addVertex(gVertices[i]);  //add them to the copy, t
	  }
	  
	  
	  LinkedQueue edgeList = new LinkedQueue();	
	  
	  for(int n = 0; n < gVertices.length; n++){ //for every vertex in the graph g...
		  for(int m = 0; m < g.getNeighbors(gVertices[n]).neighborList.length; m++){ //for every neighbor that that vertex has...
			  Object o1 = gVertices[n];
			  Object o2 = g.getNeighbors(g.getVertices()[n]).neighborList[m];
			  int weight =  g.getNeighbors(g.getVertices()[n]).weightList[m];
					
			  Edge current = new Edge(o1, o2, weight);
			  boolean duplicate = false;
			  for(int c = 1; c <= edgeList.size(); c++){ //go through the linked queue
				   if(current.equivalent((Edge) edgeList.nth(c))){ //check if this current edge is already in the queue in reversed form
					   duplicate = true;
					   break;
				   }
			   }
			  if(!duplicate){
				  edgeList.enqueue(current); //add this edge to the list
			  }
		  }
	  }
	  
	  mergeSort(edgeList); //how do you use the weights as keys instead of sorting the Edge objects themselves?
	  
	  
	  HashTableChained h = new HashTableChained(t.vertexCount());
	  for(int s = 0; s < t.vertexCount(); s++){
		  h.insert(t.getVertices()[s], s);
	  }
	  
	  DisjointSets d = new DisjointSets(t.vertexCount());
	  
	  for(int q = 1; q <= edgeList.size(); q++){
		  int minCode1 = (int) h.find(((Edge) edgeList.nth(q)).vertex1()).value();
		  int minCode2 = (int) h.find(((Edge) edgeList.nth(q)).vertex2()).value();
		  if(d.find(minCode1) != d.find(minCode2)){
			  t.addEdge(((Edge) edgeList.nth(q)).vertex1(), ((Edge) edgeList.nth(q)).vertex2(), ((Edge) edgeList.nth(q)).weight());
			  d.union(d.find(minCode1),  d.find(minCode2));
		  }
	  }
	  
	  return t;
	  
}
	
  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q){
		 LinkedQueue queueOfQueues = new LinkedQueue();
		 try {
			 while(!q.isEmpty()){
				LinkedQueue newQueue = new LinkedQueue();
				newQueue.enqueue(q.front());
				q.dequeue();
				queueOfQueues.enqueue(newQueue);
			 } 
		 }
			 catch (QueueEmptyException e) {
				e.printStackTrace();
			 }
		 return queueOfQueues;
	 }
	 

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  private static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
		 LinkedQueue mergedQueue = new LinkedQueue();
		 try {
			 while(!q1.isEmpty() && !q2.isEmpty()){
				if(((Comparable) ((Edge) q2.front()).weight()).compareTo(((Edge) q1.front()).weight()) < 0){
					 mergedQueue.enqueue(q2.front());
					 q2.dequeue();
				 }
				else{
					mergedQueue.enqueue(q1.front());
					q1.dequeue();
				}
			 }
			 if(q1.size() > q2.size()){
				 while(!q1.isEmpty()){
					 mergedQueue.enqueue(q1.front());
					 q1.dequeue();
				 }
			 }
			 else if(q1.size() < q2.size()){
				 while(!q2.isEmpty()){
					 mergedQueue.enqueue(q2.front());
					 q2.dequeue();
				 }
			 }
		 }catch (QueueEmptyException e) {
			e.printStackTrace();
		 }
		 return mergedQueue;
	 }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  private static void mergeSort(LinkedQueue q) {
	  try{
		  if(q.isEmpty()){
			  return;
		  }
		  LinkedQueue p = q;
		  p = makeQueueOfQueues(p);
		  while(p.size() > 1){
			  LinkedQueue p1 = (LinkedQueue) p.dequeue();
			  LinkedQueue p2 = (LinkedQueue) p.dequeue();
			  p.enqueue(mergeSortedQueues(p1, p2));
		  }
		  p.append((LinkedQueue) p.dequeue());
		  while(!q.isEmpty()){
			  q.dequeue();
		  }
		  q.append(p);
	  }
	  catch (QueueEmptyException e) {
			e.printStackTrace();
		 }
  	}
}
