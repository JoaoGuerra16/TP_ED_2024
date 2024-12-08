package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.Personagem;

import java.util.Random;

public class InimigoImp implements Personagem {

    private String nome;
    private int vida;
    private int poder;
    private Divisao divisaoAtual;

    public InimigoImp(String nome, int vida, int poder, Divisao divisaoAtual) {
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

    public int getPoder() {
        return poder;
    }

    public String getNome() {
        return nome;
    }

    public Divisao getDivisaoAtual() {
        return divisaoAtual;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = this.poder;
        int vidaAntes = inimigo.getVida();
        int vidaDepois = Math.max(vidaAntes - dano, 0);

        inimigo.setVida(vidaDepois);
        System.out.println(nome + " atacou " + inimigo.getNome() + " causando " + dano + " de dano.");
        System.out.println(inimigo.getNome() + " agora tem " + inimigo.getVida() + " pontos de vida.");
    }

    @Override
    public void setDivisao(Divisao novaDivisao) {
        this.divisaoAtual = novaDivisao;
    }

    public void moverInimigoAleatorio(EdificioImp<Divisao> network, Personagem inimigo) {
        // Obtém os vizinhos da divisão atual
        UnorderedArrayList<Divisao> vizinhos = network.getVizinhos(divisaoAtual);

        // Se o inimigo tem vizinhos, ele pode se mover
        if (vizinhos.size() > 0) {
            Random random = new Random();

            // Mover aleatoriamente para uma das divisões vizinhas
            Divisao novoDestino = vizinhos.getIndex(random.nextInt(vizinhos.size()));
            divisaoAtual = novoDestino;
            System.out.println(nome + " se moveu para a divisão " + divisaoAtual.getNome());
            inimigo.setDivisao(novoDestino);
            // Tenta se mover para outra divisão (ainda dentro de 2 divisões de distância)
            // Vamos adicionar uma segunda "camada" de movimento, se necessário
            if (random.nextBoolean()) {
                // Agora escolhe aleatoriamente entre os vizinhos do novo destino
                UnorderedArrayList<Divisao> vizinhosDoNovoDestino = network.getVizinhos(divisaoAtual);
                if (!vizinhosDoNovoDestino.isEmpty()) {
                    Divisao destinoFinal = vizinhosDoNovoDestino.getIndex(random.nextInt(vizinhosDoNovoDestino.size()));
                    divisaoAtual = destinoFinal;
                    inimigo.setDivisao(destinoFinal);
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
