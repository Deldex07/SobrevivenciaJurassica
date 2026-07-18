package trabalho.sobrevivenciajurassica.ui.itens;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.itens.KitMedico;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.PossuiImagem;

public class KitMedicoUI extends KitMedico implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/kit_medico.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Kit Médico: " + e.getMessage());
        }
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}