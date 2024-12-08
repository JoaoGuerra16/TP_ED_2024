package tp_ed_2024.Simuladores;

import tp_ed_2024.Modelos.Edificio.Edificio;
import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Hero;
import tp_ed_2024.Modelos.Personagens.Inimigo;

import java.util.Random;
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
        System.out.println(edificio);
        System.out.println("Bem-vindo ao Simulador de Missão!");
        exibirEstadoAtual();

        while (emJogo) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Mover para uma nova divisão");
            System.out.println("2. Usar item da mochila");
            System.out.println("3. Sair da simulação");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    moverHero(scanner);
                    for (Divisao divisao : edificio.obterDivisoes()) {
                        for (Inimigo inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos(); // Reset os movimentos permitidos para a nova ronda
                        }
                    }
                    System.out.println(edificio);
                    break;
                case 2:
                    usarItem();
                    for (Divisao divisao : edificio.obterDivisoes()) {
                        for (Inimigo inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos(); // Reset os movimentos permitidos para a nova ronda
                        }
                    }
                    System.out.println(edificio);
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
        Divisao novaDivisao = null;
        while (novaDivisao == null) {
            System.out.println("Digite o nome da divisão para onde deseja mover-se:");
            String nomeDivisao = scanner.nextLine();
            novaDivisao = edificio.obterDivisaoPorNome(nomeDivisao);
            if (novaDivisao == null) {
                System.out.println("Divisão inválida. Tente novamente.");
            } else if (!edificio.verificarLigacao(hero.getDivisaoAtual(), novaDivisao)) {
                System.out.println("Movimento inválido! Não há ligação entre as divisões.");
                novaDivisao = null;
            }
        }

        hero.moverParaDivisao(novaDivisao, edificio);
        resolverEventosNaDivisao();
        exibirEstadoAtual();
    }

    private void moverInimigosForaDaSala(Divisao salaAtual) {
        System.out.println("Inimigos em outras divisões estão se movendo.");

        Network<Divisao> mapa = edificio.getNetwork();
        UnorderedArrayList<Divisao> todasDivisoes = edificio.obterDivisoes();

        for (Divisao divisao : todasDivisoes) {
            if (divisao != salaAtual && !divisao.getInimigos().isEmpty()) {
                UnorderedArrayList<Inimigo> inimigosNaDivisao = divisao.getInimigos();
                for (int i = 0; i < inimigosNaDivisao.size(); i++) {
                    Inimigo inimigo = inimigosNaDivisao.getIndex(i);

                    if (inimigo.getMovimentosRestantes() > 0) {
                        moverInimigosAleatoriamente(mapa, inimigo, divisao);
                        inimigo.decrementarMovimentosRestantes();
                    }
                }
            }
        }
    }

    private void moverInimigosAleatoriamente(Network<Divisao> mapa, Inimigo inimigo, Divisao divisaoAtual) {
        UnorderedArrayList<Divisao> vizinhos = mapa.getVizinhos(divisaoAtual);

        if (vizinhos.size() > 0) {
            Random random = new Random();
            Divisao novoDestino = vizinhos.getIndex(random.nextInt(vizinhos.size()));

            divisaoAtual.removerInimigo(inimigo);
            novoDestino.adicionarInimigo(inimigo);
            divisaoAtual = novoDestino;

            System.out.println(inimigo.getNome() + " moveu-se para a divisão " + divisaoAtual.getNome());

            // Verifica se o inimigo ainda pode se mover uma segunda vez
            if (inimigo.getMovimentosRestantes() > 1 && random.nextBoolean()) {
                UnorderedArrayList<Divisao> vizinhosDoNovoDestino = mapa.getVizinhos(inimigo.getDivisaoAtual());
                if (!vizinhosDoNovoDestino.isEmpty()) {
                    Divisao destinoFinal = vizinhosDoNovoDestino.getIndex(random.nextInt(vizinhosDoNovoDestino.size()));

                    divisaoAtual.removerInimigo(inimigo);
                    destinoFinal.adicionarInimigo(inimigo);
                    divisaoAtual = destinoFinal;

                    System.out.println(
                            inimigo.getNome() + " moveu-se novamente para a divisão " + divisaoAtual.getNome());
                    inimigo.decrementarMovimentosRestantes(); // Decrementa o contador de movimentações
                }
            }
        } else {
            System.out.println(inimigo.getNome() + " não tem divisões vizinhas para se mover.");
        }
    }

    private void usarItem() {
        hero.aumentarVida(null, 0);
        moverInimigosForaDaSala(hero.getDivisaoAtual());
        resolverCombate();
        exibirEstadoAtual();
    }

    private void resolverEventosNaDivisao() {
        Divisao divisaoAtual = hero.getDivisaoAtual();

        // Verifica se há itens na divisão e o herói apanha-os
        if (!divisaoAtual.getItens().isEmpty()) {
            System.out.println("Encontras-te um item");
            hero.pegarItemNaDivisao();
        }

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
        UnorderedArrayList<Inimigo> inimigosNaSala = divisaoAtual.getInimigos();

        if (inimigosNaSala.isEmpty()) {
            System.out.println("Nenhum inimigo na sala.");
            return;
        }

        // Verifica se o herói tem prioridade de ataque
        if (hero.getPrioridadeAtaque()) {
            System.out.println("Tó Cruz há inimigos na sala! Combate iniciado.");

            // Fase do jogador: Tó ataca todos os inimigos na sala.
            for (Inimigo inimigo : inimigosNaSala) {
                realizarAtaqueHeroi(inimigo);
            }

            // Remover inimigos eliminados após o ataque do jogador.
            for (int i = 0; i < inimigosNaSala.size(); i++) {
                Inimigo inimigo = inimigosNaSala.getIndex(i);
                if (inimigo.getVida() <= 0) {
                    divisaoAtual.removerInimigo(inimigo);
                    System.out.println("Inimigo " + inimigo.getNome() + " foi derrotado!");
                    i--;
                }
            }

            // Se ainda houver inimigos, ocorre a fase de contra-ataque.
            if (!inimigosNaSala.isEmpty()) {
                System.out.println("Os inimigos restantes contra-atacam!");

                for (Inimigo inimigo : inimigosNaSala) {
                    realizarContraAtaque(inimigo);
                }
                moverInimigosForaDaSala(divisaoAtual);

            } else {
                System.out.println("Todos os inimigos foram derrotados! A ronda termina.");
            }

        }

        // Verificar se o Tó perdeu a vida toda.
        verificarFimDeJogo();
    }

    private void realizarAtaqueHeroi(Inimigo inimigo) {
        hero.atacar(inimigo);
        System.out.println(
                "Tó Cruz atacou " + inimigo.getNome() + " causando dano. Vida do inimigo: " + inimigo.getVida());
    }

    private void realizarContraAtaque(Inimigo inimigo) {
        hero.setVida(hero.getVida() - inimigo.getPoder());
        System.out.println("O inimigo " + inimigo.getNome() + " contra-atacou! Vida do Tó Cruz: " + hero.getVida());
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
