package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.MapaPanel;

public class JanelaJogo extends JFrame {

    private static final String CARD_MENU = "menu";
    private static final String CARD_CONFIGURACAO = "configuracao";
    private static final String CARD_JOGO = "jogo";

    private final CardLayout cardLayout;
    private final JPanel painelCartas;

    private final PainelMenuPrincipal painelMenu;
    private final PainelConfiguracao painelConfiguracao;
    private final MapaPanel mapaPanel;
    private final HudPanel hudPanel;

    public JanelaJogo() {
        super("Sobrevivência Jurássica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        painelCartas = new JPanel(cardLayout);

        painelMenu = new PainelMenuPrincipal();
        painelConfiguracao = new PainelConfiguracao();

        JPanel painelJogo = new JPanel(new BorderLayout());
        mapaPanel = new MapaPanel(null);
        hudPanel = new HudPanel();
        painelJogo.add(mapaPanel, BorderLayout.CENTER);
        painelJogo.add(hudPanel, BorderLayout.SOUTH);

        painelCartas.add(painelMenu, CARD_MENU);
        painelCartas.add(painelConfiguracao, CARD_CONFIGURACAO);
        painelCartas.add(painelJogo, CARD_JOGO);

        setContentPane(painelCartas);
        setMinimumSize(new Dimension(700, 700));
        setSize(1280, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    public void mostrarMenu() {
        cardLayout.show(painelCartas, CARD_MENU);
        painelCartas.revalidate();
        painelCartas.repaint();
    }

    public void mostrarConfiguracao() {
        cardLayout.show(painelCartas, CARD_CONFIGURACAO);
        painelCartas.revalidate();
        painelCartas.repaint();
    }

    public void mostrarJogo() {
        cardLayout.show(painelCartas, CARD_JOGO);
        painelCartas.revalidate();
        painelCartas.repaint();
    }

    public void atualizarVida(int atual, int maxima) {
        hudPanel.atualizarVida(atual, maxima);
    }

    public void setJogador(Personagem jogador) {
        hudPanel.setJogador(jogador);
    }

    public void atualizarInventario() {
        hudPanel.atualizarInventario();
    }

    public void setAlertaVisivel(boolean visivel) {
        hudPanel.setAlertaVisivel(visivel);
    }

    public PainelMenuPrincipal getPainelMenu() {
        return painelMenu;
    }

    public PainelConfiguracao getPainelConfiguracao() {
        return painelConfiguracao;
    }

    public MapaPanel getMapaPanel() {
        return mapaPanel;
    }
}