package tp_ed_2024.Collections.Ficha5;

import tp_ed_2024.Collections.Interfaces.*;

public class DoubleLinkedUnorderedList<T extends Comparable<T>> extends AbstractDoubleList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        
        nodeDouble<T> newNode = new nodeDouble<T>(element);


        if(isEmpty()){
            head = newNode;
            tail = newNode;
            head.setNext(null);
            head.setPrevious(null);
           
        }else{
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
           
        }

      count++;
       
    }

    @Override
    public void addToRear(T element) {
        nodeDouble<T> newNode = new nodeDouble<T>(element);

        if(isEmpty()){
            head = newNode;
            tail = newNode;
            tail.setNext(null);
            tail.setPrevious(null);
            count++;
        }else{
            tail.setNext(newNode);
        newNode.setPrevious(tail);
        newNode.setNext(null);
        tail = newNode;
        }

      count++;

    }

    @Override
    public void addAfter(T element, T target) {

        
    
        nodeDouble<T> newnode = new nodeDouble<T>(element);
        nodeDouble<T> current = head;


            while (current != null && current.getElement().compareTo(target) != 0) {
                current = current.getNext();
            }

            if(current == null){
                throw new RuntimeException("O target nao foi encontrado");
            }

                nodeDouble<T> nextNode = current.getNext();

                current.setNext(newnode);       
                newnode.setPrevious(current);    
                newnode.setNext(nextNode);

                if(nextNode != null){
                    nextNode.setPrevious(newnode);
                  
                }else{
                    tail = newnode;
                  
                   
                }
                count++;
            }


            public T[] toArray(){
                @SuppressWarnings("unchecked")
                T[] array = (T[]) new Object[count];
                nodeDouble<T> current = head;
                int index = 0;

                while (current != null) {
                    array[index++] = current.getElement();
                    current = current.getNext();
                }
        
                return array;
            }
        

        }  

