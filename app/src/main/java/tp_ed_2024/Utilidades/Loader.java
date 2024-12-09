package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.EdificioImp;

public class Loader {
    public EdificioImp carregarEdificio(String jsonPath) {
        JsonLoader loader = new JsonLoader(jsonPath);
        EdificioImp edificio = loader.carregarEdificio();

        if (edificio == null) {
            System.err.println("Erro ao carregar o edifício do JSON.");
        }

        return edificio;
    }
}
