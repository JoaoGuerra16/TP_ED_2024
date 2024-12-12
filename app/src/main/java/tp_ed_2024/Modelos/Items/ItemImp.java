package tp_ed_2024.Modelos.Items;

import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;

public interface ItemImp {

    TipoItemEnum getTipo(); // Obtém o tipo do item

    void setTipo(TipoItemEnum tipo); // Setar o tipo do item


    int getPontos(); // Obtém os pontos de vida do item

    void setPontos(int pontos); // Setar os pontos de vida que o item dá

}
