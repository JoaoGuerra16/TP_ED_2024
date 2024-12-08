package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Simuladores.SimuladorImp;

public class Main {
    public static void main(String[] args) {
        // Carregar o edifício e os dados do JSON
        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";
        JsonLoader loader = new JsonLoader(jsonPath);
        EdificioImp<Divisao> edificio = loader.carregarEdificio();

        //Divisao divisaoInicial = edificio.escolherDivisoesEntradaSaida();

       // HeroImp heroi = new HeroImp(divisaoInicial);
        // SimuladorImp simulador = new SimuladorImp(edificio, heroi);

        // simulador.iniciarSimulacao();
        Divisao testDivisao = new Divisao("Divisão 1");
        UnorderedArrayList<Divisao> vizinhos = edificio.getVizinhos(testDivisao);
        System.out.println("Vizinhos de " + testDivisao.getNome() + ": " + vizinhos);
        int index = edificio.getIndex(new Divisao("Divisão 1"));
        System.out.println("Índice de 'Divisão 1': " + index);

    }
}
