package tp_ed_2024.Algoritmos;

import tp_ed_2024.Collections.Interfaces.StackADT;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Collections.Stacks.LinkedStack;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import tp_ed_2024.Recursos.ConsoleColors;

import java.util.Iterator;

public class Paths {

    private EdificioImp<Divisao> edificio;
    private StackADT<Integer> calculatedPath;

    public Paths(EdificioImp<Divisao> edificio) {
        this.edificio = edificio;
        calculatedPath = new LinkedStack<>();
    }

    public Iterator<Integer> calcularCaminhoParaAlvo(Divisao posicaoAtualDivisao, Divisao alvoDivisao,
                                                     EdificioImp<Divisao> edificio) {
        // Encontrar o menor caminho para o alvo

        Iterator<Integer> caminhoParaAlvo = edificio.findShortestPath(
                edificio.getIndex(posicaoAtualDivisao),
                edificio.getIndex(alvoDivisao),
                null);
        return caminhoParaAlvo;
    }

    public Iterator<Integer> calcularCaminhoParaKit(Divisao posicaoAtualDivisao,
                                                    EdificioImp<Divisao> edificio) {
        // Encontrar o menor caminho para o kit de recuperação mais próximo
        Iterator<Integer> caminhoParaKit = null;

        for (Divisao divisao : edificio.obterDivisoes()) {
            for (Item item : divisao.getItens()) {
                if (item.getTipo() == TipoItemEnum.KIT) { // Assumindo que TipoItem é o enum
                caminhoParaKit = edificio.findShortestPath(
                        edificio.getIndex(posicaoAtualDivisao),
                        edificio.getIndex(divisao),
                        null);

                    }
            }
        }


        // Exibe a distância para o kit
        if (caminhoParaKit != null) {
            System.out.println("Distância para o kit de recuperação mais próximo: " );
        } else {
            System.out.println("Nenhum kit de recuperação disponível.");
        }

        return caminhoParaKit;
    }


    public Iterator<Integer> calcularCaminhoParaSaida(Divisao posicaoAtualDivisao,
                                                    EdificioImp<Divisao> edificio) {
        // Encontrar o menor caminho para a saida mais próxima
        Iterator<Integer> caminhoParaSaida = null;


        for (Divisao divisao : edificio.obterDivisoes()) {
                if (divisao.isEntradaSaida()) { // Assumindo que TipoItem é o enum
                    caminhoParaSaida = edificio.findShortestPath(
                            edificio.getIndex(posicaoAtualDivisao),
                            edificio.getIndex(divisao),
                            null);

                }
        }

        if (caminhoParaSaida != null) {
            System.out.println("Distância para a saida mais próximo: " );
        } else {
            System.out.println("Nenhuma saida disponível.");
        }

        return caminhoParaSaida;
    }




    
    public Iterator<Integer> calcularCaminhoParaColete(Divisao posicaoAtualDivisao,
                                                    EdificioImp<Divisao> edificio) {
        // Encontrar o menor caminho para o kit de recuperação mais próximo
        // Encontrar o menor caminho para o kit de recuperação mais próximo
        Iterator<Integer> caminhoParaColete = null;


        for (Divisao divisao : edificio.obterDivisoes()) {
            for (Item item : divisao.getItens()) {
                if (item.getTipo() == TipoItemEnum.COLETE) { // Assumindo que TipoItem é o enum
                    caminhoParaColete = edificio.findShortestPath(
                            edificio.getIndex(posicaoAtualDivisao),
                            edificio.getIndex(divisao),
                            null);

                }
            }
        }

        if (caminhoParaColete != null) {
            System.out.println("Distância para o colete mais próximo: " );
        } else {
            System.out.println("Nenhum colete disponível.");
        }

        return caminhoParaColete;
    }

    public void calcularCaminhos(Divisao posicaoAtualDivisao, Divisao alvoDivisao, EdificioImp<Divisao> edificio) {
        // Verifica se o alvoDivisao é nulo
        System.out.println("//////////// Melhores Caminhos ////////////");
        if (alvoDivisao == null) {
            System.out.println(ConsoleColors.RED  + "O alvo já está contigo " + ConsoleColors.RESET);


            Iterator<Integer> caminhoParaSaida = calcularCaminhoParaSaida(posicaoAtualDivisao, edificio);

            // Mostra o melhor caminho para o kit de recuperação mais próximo
            System.out.println("Melhor caminho para a saida  mais próxima:");
            // Calcular o caminho para o kit
            while (caminhoParaSaida.hasNext()) {
                int index = caminhoParaSaida.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaSaida.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + "\n" + ConsoleColors.RESET);
                }
            }

        } else {
            // Encontrar o melhor caminho até o alvo
            Iterator<Integer> caminhoParaAlvo = calcularCaminhoParaAlvo(posicaoAtualDivisao, alvoDivisao, edificio);
            double distanciaParaAlvo = edificio.shortestPathWeight(posicaoAtualDivisao, alvoDivisao);

            // Exibir o melhor caminho para o alvo

            System.out.println("Melhor caminho para o alvo:");
            // Calcular o caminho para o alvo
            while (caminhoParaAlvo.hasNext()) {
                int index = caminhoParaAlvo.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaAlvo.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " ); // Imprime "->" se não for a última divisão
                } else {
                    System.out.print(divisao.getNome() + ConsoleColors.RESET); // Não imprime "->" na última divisão
                }
            }

            System.out.println("\nDistância para o alvo: " + distanciaParaAlvo);
        }


            // Encontrar o melhor caminho até o kit de recuperação mais próximo
            Iterator<Integer> caminhoParaKit = calcularCaminhoParaKit(posicaoAtualDivisao, edificio);

            // Mostra o melhor caminho para o kit de recuperação mais próximo
            System.out.println("Melhor caminho para o kit de recuperação mais próximo:");
            // Calcular o caminho para o kit
            while (caminhoParaKit.hasNext()) {
                int index = caminhoParaKit.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaKit.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + "\n" + ConsoleColors.RESET);
                }
            }




            // Encontrar o melhor caminho até o kit de recuperação mais próximo
            Iterator<Integer> caminhoParaColete = calcularCaminhoParaColete(posicaoAtualDivisao,  edificio);

            // Mostra o melhor caminho para o colete mais próximo
            System.out.println( "Melhor caminho para o colete mais próximo:");
            // Calcular o caminho para o kit
            while (caminhoParaColete.hasNext()) {
                int index = caminhoParaColete.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaColete.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + ConsoleColors.RESET);
                }

        }
        System.out.println("\n//////////////////////////////////");
    }

}
