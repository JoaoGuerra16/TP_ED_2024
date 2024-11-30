package tp_ed_2024.Collections.Ficha7;

public class node<T> {

    private node<T> next;
    private T element;



    public node(T element) {
        this.element = element;
        this.next = null;

    }


    public node<T> getNext() {
        return next;
    }


    public void setNext(node<T> next) {
        this.next = next;
    }


    public T getElement() {
        return element;
    }


    public void setElement(T element) {
        this.element = element;
    }


    

}