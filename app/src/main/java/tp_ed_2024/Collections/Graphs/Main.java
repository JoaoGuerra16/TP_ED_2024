package tp_ed_2024.Collections.Graphs;

import java.util.Iterator;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;

public class Main {
    public static void main(String[] args) {
        // Criar uma rede bidirecional
        Network<String> network = new Network<>(true);

        // Adicionar vértices
        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");

        // Adicionar arestas com pesos
        network.addEdge("A", "B", 1.0);
        network.addEdge("A", "C", 3.0);
        network.addEdge("B", "C", 1.0);
        network.addEdge("B", "D", 4.0);
        network.addEdge("C", "D", 1.0);
        network.addEdge("D", "E", 2.0);

        // Testar a existência de um vértice
        System.out.println("A rede contém o vértice 'A'? " + network.containsVertex("F"));

        // Testar o peso de uma aresta
        System.out.println("Peso da aresta entre A e B: " + network.getWeight("A", "B"));

        // Encontrar o menor caminho entre dois vértices
        System.out.println("Encontrar o menor caminho entre A e E:");
        UnorderedArrayList<Integer> locationsToAvoid = new UnorderedArrayList<>();
        locationsToAvoid.addToRear(network.getIndex("C"));
     


        Iterator<Integer> pathIterator = network.findShortestPath(
                network.getIndex("A"),
                network.getIndex("E"),
                locationsToAvoid
            
        );

        // Imprimir o caminho encontrado
        System.out.print("Caminho mais curto: ");
        while (pathIterator.hasNext()) {
            int index = pathIterator.next();
            System.out.print(network.getVertex(index) + " ");
        }
        System.out.println();

        // Calcular o peso do menor caminho
        double shortestPathWeight = network.shortestPathWeight("A", "C");
        System.out.println("Peso do menor caminho entre A e E: " + shortestPathWeight);
    }
}
