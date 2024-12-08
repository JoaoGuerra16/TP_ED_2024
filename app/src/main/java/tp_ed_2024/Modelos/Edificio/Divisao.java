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

    public AlvoImp getAlvo() {
        return alvo;
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

    public void adicionarInimigo(InimigoImp inimigo) {
        inimigos.addToRear(inimigo);
    }

    public void removerInimigo(InimigoImp inimigo) {
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
