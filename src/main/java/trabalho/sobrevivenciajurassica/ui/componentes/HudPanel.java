package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.CarregadorImagem;

/**
 * Barra inferior do jogo: vida do jogador à esquerda, inventário
 * centralizado e alerta de dinossauro próximo à direita.
 */
public class HudPanel extends JPanel {

    private static final int TAMANHO_ICONE_ALERTA = 40;

    private final JLabel labelVida;
    private final InventarioPanel inventarioPanel;
    private final JLabel labelAlerta;

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

        labelAlerta = new JLabel();
        labelAlerta.setHorizontalAlignment(SwingConstants.CENTER);
        labelAlerta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 24));
        labelAlerta.setVisible(false);
        carregarIconeAlerta();

        add(labelVida, BorderLayout.WEST);
        add(inventarioPanel, BorderLayout.CENTER);
        add(labelAlerta, BorderLayout.EAST);
    }

    private void carregarIconeAlerta() {
        try {
            Image imagem = CarregadorImagem.carregar("/trabalho/sobrevivenciajurassica/imagens/compsognato_olho.png");
            Image escalada = imagem.getScaledInstance(TAMANHO_ICONE_ALERTA, TAMANHO_ICONE_ALERTA, Image.SCALE_SMOOTH);
            labelAlerta.setIcon(new ImageIcon(escalada));
        } catch (IOException e) {
            System.out.println("Erro ao carregar ícone de alerta: " + e.getMessage());
        }
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

    public void setAlertaVisivel(boolean visivel) {
        labelAlerta.setVisible(visivel);
    }
}