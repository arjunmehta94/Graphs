/* ListSorts.java */
package graphalg;
import list.*;


public class ListSorts {

  private final static int SORTSIZE = 100000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue tmp = new LinkedQueue();
    try{
      if(q.size()==0){
        tmp.enqueue(new LinkedQueue());
        
      }
      else{
        while(q.size() > 0){
          LinkedQueue tmp_new = new LinkedQueue();
          tmp_new.enqueue(q.dequeue());
          tmp.enqueue(tmp_new);

        }
        
      }
    }catch(QueueEmptyException e){
      System.out.println(e);
    } 
    return tmp; 
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
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue new_queue = new LinkedQueue();

    try{
      // loops while both have more than 1 item in queue
      while(q1.size() > 0 && q2.size()>0){
        Comparable item1 = ((Comparable)((Edge)q1.front()).getWeight()); //Originally (Comparable)q1.front()
        Comparable item2 = ((Comparable)((Edge)q2.front()).getWeight()); //Originally (Comparable)q2.front()
        if(item1.compareTo(item2) <= 0){
          new_queue.enqueue(q1.dequeue());
          
        }
        else{
          new_queue.enqueue(q2.dequeue());
          
        }
        
      }
      // once all q2 items are enqueued to new queue, remaining q1 items put
      if(q1.size() > 0){
        while(q1.size() > 0){
          new_queue.enqueue(q1.dequeue());
        }
      }
      else{
        while(q2.size() > 0){
          new_queue.enqueue(q2.dequeue());
        }
      }


    }catch(QueueEmptyException e){
      System.out.println(e);
    }

    return new_queue;

  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    try{
      while(qIn.size() > 0){
        Comparable item = ((Comparable)qIn.dequeue());
        if(item.compareTo(pivot) < 0){
          qSmall.enqueue(item);
        }
        else if(item.compareTo(pivot) == 0){
          qEquals.enqueue(item);
        }
        else{
          qLarge.enqueue(item);
        }
    }
    }catch(QueueEmptyException e){
      System.out.println(e);
    }

    
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergeSort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    LinkedQueue qOfQ = makeQueueOfQueues(q);
    try{
      while(qOfQ.size() != 1){
        LinkedQueue item1 = ((LinkedQueue)qOfQ.dequeue());
        LinkedQueue item2 = ((LinkedQueue)qOfQ.dequeue());
        LinkedQueue merged = mergeSortedQueues(item1, item2);
        qOfQ.enqueue(merged);
      }
      q.append(((LinkedQueue)qOfQ.front()));

    }catch(QueueEmptyException e){
      System.out.println(e);
    }
    
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
     LinkedQueue qSmall = new LinkedQueue();
      LinkedQueue qEquals = new LinkedQueue();
      LinkedQueue qLarge = new LinkedQueue();
    
   
    if(q.size() == 1 || q.size() == 0){
      return;
    }
    else{
      int pivot_index = (int)Math.random()*q.size();
      Comparable pivot = ((Comparable)q.nth(pivot_index));
      partition(q, pivot, qSmall, qEquals, qLarge);
      quickSort(qSmall);
      quickSort(qLarge);
      
    }

    qSmall.append(qEquals);
      qSmall.append(qLarge);
      q.append(qSmall);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergeSort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  //public static void main(String [] args) {



    // ////TESTS FOR MAKEQUEUEOFQUEUES -- WORKS/////

    // LinkedQueue q = makeRandom(5);
    // System.out.println(q.toString());
    // System.out.println(makeQueueOfQueues(q));

    // LinkedQueue q1 = new LinkedQueue();
    // q1.enqueue(new Integer(3));
    // q1.enqueue(new Integer(5));
    // q1.enqueue(new Integer(2));
    // System.out.println(q1.toString());
    // System.out.println(makeQueueOfQueues(q1));


    // LinkedQueue q2 = new LinkedQueue();
    // System.out.println(q2.toString());
    // System.out.println(makeQueueOfQueues(q2));





    // /////TESTS FOR MERGESORTEDQUEUES -- WORKS ///

    // LinkedQueue q1 = new LinkedQueue();
    // LinkedQueue q2 = new LinkedQueue();
    // q1.enqueue(new Integer(2));
    // q1.enqueue(new Integer(4));
    // q1.enqueue(new Integer(6));

    // System.out.println(q1);

    // q2.enqueue(new Integer(3));
    // q2.enqueue(new Integer(5));
    // q2.enqueue(new Integer(7));
    // System.out.println(q2);
    // System.out.println(mergeSortedQueues(q1,q2));
    // System.out.println(q1);
    // System.out.println(q2);
    




    // //TESTS FOR MERGESORT -- WORKS ON TESTS TRIED

    // System.out.println("Test for mergeSort on random queue with 10 entries ");
    // LinkedQueue q = makeRandom(10);
    // System.out.println(q.toString());
    // mergeSort(q);
    // System.out.println("Should be sorted ascending order");
    // System.out.println(q.toString());

