package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

import tp_ed_2024.Enums.TipoAlvoEnum;

public interface Alvo {


    TipoAlvoEnum getTipoAlvo();

    void setipoAlvo(TipoAlvoEnum tipoAlvoEnum);
}