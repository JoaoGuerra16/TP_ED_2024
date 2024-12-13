package tp_ed_2024.Simuladores;

import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.DivisaoImp;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import tp_ed_2024.Recursos.ConsoleColors;
import tp_ed_2024.Algoritmos.Paths;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * The type Simulador imp.
 */
public class SimuladorManualImp {

    private EdificioImp<DivisaoImp> edificio;
    private HeroImp hero;
    private boolean emJogo;
    Scanner scanner = new Scanner(System.in);
    /**
     * The Path.
     */
    Paths path = new Paths(edificio);

    /**
     * Instantiates a new Simulador imp.
     *
     * @param edificio the edificio
     * @param hero     the hero
     */
    public SimuladorManualImp(EdificioImp<DivisaoImp> edificio, HeroImp hero) {
        this.edificio = edificio;
        this.hero = hero;
        this.emJogo = true;
    }

    /**
     * Iniciar simulacao.
     */
    public void iniciarSimulacao() {
        System.out.println(ConsoleColors.BLUE + "Bem-vindo ao Simulador XPTO de missões!" + ConsoleColors.RESET);
        mostrarMapa();
        resolverEventosNaDivisao();
        exibirEstadoAtual();


        while (emJogo) {


            DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();


            printPesosVizinhos(edificio, divisaoAtual);

            System.out.println(ConsoleColors.BLUE +"Escolha uma ação:" + ConsoleColors.RESET);
            System.out.println("1. Mover para uma nova divisão");
            System.out.println("2. Usar item da mochila");
            System.out.println("3. Sair do Edificio");

            int escolha = scanner.nextInt();
            scanner.nextLine();


            switch (escolha) {
                case 1:
                    moverHero();
                    for (DivisaoImp divisao : edificio.obterDivisoes()) {
                        for (InimigoImp inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos();
                        }
                    }
                    mostrarMapa();
                    break;
                case 2:
                    hero.usarMedikit();
                    for (DivisaoImp divisao : edificio.obterDivisoes()) {
                        for (InimigoImp inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos();

                        }

                    }
                    moverInimigosForaDaSala(divisaoAtual);
                    resolverCombate();
                    mostrarMapa();
                    break;
                case 3:
                    finalizarSimulacao();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Escolha inválida. Tente novamente." + ConsoleColors.RESET);
            }


        }

        System.out.println("Simulação encerrada.");
    }


    private void moverHero() {
        DivisaoImp novaDivisao = null;


        DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();

        while (novaDivisao == null) {
            System.out.println(ConsoleColors.BLUE + "Digite o nome da divisão para onde deseja mover-se:" + ConsoleColors.RESET);
            String nomeDivisao = scanner.nextLine();


            novaDivisao = edificio.obterDivisaoPorNome(nomeDivisao);

            if (novaDivisao == null) {
                System.out.println(ConsoleColors.RED + "Divisão inválida. Tente novamente." + ConsoleColors.RESET);
            } else if (!edificio.verificarLigacao(divisaoAtual, novaDivisao)) {
                System.out.println(ConsoleColors.RED +"Movimento inválido! Não há ligação entre as divisões." + ConsoleColors.RESET);
                novaDivisao = null;
            }
        }


        divisaoAtual.removerHeroi();


        novaDivisao.adicionarHeroi(hero);
        System.out.println("O"+ ConsoleColors.YELLOW_BRIGHT+" Herói "+ConsoleColors.RESET+ "foi movido para a divisão: " + novaDivisao.getNome());



        if (!novaDivisao.temInimigos()) {
            moverInimigosForaDaSala(divisaoAtual);
        }
        edificio.resetPeso(edificio, novaDivisao);


        resolverEventosNaDivisao();
        exibirEstadoAtual();
    }

