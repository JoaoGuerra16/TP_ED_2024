package tp_ed_2024.Collections.Stacks;

import java.util.EmptyStackException;

import tp_ed_2024.Collections.Interfaces.StackADT;



public class LinkedStack<T> implements StackADT<T>{
    private LinearNode<T> top;  // Referência para o nó no topo da pilha
    private int size; 

    

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        newNode.setNext(top);
        top = newNode;
        size++;



    }

    @Override
    public T pop() {

        if(isEmpty()){
            throw new EmptyStackException();
        }
        T element = top.getElement();
        top = top.getNext();
        size--;

        return element;
       
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getElement();
       
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
    public String toString()
    {
      String result = "";
      LinearNode current = top;
  
      while (current != null)
      {
        result = result + (current.getElement()).toString() + "\n";
        current = current.getNext();
      }
  
      return result;
    }

    
}
