package tp_ed_2024.Collections.Ficha12;

import java.util.*;

import tp_ed_2024.Collections.Ficha3.LinkedStack;
import tp_ed_2024.Collections.Interfaces.*;
import tp_ed_2024.Collections.Ficha5.UnorderedArrayList;
import tp_ed_2024.Collections.Ficha4.LinkedQueue;


public class Network<T> implements GraphADT<T> {
    private Map<T, Integer> vertexIndexMap;  // Mapeia vértices para índices da matriz
    private T[] vertices;  // Array de vértices
    private int[][] adjacencyMatrix;  // Matriz de adjacências (pesos das arestas)
    private int numVertices;

    // Construtor
    public Network(int maxVertices) {
        vertexIndexMap = new HashMap<>();
        vertices = (T[]) new Object[maxVertices];
        adjacencyMatrix = new int[maxVertices][maxVertices];
        numVertices = 0;
    }

    // Método para adicionar um vértice
    @Override
    public void addVertex(T vertex) {
        if (!vertexIndexMap.containsKey(vertex)) {
            vertices[numVertices] = vertex;
            vertexIndexMap.put(vertex, numVertices);
            numVertices++;
        }
    }

    // Método para remover um vértice
    @Override
    public void removeVertex(T vertex) {
        Integer index = vertexIndexMap.remove(vertex);
        if (index != null) {
            // Remover vértice da matriz
            for (int i = 0; i < numVertices; i++) {
                adjacencyMatrix[index][i] = 0;  // Remove arestas
                adjacencyMatrix[i][index] = 0;  // Remove arestas
            }
            // Ajusta o array de vértices
            System.arraycopy(vertices, index + 1, vertices, index, numVertices - index - 1);
            numVertices--;
        }
    }

    // Método para adicionar uma aresta (com peso)
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(vertex1, vertex2, 1);  // Aresta com peso 1 por padrão
    }

    // Método para adicionar uma aresta com peso
    public void addEdge(T vertex1, T vertex2, int weight) {
        Integer index1 = vertexIndexMap.get(vertex1);
        Integer index2 = vertexIndexMap.get(vertex2);

        if (index1 != null && index2 != null) {
            adjacencyMatrix[index1][index2] = weight;
            adjacencyMatrix[index2][index1] = weight;  // Para grafos não direcionados
        }
    }

    // Método para remover uma aresta
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        Integer index1 = vertexIndexMap.get(vertex1);
        Integer index2 = vertexIndexMap.get(vertex2);

        if (index1 != null && index2 != null) {
            adjacencyMatrix[index1][index2] = 0;  // Remove a aresta
            adjacencyMatrix[index2][index1] = 0;  // Remove a aresta (não direcionado)
        }
    }

    // Método para realizar travessia em largura (BFS)
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        Integer startIndex = vertexIndexMap.get(startVertex);
        if (startIndex == null) {
            throw new NoSuchElementException("Vértice não encontrado");
        }

        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        List<T> result = new ArrayList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();
            result.add(currentVertex);

            Integer currentIndex = vertexIndexMap.get(currentVertex);
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentIndex][i] > 0 && !visited.contains(vertices[i])) {
                    visited.add(vertices[i]);
                    queue.offer(vertices[i]);
                }
            }
        }

        return result.iterator();
    }

    // Método para realizar travessia em profundidade (DFS)
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        Integer startIndex = vertexIndexMap.get(startVertex);
        if (startIndex == null) {
            throw new NoSuchElementException("Vértice não encontrado");
        }

        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        List<T> result = new ArrayList<>();

        visited.add(startVertex);
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            T currentVertex = stack.pop();
            result.add(currentVertex);

            Integer currentIndex = vertexIndexMap.get(currentVertex);
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentIndex][i] > 0 && !visited.contains(vertices[i])) {
                    visited.add(vertices[i]);
                    stack.push(vertices[i]);
                }
            }
        }

        return result.iterator();
    }

    // Método para verificar se o grafo está vazio
    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    // Método para obter o número de vértices
    @Override
    public int size() {
        return numVertices;
    }

    // Método para gerar uma representação em string do grafo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            sb.append(vertices[i]).append(": ");
            for (int j = 0; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    sb.append(vertices[j]).append("(").append(adjacencyMatrix[i][j]).append(") ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Método para verificar se o grafo está conectado (opcional)
    @Override
    public boolean isConnected() {
        if (numVertices == 0) {
            return true; // Um grafo vazio é tecnicamente conectado
        }

        Set<T> visited = new HashSet<>();
        Iterator<T> bfsIterator = iteratorBFS(vertices[0]);
        while (bfsIterator.hasNext()) {
            visited.add(bfsIterator.next());
        }

        return visited.size() == numVertices;
    }


// Algoritmo de Dijkstra para encontrar o caminho mais curto
    public LinkedStack<T> shortestPath(T startVertex, T targetVertex) {
        LinkedStack<T> pathStack = new LinkedStack<>();
        UnorderedArrayList<Integer> distQueue = new UnorderedArrayList<>();

        Integer startIndex = vertexIndexMap.get(startVertex);
        Integer targetIndex = vertexIndexMap.get(targetVertex);

        if (startIndex == null || targetIndex == null) {
            throw new NoSuchElementException("Vértice não encontrado");
        }

        int[] dist = new int[numVertices];
        int[] prev = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        // Inicialização
        for (int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
        }

        dist[startIndex] = 0;

        distQueue.addToRear(startIndex); // Adiciona o vértice de início na fila

        while (!distQueue.isEmpty()) {
            // Encontra o vértice com a menor distância
            int currentIndex = getMinDistanceVertex(dist, visited);
            if (currentIndex == -1) break; // Não há mais vértices para processar

            visited[currentIndex] = true;

            // Relaxação das arestas adjacentes
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentIndex][i] > 0 && !visited[i]) {
                    int newDist = dist[currentIndex] + adjacencyMatrix[currentIndex][i];
                    if (newDist < dist[i]) {
                        dist[i] = newDist;
                        prev[i] = currentIndex;
                        distQueue.addToRear(i); // Adiciona o vértice à fila
                    }
                }
            }
        }

        // Reconstrução do caminho usando a pilha personalizada
        LinkedStack<T> path = new LinkedStack<>();
        for (int at = targetIndex; at != -1; at = prev[at]) {
            path.push(vertices[at]);
        }

        if (path.isEmpty()) {
            return new LinkedStack<>(); // Se não houver caminho, retorna pilha vazia
        }

        return path; // Retorna o caminho de volta (com a ordem correta)
    }

    private int getMinDistanceVertex(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }


    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iteratorShortestPath'");
    }
}
