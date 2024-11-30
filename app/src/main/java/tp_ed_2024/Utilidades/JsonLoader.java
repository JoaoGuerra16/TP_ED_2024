package tp_ed_2024.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tp_ed_2024.Collections.Ficha12.GraphMatrix;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {

    private GraphMatrix<String> grafo;

    public JsonLoader() {
        this.grafo = new GraphMatrix<>();
    }

    // Método para carregar o JSON e criar o grafo
    public void loadFromJson(String jsonPath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            // Parse do arquivo JSON
            JSONObject json = (JSONObject) parser.parse(reader);

            // Carregar os vértices (divisões)
            JSONArray edificio = (JSONArray) json.get("edificio");
            for (Object divisao : edificio) {
                grafo.addVertex((String) divisao); // Chama o método da classe GraphMatrix
            }

            // Carregar as arestas (ligações)
            JSONArray ligacoes = (JSONArray) json.get("ligacoes");
            for (Object ligacao : ligacoes) {
                JSONArray conexao = (JSONArray) ligacao;
                String origem = (String) conexao.get(0);
                String destino = (String) conexao.get(1);
                grafo.addEdge(origem, destino); // Chama o método da classe GraphMatrix
            }

            // Exibir as informações carregadas
            System.out.println("Grafo carregado do JSON:");
            System.out.println(grafo.toString());

        } catch (IOException | ParseException e) {
            System.err.println("Erro ao carregar o JSON: " + e.getMessage());
        }
    }

    // Retorna o grafo
    public GraphMatrix<String> getGrafo() {
        return grafo;
    }
}
