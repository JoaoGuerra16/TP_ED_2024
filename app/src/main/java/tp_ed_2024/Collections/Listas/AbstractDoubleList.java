package tp_ed_2024.Collections.Listas;

import java.util.Iterator;
import java.util.NoSuchElementException;
import tp_ed_2024.Collections.Interfaces.*;

public class AbstractDoubleList<T extends Comparable<T>> implements ListADT<T> {

    protected nodeDouble<T> tail, head;
    protected int count;

    public AbstractDoubleList() {
        this.tail = null;
        this.head = null;
        this.count = 0;

    }

    @Override
    public T removeFirst() {

        nodeDouble<T> elemento = head;

        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);

        }

        return elemento.getElement();

    }

    @Override
    public T removeLast() {

        nodeDouble<T> elemento = tail;
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }

        return elemento.getElement();

    }

    @Override
    public T remove(T element) {

        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }

        nodeDouble<T> previous = null;
        nodeDouble<T> current = head;

        while (current != null) {
            if (element.equals(current.getElement())) {

                if (current == head) {
                    count--;
                    return removeFirst();
            
                } else if (current == tail) {
                    count--;
                    return removeLast();
                } else {
                    count--;
                    previous.setNext(current.getNext());
                    current.getNext().setPrevious(previous);
                }
                return current.getElement();

            }

            previous = current;
            current = current.getNext();

        }

        throw new NoSuchElementException("Não existe esse elemento");

    }

    @Override
    public T first() {
        return head.getElement();

    }

    @Override
    public T last() {
        return tail.getElement();

    }

    @Override
    public boolean contains(T target) {
        if (isEmpty()) {
            return false;
        }

        nodeDouble<T> current = head;

        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            current = current.getNext();
        }
        return false;

    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;

    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    private class DoubleLinkedListIterator implements Iterator<T> {
        private nodeDouble<T> current;

        public DoubleLinkedListIterator() {
            this.current = head; // Começa no head
        }

        @Override
        public boolean hasNext() {
            return current != null; // Retorna true se há um próximo elemento
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Não há mais elementos na lista.");
            }
            T element = current.getElement(); // Armazena o elemento atual
            current = current.getNext(); // Move para o próximo nó
            return element; // Retorna o elemento
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operação não suportada.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        nodeDouble<T> current = head;
        
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(", "); // Adiciona uma vírgula entre elementos
            }
            current = current.getNext(); // Move para o próximo nó
        }
    
        return sb.toString();
    }
    
    
    
}
