package tp_ed_2024.Modelos.Items;

import tp_ed_2024.Enums.TipoItemEnum;

public class Item implements ItemImp {

    private TipoItemEnum tipo;

    private int pontos;

    public Item(TipoItemEnum tipo, int pontos) {
        this.tipo = tipo;
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
