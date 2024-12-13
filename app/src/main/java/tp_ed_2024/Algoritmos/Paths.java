package tp_ed_2024.Algoritmos;

import tp_ed_2024.Collections.Interfaces.StackADT;
import tp_ed_2024.Collections.Stacks.LinkedStack;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.DivisaoImp;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Recursos.ConsoleColors;

import java.util.Iterator;

public class Paths {

    private EdificioImp<DivisaoImp> edificio;
    private StackADT<Integer> calculatedPath;

    public Paths(EdificioImp<DivisaoImp> edificio) {
        this.edificio = edificio;
        calculatedPath = new LinkedStack<>();
    }

    public Iterator<Integer> calcularCaminhoParaAlvo(DivisaoImp posicaoAtualDivisao, DivisaoImp alvoDivisao,
                                                     EdificioImp<DivisaoImp> edificio) {


        Iterator<Integer> caminhoParaAlvo = edificio.findShortestPath(
                edificio.getIndex(posicaoAtualDivisao),
                edificio.getIndex(alvoDivisao));
        return caminhoParaAlvo;
    }

    public Iterator<Integer> calcularCaminhoParaKit(DivisaoImp posicaoAtualDivisao,
                                                    EdificioImp<DivisaoImp> edificio) {

        Iterator<Integer> caminhoParaKit = null;
        double distancia = Double.POSITIVE_INFINITY;

        for (DivisaoImp divisao : edificio.obterDivisoes()) {
            for (Item item : divisao.getItens()) {
                if (item.getTipo() == TipoItemEnum.KIT) {
                    Iterator<Integer> caminhoAtual = edificio.findShortestPath(
                            edificio.getIndex(posicaoAtualDivisao),
                            edificio.getIndex(divisao));
                    double distanciaSaida = edificio.shortestPathWeight(posicaoAtualDivisao, divisao);
                    if (distanciaSaida < distancia) {
                        distancia = distanciaSaida;
                        caminhoParaKit = caminhoAtual;

                    }
                }
            }

        }


        if (caminhoParaKit == null) {

            System.out.println("Nenhum kit de recuperação disponível.");
        }

        return caminhoParaKit;
    }


    public Iterator<Integer> calcularCaminhoParaSaida(DivisaoImp posicaoAtualDivisao,
                                                      EdificioImp<DivisaoImp> edificio) {

        Iterator<Integer> caminhoParaSaida = null;
        double distancia = Double.POSITIVE_INFINITY;

        for (DivisaoImp divisao : edificio.obterDivisoes()) {
                if (divisao.isEntradaSaida()) {
                   Iterator<Integer> caminhoAtual = edificio.findShortestPath(
                            edificio.getIndex(posicaoAtualDivisao),
                            edificio.getIndex(divisao));

                    double distanciaSaida = edificio.shortestPathWeight( posicaoAtualDivisao ,divisao );
                if(distanciaSaida < distancia){
                    distancia = distanciaSaida;
                    caminhoParaSaida = caminhoAtual;
                }
                }
        }

        if (caminhoParaSaida == null) {
            System.out.println("Nenhuma saida disponível.");
        }

        return caminhoParaSaida;
    }




    
    public Iterator<Integer> calcularCaminhoParaColete(DivisaoImp posicaoAtualDivisao,
                                                       EdificioImp<DivisaoImp> edificio) {

        Iterator<Integer> caminhoParaColete = null;
        double distancia = Double.POSITIVE_INFINITY;

        for (DivisaoImp divisao : edificio.obterDivisoes()) {
            for (Item item : divisao.getItens()) {
                if (item.getTipo() == TipoItemEnum.COLETE) {
                    Iterator<Integer> caminhoAtual = edificio.findShortestPath(
                            edificio.getIndex(posicaoAtualDivisao),
                            edificio.getIndex(divisao));

                    double distanciaSaida = edificio.shortestPathWeight(posicaoAtualDivisao, divisao);
                    if (distanciaSaida < distancia) {
                        distancia = distanciaSaida;
                        caminhoParaColete = caminhoAtual;

                    }
                }
            }
        }
        if (caminhoParaColete == null) {
            System.out.println("Nenhum colete disponível.");
        }

        return caminhoParaColete;
    }

    public void calcularCaminhos(DivisaoImp posicaoAtualDivisao, DivisaoImp alvoDivisao, EdificioImp<DivisaoImp> edificio) {

        System.out.println("//////////// Melhores Caminhos ////////////");
        if (alvoDivisao == null) {
            System.out.println(ConsoleColors.RED  + "O alvo já está contigo " + ConsoleColors.RESET);


            Iterator<Integer> caminhoParaSaida = calcularCaminhoParaSaida(posicaoAtualDivisao, edificio);


            System.out.println("Melhor caminho para a saida  mais próxima:");

            while (caminhoParaSaida.hasNext()) {
                int index = caminhoParaSaida.next();
                DivisaoImp divisao = edificio.getVertex(index);


                if (caminhoParaSaida.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + "\n" + ConsoleColors.RESET);
                }
            }

        } else {

            Iterator<Integer> caminhoParaAlvo = calcularCaminhoParaAlvo(posicaoAtualDivisao, alvoDivisao, edificio);


            System.out.println("Melhor caminho para o alvo:");

            while (caminhoParaAlvo.hasNext()) {
                int index = caminhoParaAlvo.next();
                DivisaoImp divisao = edificio.getVertex(index);


                if (caminhoParaAlvo.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + ConsoleColors.RESET);
                }
            }

        }



            Iterator<Integer> caminhoParaKit = calcularCaminhoParaKit(posicaoAtualDivisao, edificio);


            System.out.println("\nMelhor caminho para o kit de recuperação mais próximo:");

            while (caminhoParaKit.hasNext()) {
                int index = caminhoParaKit.next();
                DivisaoImp divisao = edificio.getVertex(index);


                if (caminhoParaKit.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + "\n" + ConsoleColors.RESET);
                }
            }


            Iterator<Integer> caminhoParaColete = calcularCaminhoParaColete(posicaoAtualDivisao,  edificio);


            System.out.println( "Melhor caminho para o colete mais próximo:");

            while (caminhoParaColete.hasNext()) {
                int index = caminhoParaColete.next();
                DivisaoImp divisao = edificio.getVertex(index);


                if (caminhoParaColete.hasNext()) {
                    System.out.print(ConsoleColors.GREEN_BRIGHT + divisao.getNome() + " -> " );
                } else {
                    System.out.print(divisao.getNome() + ConsoleColors.RESET);
                }

        }
        System.out.println("\n//////////////////////////////////");
    }

}
