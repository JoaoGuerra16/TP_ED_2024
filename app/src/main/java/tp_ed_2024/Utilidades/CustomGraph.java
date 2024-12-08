package tp_ed_2024.Utilidades;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;


public class CustomGraph<T> extends Network<T> {

    // Método para obter os vizinhos de um vértice

    public UnorderedArrayList<T> getVizinhos(T vertex) {
        UnorderedArrayList<T> vizinhos = new UnorderedArrayList<>();
        int index = getIndex(vertex);
        if (!indexIsValid(index)) {
            return vizinhos; // REtorna uma lista vazia se não tiver vizinhos
        }
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[index][i]) {
                vizinhos.addToRear(vertices[i]);
            }
        }
        return vizinhos;
    }


}
