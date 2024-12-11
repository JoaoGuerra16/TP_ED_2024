package tp_ed_2024.Algoritmos;

import tp_ed_2024.Collections.Interfaces.StackADT;
import tp_ed_2024.Collections.Stacks.LinkedStack;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
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

    public void calcularCaminhos(Divisao posicaoAtualDivisao, Divisao alvoDivisao, Divisao kitDivisao,
            EdificioImp<Divisao> edificio) {
        // Verifica se o alvoDivisao é nulo
        System.out.println("//////////// Melhores Caminhos ////////////");
        if (alvoDivisao == null) {
            System.out.println("Não existe alvo para ser alcançado.");
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
                    System.out.print(divisao.getNome() + " -> "); // Imprime "->" se não for a última divisão
                } else {
                    System.out.print(divisao.getNome()); // Não imprime "->" na última divisão
                }
            }

            System.out.println("\nDistância para o alvo: " + distanciaParaAlvo);
        }

        // Verifica se o kitDivisao é nulo
        if (kitDivisao == null) {
            System.out.println("Não existem kits de recuperação disponíveis.");
        } else {
            // Encontrar o melhor caminho até o kit de recuperação mais próximo
            Iterator<Integer> caminhoParaKit = calcularCaminhoParaKit(posicaoAtualDivisao, kitDivisao, edificio);

            // Exibir o melhor caminho para o kit de recuperação mais próximo
            System.out.println("Melhor caminho para o kit de recuperação mais próximo:");
            // Calcular o caminho para o kit
            while (caminhoParaKit.hasNext()) {
                int index = caminhoParaKit.next();
                Divisao divisao = edificio.getVertex(index);

                // Verificar se há mais divisões no caminho
                if (caminhoParaKit.hasNext()) {
                    System.out.print(divisao.getNome() + " -> "); // Imprime "->" se não for a última divisão
                } else {
                    System.out.print(divisao.getNome()); // Não imprime "->" na última divisão
                }
            }

        }
        System.out.println("//////////////////////////////////");
    }

    // public void calcularCaminhos(Divisao posicaoAtualDivisao, Divisao
    // alvoDivisao, Divisao kitDivisao,
    // EdificioImp<Divisao> edificio) {
    // // Encontrar o menor caminho para o alvo
    // Iterator<Integer> caminhoParaAlvo = edificio.findShortestPath(
    // edificio.getIndex(posicaoAtualDivisao),
    // edificio.getIndex(alvoDivisao),
    // null);
    // double distanciaParaAlvo = edificio.shortestPathWeight(posicaoAtualDivisao,
    // alvoDivisao);

    // // Encontrar o menor caminho para o kit de recuperação mais próximo
    // Iterator<Integer> caminhoParaKit = null;
    // double menorDistanciaKit = Double.POSITIVE_INFINITY;

    // if (kitDivisao != null) {
    // int indexKit = edificio.getIndex(kitDivisao);
    // menorDistanciaKit = edificio.shortestPathWeight(posicaoAtualDivisao,
    // kitDivisao);
    // caminhoParaKit = edificio.findShortestPath(
    // edificio.getIndex(posicaoAtualDivisao),
    // indexKit,
    // null);
    // }

    // // Exibir o melhor caminho para o alvo
    // System.out.println("//////////// Melhores Caminhos ////////////");
    // System.out.println("Melhor caminho para o alvo:");
    // while (caminhoParaAlvo.hasNext()) {
    // int index = caminhoParaAlvo.next();
    // Divisao divisao = edificio.getVertex(index);
    // System.out.print(divisao.getNome() + " "); // Usando o método getNome() para
    // o nome da divisão
    // }
    // System.out.println("\nDistância para o alvo: " + distanciaParaAlvo);

    // // Exibir o melhor caminho para o kit de recuperação mais próximo
    // if (kitDivisao != null) {
    // System.out.println("Melhor caminho para o kit de recuperação mais próximo:");
    // while (caminhoParaKit.hasNext()) {
    // int index = caminhoParaKit.next();
    // Divisao divisao = edificio.getVertex(index);
    // System.out.print(divisao.getNome() + " "); // Usando o método getNome() para
    // o nome da divisão
    // }
    // System.out.println("\nDistância para o kit de recuperação mais próximo: " +
    // menorDistanciaKit);
    // } else {
    // System.out.println("Nenhum kit de recuperação disponível.");
    // }
    // System.out.println("//////////////////////////////////");
    // }

}
