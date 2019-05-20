package block2.cp.queue;

public class LinkedList {

    private LinkedNode firstNode;
    private LinkedNode lastNode;
    private int size = 0;

    public void addFirst(Object object) {
        if (firstNode == null) {
            firstNode = new LinkedNode(object);
            lastNode = firstNode;
        } else {
            LinkedNode newNode = new LinkedNode(object);
            firstNode.setPrev(newNode);
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        size++;
    }

    public Object removeLast() {
        if (this.getSize() == 0) {
            return null;
        }
        size--;
        LinkedNode OldLast = lastNode;
        lastNode = lastNode.getPrev();
        if (firstNode == OldLast) {
            firstNode = null;
        }
        return OldLast.getThisObject();
    }

    public int getSize() {
        return size;
    }

}
