package tp_ed_2024.Simuladores;

import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.Personagem;

import java.util.Scanner;

public class SimuladorImp {

    private EdificioImp<Divisao> edificio;
    private HeroImp hero;
    private boolean emJogo;

    public SimuladorImp(EdificioImp<Divisao> edificio, HeroImp hero) {
        this.edificio = edificio;
        this.hero = hero;
        this.emJogo = true;
    }

    public void iniciarSimulacao() {
        Scanner scanner = new Scanner(System.in);
        resolverEventosNaDivisao();
        System.out
                .println(
                        "\nBem-vindo Tó, a missão já começou!\n Até te desejava 'Boa sorte' mas sei que não precisas.");
        exibirEstadoAtual();

        while (emJogo) {

            System.out.println(edificio);

            System.out.println("\nEscolha uma ação:");
            System.out.println("1. Mover para uma nova divisão");
            System.out.println("2. Usar item da mochila");
            System.out.println("3. Sair da simulação");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    moverHero(scanner);
                    break;
                case 2:
                    usarItem();
                    break;
                case 3:
                    finalizarSimulacao();
                    break;
                default:
                    System.out.println("\nEscolha inválida. Tente novamente.");
            }

            verificarFimDeJogo();
        }

        System.out.println("\nSimulação encerrada.");
    }

    private void moverHero(Scanner scanner) {
        System.out.println("\nDiz o nome da divisão que queres entrar:");
        String nomeDivisao = scanner.nextLine();

        Divisao novaDivisao = edificio.obterDivisaoPorNome(nomeDivisao);
        if (novaDivisao == null) {
            System.out.println("\nDivisão inválida. Tente novamente.");
            return;
        }

        if (edificio.verificarLigacao(hero.getDivisaoAtual(), novaDivisao)) {
            hero.moverParaDivisao(novaDivisao, edificio);
            moverTodosInimigos();
            resolverEventosNaDivisao();

        } else {
            System.out.println("\nMovimento inválido! Não há ligação entre as divisões.");
        }

        exibirEstadoAtual();
    }

    private void moverTodosInimigos() {
        System.out.println("\nOs inimigos estão se movendo...");
        for (int i = 0; i < edificio.getNetwork().size(); i++) {
            Divisao divisao = edificio.getNetwork().getVertex(i);
            for (InimigoImp inimigo : divisao.getInimigos()) {
                inimigo.moverInimigoAleatorio(edificio, inimigo);
            }
        }
    }

    private void usarItem() {
        hero.aumentarVida(null, 0);
        moverTodosInimigos();
        exibirEstadoAtual();
    }

    private void resolverEventosNaDivisao() {
        Divisao divisaoAtual = hero.getDivisaoAtual();

        if (!divisaoAtual.getInimigos().isEmpty()) {
            System.out.println("\nInimigos encontrados! O combate começou.");
            resolverCombate();
        }

        if (divisaoAtual.getAlvo() != null) {
            System.out.println("\nVocê encontrou o alvo na divisão!");
        }
    }

    private void resolverCombate() {
        Divisao divisaoAtual = hero.getDivisaoAtual();
        for (var inimigo : divisaoAtual.getInimigos()) {
            hero.atacar((Personagem) inimigo);
            if (inimigo.getVida() <= 0) {
                divisaoAtual.removerInimigo(inimigo);
                System.out.println("Inimigo derrotado!");
            } else {
                hero.setVida(hero.getVida() - inimigo.getPoder());
                System.out.println("\nO inimigo contra-atacou! Vida do herói: " + hero.getVida());
            }
        }
        moverTodosInimigos();

    }

    private void exibirEstadoAtual() {
        System.out.println("Estado Atual:");
        System.out.println("Divisão Atual: " + hero.getDivisaoAtual().getNome());
        System.out.println("Vida do Herói: " + hero.getVida());
        System.out.println("Inimigos na divisão: " + hero.getDivisaoAtual().getInimigos().size());
        System.out.println("Itens na divisão: " + hero.getDivisaoAtual().getItens().size());
    }

    private void verificarFimDeJogo() {
        if (hero.getVida() <= 0) {
            System.out.println("O herói morreu! Missão fracassada.");
            finalizarSimulacao();
        } else if (hero.getAlvo() != null && hero.getDivisaoAtual().isEntradaSaida()) {
            System.out.println("Você completou a missão com sucesso!");
            finalizarSimulacao();
        }
    }

    private void finalizarSimulacao() {
        emJogo = false;
    }
}
