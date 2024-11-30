package tp_ed_2024.Collections.Ficha7;

public class demo {
    public static void main(String[] args) {
        listaLigada<Integer> list = new listaLigada<>();
        ListaDuplamenteLigada<Integer> list2= new ListaDuplamenteLigada<>();

        // Adicionando elementos à lista
        list2.adicionarInicio(2);
        list2.adicionarInicio(2);
        list2.adicionarInicio (3);
        list2.adicionarInicio(2);
        list2.adicionarInicio(2);

        

        // Imprimindo os elementos da lista
        System.out.println(list2);
        list2.replace(2, 6);
        System.out.println(list2);
        
    }
}

