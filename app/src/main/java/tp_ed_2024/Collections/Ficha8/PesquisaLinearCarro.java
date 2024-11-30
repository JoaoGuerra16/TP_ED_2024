package tp_ed_2024.Collections.Ficha8;

public class PesquisaLinearCarro {

    public static boolean linearSearch(Carro[] data, int min, int max, Carro target) {
        int index = min;
        boolean found = false;

        while (!found && index <= max) {
            if (data[index].equals(target)) {
                found = true;
            }
            index++;
        }

        return found;
    }
}