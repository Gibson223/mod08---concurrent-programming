package block3.cp.lockcoupling;

public class MyLinkedList implements List {

    private int size;
    private MyLinkedNode firstNode;

    /**
     * Insert an element at the specified position in the list.
     *
     * @param position The position to insert the element at.
     * @param value    The value of the element to insert.
     */
    @Override
    public void insert(int position, Object value) {
    }

    /**
     * Add an element to the end of the list.
     *
     * @param value The value of the element to add to the list.
     */
    @Override
    public void add(Object value) {

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
