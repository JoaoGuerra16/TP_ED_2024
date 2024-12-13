package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.AlvoImp;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public class DivisaoImp implements Divisao {

    private String nome;
    private UnorderedArrayList<InimigoImp> inimigos;
    private UnorderedArrayList<Item> itens;
    private boolean isEntradaSaida;
    private HeroImp hero;
    private AlvoImp alvo;
    private boolean flagAlvo;

    public DivisaoImp(String nome) {
        this.nome = nome;
        this.hero = null;
        this.isEntradaSaida = false;
        this.inimigos = new UnorderedArrayList<>();
        this.itens = new UnorderedArrayList<>();
        this.flagAlvo = false;
    }

    @Override
    public boolean isEntradaSaida() {
        return isEntradaSaida;
    }
    @Override
    public void adicionarHeroi(HeroImp hero) {
        this.hero = hero;
    }

    @Override
    public void removerHeroi() {
        this.hero = null;
    }
    @Override
    public void setEntradaSaida(boolean entradaSaida) {
        isEntradaSaida = entradaSaida;
    }
    @Override
    public AlvoImp getAlvo() {
        return alvo;
    }
    @Override
    public void adicionarAlvo(AlvoImp alvo) {
        this.alvo = alvo;
    }
    @Override
    public String getNome() {
        return nome;
    }
    @Override
    public boolean temHeroi() {
        return this.hero != null;
    }
    @Override
    public UnorderedArrayList<InimigoImp> getInimigos() {
        return inimigos;
    }
    @Override
    public boolean temInimigos() {
        return inimigos != null && !inimigos.isEmpty();
    }
    @Override
    public void adicionarInimigo(InimigoImp inimigo) {
        inimigos.addToRear(inimigo);
    }
    @Override
    public void removerInimigo(InimigoImp inimigo) {
        if (!inimigos.isEmpty()) {
            inimigos.remove(inimigo);
        }

    }
    @Override
    public UnorderedArrayList<Item> getItens() {
        return itens;
    }
    @Override
    public void adicionarItem(Item item) {
        itens.addToRear(item);
    }
    @Override
    public boolean isFlagAlvo() {
        return flagAlvo;
    }
    @Override
    public void setFlagAlvo(boolean flagAlvo) {
        this.flagAlvo = flagAlvo;
    }
    @Override
    public void ativarFlagAlvo() {
        this.flagAlvo = true;
    }
    @Override
    public void desativarFlagAlvo() {
        this.flagAlvo = false;
    }

    @Override
    public String toString() {
        return nome + " (Inimigos: " + inimigos.size() + ", Entrada/Saída: " + isEntradaSaida + ", Alvo: "
                + (alvo != null ? alvo : "Nenhum") + ")";
    }
}
