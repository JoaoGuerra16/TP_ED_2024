package tp_ed_2024.Utilidades;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Inimigo;

public class Main {
    public static void main(String[] args) {
        // Caminho para o arquivo JSON
        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";

        // Carregar o grafo e os inimigos do JSON
        JsonLoader loader = new JsonLoader();
        loader.loadFromJson(jsonPath);

        // Obter o grafo carregado
        Network<Divisao> network = loader.getGrafo();

        // Exibir o estado inicial do grafo com os inimigos
        System.out.println("Estado inicial do grafo:");
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            System.out.println(divisao);
        }

        // Exibir os inimigos do grafo
        System.out.println("\nInimigos presentes nas divisões:");
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);

            // Verifica se a divisão tem inimigos antes de imprimir
            if (divisao.getInimigos().size() > 0) { // Somente imprime se a lista de inimigos não estiver vazia
                for (Inimigo inimigo : divisao.getInimigos()) {
                    if (inimigo != null) {  // Verifica se o inimigo não é null
                        System.out.println(inimigo);  // Chama o toString do Inimigo
                    }
                }
            } else {
                System.out.println(divisao.getNome() + " (Inimigos: 0)");  // Caso não tenha inimigos
            }
        }
    }
}
