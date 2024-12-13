package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import tp_ed_2024.Recursos.ConsoleColors;

import java.util.Iterator;

public class EdificioImp<T> extends Network<DivisaoImp> implements Edificio {

    public EdificioImp(boolean isBidirectional) {
        super();
        this.isBidirectional = isBidirectional;
    }

    @Override
    public void adicionarDivisao(DivisaoImp divisao) {
        addVertex(divisao);
    }

    public void adicionarLigacao(String origem, String destino) {
        DivisaoImp divisaoOrigem = obterDivisaoPorNome(origem);
        DivisaoImp divisaoDestino = obterDivisaoPorNome(destino);

        if (divisaoOrigem == null || divisaoDestino == null) {
            System.out.println("Erro ao adicionar ligação: divisões não encontradas.");
            return;
        }

      int peso = calcularPeso(divisaoOrigem, divisaoDestino);

        addEdge(divisaoOrigem, divisaoDestino, peso);
        addEdge(divisaoDestino, divisaoOrigem, peso);
    }

    public boolean verificarLigacao(DivisaoImp divisao1, DivisaoImp divisao2) {
        UnorderedArrayList<DivisaoImp> vizinhos = getVizinhos(divisao1);
        return vizinhos.contains(divisao2);
    }

    @Override
    public DivisaoImp obterDivisaoPorNome(String nome) {
        for (int i = 0; i < size(); i++) {
            DivisaoImp divisao = getVertex(i);
            if (divisao.getNome().equals(nome)) {
                return divisao;
            }
        }
        return null;
    }

    public UnorderedArrayList<DivisaoImp> obterDivisoes() {
        UnorderedArrayList<DivisaoImp> divisoes = new UnorderedArrayList<>();
        for (int i = 0; i < size(); i++) {
            DivisaoImp divisao = getVertex(i);
            divisoes.addToFront(divisao);
        }
        return divisoes;
    }

    @Override
    public void exibirDivisoes() {
        for (int i = 0; i < size(); i++) {
            DivisaoImp divisao = getVertex(i);
            System.out.println(divisao);
        }
    }


    public UnorderedArrayList<DivisaoImp> getEntradasSaidas() {
        UnorderedArrayList<DivisaoImp> entradasSaidas = new UnorderedArrayList<>();

        for (int i = 0; i < size(); i++) {
            DivisaoImp divisao = getVertex(i);
            if (divisao.isEntradaSaida()) {
                entradasSaidas.addToRear(divisao);
            }
        }

        return entradasSaidas;
    }


    public void resetPeso(EdificioImp<DivisaoImp> edificio, DivisaoImp divisaoAtual) {

        UnorderedArrayList<DivisaoImp> vizinhos = edificio.getVizinhos(divisaoAtual);


        for (int i = 0; i < vizinhos.size(); i++) {
            DivisaoImp vizinho = vizinhos.getIndex(i);

            if (edificio.getWeight(divisaoAtual, vizinho) > 0) {

                int novoPeso = calcularPeso(divisaoAtual, vizinho);


                edificio.setEdgeWeight(divisaoAtual, vizinho, novoPeso);
                edificio.setEdgeWeight(vizinho, divisaoAtual, novoPeso);
            }
        }
    }


    private int calcularPeso(DivisaoImp divisao1, DivisaoImp divisao2) {
        int pesoBase = 1;

        for (InimigoImp inimigo : divisao1.getInimigos()) {
            pesoBase += inimigo.getPoder();
        }
        for (InimigoImp inimigo : divisao2.getInimigos()) {
            pesoBase += inimigo.getPoder();
        }


        return  pesoBase;
    }



    private void setEdgeWeight(DivisaoImp origem, DivisaoImp destino, int novoPeso) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("As divisões origem e destino não podem ser nulas.");
        }

        int origemIndex = this.getIndex(origem);
        int destinoIndex = this.getIndex(destino);


        this.weightMatrix[origemIndex][destinoIndex] = novoPeso;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n===== Mapa do Edifício ===== \n");
        for (int i = 0; i < size(); i++) {
            DivisaoImp divisao = getVertex(i);

            sb.append("- ").append(divisao.getNome());


            if (divisao.getItens() != null && !divisao.getItens().isEmpty()) {
                sb.append(ConsoleColors.GREEN_BRIGHT +" [Items: " + ConsoleColors.RESET).append(divisao.getItens().size()).append("]");
            }


            if (divisao.getInimigos() != null && !divisao.getInimigos().isEmpty()) {
                sb.append(ConsoleColors.RED + " [Inimigos: "+ ConsoleColors.RESET).append(divisao.getInimigos().size()).append("]");
            }


            if (divisao.isFlagAlvo()) {
                sb.append(ConsoleColors.YELLOW_BRIGHT +" [Alvo AQUIIII]" + ConsoleColors.RESET);
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public Iterator<Integer> findShortestPath(int startVertex, int endVertex) {


        double[] distances = new double[numVertices];
        boolean[] visited = new boolean[numVertices];
        int[] prev = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            visited[i] = false;
            prev[i] = -1;
        }

        distances[startVertex] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = minDistance(distances, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {

                if (!visited[v] && adjMatrix[u][v] && distances[u] != Double.POSITIVE_INFINITY
                        && distances[u] + weightMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + weightMatrix[u][v];
                    prev[v] = u;
                }
            }
        }

        UnorderedArrayList<Integer> path = new UnorderedArrayList<>();
        if (prev[endVertex] != -1 || startVertex == endVertex) {
            for (int vertex = endVertex; vertex != -1; vertex = prev[vertex]) {
                path.addToFront(vertex);
                if (vertex == startVertex)
                    break;
            }
        }

        return path.iterator();
    }



}
