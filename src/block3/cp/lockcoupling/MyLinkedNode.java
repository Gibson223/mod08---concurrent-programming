package block3.cp.lockcoupling;

public class MyLinkedNode {

    private Object thisObject;
    private MyLinkedNode next;

    public MyLinkedNode(Object object) {
        this.thisObject = object;
    }

    public MyLinkedNode getNext() {
        return next;
    }

    public void setNext(MyLinkedNode next) {
        this.next = next;
    }

    public Object getThisObject() {
        return thisObject;
    }
}
