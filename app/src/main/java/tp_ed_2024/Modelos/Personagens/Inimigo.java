package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.PersonagemImp;

import java.util.Random;

public class Inimigo implements PersonagemImp {

    private String nome;
    private int vida;
    private int poder;
    private Random random = new Random();
    private String divisaoAtual;

    public Inimigo(String nome, int vida, int poder, String divisaoAtual) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;
        this.divisaoAtual = divisaoAtual;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public int getPoder() {
        return poder;
    }

    @Override
    public void atacar(PersonagemImp inimigo) {

        if (inimigo instanceof Hero) {
            int dano = this.poder;
            int vidaAntes = inimigo.getVida();
            int vidaDepois = Math.max(vidaAntes - dano, 0);

            inimigo.setVida(vidaDepois);
            System.out.println(nome + " atacou " + inimigo.getNome() + " causando " + dano + " de dano.");
            System.out.println(inimigo.getNome() + " agora tem " + inimigo.getVida() + " pontos de vida.");
        }
    }

    @Override
    public String getDivisaoAtual() {
        return divisaoAtual;
    }


    @Override
    public void setDivisao(String novaDivisao) {
        this.divisaoAtual = novaDivisao;
    }


    @Override
    public String toString() {
        return "Inimigo{" +
                "nome='" + nome + '\'' +
                ", vida=" + vida +
                ", poder=" + poder +
                ", divisaoAtual='" + divisaoAtual + '\'' +
                '}';
    }


    public void moverInimigoAleatorio(Network<String> network) {
        // Obtém os vizinhos da divisão atual
        UnorderedArrayList<String> vizinhos = network.getNeighbors(divisaoAtual);

        // Se o inimigo tem vizinhos, ele pode se mover
        if (vizinhos.size() > 0) {
            Random random = new Random();

            // Mover uma vez (aleatoriamente)
            String novoDestino = vizinhos.get(random.nextInt(vizinhos.size()));
            divisaoAtual = novoDestino;
            System.out.println(nome + " se moveu para a divisão " + divisaoAtual);

            // Tentativa de mover mais uma vez, se ele tiver mais de uma divisão para ir
            if (random.nextBoolean() && !vizinhos.isEmpty()) {
                vizinhos = network.getNeighbors(divisaoAtual); // Atualiza os vizinhos da nova divisão
                novoDestino = vizinhos.get(random.nextInt(vizinhos.size()));
                divisaoAtual = novoDestino;
                System.out.println(nome + " se moveu novamente para a divisão " + divisaoAtual);
            }
        } else {
            System.out.println(nome + " não tem divisões vizinhas para se mover.");
        }
    }
}



