package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Personagens.Inimigo;

public class Divisao {

    private String nome;
    private UnorderedArrayList<Inimigo> inimigos;

    public Divisao(String nome) {
        this.nome = nome;
        this.inimigos = new UnorderedArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public UnorderedArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    // Adiciona um inimigo à divisão
    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.addToRear(inimigo);
    }

    // Remove um inimigo da divisão
    public void removerInimigo(Inimigo inimigo) {
        inimigos.remove(inimigo);
    }

    // Método que retorna o número de inimigos
    public int getNumeroDeInimigos() {
        return inimigos.size();
    }

    @Override
    public String toString() {
        return nome + " (Inimigos: " + getNumeroDeInimigos() + ")";
    }
}
