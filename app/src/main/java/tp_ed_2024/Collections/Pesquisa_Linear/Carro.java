package tp_ed_2024.Collections.Pesquisa_Linear;

public class Carro implements Comparable<Carro> {
    private String marca;
    private String modelo;
    private int ano;
    private double preco;

    public Carro(String marca, String modelo, int ano, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public int compareTo(Carro outro) {
        return this.modelo.compareTo(outro.modelo);
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Carro outroCarro = (Carro) obj;
        return ano == outroCarro.ano &&
               Double.compare(outroCarro.preco, preco) == 0 &&
               marca.equals(outroCarro.marca) &&
               modelo.equals(outroCarro.modelo);
    }

    
    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", preco=" + preco +
        
                '}';
    }
   

}


    