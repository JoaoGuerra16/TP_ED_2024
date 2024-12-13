package tp_ed_2024.Modelos.Edificio;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;

import java.util.Iterator;

public interface Edificio {


    void adicionarDivisao(DivisaoImp divisao);


    DivisaoImp obterDivisaoPorNome(String nome);


    void adicionarLigacao(String origem, String destino);


    boolean verificarLigacao(DivisaoImp divisao1, DivisaoImp divisao2);


    void exibirDivisoes();


    UnorderedArrayList<DivisaoImp> obterDivisoes();


    UnorderedArrayList<DivisaoImp> getEntradasSaidas();


    void resetPeso(EdificioImp<DivisaoImp> edificio, DivisaoImp divisaoAtual);


    Iterator<Integer> findShortestPath(int startVertex, int endVertex);

    String toString();
}