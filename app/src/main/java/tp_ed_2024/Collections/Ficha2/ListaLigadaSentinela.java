package tp_ed_2024.Collections.Ficha2;

public class ListaLigadaSentinela<T> {

    private node<T> sentinela;
    private int count;

    public ListaLigadaSentinela() {
        sentinela = new node<>(null);
        this.count = 0;
    }

    public void adicionar(T element) {
        node<T> newnNode = new node<>(element);

        newnNode.setNext(sentinela.getNext());

        sentinela.setNext(newnNode);

        count++;

    }

    public void remove(T element) {
        node<T> previous = sentinela;
        node<T> current = sentinela.getNext();

        while (current != null) {
            if (current.getElement().equals(element)) {

                previous.setNext(current.getNext());
                count--;

                return;

            }

            previous = current;
            current = current.getNext();

        }

        System.out.println("Elemento: " + element + "não encontrado");

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        node<T> current = sentinela.getNext(); // Ignora o sentinela

        while (current != null) {
            result.append(current.getElement());
            if (current.getNext() != null) {
                result.append(" -> ");
            }
            current = current.getNext();
        }
        return result.toString();
    }

}
