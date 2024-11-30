package tp_ed_2024.Collections.Ficha2;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

/**
 * ListaDuplamenteLigada
 */
public class ListaDuplamenteLigada<T> {

    private nodeDouble<T> tail, head;
    private int count;

    public ListaDuplamenteLigada() {
        this.tail = null;
        this.head = null;
        this.count = 0;
    }
    

    public void adicionarInicio(T element) {

        nodeDouble<T> newNode = new nodeDouble<>(element);

        if (head == null) {
            head = tail = newNode;
            count++;
        } else {

            newNode.setNext(head); 
            head.setPrevious(newNode); 
            head = newNode; 
            head.setPrevious(null); 
            count++;

        }
    }

    public void adicionarFim(T element) {

        nodeDouble<T> newNode = new nodeDouble<>(element);

        if (head == null) {
            head = tail = newNode;
            count++;
        } else {

            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(null);
            count++;
        }
    }

    /*
     * public void adicionarMeio(T elemement) {
     * 
     * nodeDouble<T> newNode = new nodeDouble<T>(elemement);
     * 
     * if (head == null) {
     * head = tail = newNode;
     * count++;
     * } else {
     * 
     * }
     * 
     * }
     */
    public void removerInicio() {

        if (head == null) {
            System.out.println("A lista está vazia.");
            return; // Retorna sem fazer nada
        }

        nodeDouble<T> FirstNode = head.getNext();
        if (head == tail) { //se a lista tiver apenas um nó
            head = null;
            tail = null;
            System.out.println("removido");
        } else {

            FirstNode.setPrevious(null);
            head = FirstNode;
        }
        count--;

    }

    public void removerFim() {

        if (head == null) {
            System.out.println("A lista está vazia.");
            return; // Retorna sem fazer nada
        }

        nodeDouble<T> LastNode = tail.getPrevious();

        if (head == tail) { //se a lista tiver apenas um nó
            head = null;
            tail = null;
            System.out.println("Removido");

        } else {

            LastNode.setNext(null);
            tail = LastNode;

        }
        count--;

    }

    public void verificarVazia() {

        if (head == null) {
            System.out.println("Está vazia");
        } else {
            System.out.println("Não está vazia");
        }

    }

    public void imprimirTras() {

        nodeDouble<T> current = tail;

        while (current != null) {
            System.out.println(current.getElement());
            current = current.getPrevious();
        }

    }

    public Object[] toArray() {

        List<T> elements = new ArrayList<>(); //Este arrayList é da biblioteca

        nodeDouble<T> current = head;

        while (current != null) {
            elements.add(current.getElement());
            current = current.getNext();
        }

        return elements.toArray(); //transforma em array
    }

    public T[] toArrayUntil(T[] array, int pos) {

        if (pos < 0 || pos >= count) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        }

        nodeDouble<T> current = head;
        int tamanho = 0;

        while (tamanho <= pos && current != null) {
            array[tamanho] = current.getElement();
            current = current.getNext();
            tamanho++;
        }

        return array;
    }

    public ListaDuplamenteLigada<T> listaDePares() {

        ListaDuplamenteLigada<T> listaPar = new ListaDuplamenteLigada<>();

        nodeDouble<T> current = head;

        while (current != null) {
            if (current.getElement() instanceof Integer) {  // Verifica se o elemento é uma instacia do tipo Integer
                Integer valor = (Integer) current.getElement(); // caso seja é feito um casting 

                if (valor % 2 == 0) { 
                    listaPar.adicionarInicio(current.getElement());
                }
            }else{
                System.out.println("elemento não é inteiro");
            }
            current = current.getNext();

        }

        return listaPar;

    }


    public int removerRepetidos (T elemento){

        nodeDouble<T> current = head;
     
        int contador = 0;

        while(current != null){

            if(current.getElement().equals(elemento)){ // se o elemento do currente for igual ao elemento fornecido
                contador++;
                if(current.getPrevious() != null ){ // e se o currente nao estiver na cabeça
                    current.getPrevious().setNext(current.getNext()); // O next do anterior do current será o next do currente

                }else{
                    head = current.getNext(); // se o currente for a cabeça então a cabeça é o next do currente
                }
                if(current.getNext() != null){ // se o currente nao for a tail
                    current.getNext().setPrevious(current.getPrevious()); // o anterior do proximo do corrente será o anterior do currente
                }else{
                    tail = current.getPrevious(); // se o currente for a tail então a tail é o anteior do currente
                }
                count--;
            }
            current = current.getNext();
            
        }

    return contador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        nodeDouble<T> atual = head;

        // Percorre a lista e adiciona os valores ao StringBuilder
        while (atual != null) {
            sb.append(atual.getElement()).append(" <-> "); // Adiciona o valor e uma seta
            atual = atual.getNext(); // Avança para o próximo nó
        }

        // Remove a última seta e espaço, se a lista não estiver vazia
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 5); // Remove " <-> "
        }

        return sb.toString();
    }

}
