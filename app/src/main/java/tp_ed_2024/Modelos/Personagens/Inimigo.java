package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;

import java.util.Random;

public class Inimigo {

    private String nome;
    private int vida;
    private int poder;

    private Divisao divisaoAtual;

    public Inimigo(String nome, int vida, int poder, Divisao divisaoAtual) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;
        this.divisaoAtual = divisaoAtual; // Pode estar sendo inicializado como null
    }


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    public void setDivisaoAtual(Divisao divisaoAtual) {
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
        // Obtém os vizinhos da divisão atual
        UnorderedArrayList<Divisao> vizinhos = network.getVizinhos(divisaoAtual);

        // Se o inimigo tem vizinhos, ele pode se mover
        if (vizinhos.size() > 0) {
            Random random = new Random();

            // Mover aleatoriamente para uma das divisões vizinhas
            Divisao novoDestino = vizinhos.getIndex(random.nextInt(vizinhos.size()));
            divisaoAtual = novoDestino;
            System.out.println(nome + " se moveu para a divisão " + divisaoAtual.getNome());

            // Tenta se mover para outra divisão (ainda dentro de 2 divisões de distância)
            // Vamos adicionar uma segunda "camada" de movimento, se necessário
            if (random.nextBoolean()) {
                // Agora escolhe aleatoriamente entre os vizinhos do novo destino
                UnorderedArrayList<Divisao> vizinhosDoNovoDestino = network.getVizinhos(divisaoAtual);
                if (!vizinhosDoNovoDestino.isEmpty()) {
                    Divisao destinoFinal = vizinhosDoNovoDestino.getIndex(random.nextInt(vizinhosDoNovoDestino.size()));
                    divisaoAtual = destinoFinal;
                    System.out.println(nome + " se moveu novamente para a divisão " + divisaoAtual.getNome());
                }
            }
        } else {
            System.out.println(nome + " não tem divisões vizinhas para se mover.");
        }
    }

    @Override
    public String toString() {
        return "Inimigo{" +
                "nome='" + nome + '\'' +
                ", vida=" + vida +
                ", poder=" + poder +
                ", divisaoAtual='" + (divisaoAtual != null ? divisaoAtual.getNome() : "Sem divisão") + '\'' +
                '}';
    }

}
