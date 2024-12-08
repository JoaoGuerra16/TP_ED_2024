package tp_ed_2024.Modelos.Edificio;


public interface Edificio {

    // Adiciona uma divisão ao edifício
    void adicionarDivisao(Divisao divisao);


    // Obtém uma divisão por nome
    Divisao obterDivisaoPorNome(String nome);

    // Exibe todas as divisões
    void exibirDivisoes();

    void adicionarLigacao(String origem, String destino);


}
