package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Stacks.ArrayStack;
import tp_ed_2024.Modelos.Items.*;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.PersonagemImp;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.PersonagemPrincipalImp;

public class Hero implements PersonagemPrincipalImp {

    private String nome = "Tó Cruz";
    private int vida;
    private int poder;
    private ArrayStack<ItemImp> mochila;
    private String divisaoAtual;

    public Hero(int vida, int poder, ArrayStack<ItemImp> mochila, String divisaoAtual) {
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
    public void atacar(PersonagemImp inimigo) {
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
    public void setDivisao(String novaDivisao) {
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
    public void aumentarVida(ItemImp item, int pontos) {
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
