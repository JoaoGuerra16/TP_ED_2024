package tp_ed_2024.Utilidades;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Collections.Listas.listaLigada;
import tp_ed_2024.Modelos.Items.*;
import tp_ed_2024.Modelos.Personagens.*;

public class Main {
    public static void main(String[] args) {
        // Caminho para o arquivo JSON

        // PersonagemPrincipalADT ToCruz = new Hero(100, 40, null, null);
        // PersonagemADT inimigo = new Inimigo("inimigo muito mau", 100, 40, null);

        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";

        // Cria o objeto JsonLoader para carregar o grafo
        JsonLoader loader = new JsonLoader();

        // Carregar o grafo a partir do JSON
        loader.loadFromJson(jsonPath);
        UnorderedArrayList<Inimigo> inimigos = loader.getInimigos();
        if (inimigos != null) {
            System.out.println("Inimigos carregados: " + inimigos.toString());
        } else {
            System.out.println("Nenhum inimigo foi carregado.");
        }

        listaLigada<Item> items = loader.getItems();
        if (items != null) {
            System.out.println("Items carregados: " + items.toString());
        } else {
            System.out.println("Nenhum item foi carregado.");
        }

        listaLigada<Alvo> alvos = loader.getAlvos();
        if (items != null) {
            System.out.println("Alvos carregados: " + alvos.toString());
        } else {
            System.out.println("Nenhum alvo foi carregado.");
        }

        listaLigada<String> entradasSaidas = loader.getEntradasSaidas();
        if (entradasSaidas != null) {
            System.out.println("Entras/saidas carregadas: " + entradasSaidas.toString());
        } else {
            System.out.println("Nenhuma entrada/saida foi carregado.");
        }
    }
}
