package trabalho.sobrevivenciajurassica.ui;

import java.awt.Image;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Personagem;

public class PersonagemUI extends Personagem implements PossuiImagem {

    private static Image IMAGEM;

    static {
        try {
            IMAGEM = CarregadorImagem.carregar("/imagens/personagem.png");
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem do Personagem: " + e.getMessage());
        }
    }

    public PersonagemUI(int linha, int coluna, int saude, int percepcao) {
        super(linha, coluna, saude, percepcao);
    }

    @Override
    public Image getImagem() { return IMAGEM; }
}