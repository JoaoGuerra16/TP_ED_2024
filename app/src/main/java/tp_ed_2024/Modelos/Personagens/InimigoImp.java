package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Modelos.Edificio.Divisao;

public class InimigoImp {

    private String nome;
    private int vida;
    private int poder;

    private boolean contraAtaqueRealizado;
    private int movimentosRestantes;

    public InimigoImp(String nome, int vida, int poder) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;

        this.movimentosRestantes = 2;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }


    // Método para marcar o contra-ataque como realizado
    public void setContraAtaqueRealizado(boolean realizado) {
        this.contraAtaqueRealizado = realizado;
    }

    // Método para verificar se o contra-ataque foi realizado
    public boolean isContraAtaqueRealizado() {
        return this.contraAtaqueRealizado;
    }

    public int getPoder() {
        return poder;
    }

    public String getNome() {
        return nome;
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
                 '\'' +
                '}';
    }

}
