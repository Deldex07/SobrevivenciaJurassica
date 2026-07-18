package trabalho.sobrevivenciajurassica.ui.entidades;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Velociraptor;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class VelociraptorUI extends Velociraptor implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/velociraptor.png");
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