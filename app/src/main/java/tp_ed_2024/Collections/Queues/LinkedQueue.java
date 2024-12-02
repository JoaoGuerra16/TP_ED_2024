package tp_ed_2024.Collections.Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import tp_ed_2024.Collections.Interfaces.QueueADT;

public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode<T> rear; // Último nó da fila
    private LinearNode<T> front; // Primeiro nó da fila
    private int size; // Tamanho da fila

    public LinkedQueue() {
        this.rear = null;
        this.front = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        if (isEmpty()) {
            front = newNode; // Se a fila estiver vazia, o novo nó é tanto a frente quanto o rear
        } else {
            rear.setNext(newNode); // Adiciona o novo nó ao final da fila
        }
        rear = newNode; // Atualiza o rear
        size++; // Incrementa o tamanho
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }

        T oldFront = front.getElement(); // Armazena o elemento a ser removido

        // Move o front para o próximo nó
        front = front.getNext();
        
        // Se a fila ficar vazia após a remoção
        if (front == null) {
            rear = null; // Atualiza o rear
        }

        size--; // Decrementa o tamanho
        return oldFront; // Retorna o elemento removido
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return front.getElement(); // Retorna o elemento do nó frontal
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // Verifica se o tamanho é zero
    }

    @Override
    public int size() {
        return size; // Retorna o tamanho da fila
    }

    // Implementação do iterador
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private LinearNode<T> current = front; // Começa do nó frontal

            @Override
            public boolean hasNext() {
                return current != null; // Verifica se há mais elementos
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Não há mais elementos.");
                }
                T element = current.getElement(); // Obtém o elemento atual
                current = current.getNext(); // Move para o próximo nó
                return element; // Retorna o elemento
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        LinearNode<T> currentNode = front; // Começa do nó frontal

        result.append("Fila: ");
        if (isEmpty()) {
            result.append("vazia");
        } else {
            while (currentNode != null) {
                result.append(currentNode.getElement()); // Adiciona o elemento atual
                currentNode = currentNode.getNext(); // Move para o próximo nó

                // Adiciona uma seta entre os elementos, se não for o último
                if (currentNode != null) {
                    result.append(" -> ");
                }
            }
        }

        return result.toString(); // Retorna a representação em string da fila
    }
}
