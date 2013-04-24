/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	private int buckets;
	public DList[] table;
	public int index = 0;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	
	buckets = getPrime(sizeEstimate + 1);
    System.out.println("buckets: " + buckets);
    table = new DList[buckets];
    
    
    for(int i = 0; i < buckets; i++){
    	table[i] = new DList();
    }
  }
  
  private boolean isPrime(int n){
	  for(int d = 2; d < n; d++){
		  if(n % d == 0){
			  return false;
		  }
	  }
	  return true;
  }
  
  private int getPrime(int n){
	  for(int i = n+n; i >= n; i--){
		  if(isPrime(i)){
			  return i;
		  }
	  }
	  return n;
  }


  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
	  buckets = 113;
	  table = new DList[buckets];
	  for(int i = 0; i < buckets; i++){
	    	table[i] = new DList();
	    }
	  
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
	  //System.out.println("testing 8:" + mod(8,8));
	  //System.out.println("testing 4: " + mod(4,5));
	  //System.out.println("testing 33: " + mod(10123, 33));
	  
	  int a = 5;
	  int b = 47;
	  int p = 108739;
	  //System.out.println("P :" + p);
	  int result = mod(mod(((a * code) + b), p), buckets);
	  return result;
  }
  
private int mod(int a, int b){
	int r = a % b;
	if(r < 0){
		r = r + b;
	}
	return r;
}
  

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
	  int counter = 0;
	  for(int i = 0; i < buckets; i++){
		  if(table[i] != null){  
			  counter += table[i].length();
		  }
	  }
    return counter;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return (this.size() == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
	  Entry e = new Entry();
	  e.key = key;
	  e.value = value;
	  int code = key.hashCode();
	  int index = this.compFunction(code);
	  table[index].insertBack(e);
	  return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
	  Entry e = null;
	  int code = key.hashCode();
	  int index = this.compFunction(code);
	  DList d = table[index];
	  DListNode current = (DListNode) d.front();
	  
	  while(current.isValidNode()){
		  try{
			  if(((Entry)current.item()).key().equals(key)){
				  e = (Entry) current.item();
				  break;
			  }
			  current = (DListNode) current.next();
		  }
		 catch (InvalidNodeException g){
			 System.out.println("invalid node in find :(");
		 }
	  }
	  return e;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
	  Entry e = null;
	  int code = key.hashCode();
	  int index = this.compFunction(code);
	  DList d = table[index];
	  DListNode current = (DListNode) d.front();
	  
	  while(current.isValidNode()){
		  try{
			  if(((Entry)current.item()).key().equals(key)){
				  e = (Entry) current.item();
				  current.remove();
				  break;
			  }
			  current = (DListNode) current.next();
		  }
		 catch (InvalidNodeException g){
			 System.out.println("invalid node in remove :(");
		 }
	  }
	  return e;
  }

  /**
   *  Remove all entries from the dictionary.
   *  Goes through all the buckets and sets them to reference empty DLists
   */
  public void makeEmpty() {
	  for(int i = 0 ; i < buckets; i++){
		  table[i] = new DList();
	  }
  }
  
  /**
   * Purely for testing purposes only. This tests the number of collisions
   * in the hash table versus the expected value
   */
  public int countCollisions(){
	  //String histograph = "";
	  int count = 0;
	  int maxcount = 0;
	  int max = 0;
	  for(int i = 0; i < buckets; i++){
		  //histograph = histograph + i + ":";
		  DList d = table[i];
		  if(d.length() >= 2){
				count += d.length()-1;
				maxcount += d.length()-1;
		  }
		  //histograph = histograph + "\n";
		  if(max < maxcount){
			  index = i;
			  max = maxcount;
			  maxcount = 0;
		  }
	  }
	  //System.out.println(histograph);
	  System.out.println("max: " + max);
	  System.out.println("index: " + index);
	return count;
  }
  
  /**
   * Purely for testing purposes only. This gives the expected number of collisions
   * in a hash table.
   */
  public double expectedCollisions(){
	  double problem = Math.pow((1 - 1.0 /buckets),this.size());
	  return this.size() - buckets + buckets  * problem;
  }
}

