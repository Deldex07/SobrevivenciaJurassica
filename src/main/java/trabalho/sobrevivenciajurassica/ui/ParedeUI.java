package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Parede;

public class ParedeUI extends Parede implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/parede.png");
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