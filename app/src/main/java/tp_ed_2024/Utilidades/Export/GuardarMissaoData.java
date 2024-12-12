package tp_ed_2024.Utilidades.Export;

import tp_ed_2024.Collections.Listas.ArrayOrderedList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class GuardarMissaoData {

    private String cod_mission;

    private ArrayOrderedList<String> trajetos; // Lista de divisões visitadas

    private ArrayOrderedList<String> inimigosDerrotados; // Lista de inimigos derrotados

    private int vidaHeroiFinal;

    private boolean alvoResgatado;

    public GuardarMissaoData(String nomeHeroi) {
        this.cod_mission = cod_mission;
        this.trajetos = new ArrayOrderedList<>();
        this.inimigosDerrotados = new ArrayOrderedList<>();
        this.vidaHeroiFinal = 0;
        this.alvoResgatado = false;
    }

    public void adicionarTrajeto(String nomeDivisao) {
        this.trajetos.add(nomeDivisao);
    }

    public void adicionarInimigoDerrotado(String nomeInimigo) {
        this.inimigosDerrotados.add(nomeInimigo);
    }

    public void setVidaHeroiFinal(int vidaHeroiFinal) {
        this.vidaHeroiFinal = vidaHeroiFinal;
    }

    public void setAlvoResgatado(boolean alvoResgatado) {
        this.alvoResgatado = alvoResgatado;
    }

    public String getCod_Mission() {
        return cod_mission;
    }

    public ArrayOrderedList<String> getTrajetos() {
        return trajetos;
    }

    public ArrayOrderedList<String> getInimigosDerrotados() {
        return inimigosDerrotados;
    }

    public int getVidaHeroiFinal() {
        return vidaHeroiFinal;
    }

    public boolean isAlvoResgatado() {
        return alvoResgatado;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cod_mission", cod_mission);

        JSONArray trajetosArray = new JSONArray();
        for (String trajeto : trajetos) {
            trajetosArray.add(trajeto);
        }
        jsonObject.put("trajetos", trajetosArray);

        JSONArray inimigosArray = new JSONArray();
        for (String inimigo : inimigosDerrotados) {
            inimigosArray.add(inimigo);
        }
        jsonObject.put("inimigos_derrotados", inimigosArray);

        jsonObject.put("vida_heroi_final", vidaHeroiFinal);
        jsonObject.put("alvo_resgatado", alvoResgatado);

        return jsonObject;
    }
}
