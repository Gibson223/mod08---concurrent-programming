package block3.cp.lockcoupling;

import block2.cp.queue.LinkedNode;

public class MyLinkedList implements List {

    private int size;
    private MyLinkedNode firstNode = new MyLinkedNode(null);

    /**
     * Insert an element at the specified position in the list.
     *
     * @param position The position to insert the element at.
     * @param value    The value of the element to insert.
     */
    @Override
    public void insert(int position, Object value) throws IndexOutOfBoundsException {
        firstNode.lock.lock();
        int currentIndex = 0;
        try {
            if (firstNode.getThisObject() == null) {
                firstNode.setThisObject(value);
            } else {
                MyLinkedNode currentNode = firstNode;
                MyLinkedNode nextNode;
                try {
                    while (currentNode.hasNext() && position > currentIndex) {
                        try {
                            nextNode = currentNode.getNext();
                            nextNode.lock.lock();
                            currentIndex++;
                        } finally {
                            currentNode.lock.unlock();
                        }
                        currentNode = nextNode;
                    }
                    if (currentNode.hasNext()) {
                        throw new IndexOutOfBoundsException("Index is out of bounds");
                    }
                    MyLinkedNode newNode = new MyLinkedNode(value);
                    currentNode.setNext(newNode);
                } finally {
                    currentNode.lock.unlock();
                }
            }
        } finally {
            firstNode.lock.unlock();
        }
    }

    /**
     * Add an element to the end of the list.
     *
     * @param value The value of the element to add to the list.
     */
    @Override
    public void add(Object value) {
        firstNode.lock.lock();
        try {
            if (firstNode.getThisObject() == null) {
                firstNode.setThisObject(value);
            } else {
                MyLinkedNode currentNode = firstNode;
                MyLinkedNode nextNode;
                try {
                    while (currentNode.hasNext()) {
                        try {
                            nextNode = currentNode.getNext();
                            nextNode.lock.lock();
                        } finally {
                            currentNode.lock.unlock();
                        }
                        currentNode = nextNode;
                    }
                    MyLinkedNode newNode = new MyLinkedNode(value);
                    currentNode.setNext(newNode);
                } finally {
                    currentNode.lock.unlock();
                }
            }
        } finally {
            firstNode.lock.unlock();
        }
    }

    /**
     * Remove the specified element from the list.
     *
     * @param item The element to remove from the list.
     */
    @Override
    public void remove(Object item) {

    }

    /**
     * Delete the element at the specified position.
     *
     * @param position The position of the element that should be deleted.
     */
    @Override
    public void delete(int position) {
        int currentIndex = 0;
        firstNode.lock.lock();
        try {
            if (firstNode.getThisObject() != null) {
                MyLinkedNode prevNode = firstNode;
                MyLinkedNode currentNode;
                try {
                    if (!prevNode.hasNext()) {
                        prevNode.setThisObject(null);
                    } else {
                        currentNode = prevNode.getNext();
                        currentNode.lock.lock();
                        try {
                            while (currentNode.hasNext() && ) {
                                prevNode.lock.unlock();
                                prevNode = currentNode;
                                currentNode = prevNode.getNext();
                                currentNode.lock.lock();
                            }
                            prevNode.setNext(null);
                        } finally {
                            currentNode.lock.unlock();
                            prevNode.lock.unlock();
                        }
                    }
                } finally {
                    prevNode.lock.unlock();
                }
            }
        } finally {
            firstNode.lock.unlock();
        }
    }

    /**
     * Get the amount of elements currently in this list.
     *
     * @return The size of the list.
     */
    @Override
    public synchronized int size() {
        return size;
    }
}
