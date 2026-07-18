package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import trabalho.sobrevivenciajurassica.logica.Dificuldade;

/**
 * Tela de configuração da partida: escolha de dificuldade e ativação
 * do modo debug, exibida antes de iniciar o jogo.
 */
public class PainelConfiguracao extends JPanel {

    private final JRadioButton radioFacil;
    private final JRadioButton radioMedio;
    private final JRadioButton radioDificil;
    private final JCheckBox checkDebug;
    private final JButton botaoIniciar;

    public PainelConfiguracao() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        add(new JLabel("Escolha a dificuldade:"), gbc);

        radioFacil = new JRadioButton("Fácil", true);
        radioMedio = new JRadioButton("Médio");
        radioDificil = new JRadioButton("Difícil");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioFacil);
        grupo.add(radioMedio);
        grupo.add(radioDificil);
        add(radioFacil, gbc);
        add(radioMedio, gbc);
        add(radioDificil, gbc);

        checkDebug = new JCheckBox("Ativar modo Debug");
        add(checkDebug, gbc);

        botaoIniciar = new JButton("Iniciar Jogo");
        add(botaoIniciar, gbc);
    }

    public Dificuldade getDificuldadeSelecionada() {
        if (radioMedio.isSelected()) return Dificuldade.MEDIO;
        if (radioDificil.isSelected()) return Dificuldade.DIFICIL;
        return Dificuldade.FACIL;
    }

    public boolean isDebugSelecionado() {
        return checkDebug.isSelected();
    }

    public void aoClicarIniciar(Runnable acao) {
        botaoIniciar.addActionListener(e -> acao.run());
    }
}