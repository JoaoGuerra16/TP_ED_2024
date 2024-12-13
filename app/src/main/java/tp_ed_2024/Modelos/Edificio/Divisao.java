package tp_ed_2024.Modelos.Edificio;

import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.AlvoImp;
import tp_ed_2024.Modelos.Personagens.HeroImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;

public interface Divisao {

    // Retorna o nome da divisão
    String getNome();

    // Adiciona um herói a essa divisão
    void adicionarHeroi(HeroImp hero);

    // Remove o herói da divisão
    void removerHeroi();

    // Verifica se a divisão contém um herói
    boolean temHeroi();

    // Definir se a divisão é uma entrada ou saída
    void setEntradaSaida(boolean entradaSaida);

    // Verifica se a divisão é uma entrada ou saída
    boolean isEntradaSaida();

    // Adiciona um alvo à divisão
    void adicionarAlvo(AlvoImp alvo);

    // Obtém o alvo da divisão
    AlvoImp getAlvo();

    // Adiciona um inimigo à divisão
    void adicionarInimigo(InimigoImp inimigo);

    // Remove um inimigo da divisão
    void removerInimigo(InimigoImp inimigo);

    // Verifica se a divisão tem inimigos
    boolean temInimigos();

    // Obtém a lista de inimigos dessa divisão
    UnorderedArrayList<InimigoImp> getInimigos();

    // Adiciona um item à divisão
    void adicionarItem(Item item);

    // Obtém a lista de itens dessa divisão
    UnorderedArrayList<Item> getItens();

    // Define a flag do alvo
    void setFlagAlvo(boolean flagAlvo);

    // Verifica se a flag do alvo está ativa
    boolean isFlagAlvo();

    // Ativa a flag do alvo
    void ativarFlagAlvo();

    // Desativa a flag do alvo
    void desativarFlagAlvo();

    // Método toString para mostrar informações sobre a divisão
    @Override
    String toString();
}