package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Tela inicial do jogo, com as opções "Jogar" e "Sair".
 */
public class PainelMenuPrincipal extends JPanel {

    private final JButton botaoJogar;
    private final JButton botaoSair;

    public PainelMenuPrincipal() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel titulo = new JLabel("SOBREVIVÊNCIA JURÁSSICA", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(28f));
        add(titulo, gbc);

        botaoJogar = new JButton("Jogar");
        botaoSair = new JButton("Sair");
        add(botaoJogar, gbc);
        add(botaoSair, gbc);
    }

    public void aoClicarJogar(Runnable acao) {
        botaoJogar.addActionListener(e -> acao.run());
    }

    public void aoClicarSair(Runnable acao) {
        botaoSair.addActionListener(e -> acao.run());
    }
}