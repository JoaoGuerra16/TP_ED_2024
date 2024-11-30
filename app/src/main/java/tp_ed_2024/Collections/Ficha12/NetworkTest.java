package tp_ed_2024.Collections.Ficha12;

import tp_ed_2024.Collections.Ficha3.LinkedStack;

public class NetworkTest {
    public static void main(String[] args) {
         // Criar um grafo com 6 vértices
        Network<String> network = new Network<>(6);

        // Adicionar vértices
        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");
        network.addVertex("F");

        // Adicionar arestas com pesos
        network.addEdge("A", "B", 2);
        network.addEdge("A", "C", 4);
        network.addEdge("B", "C", 1);
        network.addEdge("B", "D", 7);
        network.addEdge("C", "E", 3);
        network.addEdge("D", "F", 1);
        network.addEdge("E", "D", 2);
        network.addEdge("E", "F", 5);

        // Imprimir a matriz de adjacência para referência
        System.out.println("Matriz de Adjacência do Grafo:");
        System.out.println(network);

        // Encontrar o caminho mais curto de A para F
        LinkedStack<String> shortestPath = network.shortestPath("A", "F");

        // Exibir o caminho mais curto
        System.out.print("Caminho mais curto de A para F: ");
        while (!shortestPath.isEmpty()) {
            System.out.print(shortestPath.pop() + " ");
        }
    }
}
        