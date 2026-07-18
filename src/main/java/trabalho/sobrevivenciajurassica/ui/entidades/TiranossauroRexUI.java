package trabalho.sobrevivenciajurassica.ui.entidades;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.TiranossauroRex;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class TiranossauroRexUI extends TiranossauroRex implements PossuiImagem {
    
    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/TiranossauroRex.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do TiranossauroRex: " + e.getMessage());
        }
    }

    public TiranossauroRexUI(int saudeInicial, int linha, int coluna, char simbolo) {
        super(saudeInicial, linha, coluna, simbolo);
    }

    @Override
    public Image getImagem() { return IMAGEM; }    
}
