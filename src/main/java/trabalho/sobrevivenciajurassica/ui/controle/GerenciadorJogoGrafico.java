package trabalho.sobrevivenciajurassica.ui.controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;
import trabalho.sobrevivenciajurassica.logica.Dificuldade;
import trabalho.sobrevivenciajurassica.ui.componentes.JanelaJogo;
import trabalho.sobrevivenciajurassica.ui.entidades.PersonagemUI;
import trabalho.sobrevivenciajurassica.ui.renderizacao.MapaUI;

public class GerenciadorJogoGrafico implements KeyListener {

    private final JanelaJogo janela;
    private final EntradaCombate entradaCombate;
    private Dificuldade dificuldade;
    private MapaUI mapa;
    private Personagem jogador;
    private boolean debugPermitido;
    private boolean debugAtivo;
    private long seedAtual;
    private boolean jogoAtivo;

    public GerenciadorJogoGrafico() {
        janela = new JanelaJogo();
        entradaCombate = new EntradaCombateGrafica(janela);

        janela.getMapaPanel().addKeyListener(this);
        janela.getMapaPanel().setFocusable(true);

        janela.getPainelMenu().aoClicarJogar(this::abrirConfiguracao);
        janela.getPainelMenu().aoClicarSair(this::sairDoJogo);
        janela.getPainelConfiguracao().aoClicarIniciar(this::confirmarConfiguracao);
    }

    public void iniciarJogo() {
        janela.setVisible(true);
        janela.mostrarMenu();
    }

    private void abrirConfiguracao() {
        janela.mostrarConfiguracao();
    }

    private void sairDoJogo() {
        janela.dispose();
        System.exit(0);
    }

    private void confirmarConfiguracao() {
        dificuldade = janela.getPainelConfiguracao().getDificuldadeSelecionada();
        debugPermitido = janela.getPainelConfiguracao().isDebugSelecionado();
        seedAtual = System.nanoTime();
        iniciarPartida();
    }

    private void iniciarPartida() {
        criarJogo(seedAtual);
        jogoAtivo = true;
        janela.mostrarJogo();
        atualizarTela();
        janela.getMapaPanel().requestFocusInWindow();
    }

    private void criarJogo(long seed) {
        jogador = new PersonagemUI(0, 0, 5, dificuldade.getPercepcao());
        mapa = new MapaUI(dificuldade.getTamanhoMapa(), entradaCombate, seed);
        mapa.gerar(jogador, dificuldade);
        debugAtivo = debugPermitido;
        janela.getMapaPanel().setMapa(mapa);
        janela.getMapaPanel().setDebug(debugAtivo);
        janela.setJogador(jogador);
        janela.atualizarVida(jogador.getSaude(), jogador.getSaudeMaxima());
        janela.atualizarInventario();
        janela.setAlertaVisivel(mapa.existeDinossauroNaLinhaDeVisao());
    }

    private void atualizarTela() {
        janela.atualizarVida(jogador.getSaude(), jogador.getSaudeMaxima());
        janela.atualizarInventario();
        janela.setAlertaVisivel(mapa.existeDinossauroNaLinhaDeVisao());
        janela.getMapaPanel().repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!jogoAtivo) return;

        boolean consomeTurno = switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> processarMovimento(-1, 0);
            case KeyEvent.VK_S -> processarMovimento(1, 0);
            case KeyEvent.VK_A -> processarMovimento(0, -1);
            case KeyEvent.VK_D -> processarMovimento(0, 1);
            case KeyEvent.VK_C -> usarCura();
            case KeyEvent.VK_G -> alternarDebug();
            default -> false;
        };

        if (consomeTurno) {
            mapa.moverDinossauros();
            atualizarTela();
            verificarFimDeJogo();
        } else {
            atualizarTela();
        }
    }

    private boolean processarMovimento(int deltaLinha, int deltaColuna) {
        int linha = jogador.getLinha() + deltaLinha;
        int coluna = jogador.getColuna() + deltaColuna;
        return mapa.moverPersonagem(linha, coluna);
    }

    private boolean usarCura() {
        return jogador.usarKitMedico();
    }

    private boolean alternarDebug() {
        if (!debugPermitido) return false;
        debugAtivo = !debugAtivo;
        janela.getMapaPanel().setDebug(debugAtivo);
        return false;
    }

    private void verificarFimDeJogo() {
        if (mapa.venceu()) {
            jogoAtivo = false;
            JOptionPane.showMessageDialog(janela,
                    "Você venceu! Você conseguiu escapar da ilha.",
                    "Vitória", JOptionPane.INFORMATION_MESSAGE);
            perguntarReiniciarOuNovo();
        } else if (mapa.perdeu()) {
            jogoAtivo = false;
            JOptionPane.showMessageDialog(janela,
                    "Game Over! Você foi derrotado.",
                    "Derrota", JOptionPane.INFORMATION_MESSAGE);
            perguntarReiniciarOuNovo();
        }
    }

    private void perguntarReiniciarOuNovo() {
        int opcao = JOptionPane.showOptionDialog(janela,
                "O que deseja fazer?", "Fim de Jogo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, new Object[]{"Reiniciar Jogo", "Novo Jogo"}, "Reiniciar Jogo");

        if (opcao == 0) {
            iniciarPartida();
        } else {
            janela.mostrarMenu();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}