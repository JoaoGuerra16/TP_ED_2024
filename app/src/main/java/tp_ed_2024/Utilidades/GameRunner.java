package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Simuladores.SimuladorAutomaticoImp;
import tp_ed_2024.Simuladores.SimuladorManualImp;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;

public class GameRunner {
    private Menu menu;
    private Loader loader;

    public GameRunner() {
        this.menu = new Menu();
        this.loader = new Loader();
    }

    public void iniciar() {
        // Caminho para os arquivos JSON
        String[] jsonFiles = {
                "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\Missoes\\json_missao1.json",
                "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\Missoes\\json_missao2.json",
                "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\Missoes\\json_missao3.json"
        };

        String jsonPath = menu.escolherJson(jsonFiles);
        if (jsonPath == null) {
            return;
        }

        // Carregar o edifício a partir do JSON
        EdificioImp<Divisao> edificio = loader.carregarEdificio(jsonPath);
        if (edificio == null) {
            return;
        }

        UnorderedArrayList<Divisao> entradasSaidas = edificio.getEntradasSaidas();
        if (entradasSaidas == null || entradasSaidas.isEmpty()) {
            System.err.println("Erro: Nenhuma entrada ou saída foi definida no edifício.");
            return;
        }
        Divisao divisaoInicial = menu.escolherEntrada(entradasSaidas);
        if (divisaoInicial == null) {
            System.out.println("Nenhuma entrada escolhida. Encerrando o programa.");
            return;
        }
        HeroImp heroi = new HeroImp(115);
        divisaoInicial.adicionarHeroi(heroi);

        String modo = menu.escolherModo();
        switch (modo) {
            case "Manual":
                SimuladorManualImp simulador = new SimuladorManualImp(edificio, heroi);
                simulador.iniciarSimulacao();
                break;
            case "Automático":
                SimuladorAutomaticoImp simuladorAutomaticoImp = new SimuladorAutomaticoImp(edificio, heroi );
                simuladorAutomaticoImp.iniciarSimulacao();
                break;
            default:
                System.out.println("Modo inválido. Encerrando o programa.");
        }


    }
}
