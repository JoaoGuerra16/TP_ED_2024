package tp_ed_2024.Utilidades;

import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Modelos.Edificio.DivisaoImp;

import java.util.Scanner;

/**
 * The type Menu.
 */
public class Menu {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Escolher json string.
     *
     * @param jsonFiles the json files
     * @return the string
     */
    public String escolherJson(String[] jsonFiles) {
        System.out.println("Escolha uma missão para simular:");
        for (int i = 0; i < jsonFiles.length; i++) {
            System.out.println((i + 1) + ". " + jsonFiles[i]);
        }

        int escolha;
        do {
            System.out.print("Digite o número da sua escolha (1-" + jsonFiles.length + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, insira um número válido: ");
                scanner.next();
            }
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > jsonFiles.length);

        return jsonFiles[escolha - 1];
    }

    /**
     * Escolher entrada divisao.
     *
     * @param entradasSaidas the entradas saidas
     * @return the divisao
     */
    public DivisaoImp escolherEntrada(UnorderedArrayList<DivisaoImp> entradasSaidas) {

        System.out.println("Escolha uma entrada para começar:");
        int index = 1;
        for (DivisaoImp divisao : entradasSaidas) {
            System.out.println(index + ". " + divisao.getNome());
            index++;
        }

        int escolha = -1;
        while (escolha < 1 || escolha > entradasSaidas.size()) {
            System.out.print("Digite o número da missão que deseja simular: ");


            while (!scanner.hasNextInt()) {
                System.out.print("Entrada inválida! Por favor, insira um número válido: ");
                scanner.next();
            }

            escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha < 1 || escolha > entradasSaidas.size()) {
                System.err.println(
                        "Escolha inválida! Por favor, escolha um número entre 1 e " + entradasSaidas.size() + ".");
            }
        }

        return entradasSaidas.getIndex(escolha - 1);
    }

    /**
     * Escolher modo string.
     *
     * @return the string
     */
    public String escolherModo() {
        System.out.println("Escolha o modo de simulação:");
        System.out.println("1. Manual");
        System.out.println("2. Automático");

        int escolha;
        do {
            System.out.print("Digite o número da sua escolha (1 ou 2): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, insira um número válido (1 ou 2): ");
                scanner.next();
            }
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 2);

        if (escolha == 1) {
            return "Manual";
        } else {
            return "Automático";
        }

    }

}