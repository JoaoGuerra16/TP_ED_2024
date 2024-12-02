package tp_ed_2024.Collections.Listas;

import tp_ed_2024.Collections.Interfaces.*;

public class ArrayOrderedList<T> extends AbstractArrayList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) {

        if (rear == list.length) {
            ExpandCapacity();

        }

        int i = 0;
        while (i < rear && element.equals(list[i])) {
            i++;

        }

        for (int j = rear; j > i; j--) {
            list[j] = list[j - 1];
        }

        list[i] = element;
        rear++;

    }

    
}
