package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Personagens.Inimigo;
import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.PersonagemImp;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo JSON
        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";

        // Carregar os dados do JSON
        JsonLoader loader = new JsonLoader();
        loader.loadFromJson(jsonPath);

        // Obter o grafo e os inimigos
        Network<String> network = loader.getGrafo();
        UnorderedArrayList<Inimigo> inimigos = loader.getInimigos();


        // Exibir os inimigos e suas posições iniciais
        System.out.println("Inimigos e suas posições iniciais:");
        for (Inimigo inimigo : inimigos) {
            System.out.println(inimigo.getNome() + " está na divisão: " + inimigo.getDivisaoAtual());
        }

        // Exibir os inimigos e suas novas posições após a movimentação
        System.out.println("\nInimigos e suas novas posições:");
        for (Inimigo inimigo : inimigos) {
            System.out.println(inimigo.getNome() + " agora está na divisão: " + inimigo.getDivisaoAtual());
        }

        // Simulação de movimentação dos inimigos (movimentação aleatória)
        System.out.println("\nMovimentação dos inimigos:");
        for (Inimigo inimigo : inimigos) {
            inimigo.moverInimigoAleatorio(network);
        }

        // Exibir os inimigos e suas novas posições após a movimentação
        System.out.println("\nInimigos e suas novas posições:");
        for (Inimigo inimigo : inimigos) {
            System.out.println(inimigo.getNome() + " agora está na divisão: " + inimigo.getDivisaoAtual());
        }
        // Simulação de movimentação dos inimigos (movimentação aleatória)
        System.out.println("\nMovimentação dos inimigos:");
        for (Inimigo inimigo : inimigos) {
            inimigo.moverInimigoAleatorio(network);
        }



        // Exibir os inimigos e suas novas posições após a movimentação
        System.out.println("\nInimigos e suas novas posições:");
        for (Inimigo inimigo : inimigos) {
            System.out.println(inimigo.getNome() + " agora está na divisão: " + inimigo.getDivisaoAtual());
        }
    }
}
