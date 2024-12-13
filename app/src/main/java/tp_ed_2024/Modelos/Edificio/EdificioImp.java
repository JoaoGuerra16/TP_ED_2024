package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import tp_ed_2024.Recursos.ConsoleColors;
public class EdificioImp<T> extends Network<Divisao> implements Edificio {

    public EdificioImp(boolean isBidirectional) {
        super();
        this.isBidirectional = isBidirectional;
    }

    @Override
    public void adicionarDivisao(Divisao divisao) {
        addVertex(divisao);
    }

    public void adicionarLigacao(String origem, String destino) {
        Divisao divisaoOrigem = obterDivisaoPorNome(origem);
        Divisao divisaoDestino = obterDivisaoPorNome(destino);

        if (divisaoOrigem == null || divisaoDestino == null) {
            System.out.println("Erro ao adicionar ligação: divisões não encontradas.");
            return;
        }

      int peso = calcularPeso(divisaoOrigem, divisaoDestino);

        System.out.println("Divisao : " + divisaoOrigem.getNome() + " está ligada a " + divisaoDestino.getNome()
                + " com o peso de " + peso);

        addEdge(divisaoOrigem, divisaoDestino, peso);
        addEdge(divisaoDestino, divisaoOrigem, peso);
    }

    public boolean verificarLigacao(Divisao divisao1, Divisao divisao2) {
        UnorderedArrayList<Divisao> vizinhos = getVizinhos(divisao1);
        return vizinhos.contains(divisao2);
    }

    @Override
    public Divisao obterDivisaoPorNome(String nome) {
        for (int i = 0; i < size(); i++) {
            Divisao divisao = getVertex(i);
            if (divisao.getNome().equals(nome)) {
                return divisao;
            }
        }
        return null;
    }

    public UnorderedArrayList<Divisao> obterDivisoes() {
        UnorderedArrayList<Divisao> divisoes = new UnorderedArrayList<>();
        for (int i = 0; i < size(); i++) {
            Divisao divisao = getVertex(i);
            divisoes.addToFront(divisao);
        }
        return divisoes;
    }

    @Override
    public void exibirDivisoes() {
        for (int i = 0; i < size(); i++) {
            Divisao divisao = getVertex(i);
            System.out.println(divisao);
        }
    }

    public void exibirLigacoes() {
        for (int i = 0; i < size(); i++) {
            Divisao divisao = getVertex(i);
            System.out.println("Divisão: " + divisao.getNome() + " está conectada com:");

            UnorderedArrayList<Divisao> vizinhos = getVizinhos(divisao);
            for (int j = 0; j < vizinhos.size(); j++) {
                Divisao vizinho = vizinhos.getIndex(j);
                double peso = getWeight(divisao, vizinho);
                System.out.println("  -> " + vizinho.getNome() + " (Peso: " + peso + ")");
            }
        }
    }

    public UnorderedArrayList<Divisao> getEntradasSaidas() {
        UnorderedArrayList<Divisao> entradasSaidas = new UnorderedArrayList<>();

        for (int i = 0; i < size(); i++) {
            Divisao divisao = getVertex(i);
            if (divisao.isEntradaSaida()) {
                entradasSaidas.addToRear(divisao);
            }
        }

        return entradasSaidas;
    }

    public double[][] obterMatrizAdjacente() {
        int tamanho = size(); // Número de divisões
        double[][] matrizAdjacente = new double[tamanho][tamanho];

        // Inicializar a matriz com valores padrão (ausência de conexão)
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i == j) {
                    matrizAdjacente[i][j] = 0; // Distância para si mesmo é 0
                } else {
                    matrizAdjacente[i][j] = Double.POSITIVE_INFINITY; // Sem conexão
                }
            }
        }

        // Preencher a matriz com os pesos das conexões
        for (int i = 0; i < tamanho; i++) {
            Divisao divisaoOrigem = getVertex(i);
            UnorderedArrayList<Divisao> vizinhos = getVizinhos(divisaoOrigem);

            for (int j = 0; j < vizinhos.size(); j++) {
                Divisao divisaoDestino = vizinhos.getIndex(j);
                int indiceDestino = getIndex(divisaoDestino);

                if (indiceDestino != -1) { // Verificar se o índice do destino é válido
                    matrizAdjacente[i][indiceDestino] = getWeight(divisaoOrigem, divisaoDestino);
                }
            }
        }

        return matrizAdjacente;
    }

    public void resetPeso(EdificioImp<Divisao> edificio, Divisao divisaoAtual) {
        // Obter vizinhos da divisão atual
        UnorderedArrayList<Divisao> vizinhos = edificio.getVizinhos(divisaoAtual);

        // Atualizar pesos das arestas apenas para os vizinhos
        for (int i = 0; i < vizinhos.size(); i++) {
            Divisao vizinho = vizinhos.getIndex(i);

            if (edificio.getWeight(divisaoAtual, vizinho) > 0) {
                // Calcular o novo peso para a aresta entre divisaoAtual e vizinho
                int novoPeso = calcularPeso(divisaoAtual, vizinho);

                // Atualizar o peso da aresta usando o método setEdgeWeight
                edificio.setEdgeWeight(divisaoAtual, vizinho, novoPeso);
                edificio.setEdgeWeight(vizinho, divisaoAtual, novoPeso); // Para grafos bidirecionais
            }
        }
    }


    private int calcularPeso(Divisao divisao1, Divisao divisao2) {
        int pesoBase = 1;  // Peso mínimo por padrão

        // Verificar se há inimigos em cada divisão e somar o poder se houver
        for (InimigoImp inimigo : divisao1.getInimigos()) {
            pesoBase += inimigo.getPoder();
        }
        for (InimigoImp inimigo : divisao2.getInimigos()) {
            pesoBase += inimigo.getPoder();
        }

        // Garantir que o peso não seja menor que 1
        return  pesoBase;  // Peso não pode ser menor que 1
    }



    private void setEdgeWeight(Divisao origem, Divisao destino, int novoPeso) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("As divisões origem e destino não podem ser nulas.");
        }

        int origemIndex = this.getIndex(origem);
        int destinoIndex = this.getIndex(destino);

        // Atualizar o peso na matriz de pesos
        this.weightMatrix[origemIndex][destinoIndex] = novoPeso;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n===== Mapa do Edifício ===== \n");
        for (int i = 0; i < size(); i++) {
            Divisao divisao = getVertex(i);

            sb.append("- ").append(divisao.getNome());

            // Verificar se há itens
            if (divisao.getItens() != null && !divisao.getItens().isEmpty()) {
                sb.append(ConsoleColors.GREEN_BRIGHT +" [Items: " + ConsoleColors.RESET).append(divisao.getItens().size()).append("]");
            }

            // Verificar se há inimigos
            if (divisao.getInimigos() != null && !divisao.getInimigos().isEmpty()) {
                sb.append(ConsoleColors.RED + " [Inimigos: "+ ConsoleColors.RESET).append(divisao.getInimigos().size()).append("]");
            }

            // Verificar se há alvo
            if (divisao.isFlagAlvo()) {
                sb.append(ConsoleColors.YELLOW_BRIGHT +" [Alvo AQUIIII]" + ConsoleColors.RESET);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
