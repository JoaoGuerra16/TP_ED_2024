package tp_ed_2024.Collections.Listas;


import java.util.NoSuchElementException;
import tp_ed_2024.Collections.Interfaces.*;
//Herda os metodos de abstractArrayList e implementa a interface UnorderedListADT para fornecer as implementações corretas 
public class UnorderedArrayList<T> extends AbstractArrayList<T>  implements UnorderedListADT<T>  { 

    @Override
    public void addToFront(T element) {

        if(rear == list.length){
          ExpandCapacity();
    
     }
    //Se a lista tiver mais que elemento
     if (rear > 0) {
        for (int i = rear; i > 0; i--) {
            list[i] = list[i - 1];
        }
    }

       list[0] = element;
       rear++;
    }


    @Override
    public void addToRear(T element) {

        if(rear == list.length){
            ExpandCapacity();      
       }
      list[rear] = element;
        rear++;
    }

    @Override
    public void addAfter(T element, T target) {
       
        if(rear == list.length){
            ExpandCapacity();      
       }
    

       int index = -1;
       //Loop para encontrar o index da array
       for (int i = 0; i < rear; i++) {
           if (list[i].equals(target)) {
               index = i;  
               break;
           }
       }

       if (index == -1) {
        throw new NoSuchElementException("Target element not found.");
    }
       //Mover a lista para a direita
       for ( int i = rear; i > index; i--) {
        list[i] = list[i - 1];
    }

    list[index + 1]= element;
    rear++;

    }

    
    }

 


    
