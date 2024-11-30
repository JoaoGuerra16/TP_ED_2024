package tp_ed_2024.Utilidades;



public class Main {
    public static void main(String[] args) {
        // Caminho para o arquivo JSON
        String jsonPath = "missao.json"; // Certifique-se de que o arquivo está no mesmo diretório ou forneça o caminho correto

        // Cria o objeto JsonLoader para carregar o grafo
        JsonLoader loader = new JsonLoader();

        // Carregar o grafo a partir do JSON
        loader.loadFromJson(jsonPath);

        // Grafo será exibido automaticamente na consola dentro do método loadFromJson
    }
}
