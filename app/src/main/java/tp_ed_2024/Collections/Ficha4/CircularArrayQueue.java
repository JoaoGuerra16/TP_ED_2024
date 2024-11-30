package tp_ed_2024.Collections.Ficha4;
import java.util.NoSuchElementException;

import tp_ed_2024.Collections.Interfaces.QueueADT;
public class CircularArrayQueue<T> implements QueueADT<T> {

    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacidade;

    public CircularArrayQueue(int capacidade) {
        front = 0;
        rear = 0;
        size = 0;
        this.queue = (T[]) new Object[capacidade];
        this.capacidade = capacidade;
    }

    @Override
    public void enqueue(T element) {
        if (size == capacidade) {
            throw new IllegalStateException("A fila está cheia.");
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        size++;

        if (size == 1) {
            front = rear;
        }

    }

    @Override
    public T dequeue() {
        

        if(isEmpty()){
            throw new NoSuchElementException("A fila está vazia.");
        }
        T oldFront = queue[front];


        queue[front] = null;
        front = (front +1) % queue.length;
        size--;

        return oldFront;

    }

    @Override
    public T first() {
        return queue[front];

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {

        return size;

    }

    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Fila: ");
        if (isEmpty()) {
            result.append("vazia");
        } else {
            for (int i = 0; i < size; i++) {
                result.append(queue[(front + i) % capacidade]);
                if (i < size - 1) {
                    result.append(" -> ");
                }
            }
        }
        return result.toString();
    }

}
