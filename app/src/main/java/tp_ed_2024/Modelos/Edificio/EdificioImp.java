package tp_ed_2024.Modelos.Edificio;


import tp_ed_2024.Modelos.Personagens.Alvo;
import tp_ed_2024.Modelos.Personagens.Inimigo;

public interface EdificioImp {

    // Adiciona uma divisão ao edifício
    void adicionarDivisao(Divisao divisao);

    // Adiciona um inimigo a uma divisão específica
    void adicionarInimigoNaDivisao(String nomeDivisao, Inimigo inimigo);

    // Obtém uma divisão por nome
    Divisao obterDivisaoPorNome(String nome);

    // Exibe todas as divisões
    void exibirDivisoes();

    void adicionarLigacao(String origem, String destino);

     void adicionarAlvoNaDivisao(String nomeDivisao, Alvo alvo);
}
