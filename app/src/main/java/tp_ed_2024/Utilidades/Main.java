package tp_ed_2024.Utilidades;

import tp_ed_2024.Modelos.Edificio.Edificio;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Hero;
import tp_ed_2024.Modelos.Personagens.Alvo;
import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Utilidades.JsonLoader;

public class Main {
    public static void main(String[] args) {
        // Carregar o edifício e os dados do JSON
        String jsonPath = "app\\src\\main\\java\\tp_ed_2024\\Utilidades\\json.json";
        JsonLoader loader = new JsonLoader(jsonPath);
        Edificio edificio = loader.carregarEdificio();

        // Criar o jogador (Tó Cruz) e colocá-lo na divisão inicial
        Divisao divisaoInicial = edificio.obterDivisaoPorNome("Heliporto"); // Altere para a divisão inicial correta
        if (divisaoInicial == null) {
            System.err.println("Erro: Divisão inicial não encontrada!");
            return;
        }



        Hero heroi = new Hero(100, divisaoInicial);
        System.out.println("\nVida final do " + heroi.getNome() + ": " + heroi.getVida() + " pontos.");
        Divisao novaDivisao = edificio.obterDivisaoPorNome("Escada 6");
        if (novaDivisao != null) {
            heroi.moverParaDivisao(novaDivisao, edificio);
        }

        Divisao novaDivisao2 = edificio.obterDivisaoPorNome("Camaratas");
        if (novaDivisao != null) {
            heroi.moverParaDivisao(novaDivisao2, edificio);
        }

        // Exibir a posição inicial do jogador
        System.out.println(heroi.getNome() + " começou na divisão: " + heroi.getDivisaoAtual().getNome());

        // Testar pegar itens na divisão
        heroi.pegarItemNaDivisao(); // Tenta pegar item na divisão atual


        System.out.println("\nVida final do " + heroi.getNome() + ": " + heroi.getVida() + " pontos.");
        // Mostrar a mochila
        heroi.mostrarMochila();

        // Mostrar a mochila após movimentação
        heroi.mostrarMochila();
    }
}
