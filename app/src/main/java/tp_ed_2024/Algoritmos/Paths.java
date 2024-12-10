package tp_ed_2024.Algoritmos;

import tp_ed_2024.Collections.Interfaces.StackADT;
import tp_ed_2024.Collections.Stacks.LinkedStack;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public class Paths {

    private EdificioImp<Divisao> network;
    private StackADT<Integer> calculatedPath;

    public Paths(EdificioImp<Divisao> network) {
        this.network = network;
        calculatedPath = new LinkedStack<>();
    }

    private boolean calcularMenorDano(int startIndex, int endIndex) {
        double[][] adjacencyMatrix = network.obterMatrizAdjacente();
        final int NO_PARENT = -1;
        int numVertices = adjacencyMatrix[0].length;

        double[] totalDamage = new double[numVertices];
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            totalDamage[i] = Double.POSITIVE_INFINITY;
            visited[i] = false;
        }

        totalDamage[startIndex] = 0.0;
        int[] parents = new int[numVertices];
        parents[startIndex] = NO_PARENT;

        for (int i = 1; i < numVertices; i++) {
            int nearestVertex = -1;
            double minDamage = Double.POSITIVE_INFINITY;

            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                if (!visited[vertexIndex] && totalDamage[vertexIndex] < minDamage) {
                    nearestVertex = vertexIndex;
                    minDamage = totalDamage[vertexIndex];
                }
            }

            if (nearestVertex == -1) {
                return false;
            }

            visited[nearestVertex] = true;

            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                double edgeDamage = adjacencyMatrix[nearestVertex][vertexIndex];
                double vertexDamage = calcularDano(vertexIndex);

                if (edgeDamage > 0 && !visited[vertexIndex] &&
                        (minDamage + edgeDamage + vertexDamage) < totalDamage[vertexIndex]) {
                    parents[vertexIndex] = nearestVertex;
                    totalDamage[vertexIndex] = minDamage + edgeDamage + vertexDamage;
                }
            }
        }

        int currentVertexIndex = endIndex;
        while (currentVertexIndex != NO_PARENT) {
            this.calculatedPath.push(currentVertexIndex);
            currentVertexIndex = parents[currentVertexIndex];
        }

        this.calculatedPath.pop();

        return true;
    }

    public double calcularDano(int vertex) {
        Divisao divisao = network.getVertex(vertex);
        if (divisao == null || divisao.getInimigos().isEmpty()) {
            return 0;
        }

        double danoTotal = 0;
        for (InimigoImp inimigo : divisao.getInimigos()) {
            danoTotal += inimigo.getPoder(); 
        }
        return danoTotal;
    }

    public StackADT<Integer> getCalculatedPath() {
        return this.calculatedPath;
    }

    public boolean calcularCaminhoMenorDano(int startIndex, int endIndex) {
        return calcularMenorDano(startIndex, endIndex);
    }

}
