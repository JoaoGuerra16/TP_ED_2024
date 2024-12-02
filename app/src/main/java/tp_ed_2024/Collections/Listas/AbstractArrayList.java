package tp_ed_2024.Collections.Listas;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tp_ed_2024.Collections.Interfaces.*;

public abstract class AbstractArrayList<T> implements ListADT<T>, Iterable<T>{

    protected int rear;
    protected T[] list;
    protected final static int default_size = 100;


    public AbstractArrayList() {
        this.list = (T[]) (new Object[default_size]);
        this.rear = 0;
    }

    public AbstractArrayList(int initialCapacity) {
        this.list = (T[]) (new Object[initialCapacity]);
        this.rear = 0;
    }


    public T removeFirst() {

        if (isEmpty()) {
            throw new RuntimeException("A lista está vazia.");
        }

        T resultado = list[0];

        for (int i = 0; i < rear -1; i++) {
            list[i] = list[i + 1];

        }

        list[rear-1] = null;
        rear--;
        return resultado;

    }

    @Override
    public T removeLast() {

        if (isEmpty()) {
            throw new RuntimeException("A lista está vazia.");
        }

        T resultado = list[rear - 1];

        list[rear - 1] = null;
        rear--;

        return resultado;
    }

    @Override
    public T remove(T element) {

        if (isEmpty()) {
            throw new RuntimeException("A lista está vazia.");
        };

        int index = 0;
        while (index < rear && element.equals(list[index])) {
            index++;
        }

        if(index == rear){
            throw new RuntimeException("Elemento não encontrado na lista");
        }

        T result = list[index];

        for (int i = index; i < rear - 1; i++) {
            list[i] = list[i + 1];
        }
        
        list[rear-1] = null;
        rear--;

        return result;

    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new RuntimeException("A lista está vazia.");
        }
        return list[0];

    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new RuntimeException("A lista está vazia.");
        }
        return list[rear - 1];

    }

    @Override
    public boolean contains(T target) {

        for (int i = 0; i < rear; i++) {
            if (target.equals(list[i])) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return rear == 0;

    }

    @Override
    public int size() {
        return rear - 1;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rear; i++) {
            sb.append(list[i].toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayOrderedListInterator();

    }

    private class ArrayOrderedListInterator implements Iterator<T> {

        private int current = 0;

        public boolean hasNext() {
            return current < rear;
        }

        public T next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException(); // Este exemplo não suporta a remoção via iterador
        }

    }

    public void ExpandCapacity(){

        T[] NovaLista = (T[]) new Comparable[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            NovaLista[i] = list[i];
            
        }
        list = NovaLista;
    }
    
}
