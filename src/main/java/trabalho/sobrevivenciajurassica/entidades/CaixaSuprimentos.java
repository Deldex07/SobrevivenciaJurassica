package trabalho.sobrevivenciajurassica.entidades;
import trabalho.sobrevivenciajurassica.itens.*;

public class CaixaSuprimentos extends ElementoMapa {
    ConteudoCaixa conteudo;

    public CaixaSuprimentos(ConteudoCaixa conteudo, int linha, int coluna, char simbolo) {
        super(linha, coluna, simbolo);
        this.conteudo = conteudo;
    }

    public void abrir(Personagem jogador){
        switch (conteudo) {
            case KIT_MEDICO:
                KitMedico kit = new KitMedico();
                jogador.getInventario().adicionarItem(kit);
                break;
            case BASTAO_ELETRICO:
                BastaoEletrico bastao = new BastaoEletrico();
                jogador.getInventario().adicionarItem(bastao);
                break;
            case DARDOS:
                Dardos dardos = new Dardos(1);
                jogador.getInventario().adicionarItem(dardos);
                break;
            case COMPSOGNATO:
                //combate com compsognato
                break;
        }
    }
}