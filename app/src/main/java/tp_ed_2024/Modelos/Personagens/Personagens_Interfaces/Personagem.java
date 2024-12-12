package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public interface Personagem {
    String getNome(); // Obtém o nome do personagem

    void setNome(String nome); // Setar o nome do personagem

    int getVida(); // Obtém a vida do personagem

    void setVida(int vida); // Setar a vida do personagem

    int getPoder(); // Obtém os pontos de ataque (poder)

    public void atacar(InimigoImp inimigo) ; // Reduz os pontos de vida


}
