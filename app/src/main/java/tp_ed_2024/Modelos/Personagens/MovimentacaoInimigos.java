import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.Inimigo;

import java.util.Random;

public class MovimentacaoInimigos {

    private Random random = new Random();

    // Método para mover inimigos
    public static void moverInimigoAleatorio(Inimigo inimigo, Network<String> network, Random random) {
        String divisaoAtual = inimigo.getDivisaoAtual();

        // Obter as divisões vizinhas (vizinhos do vértice atual)
        UnorderedArrayList<String> divisaoVizinhas = network.getNeighbors(divisaoAtual);

        // Se houver divisões vizinhas, mover aleatoriamente para uma delas
        if (!divisaoVizinhas.isEmpty()) {
            String novaDivisao = divisaoVizinhas.get(random.nextInt(divisaoVizinhas.size()));
            inimigo.setDivisao(novaDivisao); // Atualiza a divisão do inimigo
            System.out.println(inimigo.getNome() + " se moveu para a divisão " + novaDivisao);
        } else {
            System.out.println(inimigo.getNome() + " não tem divisões vizinhas para se mover.");
        }
    }


    // Método que retorna as divisões a uma distância máxima de 'maxDistancia' a partir da 'divisaoInicial'
    private UnorderedArrayList<String> getDivisoesProximas(String divisaoInicial, Network<String> network, int maxDistancia) {
        // Lista para armazenar as divisões possíveis
        UnorderedArrayList<String> divisaoPossiveis = new UnorderedArrayList<>();

        // Vamos realizar uma busca em largura (BFS) para encontrar as divisões até a distância máxima
        // Guardamos a distância das divisões para controlar até onde podemos ir
        UnorderedArrayList<String> visitados = new UnorderedArrayList<>();
        UnorderedArrayList<String> fila = new UnorderedArrayList<>();
        fila.addToRear(divisaoInicial);
        visitados.addToRear(divisaoInicial);

        // Inicializa o nível de distância
        int distanciaAtual = 0;

        while (!fila.isEmpty() && distanciaAtual < maxDistancia) {
            UnorderedArrayList<String> filaProxima = new UnorderedArrayList<>();

            // Processa todos os nós da fila atual
            for (String divisao : fila) {
                // Obtemos as divisões vizinhas
                UnorderedArrayList<String> vizinhos = network.getNeighbors(divisao);

                for (String vizinho : vizinhos) {
                    // Se a divisão vizinha ainda não foi visitada, adiciona na lista
                    if (!visitados.contains(vizinho)) {
                        visitados.addToRear(vizinho);
                        filaProxima.addToRear(vizinho);
                        if (distanciaAtual + 1 <= maxDistancia) {
                            divisaoPossiveis.addToRear(vizinho);
                        }
                    }
                }
            }

            // Aumenta a distância
            fila = filaProxima;
            distanciaAtual++;
        }

        return divisaoPossiveis;
    }
}
