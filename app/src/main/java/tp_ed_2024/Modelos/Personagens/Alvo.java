package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.AlvoImp;

public class Alvo implements AlvoImp {

    private Divisao divisao;
    private TipoAlvoEnum tipoAlvo;

    public Alvo(TipoAlvoEnum tipoAlvo) {
        this.tipoAlvo = tipoAlvo;
    }

    @Override
    public Divisao getDivisao() {
        return divisao;
    }

    @Override
    public void setDivisao(Divisao divisao) {
        this.divisao = divisao;
    }

    @Override
    public TipoAlvoEnum getTipoAlvo() {
        return tipoAlvo;
    }

    @Override
    public void setipoAlvo(TipoAlvoEnum tipoAlvoEnum) {
        this.tipoAlvo = tipoAlvoEnum;
    }

    @Override
    public String toString() {
        return "Alvo{" +
                "tipo='" + tipoAlvo +
                '}' + " Divisao: " + (divisao != null ? divisao.getNome() : "Nenhuma");
    }
}
