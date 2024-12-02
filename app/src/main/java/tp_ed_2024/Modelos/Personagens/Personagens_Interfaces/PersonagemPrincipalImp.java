package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Items.ItemImp;

public interface PersonagemPrincipalImp extends PersonagemImp {

    void adicionarItem(Item item); // Só aplicávem ao Tó Cruz, adiciona items à mochila

    public void aumentarVida(ItemImp item, int pontos); // Aumenta a vida ao To dependendo do item que apanha
}
