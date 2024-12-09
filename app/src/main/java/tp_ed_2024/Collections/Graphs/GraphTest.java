package tp_ed_2024.Collections.Graphs;

import java.util.Iterator;

public class GraphTest {
    public static void main(String[] args) {
        // Criar um grafo
        Graph<String> graph = new Graph<>();

        // Adicionar vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Adicionar arestas
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        

        // Imprimir a matriz de adjacências
        System.out.println(graph);

        // Testar BFS
        System.out.println("BFS a partir do vértice A:");
        Iterator<String> bfsIterator = graph.iteratorBFS("A");
        while (bfsIterator.hasNext()) {
            System.out.print(bfsIterator.next() + " ");
        }
        System.out.println();

        // Testar DFS
        System.out.println("DFS a partir do vértice A:");
        Iterator<String> dfsIterator = graph.iteratorDFS("A");
        while (dfsIterator.hasNext()) {
            System.out.print(dfsIterator.next() + " ");
        }
        System.out.println();

        // Verificar se o grafo está conectado
        System.out.println("O grafo está conectado? " + graph.isConnected());

        // Remover um vértice
        System.out.println("Removendo o vértice C...");
        graph.removeVertex("C");
        System.out.println(graph);

        // Verificar conectividade novamente
        System.out.println("O grafo está conectado? " + graph.isConnected());

        // Testar número de vértices
        System.out.println("Número de vértices no grafo: " + graph.size());


          // Testar o caminho mais curto
          System.out.println("Caminho mais curto de A para E:");
          Iterator<String> pathIterator = graph.iteratorShortestPath("A", "E");
          while (pathIterator.hasNext()) {
              System.out.print(pathIterator.next() + " ");
          }
          System.out.println();
      }
  }
