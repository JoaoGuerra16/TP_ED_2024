package tp_ed_2024.Modelos.Items;

import tp_ed_2024.Enums.TipoItemEnum;


public interface ItemImp {

    // Retorna o tipo do item (como KIT, COLETE, etc.)
    TipoItemEnum getTipo();

    // Define o tipo do item
    void setTipo(TipoItemEnum tipo);

    // Retorna os pontos associados ao item (ex: pontos de recuperação, poder)
    int getPontos();

    // Define os pontos do item
    void setPontos(int pontos);

    // Representação do objeto em formato de string
    String toString();
}