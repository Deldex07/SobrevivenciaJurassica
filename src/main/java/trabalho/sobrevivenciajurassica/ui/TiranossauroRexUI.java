package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.TiranossauroRex;

public class TiranossauroRexUI extends TiranossauroRex implements PossuiImagem {
    
    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/rex.png");
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
