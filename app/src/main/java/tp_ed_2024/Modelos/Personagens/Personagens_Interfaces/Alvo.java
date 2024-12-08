package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;

public interface Alvo {

    Divisao getDivisao(); // Retorna o index que se encontra

    void setDivisao(Divisao divisao); // Setar o index do alvo

    TipoAlvoEnum getTipoAlvo();

    void setipoAlvo(TipoAlvoEnum tipoAlvoEnum);
}