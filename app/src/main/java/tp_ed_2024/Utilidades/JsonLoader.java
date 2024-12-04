package tp_ed_2024.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Inimigo;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {
    private Network<Divisao> network;

    public JsonLoader() {
        this.network = new Network<>();
    }

    public void loadFromJson(String jsonPath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            JSONObject json = (JSONObject) parser.parse(reader);
            // Carregar divisões
            JSONArray edificio = (JSONArray) json.get("edificio");
            for (Object divisaoNome : edificio) {
                Divisao divisao = new Divisao((String) divisaoNome);
                network.addVertex(divisao);
                System.out.println("Divisão carregada: " + divisao.getNome());
            }

            // Carregar inimigos
            JSONArray inimigosArray = (JSONArray) json.get("inimigos");
            for (Object inimigoObj : inimigosArray) {
                JSONObject inimigoJson = (JSONObject) inimigoObj;
                String nome = (String) inimigoJson.get("nome");
                int poder = ((Long) inimigoJson.get("poder")).intValue();
                String divisaoNome = (String) inimigoJson.get("divisao");

                // Encontrar a divisão e adicionar o inimigo
                Divisao divisao = encontrarDivisaoPorNome(divisaoNome);
                if (divisao != null) {
                    Inimigo inimigo = new Inimigo(nome, poder, poder, divisao);
                    divisao.adicionarInimigo(inimigo);  // Adiciona o inimigo na divisão
                    System.out.println("Inimigo " + inimigo.getNome() + " adicionado na divisão " + divisao.getNome());
                }
            }



            // Carregar ligações entre divisões
            JSONArray ligacoes = (JSONArray) json.get("ligacoes");
            for (Object ligacao : ligacoes) {
                JSONArray conexao = (JSONArray) ligacao;
                String origem = (String) conexao.get(0);
                String destino = (String) conexao.get(1);

                Divisao divisaoOrigem = encontrarDivisaoPorNome(origem);
                Divisao divisaoDestino = encontrarDivisaoPorNome(destino);

                if (divisaoOrigem != null && divisaoDestino != null) {
                    network.addEdge(divisaoOrigem, divisaoDestino, 1.0);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Divisao encontrarDivisaoPorNome(String nome) {
        for (int i = 0; i < network.size(); i++) {
            Divisao divisao = network.getVertex(i);
            if (divisao.getNome().equals(nome)) {
                return divisao;
            }
        }
        return null;
    }

    // Método para obter o grafo
    public Network<Divisao> getGrafo() {
        return network;
    }
}