    private void moverInimigosForaDaSala(DivisaoImp salaAtual) {


        EdificioImp<DivisaoImp> mapa = edificio;
        UnorderedArrayList<DivisaoImp> todasDivisoes = edificio.obterDivisoes();

        for (DivisaoImp divisao : todasDivisoes) {
            if (divisao != salaAtual && !divisao.getInimigos().isEmpty()) {
                UnorderedArrayList<InimigoImp> inimigosNaDivisao = divisao.getInimigos();
                for (int i = 0; i < inimigosNaDivisao.size(); i++) {
                    InimigoImp inimigo = inimigosNaDivisao.getIndex(i);

                    if (inimigo.getMovimentosRestantes() > 0) {

                        if (!inimigo.isContraAtaqueRealizado()) {
                            moverInimigosAleatoriamente(mapa, inimigo);
                            inimigo.decrementarMovimentosRestantes();

                        }
                    }

                }
            }
        }
    }
    private void moverInimigosAleatoriamente(EdificioImp<DivisaoImp> mapa, InimigoImp inimigo) {
        DivisaoImp divisaoAtual = encontrarDivisaoDoInimigo(mapa, inimigo);
        UnorderedArrayList<DivisaoImp> vizinhos = mapa.getVizinhos(divisaoAtual);

        if (vizinhos.size() > 0) {
            Random random = new Random();
            DivisaoImp novoDestino = vizinhos.getIndex(random.nextInt(vizinhos.size()));

            divisaoAtual.removerInimigo(inimigo);
            novoDestino.adicionarInimigo(inimigo);
            divisaoAtual = novoDestino;

            System.out.println(ConsoleColors.RED + inimigo.getNome()+ConsoleColors.RESET + " moveu-se para a divisão: " + divisaoAtual.getNome());


            DivisaoImp divisaoDoHeroi = encontrarDivisaoDoHeroi();
            if (novoDestino == divisaoDoHeroi) {
                System.out.println(ConsoleColors.RED +inimigo.getNome()+ConsoleColors.RESET + " encontrou o herói! Ataque imediato!");
                inimigo.atacar(hero);


                inimigo.setContraAtaqueRealizado(true);
                return;
            }


            if (inimigo.getMovimentosRestantes() > 1 && random.nextBoolean()) {
                UnorderedArrayList<DivisaoImp> vizinhosDoNovoDestino = mapa.getVizinhos(divisaoAtual);
                if (!vizinhosDoNovoDestino.isEmpty()) {
                    DivisaoImp destinoFinal = vizinhosDoNovoDestino.getIndex(random.nextInt(vizinhosDoNovoDestino.size()));

                    divisaoAtual.removerInimigo(inimigo);
                    destinoFinal.adicionarInimigo(inimigo);
                    divisaoAtual = destinoFinal;

                    System.out.println(ConsoleColors.RED +inimigo.getNome()+ConsoleColors.RESET + " moveu-se novamente para a divisão: " + divisaoAtual.getNome());


                    if (destinoFinal == divisaoDoHeroi) {
                        System.out.println(ConsoleColors.RED +inimigo.getNome()+ConsoleColors.RESET + " encontrou o herói novamente! Ataque imediato!");
                        inimigo.atacar(hero);


                        inimigo.setContraAtaqueRealizado(true);
                    }

                    inimigo.decrementarMovimentosRestantes();
                }
            }
        } else {
            System.out.println(inimigo.getNome() + " não tem divisões vizinhas para se mover.");
        }
    }



    private DivisaoImp encontrarDivisaoDoInimigo(EdificioImp<DivisaoImp> mapa, InimigoImp inimigo) {
        for (DivisaoImp divisao : mapa.obterDivisoes()) {
            if (divisao.getInimigos().contains(inimigo)) {
                return divisao;
            }
        }
        return null;
    }

    private void resolverEventosNaDivisao() {
        DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();


        if (!divisaoAtual.getItens().isEmpty()) {
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Encontraste itens na divisão!"+ ConsoleColors.RESET);
            pegarItemNaDivisao();
        }


        if (!divisaoAtual.getInimigos().isEmpty()) {
            resolverCombate();
        }


        if (divisaoAtual.getInimigos().isEmpty() && divisaoAtual.getAlvo() != null) {
            System.out.println("Encontras-te o alvo na divisão!");
            hero.ativarFlagAlvo();
            divisaoAtual.desativarFlagAlvo();
        }
    }

