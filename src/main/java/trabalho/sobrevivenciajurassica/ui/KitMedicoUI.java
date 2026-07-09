package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.itens.KitMedico;

public class KitMedicoUI extends KitMedico implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/kit_medico.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Kit Médico: " + e.getMessage());
        }
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}