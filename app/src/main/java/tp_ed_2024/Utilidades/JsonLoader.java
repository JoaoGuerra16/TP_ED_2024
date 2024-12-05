package tp_ed_2024.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Edificio.Edificio;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.Inimigo;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {
    private Edificio edificio;

    public JsonLoader() {
        this.edificio = new Edificio();
    }

    public void loadFromJson(String jsonPath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            // Carregar divisões
            JSONArray edificioArray = (JSONArray) json.get("edificio");
            for (Object divisaoNome : edificioArray) {
                Divisao divisao = new Divisao((String) divisaoNome);
                edificio.adicionarDivisao(divisao);
                System.out.println("Divisão carregada: " + divisao.getNome());
            }

            // Carregar inimigos
            JSONArray inimigosArray = (JSONArray) json.get("inimigos");
            for (Object inimigoObj : inimigosArray) {
                JSONObject inimigoJson = (JSONObject) inimigoObj;
                String nome = (String) inimigoJson.get("nome");
                int poder = ((Long) inimigoJson.get("poder")).intValue();
                String divisaoNome = (String) inimigoJson.get("divisao");

                // Criar e adicionar o inimigo na divisão
                Inimigo inimigo = new Inimigo(nome, poder, poder, null);
                edificio.adicionarInimigoNaDivisao(divisaoNome, inimigo);
                System.out.println("Inimigo " + nome + " adicionado na divisão " + divisaoNome);
            }

            // Carregar ligaçõe
            JSONArray ligacoes = (JSONArray) json.get("ligacoes");
            for (Object ligacao : ligacoes) {
                JSONArray conexao = (JSONArray) ligacao;
                String origem = (String) conexao.get(0);
                String destino = (String) conexao.get(1);
                    edificio.adicionarLigacao(origem, destino); // Adicionar ligação com peso 1.0
            }


            // Carregar entradas e saídas
            JSONArray entradasSaidasArray = (JSONArray) json.get("entradas-saidas");
            if (entradasSaidasArray != null) {
                for (Object entradaSaidaObj : entradasSaidasArray) {
                    String entradaSaida = (String) entradaSaidaObj;
                    Divisao divisao = edificio.obterDivisaoPorNome(entradaSaida);
                    if (divisao != null) {
                        divisao.setEntradaSaida(true);
                        System.out.println("Divisão marcada como entrada/saída: " + divisao.getNome());
                    }
                }
            }
            // Carregar itens
            JSONArray itensArray = (JSONArray) json.get("itens");
            for (Object itemObj : itensArray) {
                JSONObject itemJson = (JSONObject) itemObj;
                String divisaoNome = (String) itemJson.get("divisao");
                String tipo = (String) itemJson.get("tipo");

                // Criar o item de acordo com o tipo especificado no JSON
                Item item = null;
                if ("kit de vida".equalsIgnoreCase(tipo)) {
                    int pontosRecuperados = ((Long) itemJson.get("pontos-recuperados")).intValue();
                    item = new Item(TipoItemEnum.KIT, null, pontosRecuperados); // Divisão será atribuída depois
                } else if ("colete".equalsIgnoreCase(tipo)) {
                    int pontosExtra = ((Long) itemJson.get("pontos-extra")).intValue();
                    item = new Item(TipoItemEnum.COLETE, null, pontosExtra); // Divisão será atribuída depois
                }

                // Adicionar o item na divisão correspondente
                if (item != null) {
                    edificio.adicionarItensNaDivisao(divisaoNome, item);
                    System.out.println("Item " + tipo + " adicionado na divisão " + divisaoNome);
                }
            }




        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }




    public Edificio getEdificio() {
        return edificio;
    }
}