    private void resolverCombate() {
        DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();
        UnorderedArrayList<InimigoImp> inimigosNaSala = divisaoAtual.getInimigos();
        EdificioImp<DivisaoImp> mapa = edificio;

        System.out.println(ConsoleColors.RED +"Inimigos na sala! Combate iniciado." + ConsoleColors.RESET);
        Scanner scanner = new Scanner(System.in);


        for (DivisaoImp divisao : edificio.obterDivisoes()) {
            for (InimigoImp inimigo : divisao.getInimigos()) {
                inimigo.resetarMovimentos();
            }
        }

        while (!divisaoAtual.getInimigos().isEmpty() && emJogo) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar kit de vida");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {

                for (InimigoImp inimigo : inimigosNaSala) {
                    hero.atacar(inimigo);
                }


                for (int i = 0; i < inimigosNaSala.size(); i++) {
                    InimigoImp inimigo = inimigosNaSala.getIndex(i);

                    if (inimigo.getVida() <= 0) {
                        divisaoAtual.removerInimigo(inimigo);

                        System.out.println("Inimigo " + inimigo.getNome() + " foi derrotado!");
                        i--;

                    }
                    edificio.resetPeso(mapa, divisaoAtual);
                }




                if (!inimigosNaSala.isEmpty()) {
                    System.out.println("Os inimigos restantes contra-atacam!");


                    for (DivisaoImp divisao : edificio.obterDivisoes()) {
                        for (InimigoImp inimigo : divisao.getInimigos()) {
                            inimigo.resetarMovimentos();
                        }
                    }


                    for (InimigoImp inimigo : inimigosNaSala) {
                        inimigo.atacar(hero);
                    }
                    moverInimigosForaDaSala(divisaoAtual);

                } else {
                    System.out.println("Todos os inimigos foram derrotados! A ronda termina.");
                }



            } else if (escolha == 2) {

                hero.usarMedikit();


                for (DivisaoImp divisao : edificio.obterDivisoes()) {
                    for (InimigoImp inimigo : divisao.getInimigos()) {
                        inimigo.resetarMovimentos();
                    }
                }


                for (InimigoImp inimigo : inimigosNaSala) {
                    inimigo.atacar(hero);
                }

                moverInimigosForaDaSala(divisaoAtual);


            }



