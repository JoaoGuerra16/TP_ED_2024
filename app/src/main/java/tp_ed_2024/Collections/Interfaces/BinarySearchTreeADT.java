package tp_ed_2024.Collections.Interfaces;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    public void addElement(T element);

    public T removeElement(T targetElement);

    public void removeAllOcurrences(T targetElement);

    public T removeMin();

    public T removeMax();

    public T findMin();

    public T findMax();
    


    
}
