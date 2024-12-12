package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.AlvoImp;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public class Divisao {

    private String nome;
    private UnorderedArrayList<InimigoImp> inimigos;
    private UnorderedArrayList<Item> itens;
    private boolean isEntradaSaida;
    private HeroImp hero;
    private AlvoImp alvo;
    private boolean flagAlvo;

    public Divisao(String nome) {
        this.nome = nome;
        this.hero = null;
        this.isEntradaSaida = false;
        this.inimigos = new UnorderedArrayList<>();
        this.itens = new UnorderedArrayList<>();
        this.flagAlvo = false;
    }

    public boolean isEntradaSaida() {
        return isEntradaSaida;
    }

    public void adicionarHeroi(HeroImp hero) {
        this.hero = hero;
    }

    // Método para remover o herói da divisão
    public void removerHeroi() {
        this.hero = null;
    }

    public void setEntradaSaida(boolean entradaSaida) {
        isEntradaSaida = entradaSaida;
    }

    public AlvoImp getAlvo() {
        return alvo;
    }

    public void adicionarAlvo(AlvoImp alvo) {
        this.alvo = alvo;
    }

    public String getNome() {
        return nome;
    }
    public boolean temHeroi() {
        return this.hero != null;  // Verifica se a divisão tem um herói
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
