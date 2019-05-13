package block3.cp.lockcoupling;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedNode {

    public final Lock lock = new ReentrantLock();
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

    public void setThisObject(Object thisObject) {
        this.thisObject = thisObject;
    }

    public Boolean hasNext() {
        return next != null;
    }

    public Object getThisObject() {
        return thisObject;
    }
}
