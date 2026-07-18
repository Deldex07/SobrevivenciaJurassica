package trabalho.sobrevivenciajurassica.ui.itens;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.itens.BastaoEletrico;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class BastaoEletricoUI extends BastaoEletrico implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/bastao_eletrico.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Bastão Elétrico: " + e.getMessage());
        }
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}