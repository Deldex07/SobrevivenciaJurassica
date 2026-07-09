package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Velociraptor;

public class VelociraptorUI extends Velociraptor implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/Velociraptor.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Velociraptor: " + e.getMessage());
        }
    }

    public VelociraptorUI(int saudeInicial, int linha, int coluna, char simbolo) {
        super(saudeInicial, linha, coluna, simbolo);
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}