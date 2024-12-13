package tp_ed_2024.Modelos.Personagens.Personagens_Interfaces;

import tp_ed_2024.Modelos.Items.Item;
import tp_ed_2024.Modelos.Items.ItemImp;
import tp_ed_2024.Modelos.Personagens.InimigoImp;

public interface PersonagemPrincipal extends Personagem {

 // Método para verificar se o personagem tem um alvo
 boolean isTemAlvo();
 void setNome(String nome);
 void setTemAlvo(boolean temAlvo);
 void ativarFlagAlvo();  // Ativa a flag de que o personagem tem um alvo

 // Métodos específicos do personagem principal (herói), como usar itens ou habilidades especiais
 void usarMedikit();
 void aplicarColete(Item colete);
}