package tp_ed_2024.Algoritmos;

import tp_ed_2024.Collections.Interfaces.StackADT;
import tp_ed_2024.Collections.Stacks.LinkedStack;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
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

    public Iterator<Integer> calcularCaminhoParaKit(Divisao posicaoAtualDivisao, Divisao kitDivisao,
                                                    EdificioImp<Divisao> edificio) {
        // Encontrar o menor caminho para o kit de recuperação mais próximo
        Iterator<Integer> caminhoParaKit = null;
        double menorDistanciaKit = Double.POSITIVE_INFINITY;

        if (kitDivisao != null) {
            int indexKit = edificio.getIndex(kitDivisao);
            menorDistanciaKit = edificio.shortestPathWeight(posicaoAtualDivisao, kitDivisao);
            caminhoParaKit = edificio.findShortestPath(
                    edificio.getIndex(posicaoAtualDivisao),
                    indexKit,
                    null);
        }

        // Exibe a distância para o kit
        if (kitDivisao != null) {
            System.out.println("Distância para o kit de recuperação mais próximo: " + menorDistanciaKit);
        } else {
            System.out.println("Nenhum kit de recuperação disponível.");
        }

        return caminhoParaKit;
    }

    public void calcularCaminhos(Divisao posicaoAtualDivisao, Divisao alvoDivisao, Divisao kitDivisao, Divisao coleteDivisao,
                                 EdificioImp<Divisao> edificio) {
        // Verifica se o alvoDivisao é nulo
        System.out.println("//////////// Melhores Caminhos ////////////");
        if (alvoDivisao == null) {
            System.out.println(ConsoleColors.RED  + "O alvo já está contigo seu distraído" + ConsoleColors.RESET);
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
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " + ConsoleColors.RESET); // Imprime "->" se não for a última divisão
                } else {
                    System.out.print(divisao.getNome()); // Não imprime "->" na última divisão
                }
            }

            System.out.println("\nDistância para o alvo: " + distanciaParaAlvo);
        }

        // Verifica se o kitDivisao é nulo
        if (kitDivisao == null) {
            System.out.println(ConsoleColors.RED + "Não existem kits de recuperação disponíveis." + ConsoleColors.RESET);
        } else {
            // Encontrar o melhor caminho até o kit de recuperação mais próximo
            Iterator<Integer> caminhoParaKit = calcularCaminhoParaKit(posicaoAtualDivisao, kitDivisao, edificio);

            // Mostra o melhor caminho para o kit de recuperação mais próximo
            System.out.println("Melhor caminho para o kit de recuperação mais próximo:");
            // Calcular o caminho para o kit
            while (caminhoParaKit.hasNext()) {
                int index = caminhoParaKit.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaKit.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " + ConsoleColors.RESET );
                } else {
                    System.out.print(divisao.getNome());
                }
            }

        }

        if(coleteDivisao == null) {
            System.out.printf(ConsoleColors.RED + "Não existem coletes disponíveis." + ConsoleColors.RESET);
        }else {
            // Encontrar o melhor caminho até o kit de recuperação mais próximo
            Iterator<Integer> caminhoParaColete = calcularCaminhoParaKit(posicaoAtualDivisao, kitDivisao, edificio);

            // Mostra o melhor caminho para o colete mais próximo
            System.out.println( "Melhor caminho para o colete mais próximo:");
            // Calcular o caminho para o kit
            while (caminhoParaColete.hasNext()) {
                int index = caminhoParaColete.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaColete.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " + ConsoleColors.RESET);
                } else {
                    System.out.print(divisao.getNome());
                }
            }

        }
        System.out.println("//////////////////////////////////");
    }

}
