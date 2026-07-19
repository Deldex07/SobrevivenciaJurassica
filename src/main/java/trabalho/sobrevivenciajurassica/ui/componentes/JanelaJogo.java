package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.function.IntConsumer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.ui.renderizacao.MapaPanel;

public class JanelaJogo extends JFrame {

    private static final String CARD_MENU = "menu";
    private static final String CARD_CONFIGURACAO = "configuracao";
    private static final String CARD_JOGO = "jogo";

    private final CardLayout cardLayout;
    private final JPanel painelCartas;
    private final JPanel painelJogo;

    private final PainelMenuPrincipal painelMenu;
    private final PainelConfiguracao painelConfiguracao;
    private final MapaPanel mapaPanel;
    private final HudPanel hudPanel;
    private final CombatePanel combatePanel;

    public JanelaJogo() {
        super("Sobrevivência Jurássica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        painelCartas = new JPanel(cardLayout);

        painelMenu = new PainelMenuPrincipal();
        painelConfiguracao = new PainelConfiguracao();

        mapaPanel = new MapaPanel(null);
        hudPanel = new HudPanel();
        combatePanel = new CombatePanel();

        painelJogo = new JPanel(new BorderLayout());
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

    /**
     * Exibe a tela de exploração (mapa), garantindo que o mapa — e não
     * a tela de combate — esteja no centro do painel do jogo.
     */
    public void mostrarJogo() {
        painelJogo.removeAll();
        painelJogo.add(mapaPanel, BorderLayout.CENTER);
        painelJogo.add(hudPanel, BorderLayout.SOUTH);
        painelJogo.revalidate();
        painelJogo.repaint();

        cardLayout.show(painelCartas, CARD_JOGO);
        painelCartas.revalidate();
        painelCartas.repaint();

        mapaPanel.requestFocusInWindow();
    }

    /**
     * Exibe a tela de combate no lugar do mapa, dentro da mesma janela
     * do jogo — sem abrir diálogos ou janelas separadas. A barra
     * inferior (vida e inventário) permanece visível durante o combate.
     */
    public void mostrarCombate(Dinossauro inimigo, boolean temDardos, boolean temKit,
                                boolean temBastao, IntConsumer callback) {
        combatePanel.configurar(inimigo, temDardos, temKit, temBastao, callback);

        painelJogo.removeAll();
        painelJogo.add(combatePanel, BorderLayout.CENTER);
        painelJogo.add(hudPanel, BorderLayout.SOUTH);
        painelJogo.revalidate();
        painelJogo.repaint();

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

    public void atualizarAlertas(List<Dinossauro> dinossaurosVisiveis) {
        hudPanel.atualizarAlertas(dinossaurosVisiveis);
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