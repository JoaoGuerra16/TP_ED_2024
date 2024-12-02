package tp_ed_2024.Personagens;

import tp_ed_2024.Enums.TipoAlvoEnum;
import tp_ed_2024.Personagens.Personagens_Interfaces.AlvoImp;

public class Alvo implements AlvoImp {

    private String divisao;
    private TipoAlvoEnum tipoAlvo;

    public Alvo(String divisao, TipoAlvoEnum tipoAlvo) {
        this.divisao = divisao; // Ver a situacao das divisoes, nao deviamos relaciona-las com numeros?
        this.tipoAlvo = tipoAlvo;
    }

    @Override
    public String getDivisao() {
        return divisao;
    }

    @Override
    public void setDivisao(String divisao) {
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
                "divisao='" + divisao + '\'' +
                "tipo='" + tipoAlvo +
                '}';
    }

}
