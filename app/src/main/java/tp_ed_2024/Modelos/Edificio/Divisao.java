package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.AlvoImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public class Divisao {

    private String nome;
    private UnorderedArrayList<InimigoImp> inimigos;
    private UnorderedArrayList<Item> itens;
    private boolean isEntradaSaida;
    private AlvoImp alvo;
    private boolean flagAlvo;

    public Divisao(String nome) {
        this.nome = nome;
        this.isEntradaSaida = false;
        this.inimigos = new UnorderedArrayList<>();
        this.itens = new UnorderedArrayList<>();
        this.flagAlvo = false;
    }

    public boolean isEntradaSaida() {
        return isEntradaSaida;
    }

    public void setEntradaSaida(boolean entradaSaida) {
        isEntradaSaida = entradaSaida;
    }

    public AlvoImp getAlvo() {
        return alvo;
    }

    public boolean temAlvo() {
        return flagAlvo;
    }

    public void adicionarAlvo(AlvoImp alvo) {
        this.alvo = alvo;
    }

    public String getNome() {
        return nome;
    }

    public UnorderedArrayList<InimigoImp> getInimigos() {
        return inimigos;
    }

    public boolean temInimigos() {
        return inimigos != null && !inimigos.isEmpty();
    }

    public void adicionarInimigo(InimigoImp inimigo) {
        inimigos.addToRear(inimigo);
    }

    public void removerInimigo(InimigoImp inimigo) {
        if (!inimigos.isEmpty()) {
            inimigos.remove(inimigo);
        }

    }

    public UnorderedArrayList<Item> getItens() {
        return itens;
    }

    public void adicionarItem(Item item) {
        itens.addToRear(item);
    }

    public boolean isFlagAlvo() {
        return flagAlvo;
    }

    public void setFlagAlvo(boolean flagAlvo) {
        this.flagAlvo = flagAlvo;
    }

    public void ativarFlagAlvo() {
        this.flagAlvo = true;
    }

    public void desativarFlagAlvo() {
        this.flagAlvo = false;
    }

    @Override
    public String toString() {
        return nome + " (Inimigos: " + inimigos.size() + ", Entrada/Saída: " + isEntradaSaida + ", Alvo: "
                + (alvo != null ? alvo : "Nenhum") + ")";
    }
}
