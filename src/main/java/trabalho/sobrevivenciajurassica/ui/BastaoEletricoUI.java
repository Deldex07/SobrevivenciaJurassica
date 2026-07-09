package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.itens.BastaoEletrico;

public class BastaoEletricoUI extends BastaoEletrico implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/bastao_eletrico.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Bastão Elétrico: " + e.getMessage());
        }
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}