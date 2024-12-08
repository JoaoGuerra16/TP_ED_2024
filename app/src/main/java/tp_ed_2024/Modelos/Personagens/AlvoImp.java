package tp_ed_2024.Modelos.Personagens;

import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Modelos.Edificio.Divisao;
import tp_ed_2024.Modelos.Personagens.Personagens_Interfaces.Alvo;

public class AlvoImp implements Alvo {

    private Divisao divisao;
    private TipoAlvoEnum tipoAlvo;

    public AlvoImp(TipoAlvoEnum tipoAlvo) {
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
