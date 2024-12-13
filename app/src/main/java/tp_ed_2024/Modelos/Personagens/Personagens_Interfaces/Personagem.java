package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

public interface Personagem {

    // Métodos para obter e definir os atributos básicos do personagem
    String getNome();

    int getVida();
    void setVida(int vida);
    int getPoder();

    // Método para ataque de um personagem contra outro
    void atacar(Personagem inimigo);  // Aceita um Personagem, pode ser HeroImp ou InimigoImp
}