package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public class EdificioImp implements Edificio {

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
        UnorderedArrayList<Divisao> vizinhos = network.getVizinhos(divisao1);
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

    public UnorderedArrayList<Divisao> obterDivisoes() {
        UnorderedArrayList<Divisao> divisoes = new UnorderedArrayList<>();
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            divisoes.addToFront(divisao);
        }
        return divisoes;
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

            UnorderedArrayList<Divisao> vizinhos = network.getVizinhos(divisao);
            for (int j = 0; j < vizinhos.size(); j++) {
                Divisao vizinho = vizinhos.getIndex(j);
                double peso = network.getWeight(divisao, vizinho); // Obter o peso da ligação
                System.out.println("  -> " + vizinho.getNome() + " (Peso: " + peso + ")");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n===== Mapa do Edifício ===== \n");
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);

            sb.append("- ").append(divisao.getNome());

            // Verificar se há itens
            if (divisao.getItens() != null && !divisao.getItens().isEmpty()) {
                sb.append(" [Items: ").append(divisao.getItens().size()).append("]");
            }

            // Verificar se há inimigos
            if (divisao.getInimigos() != null && !divisao.getInimigos().isEmpty()) {
                sb.append(" [Inimigos: ").append(divisao.getInimigos().size()).append("]");
            }

            // Verificar se há alvo
            if (divisao.isFlagAlvo()) {
                sb.append(" [Alvo AQUIIII]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Network<Divisao> getNetwork() {
        return network;
    }
}
