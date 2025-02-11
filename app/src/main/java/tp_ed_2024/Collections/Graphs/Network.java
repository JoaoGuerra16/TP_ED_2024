package tp_ed_2024.Collections.Graphs;

import tp_ed_2024.Collections.Interfaces.NetworkADT;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.DivisaoImp;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    /**
     * Matrix to store the weights of the edges.
     */
    public double[][] weightMatrix;

    /**
     * Indicates whether the network is bidirectional.
     */
    protected boolean isBidirectional;

    /**
     * Default constructor that initializes the network with a default capacity.
     */
    public Network() {
        super();
        weightMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        initializeWeightMatrix();
    }

    /**
     * Constructor that initializes the network as either bidirectional or
     * unidirectional.
     *
     * @param isBidirectional true if the network is bidirectional, false otherwise
     */
    public Network(boolean isBidirectional) {
        super();
        this.isBidirectional = isBidirectional;
        weightMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        initializeWeightMatrix();
    }

    /**
     * Initializes the weight matrix with default values.
     * The weight for an edge from a vertex to itself is set to 0, and to
     * Double.POSITIVE_INFINITY for all other edges.
     */
    private void initializeWeightMatrix() {
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            for (int j = 0; j < DEFAULT_CAPACITY; j++) {
                weightMatrix[i][j] = Double.POSITIVE_INFINITY;

            }
        }
    }

    /**
     * Expands the capacity of the network when necessary.
     */
    @Override
    protected void expandCapacity() {
        super.expandCapacity();

        double[][] largerWeightMatrix = new double[vertices.length][vertices.length];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerWeightMatrix[i][j] = weightMatrix[i][j];
            }
        }

        for (int i = numVertices; i < weightMatrix.length; i++) {
            for (int j = numVertices; j < weightMatrix.length; j++) {
                weightMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Checks whether the network contains a specific vertex.
     *
     * @param vertex the vertex to check for
     * @return true if the vertex is present in the network, false otherwise
     */
    public boolean containsVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the vertex at a given index.
     *
     * @param index the index of the vertex
     * @return the vertex at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T getVertex(int index) {
        if (indexIsValid(index)) {
            return vertices[index];
        } else {
            throw new IndexOutOfBoundsException("Indice fora dos limites: " + index);
        }
    }

    /**
     * Retrieves the weight of the edge between two vertices.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the edge
     * @throws IllegalArgumentException if either vertex is not found
     */
    public double getWeight(T vertex1, T vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            return weightMatrix[index1][index2];
        } else {
            throw new IllegalArgumentException("Vértice não encontrado");
        }
    }

    /**
     * Adds an edge between two vertices with a specified weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight of the edge
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            weightMatrix[index1][index2] = weight;

            if (isBidirectional) {
                adjMatrix[index2][index1] = true;
                weightMatrix[index2][index1] = weight;
            }
        }
    }


    /**
     * Calculates the weight of the shortest path between two vertices.
     *
     * @param startVertex  the starting vertex
     * @param targetVertex the target vertex
     * @return the weight of the shortest path
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }
        double[] distances = new double[numVertices];
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            visited[i] = false;
        }

        distances[startIndex] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = minDistance(distances, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {

                if (!visited[v] && adjMatrix[u][v] && distances[u] != Double.POSITIVE_INFINITY
                        && distances[u] + weightMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + weightMatrix[u][v];
                }
            }
        }
        return distances[targetIndex];
    }

    /**
     * Finds the vertex with the minimum distance that has not been visited.
     *
     * @param distances an array of distances to each vertex
     * @param visited   an array indicating whether each vertex has been visited
     * @return the index of the vertex with the minimum distance
     */
    protected int minDistance(double[] distances, boolean[] visited) {
        double min = Double.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!visited[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }


    // Método para obter os vizinhos de um vértice
    public UnorderedArrayList<T> getVizinhos(T vertex) {
        UnorderedArrayList<T> vizinhos = new UnorderedArrayList<>();
        int index = getIndex(vertex);
        if (!indexIsValid(index)) {
            return vizinhos; // Retorna uma lista vazia se o índice não for válido
        }
        for (int i = 0; i < numVertices; i++) {
            if (weightMatrix[index][i] > 0 && weightMatrix[index][i] != Double.POSITIVE_INFINITY) {
                vizinhos.addToRear((T) vertices[i]);
            }
        }
        return vizinhos;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Grafo:\n");
        for (int i = 0; i < numVertices; i++) {
            DivisaoImp divisao = (DivisaoImp) vertices[i];
            sb.append(divisao.getNome()).append(": [");
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j]) {
                    sb.append(vertices[j]).append(", ");
                }
            }
            sb.append("] Inimigos: ").append(divisao.getInimigos().size()).append("\n");
        }
        return sb.toString();
    }

}