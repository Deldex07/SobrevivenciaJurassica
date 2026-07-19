package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.IconesDinossauro;

/**
 * Barra inferior do jogo: vida do jogador à esquerda, inventário
 * centralizado e ícones de alerta (um por tipo de dinossauro
 * atualmente visível na linha de visão) à direita.
 */
public class HudPanel extends JPanel {

    private static final int TAMANHO_ICONE_ALERTA = 40;

    private final JLabel labelVida;
    private final InventarioPanel inventarioPanel;
    private final JPanel painelAlertas;

    public HudPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(0, 100));

        labelVida = new JLabel("Vida: - / -");
        labelVida.setForeground(Color.WHITE);
        labelVida.setFont(labelVida.getFont().deriveFont(Font.BOLD, 18f));
        labelVida.setHorizontalAlignment(SwingConstants.CENTER);
        labelVida.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        inventarioPanel = new InventarioPanel();

        painelAlertas = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        painelAlertas.setOpaque(false);
        painelAlertas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 24));

        add(labelVida, BorderLayout.WEST);
        add(inventarioPanel, BorderLayout.CENTER);
        add(painelAlertas, BorderLayout.EAST);
    }

    public void atualizarVida(int atual, int maxima) {
        labelVida.setText("Vida: " + atual + " / " + maxima);
    }

    public void setJogador(Personagem jogador) {
        inventarioPanel.setJogador(jogador);
    }

    public void atualizarInventario() {
        inventarioPanel.atualizar();
    }

    /**
     * Atualiza os ícones de alerta, exibindo um ícone para cada tipo
     * distinto de dinossauro presente na lista informada. É acumulativo:
     * se houver, por exemplo, um Compsognato e um Velociraptor na linha
     * de visão, os dois ícones aparecem lado a lado.
     */
    public void atualizarAlertas(List<Dinossauro> dinossaurosVisiveis) {
        painelAlertas.removeAll();

        Set<String> tiposJaAdicionados = new HashSet<>();
        for (Dinossauro dino : dinossaurosVisiveis) {
            String chave = IconesDinossauro.chave(dino);
            if (!tiposJaAdicionados.add(chave)) {
                continue;
            }
            Image imagem = IconesDinossauro.obterOlho(dino);
            if (imagem == null) continue;
            Image escalada = imagem.getScaledInstance(TAMANHO_ICONE_ALERTA, TAMANHO_ICONE_ALERTA, Image.SCALE_SMOOTH);
            painelAlertas.add(new JLabel(new ImageIcon(escalada)));
        }

        painelAlertas.revalidate();
        painelAlertas.repaint();
    }
}