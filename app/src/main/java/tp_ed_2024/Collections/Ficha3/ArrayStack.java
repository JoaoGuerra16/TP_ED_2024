package tp_ed_2024.Collections.Ficha3;

import java.util.EmptyStackException;

import tp_ed_2024.Collections.Interfaces.StackADT;


public class ArrayStack<T> implements StackADT<T> {

    private int top;
    private T[] stack;
    private int default_size = 20; 



    public ArrayStack() {
        this.top = 0;
        stack = (T[]) (new Object[default_size]);

    }   


    public ArrayStack(int capacidade){
        this.top = 0;
        stack = (T[]) (new Object[capacidade]);
    }

    public T pop() {
        if(isEmpty()){
            System.out.println("Vazia");
            throw new EmptyStackException();
        }
        top--;
        T element = stack[top];
        stack[top] = null;

        return element;
    }

    public void push(T element){
        if(size() == stack.length){
            expandCapacity();
        }

        stack[top] = element;
        top++;
    }

    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return stack[top-1];
    }

    public boolean isEmpty(){
        return top == 0;
        
    }

    public int size(){
        return top;
    }


private void expandCapacity(){
    T[] newArray = (T[]) new Object[stack.length * 2];
    System.arraycopy(stack, 0, newArray, 0, stack.length);
    stack = newArray;

}

@Override
public String toString()
{
   String result = "";

   for (int scan=0; scan < top; scan++) 
      result = result + stack[scan].toString() + "\n";

   return result;
}


    
    
}