package tp_ed_2024.Collections.Listas;

public class listaLigada<T> {

    private node<T> head, tail;
    private int cont;

    public listaLigada() {
        head = tail = null;
        cont = 0;
    }

    public void add(T element) {

        node<T> newnode = new node<T>(element);

        if (head == null) {
            tail = newnode;

        } else {
            newnode.setNext(head);
            ;
        }

        head = newnode;

        cont++;

    }

    public T remove(T element) {

        if (head == null) {
            System.out.println("Está vazia");
        }

        boolean encontrei = false;
        node<T> previous = null;
        node<T> current = head;

        while (current != null && !encontrei) {

            if (element.equals(current.getElement())) {

                encontrei = true;

            } else {
                previous = current;
                current = current.getNext();
            }

        }

        if (!encontrei) {
            System.out.println("Não existe");
        }

        if (this.cont == 1) {

            tail = head = null;

        } else if (current.equals(head)) {
            head = current.getNext();
        } else if (current.equals(tail)) {
            tail = previous;
            previous.setNext(null);
        } else {
            previous.setNext(current.getNext());

        }

        cont--;
        return current.getElement();

    }

    @Override
    public String toString() {
        if (head == null) {
            return "Lista está vazia";
        }

        StringBuilder resultado = new StringBuilder();
        node<T> atual = head;

        while (atual != null) {
            resultado.append(atual.getElement());
            if (atual.getNext() != null) {
                resultado.append(" -> ");
            }
            atual = atual.getNext();
        }

        return resultado.toString();
    }


    public void printList() {
        printListRecursive(head);
    }

    private void printListRecursive(node<T> head) {
        if (head != null) {
            System.out.println(head.getElement()); 
            printListRecursive(head.getNext()); 
        }
    }
}