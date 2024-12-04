package tp_ed_2024.Modelos.Edificio;


import tp_ed_2024.Collections.Graphs.Network;
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

    @Override
    public void adicionarInimigoNaDivisao(String nomeDivisao, Inimigo inimigo) {
        Divisao divisao = obterDivisaoPorNome(nomeDivisao);
        if (divisao != null) {
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
            System.out.println(divisao.getNome() + " (Inimigos: " + divisao.getNumeroDeInimigos() + ")");
        }
    }

    public Network<Divisao> getNetwork() {
        return network;
    }
}