    // System.out.println("Test for mergeSort on queue with 1 entry");
    // LinkedQueue q1 = new LinkedQueue();
    // q1.enqueue(new Integer(1));
    // System.out.println(q1.toString());
    // mergeSort(q1);
    // System.out.println("Should be [ 1 ]");
    // System.out.println(q1.toString());


    // System.out.println("Test for mergeSort on empty queue");
    // LinkedQueue q2 = new LinkedQueue();
    // System.out.println(q2.toString());
    // mergeSort(q2);
    // System.out.println("Should be [ ] ");
    // System.out.println(q2.toString());

    // System.out.println("Test for mergeSort on already sorted queue");
    // LinkedQueue q3 = new LinkedQueue();
    // q3.enqueue(new Integer(1));
    // q3.enqueue(new Integer(2));
    // q3.enqueue(new Integer(3));
    // q3.enqueue(new Integer(4));
    // q3.enqueue(new Integer(5));
    // q3.enqueue(new Integer(6));
    // q3.enqueue(new Integer(7));
    // q3.enqueue(new Integer(8));
    // q3.enqueue(new Integer(9));
    // System.out.println(q3.toString());
    // mergeSort(q3);
    // System.out.println("Should be [ 1 2 3 4 5 6 7 8 9 ]");
    // System.out.println(q3.toString());

    // System.out.println("Test for mergeSort on queue with all same entries");
    // LinkedQueue q4 = new LinkedQueue();
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // System.out.println(q4.toString());
    // mergeSort(q4);
    // System.out.println("Should be [ 4 4 4 4 4 4 4 4 4 ]");
    // System.out.println(q4.toString());


    // //TESTS FOR QUICKSORT -- WORKS ON TESTS TRIED


    // System.out.println("Test for quickSort on queue with 10 random entries");
    // LinkedQueue q = makeRandom(10);
    // System.out.println(q.toString());
    // quickSort(q);
    // System.out.println("Should be sorted in ascending order");
    // System.out.println(q.toString());

    // System.out.println("Test for quickSort on queue with 1 entry");
    // LinkedQueue q1 = new LinkedQueue();
    // q1.enqueue(new Integer(1));
    // System.out.println(q1.toString());
    // quickSort(q1);
    // System.out.println("Should be [ 1 ]");
    // System.out.println(q1.toString());


    // System.out.println("Test for quickSort on empty queue");
    // LinkedQueue q2 = new LinkedQueue();
    // System.out.println(q2.toString());
    // quickSort(q2);
    // System.out.println("Should be [ ] ");
    // System.out.println(q2.toString());

    // System.out.println("Test for quickSort on already sorted queue");
    // LinkedQueue q3 = new LinkedQueue();
    // q3.enqueue(new Integer(1));
    // q3.enqueue(new Integer(2));
    // q3.enqueue(new Integer(3));
    // q3.enqueue(new Integer(4));
    // q3.enqueue(new Integer(5));
    // q3.enqueue(new Integer(6));
    // q3.enqueue(new Integer(7));
    // q3.enqueue(new Integer(8));
    // q3.enqueue(new Integer(9));
    // System.out.println(q3.toString());
    // quickSort(q3);
    // System.out.println("Should be [ 1 2 3 4 5 6 7 8 9 ]");
    // System.out.println(q3.toString());

    // System.out.println("Test for quickSort on queue with all same entries");
    // LinkedQueue q4 = new LinkedQueue();
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // q4.enqueue(new Integer(4));
    // System.out.println(q4.toString());
    // quickSort(q4);
    // System.out.println("Should be [ 4 4 4 4 4 4 4 4 4 ]");
    // System.out.println(q4.toString());

    // // Remove these comments for Part III.
    // Timer stopWatch = new Timer();
    // LinkedQueue q = makeRandom(SORTSIZE);
    // stopWatch.start();
    // mergeSort(q);
    // stopWatch.stop();
    // System.out.println("mergeSort time, " + SORTSIZE + " Integers:  " +
    //                    stopWatch.elapsed() + " msec.");

    // stopWatch.reset();
    // LinkedQueue q1 = makeRandom(SORTSIZE);
    // stopWatch.start();
    // quickSort(q1);
    // stopWatch.stop();
    // System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
    //                    stopWatch.elapsed() + " msec.");


    // TESTS FOR STABILITY 
    // Integer q1 = new Integer(1);
    // Integer q2 = new Integer(1);
    // LinkedQueue q = new LinkedQueue();
    // q.enqueue(q1);
    // q.enqueue(q2);
    // System.out.println(q);
    // quickSort(q);
    // System.out.println(q);
    // try{
    //   if(q1 == q.dequeue()){
    //     System.out.println("Stable");
    //   }
    //   else if(q1 != q.dequeue()){
    //     System.out.println("Unstable");
    //   }
    // }catch(QueueEmptyException e){
    //   System.out.println(e);
    // }
    
    
    
  //}

}
