package list;

public class LockDListNode extends DListNode {

	protected boolean isLocked;

	LockDListNode(Object item, DListNode previous, DListNode next) {
		super(item,previous,next);
		isLocked = false;
	}
}