package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Modelos.Edificio.Divisao;

public class InimigoImp {

    private String nome;
    private int vida;
    private int poder;
    private Divisao divisaoAtual;
    private int movimentosRestantes;

    public InimigoImp(String nome, int vida, int poder, Divisao divisaoAtual) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;
        this.divisaoAtual = divisaoAtual;
        this.movimentosRestantes = 2;
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

    public int getPoder() {
        return poder;
    }

    public String getNome() {
        return nome;
    }

    public Divisao getDivisaoAtual() {
        return divisaoAtual;
    }

    public int getMovimentosRestantes() {
        return movimentosRestantes;
    }

    public void decrementarMovimentosRestantes() {
        if (movimentosRestantes > 0) {
            movimentosRestantes--;
        }
    }

    public void resetarMovimentos() {
        this.movimentosRestantes = 2;
    }

    public void atacar(HeroImp hero) {
        int dano = this.poder;
        int vidaAntes = hero.getVida();
        int vidaDepois = Math.max(vidaAntes - dano, 0);

        hero.setVida(vidaDepois);
        System.out.println(nome + " atacou " + hero.getNome() + " causando " + dano + " de dano.");
        System.out.println(hero.getNome() + " agora tem " + hero.getVida() + " pontos de vida.");
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
