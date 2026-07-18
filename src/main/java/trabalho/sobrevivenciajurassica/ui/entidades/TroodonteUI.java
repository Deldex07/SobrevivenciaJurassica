package trabalho.sobrevivenciajurassica.ui.entidades;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Troodonte;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class TroodonteUI extends Troodonte implements PossuiImagem {
    
    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/troodonte.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Troodonte: " + e.getMessage());
        }
    }

    public TroodonteUI(int saudeInicial, int linha, int coluna, char simbolo) {
        super(saudeInicial, linha, coluna, simbolo);
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}
