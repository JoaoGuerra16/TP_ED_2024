package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;

public class Inimigo_Movimentacao {
    private Network<String> network;
    private UnorderedArrayList<Inimigo> inimigos;

    public Inimigo_Movimentacao(Network<String> network, UnorderedArrayList<Inimigo> inimigos) {
        this.network = network;
        this.inimigos = inimigos;
    }

    // Método para mover um único inimigo
    private void moveInimigo(Inimigo inimigo) {
        String divisaoAtual = inimigo.getDivisaoAtual();
        UnorderedArrayList<String> adjacentes = getAdjacentes(divisaoAtual);

        if (!adjacentes.isEmpty()) {
            // Escolhe a primeira divisão adjacente da lista
            String novaDivisao = adjacentes.remove(0); // Remove o primeiro elemento da lista

            inimigo.setDivisao(novaDivisao);
            System.out.printf("Inimigo %s moveu-se de %s para %s.%n",
                    inimigo.getNome(), divisaoAtual, novaDivisao);
        } else {
            System.out.printf("Inimigo %s está preso em %s (sem divisões adjacentes).%n",
                    inimigo.getNome(), divisaoAtual);
        }
    }

    // Método para obter divisões adjacentes
    private UnorderedArrayList<String> getAdjacentes(String divisao) {
        UnorderedArrayList<String> adjacentes = new UnorderedArrayList<>();
        for (int i = 0; i < network.size(); i++) {
            String vertice = network.getVertex(i);
            if (network.edgeExists(divisao, vertice)) {
                adjacentes.addToRear(vertice);
            }
        }
        return adjacentes;
    }

    // Método para mover todos os inimigos
    public void moveTodosInimigos() {
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = (Inimigo) inimigos.iterator();
            moveInimigo(inimigo);
        }
    }
}