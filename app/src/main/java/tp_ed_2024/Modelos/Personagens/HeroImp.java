package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Collections.Stacks.ArrayStack;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Items.*;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.Personagem;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.PersonagemPrincipal;

public class HeroImp implements PersonagemPrincipal {

    private String nome = "Tó Cruz";
    private int vida = 100;
    private int poder = 100;
    private ArrayStack<Item> mochila;
    private Divisao divisaoAtual;
    private AlvoImp alvo;

    public HeroImp(Divisao divisaoAtual) {
        this.mochila = new ArrayStack<>(5); // Limite de 5 items
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
    public void atacar(Personagem inimigo) {
        int dano = this.poder;
        int vidaAntes = inimigo.getVida();
        int vidaDepois = Math.max(vidaAntes - dano, 0);

        inimigo.setVida(vidaDepois);
        System.out.println(nome + " atacou " + inimigo.getNome() + " causando " + dano + " de dano.");
        System.out.println(inimigo.getNome() + " agora tem " + inimigo.getVida() + " pontos de vida.");
    }

    @Override
    public Divisao getDivisaoAtual() {
        return divisaoAtual;
    }

    @Override
    public void setDivisao(Divisao novaDivisao) {
        this.divisaoAtual = novaDivisao;
    }

    public AlvoImp getAlvo() {
        return alvo;
    }

    public void pegarAlvo(AlvoImp alvo) {
        this.alvo = alvo;
        alvo.setDivisao(this.divisaoAtual); // O alvo é colocado na divisão atual do herói
        System.out.println(nome + " pegou o alvo: " + alvo);
    }

    // Classe Hero
    public void moverParaDivisao(Divisao novaDivisao, EdificioImp edificio) {
        if (edificio.verificarLigacao(divisaoAtual, novaDivisao)) {
            divisaoAtual = novaDivisao;
            System.out.println("Herói se moveu para: " + novaDivisao.getNome());
            if (alvo != null) {
                alvo.setDivisao(novaDivisao); // O alvo acompanha o herói
                System.out.println("O alvo " + alvo + " agora está na divisão: " + novaDivisao.getNome());
            }
        } else {
            System.out.println("Movimento inválido! Não há ligação entre " + divisaoAtual.getNome() + " e "
                    + novaDivisao.getNome());
        }
    }

    public boolean sairDoEdificio() {
        if (divisaoAtual.isEntradaSaida()) {
            System.out.println(nome + " saiu do edifício pela divisão: " + divisaoAtual.getNome());

            // Verifica se o alvo foi resgatado
            if (alvo != null) {
                System.out.println("Missão concluída com sucesso! Alvo resgatado.");
            } else {
                System.out.println("Missão falhada! O alvo não foi resgatado.");
            }

            return true; // O jogo termina
        } else {
            System.out.println(nome + " não está em uma saída.");
            return false;
        }
    }

    public void pegarItemNaDivisao() {
        if (divisaoAtual.getItens().size() > 0) {
            Item item = divisaoAtual.getItens().removeLast(); // Retira o item da divisão

            if (item.getTipo() == TipoItemEnum.KIT) {
                // Guardar medikits na mochila
                if (mochila.size() < 5) {
                    mochila.push(item);
                    System.out.println(nome + " pegou um medikit: " + item + "PS: Só fracos é que usam kits de cura");
                } else {
                    System.out.println(
                            "A mochila está cheia! Não consegues carregar mais kits, na próxima traz uma mochila maior!");
                }
            } else if (item.getTipo() == TipoItemEnum.COLETE) {
                // Usar coletes imediatamente
                aplicarColete(item);
            }
        } else {
            System.out.println("Não há itens na divisão para pegar.");
        }
    }

    // Mostrar os itens na mochila
    // Mostrar os itens na mochila
    public void mostrarMochila() {
        System.out.println(nome + " tem os seguintes itens na mochila:");
        if (mochila.isEmpty()) {
            System.out.println("A mochila está vazia.");
        } else {
            for (int i = 0; i < mochila.size(); i++) {
                System.out.println("- " + mochila.peek()); // Exibe o topo sem remover
            }
        }
    }

    public void usarMedikit() {
        if (mochila.isEmpty()) {
            System.out.println(
                    "Mochila vazia! Não há medikits para usar. Não precisas deles de qualquer maneira, vai-te a eles!!!");
            return;
        }

        // Recuperar o medikit do topo
        Item medikit = mochila.pop();
        if (medikit.getTipo() == TipoItemEnum.KIT) {
            int pontosRecuperados = medikit.getPontos();
            int vidaAntes = vida;
            vida = Math.min(vida + pontosRecuperados, 100); // Vida máxima de 100

            System.out.println(nome + " usou um medikit e recuperou " + (vida - vidaAntes) + " pontos de vida. ");
            System.out.println("Vida atual: " + vida);
        } else {
            System.out.println("Erro: item no topo da mochila não é um medikit!");
        }
    }

    public void aplicarColete(Item colete) {
        if (colete.getTipo() == TipoItemEnum.COLETE) {
            int pontosExtra = colete.getPontos();
            vida += pontosExtra; // Sem limite de 100 para coletes

            System.out.println(nome + " usou um colete e ganhou " + pontosExtra + " pontos extras de vida.");
            System.out.println("Vida atual: " + vida);
        } else {
            System.out.println("Erro: item não é um colete!");
        }
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