            verificarFimDeJogo();
        }

        if (divisaoAtual.getAlvo() != null && inimigosNaSala.isEmpty()) {
            System.out.println("Não há inimigos por perto. Podes resgatar o alvo!");
            hero.ativarFlagAlvo();
            divisaoAtual.desativarFlagAlvo();
            System.out.println("Alvo resgatado com sucesso, és mesmo o maior!");
        }


    }


    /**
     * Encontrar divisao do heroi divisao.
     *
     * @return the divisao
     */
    private DivisaoImp encontrarDivisaoDoHeroi() {
        for (DivisaoImp divisao : edificio.obterDivisoes()) {
            if (divisao.temHeroi()) {
                return divisao;
            }
        }
        return null;

    }

    /**
     * Encontrar divisao do alvo divisao.
     *
     * @return the divisao
     */
    private DivisaoImp encontrarDivisaoDoAlvo(){
        for (DivisaoImp divisao : edificio.obterDivisoes()) {
            if (divisao.isFlagAlvo()) {
                return divisao;
            }
        }
        return null;
    }



    /**
     * Exibir estado atual.
     */
    private void exibirEstadoAtual() {



        DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();
        DivisaoImp alvoDivisao = encontrarDivisaoDoAlvo();


        System.out.println("================Estado atual===================");
        System.out.println("Divisão Atual: " + divisaoAtual.getNome());
        System.out.println("Vida do Herói: " + hero.getVida());
        System.out.println("Inimigos na divisão: " + divisaoAtual.getInimigos().size());
        System.out.println("Items na divisão: " + divisaoAtual.getItens().size());
        if (hero.isTemAlvo() ) {
            System.out.println("|||||||||||||||||||||||||||||");
            System.out.println("Tens o alvo! Sai do edifício");
            System.out.println("|||||||||||||||||||||||||||||");
        }
        System.out.println("===================================");

        path.calcularCaminhos(divisaoAtual, alvoDivisao,edificio);
    }

    /**
     * Pegar item na divisao.
     */
    private void pegarItemNaDivisao() {
        DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();
        if (divisaoAtual != null && divisaoAtual.getItens().size() > 0) {
            Item item = divisaoAtual.getItens().removeLast();

            if (item.getTipo() == TipoItemEnum.KIT) {

                if (hero.getMochila().size() < 5) {
                    hero.getMochila().push(item);
                    System.out.println(
                            hero.getNome() + " pegou um medikit: " + item + "PS: Só fracos é que usam kits de cura");
                } else {
                    System.out.println(
                            "A mochila está cheia! Não consegues carregar mais kits, na próxima traz uma mochila maior!");
                }
            } else if (item.getTipo() == TipoItemEnum.COLETE) {

                hero.aplicarColete(item);
            }
        } else {
            System.out.println("Não há itens na divisão para pegar.");
        }
    }

    private void verificarFimDeJogo() {

        if (hero.getVida() <= 0) {
            System.out.println("Morreste...");
            emJogo = false;


        }
    }

    private void finalizarSimulacao() {
        DivisaoImp divisaoAtual = encontrarDivisaoDoHeroi();
        if(hero.isTemAlvo() && divisaoAtual.isEntradaSaida()){
            System.out.println("Parabéns cumpriste a tua missão! GOAT");
            emJogo = false;

        } else if(!hero.isTemAlvo() && divisaoAtual.isEntradaSaida()){
            System.out.println("Missão falhada! O alvo não foi resgatado.");
            emJogo = false;
        }else{
            System.out.println("Não é uma saída");
        }


    }

    /**
     * Print pesos vizinhos.
     *
     * @param mapa         the mapa
     * @param divisaoAtual the divisao atual
     */
    private void printPesosVizinhos(EdificioImp<DivisaoImp> mapa, DivisaoImp divisaoAtual) {
        if (divisaoAtual == null) {
            System.out.println("A divisão atual não pode ser nula.");
            return;
        }

        System.out.println("Pesos das conexões da divisão " + divisaoAtual.getNome() + " com os vizinhos:");

        UnorderedArrayList<DivisaoImp> vizinhos = mapa.getVizinhos(divisaoAtual);

        if (vizinhos.isEmpty()) {
            System.out.println("A divisão " + divisaoAtual.getNome() + " não possui vizinhos.");
            return;
        }

        for (int i = 0; i < vizinhos.size(); i++) {
            DivisaoImp vizinho = vizinhos.getIndex(i);
            double peso = mapa.getWeight(divisaoAtual, vizinho);
            if (peso <= 0) {
                System.out.println("  -> Conexão inválida ou peso zero entre " + divisaoAtual.getNome() + " e "
                        + vizinho.getNome());
            } else {
                System.out.println("  -> Para " + vizinho.getNome() + ": " + peso);
            }
        }
    }


    public void mostrarMapa() {


        System.out.println(ConsoleColors.YELLOW_BOLD +"==============  Mapa  ==============" + ConsoleColors.RESET);
        Iterator<DivisaoImp> divisoes = edificio.obterDivisoes().iterator();

        while (divisoes.hasNext()) {
            DivisaoImp divisao = divisoes.next();

            System.out.println(ConsoleColors.GREEN_BOLD + divisao.getNome() + ":" + ConsoleColors.RESET);

            Iterator<DivisaoImp> adjacentes = edificio.getVizinhos(divisao).iterator();

            while (adjacentes.hasNext()) {
                DivisaoImp divisaoAdjacente = adjacentes.next();
                System.out.print(ConsoleColors.BLUE+"    - " + divisaoAdjacente.getNome() + ConsoleColors.RESET);

                boolean hasInfo = false;

                if (divisaoAdjacente.temHeroi()) {
                    System.out.print(ConsoleColors.PURPLE+" (Player aqui)" + ConsoleColors.RESET);
                    hasInfo = true;
                }

                if (!divisaoAdjacente.getInimigos().isEmpty()) {
                    System.out.print(ConsoleColors.RED+" (" + divisaoAdjacente.getInimigos().size()+ " Inimigos)" + ConsoleColors.RESET);
                    hasInfo = true;
                }

                if (divisaoAdjacente.getAlvo() != null) {
                    System.out.print(ConsoleColors.CYAN+" (Alvo aqui)" + ConsoleColors.RESET);
                    hasInfo = true;
                }

                if (!divisaoAdjacente.getItens().isEmpty()) {
                    for(Item item : divisaoAdjacente.getItens()){
                        if(item.getTipo() == TipoItemEnum.KIT){
                            System.out.print(ConsoleColors.GREEN+" (KIT)" + ConsoleColors.RESET);
                        }else   if(item.getTipo() == TipoItemEnum.COLETE){
                            System.out.print(ConsoleColors.GREEN+" (COLETE)" + ConsoleColors.RESET);
                        }

                    }
                    hasInfo = true;
                }


                if (!hasInfo) {
                    System.out.print(ConsoleColors.YELLOW+" (Vazia)"+ ConsoleColors.RESET);
                }

                System.out.println();
            }
        }
        System.out.println(ConsoleColors.YELLOW_BOLD+"===================================="+ ConsoleColors.RESET);
    }

}
