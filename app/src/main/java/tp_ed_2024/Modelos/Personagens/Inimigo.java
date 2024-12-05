package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;

import java.util.Random;

public class Inimigo {

    private String nome;
    private int vida;
    private int poder;

    public void setDivisaoAtual(Divisao divisaoAtual) {
        this.divisaoAtual = divisaoAtual;
    }

    private Divisao divisaoAtual;

    public Inimigo(String nome, int vida, int poder, Divisao divisaoAtual) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;
        this.divisaoAtual = divisaoAtual;
    }

    public int getPoder(){
        return  poder;
    }
    public String getNome() {
        return nome;
    }

    public Divisao getDivisaoAtual() {
        return divisaoAtual;
    }

    public void moverInimigoAleatorio(Network<Divisao> network) {
        UnorderedArrayList<Divisao> vizinhos = network.getVizinhos(divisaoAtual);

        if (vizinhos.size() > 0) {
            Random random = new Random();
            Divisao novaDivisao = vizinhos.getIndex(random.nextInt(vizinhos.size()));

            divisaoAtual.removerInimigo(this);
            novaDivisao.adicionarInimigo(this);
            divisaoAtual = novaDivisao;

            System.out.println(nome + " se moveu para " + novaDivisao.getNome());
        } else {
            System.out.println(nome + " não pode se mover.");
        }
    }

    @Override
    public String toString() {
        return "Inimigo{" +
                "nome='" + nome + '\'' +
                ", vida=" + vida +
                ", poder=" + poder +
                ", divisaoAtual='" + divisaoAtual.getNome() + '\'' +
                '}';
    }
}
