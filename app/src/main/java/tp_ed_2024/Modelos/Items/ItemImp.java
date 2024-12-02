package tp_ed_2024.Modelos.Items;

import tp_ed_2024.Enums.TipoItemEnum;

public interface ItemImp {

    TipoItemEnum getTipo(); // Obtém o tipo do item

    void setTipo(TipoItemEnum tipo); // Setar o tipo do item

    void setDivisao(String divisao); // Setar a divisão em que o item está

    String getDivisao(); // Obtém a divisão em que o item se encontra

    int getPontos(); // Obtém os pontos de vida do item

    void setPontos(int pontos); // Setar os pontos de vida que o item dá

}
