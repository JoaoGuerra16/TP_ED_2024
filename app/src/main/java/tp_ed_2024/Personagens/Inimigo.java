package tp_ed_2024.Personagens;

import tp_ed_2024.Personagens.Personagens_Interfaces.PersonagemADT;

public class Inimigo implements PersonagemADT {

    private String nome;
    private int vida;
    private int poder;
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
    public void atacar(PersonagemADT inimigo) {

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
    public void moverPara(String novaDivisao) {
        divisaoAtual = novaDivisao;
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

}
