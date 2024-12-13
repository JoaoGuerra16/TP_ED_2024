package tp_ed_2024.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Enums.TipoItemEnum;
import tp_ed_2024.Modelos.Edificio.DivisaoImp;
import tp_ed_2024.Modelos.Edificio.EdificioImp;
import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Personagens.AlvoImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {
    private String jsonPath;
    private EdificioImp<DivisaoImp> edificio;

    public JsonLoader(String jsonPath) {
        this.jsonPath = jsonPath;
        this.edificio = new EdificioImp<>(true);
    }

    public EdificioImp<DivisaoImp> carregarEdificio() {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            carregarDivisoes(json);
            carregarInimigos(json);
            carregarLigacoes(json);
            carregarEntradasESaidas(json);
            carregarItens(json);
            carregarAlvo(json);

        } catch (IOException | ParseException e) {
            System.err.println("Erro ao carregar o JSON: " + e.getMessage());
        }

        return edificio;
    }



    private void carregarDivisoes(JSONObject json) {
        JSONArray edificioArray = (JSONArray) json.get("edificio");
        for (Object divisaoNome : edificioArray) {
            DivisaoImp divisao = new DivisaoImp((String) divisaoNome);
            edificio.adicionarDivisao(divisao);
        }
    }

    private void carregarLigacoes(JSONObject json) {
        JSONArray ligacoes = (JSONArray) json.get("ligacoes");
        for (Object ligacao : ligacoes) {
            JSONArray conexao = (JSONArray) ligacao;
            String origem = (String) conexao.get(0);
            String destino = (String) conexao.get(1);

            DivisaoImp divisaoOrigem = edificio.obterDivisaoPorNome(origem);
            DivisaoImp divisaoDestino = edificio.obterDivisaoPorNome(destino);

            if (divisaoOrigem != null && divisaoDestino != null) {
                edificio.adicionarLigacao(origem, destino);
            } else {
                System.err.println("Erro ao adicionar ligação: " + origem + " ou " + destino + " não encontrada.");
            }
        }
    }


    private void carregarInimigos(JSONObject json) {
        JSONArray inimigosArray = (JSONArray) json.get("inimigos");
        for (Object inimigoObj : inimigosArray) {
            JSONObject inimigoJson = (JSONObject) inimigoObj;
            String nome = (String) inimigoJson.get("nome");
            int poder = ((Long) inimigoJson.get("poder")).intValue();
            String divisaoNome = (String) inimigoJson.get("divisao");

            InimigoImp inimigo = new InimigoImp(nome, poder, poder);

            DivisaoImp divisao = edificio.obterDivisaoPorNome(divisaoNome);
            if (divisao != null) {
                divisao.adicionarInimigo(inimigo);
            } else {
                System.err.println(
                        "Erro: divisão " + divisaoNome + " não encontrada. Inimigo " + nome + " não foi adicionado.");
            }
        }
    }


    

    private void carregarEntradasESaidas(JSONObject json) {
        JSONArray entradasSaidasArray = (JSONArray) json.get("entradas-saidas");
        if (entradasSaidasArray != null) {
            for (Object entradaSaidaObj : entradasSaidasArray) {
                String entradaSaida = (String) entradaSaidaObj;
                DivisaoImp divisao = edificio.obterDivisaoPorNome(entradaSaida);
                if (divisao != null) {
                    divisao.setEntradaSaida(true);
                } else {
                    System.err.println("Erro: divisão " + entradaSaida + " não encontrada.");
                }
            }
        }
    }

    private void carregarItens(JSONObject json) {
        JSONArray itensArray = (JSONArray) json.get("itens");
        if (itensArray == null) {
            System.err.println("Erro: A chave 'itens' não foi encontrada no JSON.");
            return;
        }

        for (Object itemObj : itensArray) {
            JSONObject itemJson = (JSONObject) itemObj;
            String divisaoNome = (String) itemJson.get("divisao");
            String tipo = (String) itemJson.get("tipo");

            if (tipo == null || divisaoNome == null) {
                System.err.println("Erro: item com tipo ou divisão ausente. Item ignorado.");
                continue; 
            }

            Item item = null;
            if ("kit de vida".equalsIgnoreCase(tipo)) {
                if (itemJson.containsKey("pontos-recuperados") && itemJson.get("pontos-recuperados") != null) {
                    int pontosRecuperados = ((Long) itemJson.get("pontos-recuperados")).intValue();
                    item = new Item(TipoItemEnum.KIT,  pontosRecuperados);
                } else {
                    System.err.println(
                            "Erro: chave 'pontos-recuperados' não encontrada ou é nula para o item 'kit de vida'.");
                }
            } else if ("colete".equalsIgnoreCase(tipo)) {
                if (itemJson.containsKey("pontos-extra") && itemJson.get("pontos-extra") != null) {
                    int pontosExtra = ((Long) itemJson.get("pontos-extra")).intValue();
                    item = new Item(TipoItemEnum.COLETE,  pontosExtra);
                } else {
                    System.err.println("Erro: chave 'pontos-extra' não encontrada ou é nula para o item 'colete'.");
                }
            }

            if (item != null) {
                DivisaoImp divisao = edificio.obterDivisaoPorNome(divisaoNome);
                if (divisao != null) {
                    divisao.adicionarItem(item);
                } else {
                    System.err.println(
                            "Erro: divisão " + divisaoNome + " não encontrada. Item " + tipo + " não foi adicionado.");
                }
            }
        }
    }

    private void carregarAlvo(JSONObject json) {
        JSONObject alvoJson = (JSONObject) json.get("alvo");
        if (alvoJson != null) {
            String divisaoNome = (String) alvoJson.get("divisao");
            String tipoAlvoString = (String) alvoJson.get("tipo");

            TipoAlvoEnum tipoAlvo;
            try {
                tipoAlvo = TipoAlvoEnum.valueOf(tipoAlvoString.toUpperCase());
            } catch (IllegalArgumentException e) {
                tipoAlvo = TipoAlvoEnum.DESCONHECIDO;
            }

            AlvoImp alvo = new AlvoImp(tipoAlvo);

            DivisaoImp divisao = edificio.obterDivisaoPorNome(divisaoNome);
            if (divisao != null) {
                divisao.adicionarAlvo(alvo);
                divisao.ativarFlagAlvo();
            } else {
                System.err.println("Erro: divisão " + divisaoNome + " não encontrada. Alvo não foi adicionado.");
            }
        }
    }
}
