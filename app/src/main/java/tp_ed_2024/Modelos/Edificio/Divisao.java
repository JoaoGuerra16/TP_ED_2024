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

    public void setAlvo(Alvo alvo) {
        this.alvo = alvo;
    }

    public String getNome() {
        return nome;
    }

    public UnorderedArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    // Adiciona um inimigo à divisão
    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.addToRear(inimigo);
    }

    public UnorderedArrayList<Item> getItens() {
        return itens;
    }

    public void adicionarItem(Item itens) {
        this.itens.addToRear(itens);
    }

    // Remove um inimigo da divisão
    public void removerInimigo(Inimigo inimigo) {
        inimigos.remove(inimigo);
    }

    // Método que retorna o número de inimigos
    public int getNumeroDeInimigos() {
        return inimigos.size();
    }

    @Override
    public String toString() {
        return nome + " (Inimigos: " + getNumeroDeInimigos() + ", Entrada/Saída: " + isEntradaSaida + ", Alvo: "
                + (alvo != null ? alvo : "Nenhum") + ")";
    }
}
