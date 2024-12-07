package tp_ed_2024.Simuladores;

import tp_ed_2024.Modelos.Edificio.Edificio;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Hero;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.PersonagemImp;

import java.util.Scanner;

public class SimuladorImp {

    private Edificio edificio;
    private Hero hero;
    private boolean emJogo;

    public SimuladorImp(Edificio edificio, Hero hero) {
        this.edificio = edificio;
        this.hero = hero;
        this.emJogo = true;
    }

    public void iniciarSimulacao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Simulador de Missão!");
        exibirEstadoAtual();

        while (emJogo) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Mover para uma nova divisão");
            System.out.println("2. Usar item da mochila");
            System.out.println("3. Sair da simulação");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

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
                    System.out.println("Escolha inválida. Tente novamente.");
            }

            verificarFimDeJogo();
        }

        System.out.println("Simulação encerrada.");
    }

    private void moverHero(Scanner scanner) {
        System.out.println("Digite o nome da divisão para onde deseja mover-se:");
        String nomeDivisao = scanner.nextLine();

        Divisao novaDivisao = edificio.obterDivisaoPorNome(nomeDivisao);
        if (novaDivisao == null) {
            System.out.println("Divisão inválida. Tente novamente.");
            return;
        }

        if (edificio.verificarLigacao(hero.getDivisaoAtual(), novaDivisao)) {
            hero.moverParaDivisao(novaDivisao, edificio);
            resolverEventosNaDivisao();
        } else {
            System.out.println("Movimento inválido! Não há ligação entre as divisões.");
        }

        exibirEstadoAtual();
    }

    private void usarItem() {
        hero.aumentarVida(null, 0); // Ajuste o método para usar o próximo item da mochila
        exibirEstadoAtual();
    }

    private void resolverEventosNaDivisao() {
        Divisao divisaoAtual = hero.getDivisaoAtual();

        if (!divisaoAtual.getInimigos().isEmpty()) {
            System.out.println("Inimigos encontrados! O combate começou.");
            resolverCombate();
        }


        if (divisaoAtual.getAlvo() != null) {
            System.out.println("Você encontrou o alvo na divisão!");
        }
    }

    private void resolverCombate() {
        Divisao divisaoAtual = hero.getDivisaoAtual();
        for (var inimigo : divisaoAtual.getInimigos()) {
            hero.atacar((PersonagemImp) inimigo);
            if (inimigo.getVida() <= 0) {
                divisaoAtual.removerInimigo(inimigo);
                System.out.println("Inimigo derrotado!");
            } else {
                hero.setVida(hero.getVida() - inimigo.getPoder());
                System.out.println("O inimigo contra-atacou! Vida do herói: " + hero.getVida());
            }
        }
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
            emJogo = false;
        } else if (hero.getAlvo() != null && hero.getDivisaoAtual().isEntradaSaida()) {
            System.out.println("Você completou a missão com sucesso!");
            emJogo = false;
        }
    }

    private void finalizarSimulacao() {
        emJogo = false;
    }
}
