package tp_ed_2024.Personagens;

public interface Personagem {
    String getNome();                    // Obtém o nome do personagem
    int getPontosDeVida();               // Obtém os pontos de vida
    void reduzirVida(int dano);          // Reduz os pontos de vida
    String getDivisaoAtual();            // Obtém a divisão em que o personagem se encontra
    void moverPara(String novaDivisao); // Move o personagem para outra divisão
}
