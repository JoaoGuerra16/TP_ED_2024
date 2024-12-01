package tp_ed_2024.Personagens.Personagens_Interfaces;

import tp_ed_2024.Enums.TipoAlvoEnum;

public interface AlvoADT {

    String getDivisao(); // Retorna o index que se encontra

    void setDivisao(String divisao); // Setar o index do alvo

    TipoAlvoEnum getTipoAlvo();

    void setipoAlvo(TipoAlvoEnum tipoAlvoEnum);
}