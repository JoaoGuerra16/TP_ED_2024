package tp_ed_2024.Modelos.Edificio;


import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.Inimigo;

public class Edificio implements EdificioImp {

    private Network<Divisao> network;

    public Edificio() {
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

        // Inicializar o peso como 0
        int peso = 0;

        // Calcular o peso baseado nos inimigos presentes nas divisões
        for (Inimigo inimigo : divisaoOrigem.getInimigos()) {
            peso += inimigo.getPoder();
        }
        for (Inimigo inimigo : divisaoDestino.getInimigos()) {
            peso += inimigo.getPoder();
        }
        // Adicionar a aresta no grafo bidirecionalmente
        network.addEdge(divisaoOrigem, divisaoDestino, peso);
        network.addEdge(divisaoDestino, divisaoOrigem, peso);
    }

    public void adicionarItensNaDivisao(String nomeDivisao, Item item){
        Divisao divisao = obterDivisaoPorNome(nomeDivisao);
        if (divisao != null) {
            item.setDivisao(divisao);
            divisao.adicionarItem(item);
        } else {
            System.out.println("Divisão " + nomeDivisao + " não encontrada.");
        }
    }


    @Override
    public void adicionarInimigoNaDivisao(String nomeDivisao, Inimigo inimigo) {
        Divisao divisao = obterDivisaoPorNome(nomeDivisao);
        if (divisao != null) {
            inimigo.setDivisaoAtual(divisao);
            divisao.adicionarInimigo(inimigo);
        } else {
            System.out.println("Divisão " + nomeDivisao + " não encontrada.");
        }
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

            UnorderedArrayList<Divisao> vizinhos = network.getVizinhos(divisao);
            for (int j = 0; j < vizinhos.size(); j++) {
                Divisao vizinho = vizinhos.getIndex(j);
                double peso = network.getWeight(divisao, vizinho); // Obter o peso da ligação
                System.out.println("  -> " + vizinho.getNome() + " (Peso: " + peso + ")");
            }
        }
    }


    public Network<Divisao> getNetwork() {
        return network;
    }
}
