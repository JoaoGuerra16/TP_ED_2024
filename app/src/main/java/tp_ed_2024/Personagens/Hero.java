package tp_ed_2024.Personagens;

import tp_ed_2024.Collections.Ficha3.ArrayStack;
import tp_ed_2024.Items.*;
import tp_ed_2024.Personagens.Personagens_Interfaces.PersonagemADT;
import tp_ed_2024.Personagens.Personagens_Interfaces.PersonagemPrincipalADT;

public class Hero implements PersonagemPrincipalADT {

    private String nome = "Tó Cruz";
    private int vida;
    private int poder;
    private ArrayStack<ItemADT> mochila;
    private String divisaoAtual;

    public Hero(int vida, int poder, ArrayStack<ItemADT> mochila, String divisaoAtual) {
        this.vida = vida;
        this.poder = poder;
        this.mochila = mochila;
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
        int dano = this.poder;
        int vidaAntes = inimigo.getVida();
        int vidaDepois = Math.max(vidaAntes - dano, 0);

        inimigo.setVida(vidaDepois);
        System.out.println(nome + " atacou " + inimigo.getNome() + " causando " + dano + " de dano.");
        System.out.println(inimigo.getNome() + " agora tem " + inimigo.getVida() + " pontos de vida.");
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
    public void adicionarItem(Item item) {
        if (mochila.size() < 55) {
            mochila.push(item);
            System.out.println("Item adicionado à mochila. \nPS: Só fracos é que usam kits de cura");
        } else {
            System.out.println(
                    "A mochila está cheia! Não consegues carregar mais kits, na próxima traz uma mochila maior!");
        }
    }

    @Override
    public void aumentarVida(ItemADT item, int pontos) {
        if (!mochila.isEmpty()) {
            mochila.pop();
            int pontosCura = item.getPontos();
            vida = Math.min(vida + pontosCura, 100);
            System.out.println(nome + " usou um kit de recuperação e agora tem " + vida
                    + " pontos de vida. É bom que ganhes depois de teres usado um kit");
        } else {
            System.out.println(nome
                    + " não tem kits de recuperação na mochila! Não precisas deles de qualquer maneira, vai-te a eles!!!");
        }
    }

}
