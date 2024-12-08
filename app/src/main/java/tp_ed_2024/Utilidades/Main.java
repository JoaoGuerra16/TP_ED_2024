package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Simuladores.SimuladorImp;


public class Main {
    public static void main(String[] args) {
        // Carregar o edifício e os dados do JSON
        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";
        JsonLoader loader = new JsonLoader(jsonPath);
        EdificioImp edificio = loader.carregarEdificio();

        // Criar o jogador (Tó Cruz) e colocá-lo na divisão inicial
        Divisao divisaoInicial = edificio.obterDivisaoPorNome("Heliporto"); // Altere para a divisão inicial correta
        if (divisaoInicial == null) {
            System.err.println("Erro: Divisão inicial não encontrada!");
            return;
        }

        HeroImp heroi = new HeroImp(100, divisaoInicial);
        SimuladorImp simulador = new SimuladorImp(edificio, heroi);
        simulador.iniciarSimulacao();
    }
}
