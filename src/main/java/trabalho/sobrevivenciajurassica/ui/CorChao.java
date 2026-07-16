package trabalho.sobrevivenciajurassica.ui;

import java.awt.Color;

/**
 * Classe utilitária responsável por determinar a cor do chão de cada
 * célula do mapa, alternando como um tabuleiro de xadrez (verde claro
 * e verde escuro) com base na posição (linha, coluna).
 */
public final class CorChao {

    private static final Color VERDE_CLARO = new Color(198, 224, 168);
    private static final Color VERDE_ESCURO = new Color(140, 176, 92);

    private CorChao() {} // não instanciável

    public static Color obterCor(int linha, int coluna) {
        boolean casaClara = (linha + coluna) % 2 == 0;
        return casaClara ? VERDE_CLARO : VERDE_ESCURO;
    }
}