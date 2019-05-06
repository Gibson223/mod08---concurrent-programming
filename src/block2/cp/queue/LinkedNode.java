package block2.cp.queue;

public class LinkedNode {

    private Object thisObject;
    private LinkedNode next;
    private LinkedNode prev;
    private int index;

    public LinkedNode(Object object) {
        this.thisObject = object;
    }

    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public LinkedNode getPrev() {
        return prev;
    }

    public void setPrev(LinkedNode prev) {
        this.prev = prev;
    }

    public Object getThisObject() {
        return thisObject;
    }
}
