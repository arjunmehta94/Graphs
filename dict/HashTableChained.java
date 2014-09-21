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
  private DList[] dict;
  private int numberOfBuckets;
  private int size = 0;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    numberOfBuckets = primeEqualToOrLargerThan(sizeEstimate);
    dict = new DList[numberOfBuckets];
    for (int i = 0; i < dict.length; i++) {
      dict[i] = new DList();
    }

  }

  public int numberOfBuckets() {
    return numberOfBuckets;
  }

  public int getSize() {
    return size;
  }

  public double loadFactor () {
    return (((double) size)/((double) numberOfBuckets));
  }

  private int primeEqualToOrLargerThan(int n) { //works
    int i = n;
    while (!isPrime(i)) {
      i += 1;
    }
    return i;
  }

  private boolean isPrime(int n) { //works
    for (int i = 2; i <= Math.pow(n,0.5); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
    
  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    numberOfBuckets = primeEqualToOrLargerThan(100);
    dict = new DList[numberOfBuckets];
    for (int i = 0; i < dict.length; i++) {
      dict[i] = new DList();
    }
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  private int compFunction(int code) {
    // Replace the following line with your solution.
    // System.out.println("code: " + code);
    // System.out.println("numBuckets: " + numberOfBuckets);
    // System.out.println((primeEqualToOrLargerThan(19*numberOfBuckets)) % numberOfBuckets);
    int compressedCode = ((127 * code + 673) % primeEqualToOrLargerThan(19*numberOfBuckets)) % numberOfBuckets;
    if (compressedCode < 0) {
      compressedCode += numberOfBuckets;
    }
    return compressedCode;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int countCollisions() {
    int count = 0;
    for (int i = 0; i < numberOfBuckets; i++) {
      if (dict[i].length() > 1) {
        count += dict[i].length()-1;
      }
    }
    return count;
  }

  public int size() {
    // Replace the following line with your solution.
    return size;
  //  int count;
  //  for (int i = 0; i < dict.length; i++) {
  //    count += dict[i].length();
  //  }
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size == 0;
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
    // Replace the following line with your solution.
    //System.out.println("old graph");
    //System.out.println(this);

    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;
    int compressedCode = this.compFunction(key.hashCode());
    dict[compressedCode].insertBack(entry);
    size++;
   // System.out.println("load fact before : " + loadFactor());
    //System.out.println(this);
    if ((loadFactor() <= 0.3 || loadFactor() >= 1.0) && size > 9) {
     // System.out.println("Enlarging Now");
      resize();
     // System.out.println("load fact: " + loadFactor());
      //System.out.println("graph after resizing");
      //System.out.println(this);
    }
    return entry;
  }



  private void resizeInsert(Object key, Object value, DList[] temp) {
      //System.out.println("here");
      Entry entry = new Entry();
      entry.key = key;
      entry.value = value;
      int compressedCode = this.compFunction(key.hashCode());
      temp[compressedCode].insertBack(entry);
    }


public void resize() {
    
    DList[] temp;
    if (loadFactor() < 0.3) {
      
      numberOfBuckets = this.primeEqualToOrLargerThan(numberOfBuckets/2);
      temp = new DList[numberOfBuckets];
      
    } else { //checking
      numberOfBuckets = this.primeEqualToOrLargerThan(numberOfBuckets*2);
      temp = new DList[numberOfBuckets];
     
    }

    for(int i =0; i<temp.length; i++){
        temp[i] = new DList();
      }
    

    for(DList dlist : dict) {
     
      if(!dlist.isEmpty()){
        DListNode front = dlist.front();
       
        while(front != null) {

         
          resizeInsert(((Entry)front.item).key(), ((Entry)front.item).value(), temp);
          if (front.next().item() != null){
            
            front = front.next();
          } else {
          
            break;
          }
          
        }
      }
    }

    this.dict = temp;
   
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
    // Replace the following line with your solution.
    int compressedCode = compFunction(key.hashCode());
    DList list = dict[compressedCode];
    if (list.length() != 0) {
      DListNode a = list.front();
      while (a != null) {
        if (((Entry) a.item()).key().equals(key)) {
          return (Entry) a.item();
        }
        else {
          a = list.next(a);
          if (a == null) {
            return null;
          }
        }
      }
      return null;
    }
    return null;
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
    if ((loadFactor() <= 0.3 || loadFactor() >= 1.0) && size > 9) {
    
      resize();
         
    }
    
    int compressedCode = compFunction(key.hashCode());
    DList list = dict[compressedCode];
    if (list.length() != 0) {
      DListNode a = list.front();
      while (a != null) {
        if (((Entry) a.item()).key().equals(key)) {
          list.remove(a);
          size--;
          return (Entry) a.item();
        }
        else {
          a = a.next();
        }
      }
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for(int i = 0; i < numberOfBuckets; i++) {
      dict[i].reset();
    }
    size = 0;
  }

  public String toString() {
    for(int i = 0; i < numberOfBuckets; i++) {
      DList list = dict[i];
      System.out.print("[ ");
      DListNode curr = list.front();
      for (int j = 0; j < list.length(); j++) {
        System.out.print("||" + ((Entry) curr.item()).key() + ", " + ((Entry)curr.item()).value() + "|| ");
        curr = curr.next();
      }
      System.out.println("]");
    }
    return "";
  }


  // public static void main(String[] arg) {
  //   HashTableChained hashTable = new HashTableChained(14);
  //   System.out.println(hashTable.numberOfBuckets);
  //   System.out.println(hashTable);
  //   System.out.println(hashTable.isEmpty());
  //   hashTable.insert(15,14);
  //   System.out.println(hashTable);
  //   hashTable.insert(33,27);
  //   System.out.println(hashTable);
  //   hashTable.insert(26,38);
  //   System.out.println("hashTable");
  //   System.out.println(hashTable);
  //   hashTable.insert(132,33);
  //   System.out.println(hashTable);
  //   System.out.println(hashTable.size);
  //   System.out.println(hashTable.find(27));
  //   System.out.println(((Entry) hashTable.find(15)).key());
  //   hashTable.remove(33);
  //   System.out.println(hashTable);
  //   System.out.println(hashTable.size);
  //   System.out.println(hashTable.countCollisions());
  //   hashTable.makeEmpty();
  //   System.out.println(hashTable);
  // }
//
}