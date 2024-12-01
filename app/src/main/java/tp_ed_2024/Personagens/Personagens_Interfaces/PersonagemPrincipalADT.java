package tp_ed_2024.Personagens.Personagens_Interfaces;

import tp_ed_2024.Items.Item;
import tp_ed_2024.Items.ItemADT;

public interface PersonagemPrincipalADT extends PersonagemADT {

    void adicionarItem(Item item); // Só aplicávem ao Tó Cruz, adiciona items à mochila

    public void aumentarVida(ItemADT item, int pontos); // Aumenta a vida ao To dependendo do item que apanha
}
