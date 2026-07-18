package trabalho.sobrevivenciajurassica.ui.entidades;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Parede;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class ParedeUI extends Parede implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/parede.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem da Parede: " + e.getMessage());
        }
    }

    public ParedeUI(int linha, int coluna) {
        super(linha, coluna);
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}