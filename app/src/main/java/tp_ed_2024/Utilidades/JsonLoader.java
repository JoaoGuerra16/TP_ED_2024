package tp_ed_2024.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tp_ed_2024.Collections.Graphs.Network;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Items.*;
import tp_ed_2024.Modelos.Personagens.Alvo;
import tp_ed_2024.Modelos.Personagens.Inimigo;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {

    private Network<String> network;
    private UnorderedArrayList<Inimigo> inimigos; // Não tenho certeza desta estrutura temos de ver
    private UnorderedArrayList<Item> items; // Não tenho certeza desta estrutura temos de ver
    private UnorderedArrayList<Alvo> alvos; // Não tenho certeza desta estrutura temos de ver
    private UnorderedArrayList<String> entradasSaidas;

    public JsonLoader() {
        this.network = new Network<>();
        this.inimigos = new UnorderedArrayList<>();
        this.items = new UnorderedArrayList<>();
        this.alvos = new UnorderedArrayList<>();
        this.entradasSaidas = new UnorderedArrayList<>();
    }

    // Método para carregar o JSON e criar o grafo
    public void loadFromJson(String jsonPath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            // Parse do arquivo JSON
            JSONObject json = (JSONObject) parser.parse(reader);

            String codMissao = (String) json.get("cod-missao");
            int versao = ((Long) json.get("versao")).intValue();

            // Carregar os inimigos (inimigos)
            JSONArray inimigosArray = (JSONArray) json.get("inimigos");
            for (Object inimigoObj : inimigosArray) {
                JSONObject inimigoJson = (JSONObject) inimigoObj;
                String nome = (String) inimigoJson.get("nome");
                int poder = ((Long) inimigoJson.get("poder")).intValue();
                int vida = poder;
                String divisao = (String) inimigoJson.get("divisao");

                Inimigo inimigo = new Inimigo(nome, vida, poder, divisao);
                inimigos.addToRear(inimigo);
            }

            // Carregar os vértices (edificio)
            JSONArray edificio = (JSONArray) json.get("edificio");
            for (Object divisao : edificio) {
                network.addVertex((String) divisao);
            }

            // Carregar as ligações e calcular os pesos
            System.out.println("Pesos carregados:");
            JSONArray ligacoes = (JSONArray) json.get("ligacoes");
            for (Object ligacao : ligacoes) {
                JSONArray conexao = (JSONArray) ligacao;
                String origem = (String) conexao.get(0);
                String destino = (String) conexao.get(1);

                // Inicialize o peso como 0
                double peso = 0;

                // Verifique se há inimigos na divisão de origem e acumule seus poderes
                for (Inimigo inimigo : inimigos) {
                    // Verificar se o inimigo está na divisão de origem
                    if (inimigo.getDivisaoAtual().equals(origem)) {
                        peso += inimigo.getPoder(); // Peso igual ao poder total dos inimigos na origem
                    }
                    // Verificar se o inimigo está na divisão de destino
                    if (inimigo.getDivisaoAtual().equals(destino)) {
                        peso += inimigo.getPoder(); // Peso igual ao poder total dos inimigos na divisão de destino
                    }
                }

                // Adicione a aresta com peso ao grafo (de origem para destino)
                network.addEdge(origem, destino, peso);

                // Adicionar a aresta no sentido contrário (bidirecional), com o mesmo peso
                network.addEdge(destino, origem, peso);
            }

            // Carregar as entradas e saídas
            JSONArray entradasSaidasArray = (JSONArray) json.get("entradas-saidas");
            if (entradasSaidasArray != null) {
                for (Object entradaSaidaObj : entradasSaidasArray) {
                    String entradaSaida = (String) entradaSaidaObj;
                    entradasSaidas.addToRear(entradaSaida);
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
                alvos.addToRear(alvo);
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
                    items.addToFront(kit);
                } else if ("colete".equals(tipo)) {
                    // Verifica se o campo "pontos-extra" existe e não é nulo
                    Long pontosExtraLong = (Long) itemJson.get("pontos-extra");
                    int pontosExtra = (pontosExtraLong != null) ? pontosExtraLong.intValue() : 0;
                    Item colete = new Item(TipoItemEnum.COLETE, divisao, pontosExtra);
                    items.addToRear(colete);
                }
            }

            // Exibir as informações carregadas
            System.out.println("Grafo carregado do JSON:");
            System.out.println(network.toString());

        } catch (IOException | ParseException e) {
            System.err.println("Erro ao carregar o JSON: " + e.getMessage());
        }
    }

    // Retorna o grafo
    public Network<String> getGrafo() {
        return network;
    }

    // Retorna a lista de inimigos
    public UnorderedArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    public UnorderedArrayList<Item> getItems() {
        return items;
    }

    public UnorderedArrayList<Alvo> getAlvos() {
        return alvos;
    }

    public UnorderedArrayList<String> getEntradasSaidas() {
        return entradasSaidas;
    }
}
