package tp_ed_2024.Edificio;

public interface EdificioImp {
    void adicionarVertice(String nomeDivisao);               // Adiciona uma divisão (vértice) ao grafo
    void adicionarAresta(String origem, String destino);      // Cria uma conexão entre duas divisões
    boolean existeCaminho(String origem, String destino);     // Verifica se existe um caminho entre divisões
            // Retorna o mapa de conexões entre as divisões
}