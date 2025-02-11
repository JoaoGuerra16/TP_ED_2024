package tp_ed_2024.Collections.Heaps;

public class PriorityQueue<T> extends ArrayHeap<PriorityQueueNode<T>> {

    public PriorityQueue() {
        super();
    }

    /**
     * Adds the given element to this PriorityQueue.
     *
     * @param object   the element to be added to the priority queue
     * @param priority the integer priority of the element to be added
     */
    public void addElement(T object, int priority) {
        PriorityQueueNode<T> node = new PriorityQueueNode<T>(object, priority);
        super.addElement(node);
    }

    /**
     * Removes the next highest priority element from this priority
     * queue and returns a reference to it.
     *
     * @return a reference to the next highest priority element
     *         in this queue
     */
    public T removeNext() {
        PriorityQueueNode<T> temp = super.removeMin();
        return temp.getElement();
    }
}
