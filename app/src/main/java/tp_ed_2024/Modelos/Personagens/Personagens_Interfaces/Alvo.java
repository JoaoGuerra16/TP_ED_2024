package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;

public interface Alvo {


    TipoAlvoEnum getTipoAlvo();

    void setipoAlvo(TipoAlvoEnum tipoAlvoEnum);
}