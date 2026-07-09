package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.itens.Dardos;

public class DardosUI extends Dardos implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/dardos.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem dos Dardos: " + e.getMessage());
        }
    }

    public DardosUI(int municaoInicial) {
        super(municaoInicial);
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}