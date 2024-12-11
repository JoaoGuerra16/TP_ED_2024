package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Simuladores.SimuladorImp;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import java.util.Random;

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

        String modoEscolhido = menu.escolherModo();
        System.out.println("Modo escolhido: " + modoEscolhido);

        String jsonPath = menu.escolherJson(jsonFiles);
        if (jsonPath == null) {
            return;
        }

        EdificioImp<Divisao> edificio = loader.carregarEdificio(jsonPath);
        if (edificio == null) {
            return;
        }

        // Verificar entradas/saídas no edifício
        UnorderedArrayList<Divisao> entradasSaidas = edificio.getEntradasSaidas();
        if (entradasSaidas == null || entradasSaidas.isEmpty()) {
            System.err.println("Erro: Nenhuma entrada ou saída foi definida no edifício.");
            return;
        }

        Divisao divisaoInicial;

        // Se o modo for manual, o jogador escolhe a entrada
        if ("Manual".equals(modoEscolhido)) {
            divisaoInicial = menu.escolherEntrada(entradasSaidas);
        } else { // Se for automático, escolhe uma entrada aleatória
            Random random = new Random();
            divisaoInicial = entradasSaidas.getIndex(random.nextInt(entradasSaidas.size()));
            System.out.println("Modo Automático: A entrada escolhida aleatoriamente é: " + divisaoInicial.getNome());
        }

        if (divisaoInicial == null) {
            System.out.println("Nenhuma entrada escolhida. A encerrar o jogo.");
            return;
        }

        // Criar o jogador (Tó Cruz) e colocá-lo na divisão inicial escolhida
        HeroImp heroi = new HeroImp(100, divisaoInicial);
        SimuladorImp simulador = new SimuladorImp(edificio, heroi);
        simulador.iniciarSimulacao();
    }
}
