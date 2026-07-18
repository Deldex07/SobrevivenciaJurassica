package trabalho.sobrevivenciajurassica.ui.renderizacao;

import java.awt.Image;
/**
 * Interface implementada por todas as versões "UI" das entidades/elementos do jogo.
 * Permite que o código de renderização trate qualquer elemento com imagem
 * de forma genérica, sem precisar conhecer a classe concreta.
 * PossuiImagem
 */
public interface PossuiImagem {
    Image getImagem();
}
