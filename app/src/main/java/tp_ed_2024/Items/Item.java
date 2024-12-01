package tp_ed_2024.Items;

import tp_ed_2024.Enums.TipoItemEnum;

public class Item implements ItemADT {

    private TipoItemEnum tipo; // Alterado para usar a enum
    private String divisao;
    private int pontos;

    public Item(TipoItemEnum tipo, String divisao, int pontos) {
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
    public String getDivisao() {
        return divisao;
    }

    @Override
    public void setDivisao(String divisao) {
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
                ", divisao='" + divisao + '\'' +
                ", pontos=" + pontos +
                '}';
    }
}
