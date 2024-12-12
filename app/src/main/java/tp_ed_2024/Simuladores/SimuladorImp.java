package tp_ed_2024.Simuladores;

import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

import java.util.Random;
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
        System.out.println("Bem-vindo ao Simulador XPTO de missões!");
        System.out.println(edificio);
        resolverEventosNaDivisao();
        Scanner scanner = new Scanner(System.in);
        exibirEstadoAtual();

        while (emJogo) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Mover para uma nova divisão");
            System.out.println("2. Usar item da mochila");
            System.out.println("3. Sair da simulação");

            int escolha = scanner.nextInt();
            scanner.nextLine();
            Divisao divisaoAtual = encontrarDivisaoDoHeroi();
            switch (escolha) {
                case 1:
                    moverHero(scanner);
                    for (Divisao divisao : edificio.obterDivisoes()) {
                        for (InimigoImp inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos(); // Reset os movimentos permitidos para a nova ronda
                        }
                    }
                    System.out.println(edificio);
                    break;
                case 2:
                    hero.usarMedikit();
                    for (Divisao divisao : edificio.obterDivisoes()) {
                        for (InimigoImp inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos(); // Reset os movimentos permitidos para a nova ronda
                            moverInimigosForaDaSala(divisaoAtual);
                        }
                    }
                    resolverCombate();
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

    // Dentro do método moverHero
    private void moverHero(Scanner scanner) {
        Divisao novaDivisao = null;

        // Obtenha a divisão atual do herói diretamente da estrutura do edifício
        Divisao divisaoAtual = encontrarDivisaoDoHeroi();

        while (novaDivisao == null) {
            System.out.println("Digite o nome da divisão para onde deseja mover-se:");
            String nomeDivisao = scanner.nextLine();

            // Obter a nova divisão pelo nome
            novaDivisao = edificio.obterDivisaoPorNome(nomeDivisao);

            if (novaDivisao == null) {
                System.out.println("Divisão inválida. Tente novamente.");
            } else if (!edificio.verificarLigacao(divisaoAtual, novaDivisao)) {
                System.out.println("Movimento inválido! Não há ligação entre as divisões.");
                novaDivisao = null;
            }
        }

        // Remover o herói da divisão atual
        divisaoAtual.removerHeroi(); // Método para remover o herói da divisão atual

        // Mover o herói para a nova divisão
        novaDivisao.adicionarHeroi(hero); // Método para adicionar o herói à nova divisão

        System.out.println("O herói foi movido para a divisão " + novaDivisao.getNome());

        // Resolver eventos na nova divisão
        moverInimigosForaDaSala(divisaoAtual);
        resolverEventosNaDivisao();
        exibirEstadoAtual();
    }

    private void moverInimigosForaDaSala(Divisao salaAtual) {
        System.out.println("Inimigos em outras divisões estão em movimento.");

        EdificioImp<Divisao> mapa = edificio;
        UnorderedArrayList<Divisao> todasDivisoes = edificio.obterDivisoes();

        for (Divisao divisao : todasDivisoes) {
            if (divisao != salaAtual && !divisao.getInimigos().isEmpty()) {
                UnorderedArrayList<InimigoImp> inimigosNaDivisao = divisao.getInimigos();
                for (int i = 0; i < inimigosNaDivisao.size(); i++) {
                    InimigoImp inimigo = inimigosNaDivisao.getIndex(i);

                    if (inimigo.getMovimentosRestantes() > 0) {
                        moverInimigosAleatoriamente(mapa, inimigo);
                        inimigo.decrementarMovimentosRestantes();
                    }
                }
            }
        }

        edificio.resetPeso(mapa, salaAtual);
    }

    private void moverInimigosAleatoriamente(EdificioImp<Divisao> mapa, InimigoImp inimigo) {
        Divisao divisaoAtual = encontrarDivisaoDoInimigo(mapa, inimigo);
        UnorderedArrayList<Divisao> vizinhos = mapa.getVizinhos(divisaoAtual);

        if (vizinhos.size() > 0) {
            Random random = new Random();
            Divisao novoDestino = vizinhos.getIndex(random.nextInt(vizinhos.size()));

            divisaoAtual.removerInimigo(inimigo);
            novoDestino.adicionarInimigo(inimigo);
            divisaoAtual = novoDestino;

            System.out.println(inimigo.getNome() + " moveu-se para a divisão " + divisaoAtual.getNome());

            // **Verifica se o inimigo entrou na divisão do herói**
            Divisao divisaoDoHeroi = encontrarDivisaoDoHeroi();
            if (novoDestino == divisaoDoHeroi) {
                System.out.println(inimigo.getNome() + " encontrou o herói! Contra-ataque imediato!");
                realizarContraAtaque(inimigo);
            } else if (inimigo.getMovimentosRestantes() > 1 && random.nextBoolean()) {
                UnorderedArrayList<Divisao> vizinhosDoNovoDestino = mapa.getVizinhos(divisaoAtual);
                if (!vizinhosDoNovoDestino.isEmpty()) {
                    Divisao destinoFinal = vizinhosDoNovoDestino.getIndex(random.nextInt(vizinhosDoNovoDestino.size()));

                    divisaoAtual.removerInimigo(inimigo);
                    destinoFinal.adicionarInimigo(inimigo);
                    divisaoAtual = destinoFinal;

                    System.out.println(
                            inimigo.getNome() + " moveu-se novamente para a divisão " + divisaoAtual.getNome());

                    // **Verifica novamente se o inimigo encontrou o herói após o segundo
                    // movimento**
                    if (destinoFinal == divisaoDoHeroi) {
                        System.out.println(inimigo.getNome() + " encontrou o herói novamente! Contra-ataque imediato!");
                        realizarContraAtaque(inimigo);
                    }

                    inimigo.decrementarMovimentosRestantes(); // Decrementa o contador de movimentações
                }
            }
        } else {
            System.out.println(inimigo.getNome() + " não tem divisões vizinhas para se mover.");
        }
    }

    private Divisao encontrarDivisaoDoInimigo(EdificioImp<Divisao> mapa, InimigoImp inimigo) {
        for (Divisao divisao : mapa.obterDivisoes()) {
            if (divisao.getInimigos().contains(inimigo)) {
                return divisao; // Retorna a divisão onde o inimigo está
            }
        }
        return null; // Caso o inimigo não seja encontrado em nenhuma divisão
    }

    private void resolverEventosNaDivisao() {
        Divisao divisaoAtual = encontrarDivisaoDoHeroi();

        // Primeiro verifica itens
        if (!divisaoAtual.getItens().isEmpty()) {
            System.out.println("Encontras-te um medikit, não esquecer que só os fracos é que os usam");
            pegarItemNaDivisao();
        }

        // Depois verifica inimigos
        if (!divisaoAtual.getInimigos().isEmpty()) {
            System.out.println("Inimigos encontrados! O combate começou.");
            resolverCombate();
        }

        // Finalmente verifica o alvo (apenas se não houver inimigos)
        if (divisaoAtual.getInimigos().isEmpty() && divisaoAtual.getAlvo() != null) {
            System.out.println("Encontras-te o alvo na divisão!");
            hero.ativarFlagAlvo();
            divisaoAtual.desativarFlagAlvo();
        }
    }

    private void resolverCombate() {
        Divisao divisaoAtual = encontrarDivisaoDoHeroi();
        UnorderedArrayList<InimigoImp> inimigosNaSala = divisaoAtual.getInimigos(); // Ainda é correto pegar os inimigos
                                                                                    // aqui
        EdificioImp<Divisao> mapa = edificio;

        System.out.println("Tó Cruz, há inimigos na sala! Combate iniciado.");
        Scanner scanner = new Scanner(System.in);

        while (divisaoAtual.getInimigos().size() > 0 && emJogo) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar kit de vida");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                // Fase do jogador: Tó ataca todos os inimigos na sala.
                for (InimigoImp inimigo : inimigosNaSala) {
                    realizarAtaqueHeroi(inimigo);
                }
                // Remover inimigos eliminados após o ataque do jogador.
                for (int i = 0; i < inimigosNaSala.size(); i++) {
                    InimigoImp inimigo = inimigosNaSala.getIndex(i);
                    if (inimigo.getVida() <= 0) {
                        divisaoAtual.removerInimigo(inimigo);
                        edificio.resetPeso(mapa, divisaoAtual);
                        System.out.println("Inimigo " + inimigo.getNome() + " foi derrotado!");
                        i--;
                    }
                }

                // Se ainda houver inimigos, ocorre a fase de contra-ataque.
                if (!inimigosNaSala.isEmpty()) {
                    System.out.println("Os inimigos restantes contra-atacam!");

                    for (InimigoImp inimigo : inimigosNaSala) {
                        realizarContraAtaque(inimigo);
                    }
                    moverInimigosForaDaSala(divisaoAtual);

                } else {
                    System.out.println("Todos os inimigos foram derrotados! A ronda termina.");
                }

                exibirEstadoAtual();

            } else if (escolha == 2) {
                // Usar kit de vida
                hero.usarMedikit();

                // Inimigos contra-atacam após o uso do medikit
                for (InimigoImp inimigo : inimigosNaSala) {
                    realizarContraAtaque(inimigo);
                }
                moverInimigosForaDaSala(divisaoAtual);
                exibirEstadoAtual();
            }

            verificarFimDeJogo();
        }

        if (divisaoAtual.getAlvo() != null && inimigosNaSala.isEmpty()) {
            System.out.println("Não há inimigos por perto. Podes resgatar o alvo!");
            hero.ativarFlagAlvo();
            divisaoAtual.desativarFlagAlvo();
            System.out.println("Alvo resgatado com sucesso, és mesmo o maior!");
        }

        exibirEstadoAtual();
    }

    private void realizarAtaqueHeroi(InimigoImp inimigo) {
        hero.atacar(inimigo);
        System.out.println(
                "Tó Cruz atacou " + inimigo.getNome() + " causando dano. Vida do inimigo: " + inimigo.getVida());
    }

    private void realizarContraAtaque(InimigoImp inimigo) {
        hero.setVida(hero.getVida() - inimigo.getPoder());
        System.out.println("O inimigo " + inimigo.getNome() + " contra-atacou! Vida do Tó Cruz: " + hero.getVida());
    }

    public Divisao encontrarDivisaoDoHeroi() {
        for (Divisao divisao : edificio.obterDivisoes()) {
            if (divisao.temHeroi()) { // Método que verifica se a divisão tem o herói
                return divisao;
            }
        }
        return null; // Caso o herói não esteja em nenhuma divisão (isso não deveria acontecer se a
                     // lógica estiver correta)
    }

    public void exibirEstadoAtual() {
        Divisao divisaoAtual = encontrarDivisaoDoHeroi();
        System.out.println("================Estado atual===================");
        System.out.println("Divisão Atual: " + divisaoAtual.getNome());
        System.out.println("Vida do Herói: " + hero.getVida());
        System.out.println("Inimigos na divisão: " + divisaoAtual.getInimigos().size());
        System.out.println("Items na divisão: " + divisaoAtual.getItens().size());
        if (hero.isTemAlvo()) {
            System.out.println("|||||||||||||||||||||||||||||");
            System.out.println("Tens o alvo! Sai do edifício");
            System.out.println("|||||||||||||||||||||||||||||");
        }
        System.out.println("===================================");
        printPesosVizinhos(edificio, divisaoAtual);
    }

    public void pegarItemNaDivisao() {
        Divisao divisaoAtual = encontrarDivisaoDoHeroi(); // Obter a divisão atual
        if (divisaoAtual != null && divisaoAtual.getItens().size() > 0) {
            Item item = divisaoAtual.getItens().removeLast(); // Retira o item da divisão

            if (item.getTipo() == TipoItemEnum.KIT) {
                // Guardar medikits na mochila
                if (hero.getMochila().size() < 5) {
                    hero.getMochila().push(item);
                    System.out.println(
                            hero.getNome() + " pegou um medikit: " + item + "PS: Só fracos é que usam kits de cura");
                } else {
                    System.out.println(
                            "A mochila está cheia! Não consegues carregar mais kits, na próxima traz uma mochila maior!");
                }
            } else if (item.getTipo() == TipoItemEnum.COLETE) {
                // Usar coletes imediatamente
                hero.aplicarColete(item);
            }
        } else {
            System.out.println("Não há itens na divisão para pegar.");
        }
    }

    private void verificarFimDeJogo() {
        Divisao divisaoAtual = encontrarDivisaoDoHeroi();
        if (hero.getVida() <= 0) {
            System.out.println("Morreste...");
            finalizarSimulacao();
        } else if (hero.isTemAlvo() && divisaoAtual.isEntradaSaida()) {
            System.out.println("Parabéns cumpriste a tua missão! GOAT");
            finalizarSimulacao();
        }
    }

    private void finalizarSimulacao() {
        emJogo = false;
    }

    public void printPesosVizinhos(EdificioImp<Divisao> mapa, Divisao divisaoAtual) {
        if (divisaoAtual == null) {
            System.out.println("A divisão atual não pode ser nula.");
            return;
        }

        System.out.println("Pesos das conexões da divisão " + divisaoAtual.getNome() + " com os vizinhos:");

        UnorderedArrayList<Divisao> vizinhos = mapa.getVizinhos(divisaoAtual);

        if (vizinhos.isEmpty()) {
            System.out.println("A divisão " + divisaoAtual.getNome() + " não possui vizinhos.");
            return;
        }

        for (int i = 0; i < vizinhos.size(); i++) {
            Divisao vizinho = vizinhos.getIndex(i);
            double peso = mapa.getWeight(divisaoAtual, vizinho);
            if (peso <= 0) {
                System.out.println("  -> Conexão inválida ou peso zero entre " + divisaoAtual.getNome() + " e "
                        + vizinho.getNome());
            } else {
                System.out.println("  -> Para " + vizinho.getNome() + ": " + peso);
            }
        }
    }

    // public void printAdjMatrix() {
    // System.out.println("Matriz de Adjacência:");
    // for (int i = 0; i < edificio.numVertices; i++) {
    // for (int j = 0; j < edificio.numVertices; j++) {
    // System.out.print((edificio.adjMatrix[i][j] ? 1 : 0) + " ");
    // }
    // System.out.println();
    // }
    // }
    public void imprimirMatrizDePesos() {
        for (int i = 0; i < edificio.weightMatrix.length; i++) {
            for (int j = 0; j < edificio.weightMatrix[i].length; j++) {

                System.out.print(edificio.weightMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
