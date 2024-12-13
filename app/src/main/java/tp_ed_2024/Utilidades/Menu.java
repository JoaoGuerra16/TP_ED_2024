package tp_ed_2024.Utilidades;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Recursos.ConsoleColors;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    public String escolherJson(String[] jsonFiles) {
        System.out.println(ConsoleColors.BLUE + "Escolha uma missão para simular:" + ConsoleColors.RESET);
        for (int i = 0; i < jsonFiles.length; i++) {
            System.out.println((i + 1) + ". " + jsonFiles[i]);
        }

        int escolha;
        do {
            System.out.print(ConsoleColors.BLUE + "Digite o número da sua escolha (1-" + jsonFiles.length + "): "+ ConsoleColors.RESET);
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, insira um número válido: ");
                scanner.next(); // Limpar a entrada inválida
            }
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > jsonFiles.length);

        return jsonFiles[escolha - 1];
    }

    public Divisao escolherEntrada(UnorderedArrayList<Divisao> entradasSaidas) {

      
        System.out.println(ConsoleColors.BLUE +"\nEscolha uma entrada para começar:" + ConsoleColors.RESET);
        int index = 1;
        for (Divisao divisao : entradasSaidas) {
            System.out.println(index + ". " + divisao.getNome());
            index++;
        }

        int escolha = -1; // Valor inicial para indicar que a escolha é inválida
        while (escolha < 1 || escolha > entradasSaidas.size()) {
            System.out.print(ConsoleColors.BLUE  + "Digite o número da missão que deseja simular: " + ConsoleColors.RESET );

            // Verifica se a entrada é um número válido
            while (!scanner.hasNextInt()) {
                System.out.print(ConsoleColors.RED + "Entrada inválida! Por favor, insira um número válido: " + ConsoleColors.RESET);
                scanner.next(); // Limpa a entrada inválida
            }

            escolha = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha

            if (escolha < 1 || escolha > entradasSaidas.size()) {
                System.err.println(ConsoleColors.RED+
                        "Escolha inválida! Por favor, escolha um número entre 1 e " + entradasSaidas.size() + "."+ ConsoleColors.RESET);
            }
        }

        return entradasSaidas.getIndex(escolha - 1);
    }

}
