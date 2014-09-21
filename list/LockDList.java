package list;

public class LockDList extends DList {

	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item,prev,next);
	}

	public void lockNode(DListNode node) {
		((LockDListNode) node).isLocked = true;
	}

	public void remove(DListNode node) {
		if (((LockDListNode) node).isLocked == false) {
			super.remove(node);
		}
	}

	public static void main(String[] argv) {

	LockDList l1 = new LockDList();
	System.out.println("empty? true: " + l1.isEmpty());
	System.out.println("length should be zero : " + l1.length());

	System.out.println("\ninsertFront");
	l1.insertFront(1);
	System.out.println("empty? false: " + l1.isEmpty());
	System.out.println("length Should be 1: " + l1.length());
	System.out.println("list should be [ 1 ]: " + l1);
	l1.insertFront(3);
	l1.insertFront(6);
	l1.insertFront(9);
	System.out.println(l1);

	LockDListNode l2 = (LockDListNode) ((DList) l1).front();
	l2 = (LockDListNode) l1.next(l2);
	l2 = (LockDListNode) l1.next(l2);
	l1.remove(l2);

	System.out.println(l1);

    LockDList l = new LockDList();
	l.insertFront(9);
	System.out.println("\nInserting 9 at front. \nList is " + l);
	l.insertFront(8);
	l.insertFront(7);
	System.out.println("\nInserting 7, 8 at the front. \nList with 7, 8, 9 is " + l);
	l.insertAfter(6, l.head);
	System.out.println("\nInserting 6 after head. nList with 6, 7, 8, 9 is "+l);
	l.remove(l.head.next);
	System.out.println("Removed head.next, should be list of 7, 8, 9. nList with 7, 8, 9 is " + l);
	LockDList m = new LockDList();
	m.insertFront(9);
	m.insertFront(8);
	m.insertFront(7);
	System.out.println("\nInserting 7, 8, 9 at the front. List is " + m);
	m.lockNode(m.head.next);
	m.remove(m.head.next);
	System.out.println("first element should not be removed. List with 7, 8, 9 is " + m);
  }
}