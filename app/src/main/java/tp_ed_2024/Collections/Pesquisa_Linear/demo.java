package tp_ed_2024.Collections.Pesquisa_Linear;



public class demo {

    public static void main(String[] args) {
        // Declaração do array de Carro dentro do método main
        Carro[] carros = {
            new Carro("Toyota", "Corolla", 2020, 80000),
            new Carro("Honda", "Civic", 2019, 90000),
            new Carro("Ford", "Mustang", 2021, 300000),
            new Carro("Chevrolet", "Camaro", 2018, 280000),
            new Carro("BMW", "M3", 2022, 350000)
        };

        // Carro alvo que queremos encontrar
        Carro carroProcurado = new Carro("Honda2", "Civic", 2019, 90000);

        // Chamando o método linearSearch da classe PesquisaLinearCarro
        boolean encontrado = PesquisaLinearCarro.linearSearch(carros, 0, carros.length - 1, carroProcurado);
        System.out.println("Carro encontrado? " + encontrado);
    }
}
