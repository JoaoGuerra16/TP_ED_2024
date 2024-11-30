package tp_ed_2024.Collections.Ficha5;

public class nodeDouble<T> {

    private nodeDouble<T> next, previous;
    private T element;

    public nodeDouble(T element) {
        this.next = null;
        this.previous = null;
        this.element = element;

    }

    public nodeDouble<T> getNext() {
        return next;
    }

    public void setNext(nodeDouble<T> next) {
        this.next = next;
    }

    public nodeDouble<T> getPrevious() {
        return previous;
    }

    public void setPrevious(nodeDouble<T> previous) {
        this.previous = previous;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

}