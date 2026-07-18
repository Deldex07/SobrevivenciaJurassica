package trabalho.sobrevivenciajurassica;

import trabalho.sobrevivenciajurassica.ui.controle.GerenciadorJogoGrafico;

public class SobrevivenciaJurassicaUI {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GerenciadorJogoGrafico jogo = new GerenciadorJogoGrafico();
            jogo.iniciarJogo();
        });
    }
}