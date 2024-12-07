package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.Alvo;
import tp_ed_2024.Modelos.Personagens.Inimigo;

public class Divisao {

    private String nome;
    private UnorderedArrayList<Inimigo> inimigos;
    private UnorderedArrayList<Item> itens;
    private boolean isEntradaSaida;
    private Alvo alvo;

    public Divisao(String nome) {
        this.nome = nome;
        this.isEntradaSaida = false;
        this.inimigos = new UnorderedArrayList<>();
        this.itens = new UnorderedArrayList<>();
    }

    public boolean isEntradaSaida() {
        return isEntradaSaida;
    }

    public void setEntradaSaida(boolean entradaSaida) {
        isEntradaSaida = entradaSaida;
    }

    public Alvo getAlvo() {
        return alvo;
    }

    public void adicionarAlvo(Alvo alvo) {
        this.alvo = alvo;
    }

    public String getNome() {
        return nome;
    }

    public UnorderedArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.addToRear(inimigo);
    }

    public void removerInimigo(Inimigo inimigo) {
        inimigos.remove(inimigo);
    }

    public UnorderedArrayList<Item> getItens() {
        return itens;
    }

    public void adicionarItem(Item item) {
        itens.addToRear(item);
    }

    @Override
    public String toString() {
        return nome + " (Inimigos: " + inimigos.size() + ", Entrada/Saída: " + isEntradaSaida + ", Alvo: "
                + (alvo != null ? alvo : "Nenhum") + ")";
    }
}
