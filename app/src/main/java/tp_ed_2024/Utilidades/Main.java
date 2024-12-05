package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.Edificio;
import tp_ed_2024.Modelos.Personagens.Inimigo;

public class Main {
    public static void main(String[] args) {
        // Caminho para o arquivo JSON
        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";

        // Carregar o edifício e os dados do JSON
        JsonLoader loader = new JsonLoader();
        loader.loadFromJson(jsonPath);

        // Obter o edifício carregado
        Edificio edificio = loader.getEdificio();

        // Exibir o estado inicial do grafo com os inimigos
        System.out.println("Estado inicial do grafo:");
        edificio.exibirDivisoes();

        // Exibir os inimigos presentes nas divisões
        System.out.println("\nInimigos presentes nas divisões:");
        for (int i = 0; i < edificio.getNetwork().size(); i++) {
            for (Inimigo inimigo : edificio.getNetwork().getVertex(i).getInimigos()) {
                System.out.println(inimigo);
            }
        }

        System.out.println("\nLigações:");
        edificio.exibirLigacoes();


    }
}
