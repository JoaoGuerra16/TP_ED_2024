package tp_ed_2024.Collections.Listas;

import java.util.NoSuchElementException;

import tp_ed_2024.Collections.Exceptions.ElementNotFoundException;
import tp_ed_2024.Collections.Interfaces.*;


public class UnorderedArrayList<T> extends AbstractArrayList<T> implements UnorderedListADT<T> {


    @Override
    public void addToFront(T element) {
        if (rear == list.length) {
            expandCapacity();
        }

        for (int i = rear; i > 0; i--) {
            list[i] = list[i - 1];
        }

        list[0] = element;
        rear++;
        modCount++;
    }


    @Override
    public void addToRear(T element) {
        if (rear == list.length) {
            expandCapacity();
        }

        list[rear] = element;
        rear++;
        modCount++;
    }


    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        int targetIndex = -1;

        for (int i = 0; i < rear; i++) {
            if (target.equals(list[i])) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            throw new ElementNotFoundException("ArrayList");
        }

        if (rear == list.length) {
            expandCapacity();
        }

        for (int i = rear; i > targetIndex + 1; i--) {
            list[i] = list[i - 1];
        }

        list[targetIndex + 1] = element;
        rear++;
        modCount++;
    }

    public T getIndex(int index) {
        if (index >= 0 && index < rear) {
            return list[index];
        } else {
            throw new IndexOutOfBoundsException("Indice fora dos limites: " + index);
        }
    }
}






