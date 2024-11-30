package tp_ed_2024.Collections.Ficha2;
public class Teste {


    public static void main(String[] args) {
        
        ListaLigada<Integer> lista = new ListaLigada<>();
        ListaLigadaSentinela<Integer> lista2 = new ListaLigadaSentinela<>();

        ListaDuplamenteLigada<Integer> lista3 = new ListaDuplamenteLigada<>();
      
     


       lista.add(2);
       lista.add(2);
       lista.add(2);
       lista.add(2);
       lista.add(3);
       System.out.println(lista);


      
      //System.out.println(lista3);

      int removidos = lista3.removerRepetidos(2);
      System.out.println("Numeros de elementos removidos: "+ removidos);
        System.out.println(lista3);



        

 /* 
      ListaDuplamenteLigada<Integer> listaPares = lista3.listaDePares();

      System.out.println(listaPares);
    
     
        Integer[] array2 = new Integer[1];
        array2 = lista3.toArrayUntil(array2, 0);
      Object[] array = lista3.toArray();


      System.out.print("Array: \n");
      for(Integer element : array2){
        System.out.println(element + " ");
      }
*/

/* 

        lista2.adicionar(1);
        lista2.adicionar(3);
        lista2.adicionar(4);

        System.out.println(lista2);

        lista2.remove(5);
        System.out.println(lista2);
        

        lista.add("Jose");
        lista.add("Joao");
        lista.add("Miguel");
        lista.add("James");

        lista.remove("Miguel");

        System.out.println(lista);
*/



    }
    
}
