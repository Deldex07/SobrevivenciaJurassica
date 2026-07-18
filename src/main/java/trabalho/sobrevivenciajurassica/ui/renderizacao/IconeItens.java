package trabalho.sobrevivenciajurassica.ui.renderizacao;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilitária responsável por carregar e fornecer as imagens dos
 * itens do jogo (armas e kit médico), usadas na barra de inventário.
 * As imagens são associadas por nome, evitando a criação de uma classe
 * *UI dedicada para cada item.
 */
public final class IconeItens {

    private static final Map<String, Image> ICONES = new HashMap<>();

    static {
        carregar("soco", "/trabalho/sobrevivenciajurassica/imagens/soco.png");
        carregar("bastao_eletrico", "/trabalho/sobrevivenciajurassica/imagens/bastao_eletrico.png");
        carregar("dardos", "/trabalho/sobrevivenciajurassica/imagens/dardos.png");
        carregar("kit_medico", "/trabalho/sobrevivenciajurassica/imagens/kit_medico.png");
    }

    private IconeItens() {}

    private static void carregar(String chave, String caminho) {
        try {
            ICONES.put(chave, CarregadorImagem.carregar(caminho));
        } catch (IOException e) {
            System.out.println("Erro ao carregar ícone '" + chave + "': " + e.getMessage());
        }
    }

    public static Image obter(String chave) {
        return ICONES.get(chave);
    }
}