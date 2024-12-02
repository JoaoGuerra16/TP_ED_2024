package tp_ed_2024.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tp_ed_2024.Collections.Graphs.GraphMatrix;
import tp_ed_2024.Collections.Listas.listaLigada;
import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Personagens.Alvo;
import tp_ed_2024.Personagens.Inimigo;
import tp_ed_2024.Items.*;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {

    private GraphMatrix<String> grafo;
    private listaLigada<Inimigo> inimigos; // Não tenho certeza desta estrutura temos de ver
    private listaLigada<Item> items; // Não tenho certeza desta estrutura temos de ver
    private listaLigada<Alvo> alvos; // Não tenho certeza desta estrutura temos de ver
    private listaLigada<String> entradasSaidas;

    public JsonLoader() {
        this.grafo = new GraphMatrix<>();
        this.inimigos = new listaLigada<>();
        this.items = new listaLigada<>();
        this.alvos = new listaLigada<>();
        this.entradasSaidas = new listaLigada<>();
    }

    // Método para carregar o JSON e criar o grafo
    public void loadFromJson(String jsonPath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            // Parse do arquivo JSON
            JSONObject json = (JSONObject) parser.parse(reader);

            String codMissao = (String) json.get("cod-missao");
            int versao = ((Long) json.get("versao")).intValue();

            // Carregar os vértices (edificio)
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

            // Carregar os inimigos (inimigos)
            JSONArray inimigosArray = (JSONArray) json.get("inimigos");
            for (Object inimigoObj : inimigosArray) {
                JSONObject inimigoJson = (JSONObject) inimigoObj;
                String nome = (String) inimigoJson.get("nome");
                int poder = ((Long) inimigoJson.get("poder")).intValue();
                int vida = ((Long) inimigoJson.get("poder")).intValue();
                String divisao = (String) inimigoJson.get("divisao");

                Inimigo inimigo = new Inimigo(nome, vida, poder, divisao);
                inimigos.add(inimigo);
            }

            // Carregar as entradas e saídas
            JSONArray entradasSaidasArray = (JSONArray) json.get("entradas-saidas");
            if (entradasSaidasArray != null) {
                for (Object entradaSaidaObj : entradasSaidasArray) {
                    String entradaSaida = (String) entradaSaidaObj;
                    entradasSaidas.add(entradaSaida);
                }
            }

            // Carregar os alvos
            JSONObject alvoJson = (JSONObject) json.get("alvo");
            if (alvoJson != null) {
                String divisao = (String) alvoJson.get("divisao");
                String tipoAlvoString = (String) alvoJson.get("tipo");

                // Verificar se o tipo é um valor válido da enumeração
                TipoAlvoEnum tipoAlvo;
                try {
                    tipoAlvo = TipoAlvoEnum.valueOf(tipoAlvoString.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // Caso o tipo não seja válido, define como "DESCONHECIDO"
                    tipoAlvo = TipoAlvoEnum.DESCONHECIDO;
                }

                Alvo alvo = new Alvo(divisao, tipoAlvo);
                alvos.add(alvo);
            }

            // Carregar os items
            JSONArray jsonItens = (JSONArray) json.get("itens");
            for (Object obj : jsonItens) {
                JSONObject itemJson = (JSONObject) obj;

                String divisao = (String) itemJson.get("divisao");
                String tipo = (String) itemJson.get("tipo");

                if ("kit de vida".equals(tipo)) {
                    // Verifica se o campo "pontos-recuperados" existe e não é nulo
                    Long pontosRecuperadosLong = (Long) itemJson.get("pontos-recuperados");
                    int pontosRecuperados = (pontosRecuperadosLong != null) ? pontosRecuperadosLong.intValue() : 0;
                    Item kit = new Item(TipoItemEnum.KIT, divisao, pontosRecuperados);
                    items.add(kit);
                } else if ("colete".equals(tipo)) {
                    // Verifica se o campo "pontos-extra" existe e não é nulo
                    Long pontosExtraLong = (Long) itemJson.get("pontos-extra");
                    int pontosExtra = (pontosExtraLong != null) ? pontosExtraLong.intValue() : 0;
                    Item colete = new Item(TipoItemEnum.COLETE, divisao, pontosExtra);
                    items.add(colete);
                }
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

    // Retorna a lista de inimigos
    public listaLigada<Inimigo> getInimigos() {
        return inimigos;
    }

    public listaLigada<Item> getItems() {
        return items;
    }

    public listaLigada<Alvo> getAlvos() {
        return alvos;
    }

    public listaLigada<String> getEntradasSaidas() {
        return entradasSaidas;
    }
}
