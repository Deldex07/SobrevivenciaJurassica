package trabalho.sobrevivenciajurassica.ui.renderizacao;

import java.awt.Graphics;
import javax.swing.JPanel;
import trabalho.sobrevivenciajurassica.logica.Mapa;
import trabalho.sobrevivenciajurassica.entidades.ElementoMapa;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;

public class MapaPanel extends JPanel {

    private Mapa mapa;
    private boolean debug;

    public MapaPanel(Mapa mapa) {
        this.mapa = mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
        repaint();
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mapa == null) {
            return;
        }

        int tamanho = mapa.getTamanho();
        int larguraCelula = getWidth() / tamanho;
        int alturaCelula = getHeight() / tamanho;

        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                int x = coluna * larguraCelula;
                int y = linha * alturaCelula;

                // 1. Pinta o chão
                g.setColor(CorChao.obterCor(linha, coluna));
                g.fillRect(x, y, larguraCelula, alturaCelula);

                // 2. Desenha o elemento da grade (parede, caixa, personagem)
                ElementoMapa elemento = mapa.getElemento(linha, coluna);
                if (elemento instanceof PossuiImagem comImagem) {
                    g.drawImage(comImagem.getImagem(), x, y, larguraCelula, alturaCelula, null);
                }

                // 3. Desenha o dinossauro, só se estiver visível (linha de visão ou debug)
                if (mapa.existeDinossauro(linha, coluna)
                        && mapa.dinossauroVisivel(linha, coluna, debug)) {
                    Dinossauro dino = mapa.getDinossauro(linha, coluna);
                    if (dino instanceof PossuiImagem comImagem) {
                        g.drawImage(comImagem.getImagem(), x, y, larguraCelula, alturaCelula, null);
                    }
                }
            }
        }
    }
}