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

        Divisao divisaoInicial = edificio.escolherDivisoesEntradaSaida();

        HeroImp heroi = new HeroImp(divisaoInicial);
        SimuladorImp simulador = new SimuladorImp(edificio, heroi);

        simulador.iniciarSimulacao();
    }
}

