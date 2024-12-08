package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import java.util.Scanner;

public class EdificioImp<T> extends Network<T> implements Edificio {

    private Network<Divisao> network;

    public EdificioImp() {
        this.network = new Network<>();
    }

    @Override
    public void adicionarDivisao(Divisao divisao) {
        network.addVertex(divisao);
    }

    public void adicionarLigacao(String origem, String destino) {
        Divisao divisaoOrigem = obterDivisaoPorNome(origem);
        Divisao divisaoDestino = obterDivisaoPorNome(destino);

        if (divisaoOrigem == null || divisaoDestino == null) {
            System.out.println("Erro ao adicionar ligação: divisões não encontradas.");
            return;
        }
        int peso = 0;
        // Calcular o peso baseado nos inimigos presentes nas divisões
        for (InimigoImp inimigo : divisaoOrigem.getInimigos()) {
            peso += inimigo.getPoder();
        }
        for (InimigoImp inimigo : divisaoDestino.getInimigos()) {
            peso += inimigo.getPoder();
        }
        // Adicionar ligação bidirecional
        network.addEdge(divisaoOrigem, divisaoDestino, peso);
        network.addEdge(divisaoDestino, divisaoOrigem, peso);
    }

    public boolean verificarLigacao(Divisao divisao1, Divisao divisao2) {
        UnorderedArrayList<Divisao> vizinhos = getVizinhosDivisao(divisao1);
        return vizinhos.contains(divisao2);
    }

    @Override
    public Divisao obterDivisaoPorNome(String nome) {
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            if (divisao.getNome().equals(nome)) {
                return divisao;
            }
        }
        return null;
    }

    @Override
    public void exibirDivisoes() {
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            System.out.println(divisao);
        }
    }

    public void exibirLigacoes() {
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            System.out.println("Divisão: " + divisao.getNome() + " está conectada com:");

            UnorderedArrayList<Divisao> vizinhos = getVizinhosDivisao(divisao);
            for (int j = 0; j < vizinhos.size(); j++) {
                Divisao vizinho = vizinhos.getIndex(j);
                double peso = network.getWeight(divisao, vizinho); // Obter o peso da ligação
                System.out.println("  -> " + vizinho.getNome() + " (Peso: " + peso + ")");
            }
        }
    }

    public Divisao escolherDivisoesEntradaSaida() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Divisões que são entradas/saídas:");
        UnorderedArrayList<Divisao> entradasSaidas = new UnorderedArrayList<>();
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            if (divisao.isEntradaSaida()) {
                entradasSaidas.addToRear(divisao);
                System.out.println(entradasSaidas.size() + ". " + divisao.getNome());
            }
        }

        if (entradasSaidas.isEmpty()) {
            System.out.println("Não há divisões de entrada disponíveis.");
            return null;
        }

        System.out.print("Escolha o número da divisão que deseja ir: ");
        int escolha = scanner.nextInt();

        if (escolha < 1 || escolha > entradasSaidas.size()) {
            System.out.println("Escolha inválida. Tente novamente.");
            return null;
        }

        Divisao divisaoEscolhida = entradasSaidas.getIndex(escolha - 1);
        System.out.println("Escolheu ir para a divisão: " + divisaoEscolhida.getNome());

        return divisaoEscolhida;
    }

    public Network<Divisao> getNetwork() {
        return network;
    }

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

    public UnorderedArrayList<Divisao> getVizinhosDivisao(Divisao divisao) {
        UnorderedArrayList<T> vizinhosGenericos = getVizinhos((T) divisao);
        UnorderedArrayList<Divisao> vizinhosDivisoes = new UnorderedArrayList<>();

        for (T vizinho : vizinhosGenericos) {
            if (vizinho instanceof Divisao) {
                vizinhosDivisoes.addToRear((Divisao) vizinho);
            }
        }

        return vizinhosDivisoes;
    }

    public boolean vizinhosMapa(T vertex1, T vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (!indexIsValid(index1) || !indexIsValid(index2)) {
            return false;
        }

        return adjMatrix[index1][index2];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n===== Mapa do Edifício ===== \n");
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            sb.append("- ").append(divisao.getNome()).append("\n");
        }
        return sb.toString();
    }

}
