package tp_ed_2024.Collections.Graphs;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tp_ed_2024.Collections.Interfaces.*;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Collections.Queues.LinkedQueue;
import tp_ed_2024.Collections.Stacks.LinkedStack;

/**
 * The type Graph.
 *
 * @param <T> the type parameter
 */
public class Graph<T> implements GraphADT<T> {
    /**
     * The Default capacity.
     */
    protected final int DEFAULT_CAPACITY = 100;
    /**
     * The Num vertices.
     */
    protected int numVertices; // number of vertices in the graph
    /**
     * The Adj matrix.
     */
    protected boolean[][] adjMatrix; // adjacency matrix
    /**
     * The Vertices.
     */
    protected T[] vertices; // values of vertices


    /**
     * Instantiates a new Graph.
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }


    /**
     * Add vertex.
     */
    public void addVertex() {
        if (numVertices == vertices.length)
            expandCapacity();
        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;

    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        int index = getIndex(vertex);
        if (!indexIsValid(index)) {
            return;
        }
        for (int i = index; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
        }
        for (int i = index; i < numVertices - 1; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }

        for (int i = index; i < numVertices - 1; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[j][i] = adjMatrix[j][i + 1];
            }
        }
        numVertices--;
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Remove edge.
     *
     * @param index1 the index 1
     * @param index2 the index 2
     */
    public void removeEdge(int index1, int index2) {

        if (indexIsValid(index1) && indexIsValid(index2)) {

            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        if (startIndex == -1)
            return null;

        boolean[] visited = new boolean[numVertices];
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        UnorderedArrayList<T> result = new UnorderedArrayList<>();

        queue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!queue.isEmpty()) {
            int currentIndex = queue.dequeue();
            result.addToRear(vertices[currentIndex]);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[currentIndex][i] && !visited[i]) {
                    queue.enqueue(i);
                    visited[i] = true;
                }
            }

        }
        return result.iterator();
    }

    @Override
    public Iterator iteratorDFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        if (startIndex == -1)
            return null;

        boolean[] visited = new boolean[numVertices];
        LinkedStack<Integer> stack = new LinkedStack<>();
        UnorderedArrayList<T> result = new UnorderedArrayList<>();

        stack.push(startIndex);

        while (!stack.isEmpty()) {
            int currentIndex = stack.pop();
            if (!visited[currentIndex]) {
                result.addToRear(vertices[currentIndex]);
                visited[currentIndex] = true;

                for (int i = numVertices - 1; i > 0; i--) {
                    if (adjMatrix[currentIndex][i] && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return result.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (startIndex == -1 || targetIndex == -1) {
            throw new NoSuchElementException("Vértice não encontrado");
        }

        // Array para marcar os vértices visitados
        boolean[] visited = new boolean[numVertices];
        // Array para armazenar o "pai" de cada vértice, usado para reconstruir o
        // caminho
        int[] parent = new int[numVertices];

        // Fila para BFS
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(startIndex);
        visited[startIndex] = true;
        parent[startIndex] = -1; // O "pai" de startVertex é -1, pois não tem pai

        // Realiza a BFS
        while (!queue.isEmpty()) {
            int currentIndex = queue.dequeue();

            // Se encontramos o vértice alvo, paramos
            if (currentIndex == targetIndex) {
                break;
            }

            // Explora os vizinhos
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[currentIndex][i] && !visited[i]) {
                    queue.enqueue(i);
                    visited[i] = true;
                    parent[i] = currentIndex; // Armazena o vértice anterior para reconstrução do caminho
                }
            }
        }

        // Se não encontramos o vértice alvo
        if (!visited[targetIndex]) {
            throw new NoSuchElementException("Caminho não encontrado");
        }

        // Agora, reconstruímos o caminho a partir do targetVertex
        UnorderedArrayList<T> path = new UnorderedArrayList<>();
        for (int at = targetIndex; at != -1; at = parent[at]) {
            path.addToFront(vertices[at]);
        }

        // Retorna um iterador para o caminho
        return path.iterator();
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    @Override
    public boolean isConnected() {

        if (isEmpty()) {
            return false;
        }
        Iterator<T> iterator = iteratorBFS(vertices[0]);
        int count = 0;

        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count == numVertices;
    }

    @Override
    public int size() {
        return numVertices;
    }

    /**
     * Expand capacity.
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        boolean[][] newAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                newAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = newAdjMatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Adjacency Matrix:\n");

        for (int i = 0; i < numVertices; i++) {
            sb.append(vertices[i]).append(": ");
            for (int j = 0; j < numVertices; j++) {
                sb.append(adjMatrix[i][j] ? "1 " : "0 ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Gets index.
     *
     * @param vertex the vertex
     * @return the index
     */
    public int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Index is valid boolean.
     *
     * @param index the index
     * @return the boolean
     */
    protected boolean indexIsValid(int index) {
        return index >= 0 && index < numVertices;
    }

}