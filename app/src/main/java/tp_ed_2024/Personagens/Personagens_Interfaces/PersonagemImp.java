package tp_ed_2024.Personagens.Personagens_Interfaces;

public interface PersonagemImp {
    String getNome(); // Obtém o nome do personagem

    void setNome(String nome); // Setar o nome do personagem

    int getVida(); // Obtém a vida do personagem

    void setVida(int vida); // Setar a vida do personagem

    int getPoder(); // Obtém os pontos de ataque (poder)

    public void atacar(PersonagemImp inimigo) ; // Reduz os pontos de vida

    String getDivisaoAtual(); // Obtém a divisão em que o personagem se encontra

    void moverPara(String novaDivisao); // Move o personagem para outra divisão
}
