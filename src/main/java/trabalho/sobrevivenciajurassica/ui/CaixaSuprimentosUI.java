package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.CaixaSuprimentos;
import trabalho.sobrevivenciajurassica.itens.ConteudoCaixa;

public class CaixaSuprimentosUI extends CaixaSuprimentos implements PossuiImagem{
    private final Image imagem;

    public CaixaSuprimentosUI(ConteudoCaixa conteudo, int linha, int coluna, char simbolo) throws IOException {
        super(conteudo, linha, coluna, simbolo);
        this.imagem = CarregadorImagem.carregar(caminhoParaConteudo(conteudo));
    }

    private static String caminhoParaConteudo(ConteudoCaixa conteudo) {
        return switch (conteudo) {
            case KIT_MEDICO -> "/imagens/kit_medico.png";
            case DARDOS -> "/imagens/dardos.png";
            case BASTAO_ELETRICO -> "/imagens/bastao_eletrico.png";
            case COMPSOGNATO -> "/imagens/compsognato.png";
        };
    }
    @Override
    public Image getImagem() {
        return imagem;
    }
}
