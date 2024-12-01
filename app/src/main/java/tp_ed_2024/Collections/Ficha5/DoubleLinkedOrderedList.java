package tp_ed_2024.Collections.Ficha5;

import tp_ed_2024.Collections.Interfaces.*;

public class DoubleLinkedOrderedList<T extends Comparable<T>> extends AbstractDoubleList<T>
        implements OrderedListADT<T> {

    @Override
    public void add(T element) {

        nodeDouble<T> newnode = new nodeDouble<T>(element);

        if (isEmpty()) {
            head = tail = newnode;
            count++;
        } else {
            nodeDouble<T> current = head;
            while (current != null && current.getElement().compareTo(element) < 0) {
                current = current.getNext();
            }

            if (current == head) {
                newnode.setNext(head);
                head.setPrevious(newnode);
                head = newnode;
            } else if (current == null) {
                tail.setNext(newnode);
                newnode.setPrevious(tail);
                tail = newnode;

            } else {
                nodeDouble<T> previous = current.getPrevious(); // O nó anterior ao atual

                previous.setNext(newnode);
                newnode.setPrevious(previous);
                newnode.setNext(current);
                current.setPrevious(newnode);
            }
        }
    }

        public void addNao(T element) {
            nodeDouble<T> newnode = new nodeDouble<>(element);
    
            if (isEmpty()) {
                head = tail = newnode;
            } else {
                tail.setNext(newnode);
                newnode.setPrevious(tail);
                tail = newnode;
            }
            count++;
        }
    
        public DoubleLinkedOrderedList<T> inverter() {
            DoubleLinkedOrderedList<T> listaInvertida = new DoubleLinkedOrderedList<>();
    
            nodeDouble<T> current = tail;
    
            while (current != null) {
                listaInvertida.addNao(current.getElement()); // Usa o método addUnordered
                current = current.getPrevious();
            }
    
            return listaInvertida;
        }

    }


