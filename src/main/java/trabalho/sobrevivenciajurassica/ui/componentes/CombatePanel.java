package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.function.IntConsumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.ui.renderizacao.IconesDinossauro;

/**
 * Tela de combate exibida dentro da própria janela do jogo, no lugar
 * do mapa (sem diálogos ou janelas separadas). Mostra a imagem e a
 * vida do dinossauro inimigo, no estilo de RPGs por turno, e os
 * botões de ação disponíveis para o jogador.
 */
public class CombatePanel extends JPanel {

    private static final int TAMANHO_IMAGEM = 320;

    private final JLabel labelImagem;
    private final JLabel labelNome;
    private final JLabel labelVida;
    private final JButton botaoAcao1;
    private final JButton botaoDardos;
    private final JButton botaoKit;
    private final JButton botaoFugir;

    private IntConsumer callback;

    public CombatePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 25));

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setOpaque(false);

        labelImagem = new JLabel();
        labelImagem.setAlignmentX(CENTER_ALIGNMENT);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

        labelNome = new JLabel();
        labelNome.setAlignmentX(CENTER_ALIGNMENT);
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        labelNome.setForeground(Color.WHITE);
        labelNome.setFont(labelNome.getFont().deriveFont(Font.BOLD, 24f));

        labelVida = new JLabel();
        labelVida.setAlignmentX(CENTER_ALIGNMENT);
        labelVida.setHorizontalAlignment(SwingConstants.CENTER);
        labelVida.setForeground(Color.WHITE);
        labelVida.setFont(labelVida.getFont().deriveFont(Font.BOLD, 18f));

        painelCentro.add(Box.createVerticalGlue());
        painelCentro.add(labelImagem);
        painelCentro.add(labelNome);
        painelCentro.add(labelVida);
        painelCentro.add(Box.createVerticalGlue());

        add(painelCentro, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 16));
        painelBotoes.setOpaque(false);

        botaoAcao1 = new JButton();
        botaoDardos = new JButton("Dardos Tranquilizantes");
        botaoKit = new JButton("Usar Kit Médico");
        botaoFugir = new JButton("Fugir");

        botaoAcao1.addActionListener(e -> disparar(1));
        botaoDardos.addActionListener(e -> disparar(2));
        botaoKit.addActionListener(e -> disparar(3));
        botaoFugir.addActionListener(e -> disparar(4));

        painelBotoes.add(botaoAcao1);
        painelBotoes.add(botaoDardos);
        painelBotoes.add(botaoKit);
        painelBotoes.add(botaoFugir);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void disparar(int codigo) {
        if (callback != null) {
            callback.accept(codigo);
        }
    }

    public void configurar(Dinossauro inimigo, boolean temDardos, boolean temKit,
                            boolean temBastao, IntConsumer callback) {
        this.callback = callback;

        labelNome.setText(nomeExibicao(inimigo));
        labelVida.setText("Vida: " + inimigo.getSaude());

        Image imagem = IconesDinossauro.obterAtacar(inimigo);
        if (imagem != null) {
            Image escalada = imagem.getScaledInstance(TAMANHO_IMAGEM, TAMANHO_IMAGEM, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(escalada));
        } else {
            labelImagem.setIcon(null);
        }

        botaoAcao1.setText(temBastao ? "Bastão Elétrico" : "Soco");
        botaoDardos.setVisible(temDardos);
        botaoKit.setVisible(temKit);
    }

    private String nomeExibicao(Dinossauro inimigo) {
        String chave = IconesDinossauro.chave(inimigo);
        return switch (chave) {
            case "velociraptor" -> "Velociraptor";
            case "troodonte" -> "Troodonte";
            case "compsognato" -> "Compsognato";
            case "tiranossaurorex" -> "Tiranossauro Rex";
            default -> chave;
        };
    }
}