package tp_ed_2024.Collections.Interfaces;

public interface OrderedListADT<T> extends ListADT<T> {  //Eu aqui estou a dizer que OrderListADT vai herdar os metodos da ListADT
    /**
    * Adds the specified element to this list at
    * the proper location
    *
    * @param element the element to be added to this list
    */
    public void add (T element);
   }