package tp_ed_2024.Modelos.Items;

import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;

public class Item implements ItemImp {

    private TipoItemEnum tipo; // Alterado para usar a enum
    private Divisao divisao;
    private int pontos;

    public Item(TipoItemEnum tipo, Divisao divisao, int pontos) {
        this.tipo = tipo;
        this.divisao = divisao;
        this.pontos = pontos;
    }

    @Override
    public TipoItemEnum getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(TipoItemEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public void setDivisao(String divisao) {

    }

    @Override
    public Divisao getDivisao() {
        return divisao;
    }

    public void setDivisao(Divisao divisao) {
        this.divisao = divisao;
    }

    @Override
    public int getPontos() {
        return pontos;
    }

    @Override
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Item{" +
                "tipo=" + tipo +
                ", pontos=" + pontos +
                '}';
    }
}
