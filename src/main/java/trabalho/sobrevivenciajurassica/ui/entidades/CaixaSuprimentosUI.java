package trabalho.sobrevivenciajurassica.ui.entidades;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.CaixaSuprimentos;
import trabalho.sobrevivenciajurassica.itens.ConteudoCaixa;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class CaixaSuprimentosUI extends CaixaSuprimentos implements PossuiImagem{
    private final Image imagem;

    public CaixaSuprimentosUI(ConteudoCaixa conteudo, int linha, int coluna, char simbolo) throws IOException {
        super(conteudo, linha, coluna, simbolo);
        this.imagem = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/Caixa.png");
    }

    @Override
    public Image getImagem() {
        return imagem;
    }
}
