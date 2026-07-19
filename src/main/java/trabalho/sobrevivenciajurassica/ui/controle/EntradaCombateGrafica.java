package trabalho.sobrevivenciajurassica.ui.controle;

import java.awt.SecondaryLoop;
import java.awt.Toolkit;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;
import trabalho.sobrevivenciajurassica.ui.componentes.JanelaJogo;

/**
 * Implementação gráfica de EntradaCombate. Em vez de abrir um diálogo
 * separado, exibe a tela de combate na própria janela do jogo e usa
 * um SecondaryLoop para aguardar a escolha do jogador sem travar a
 * interface gráfica — o mesmo mecanismo usado internamente pelo
 * JOptionPane, só que sem criar nenhuma janela nova.
 */
public class EntradaCombateGrafica implements EntradaCombate {

    private final JanelaJogo janela;
    private int resultado;

    public EntradaCombateGrafica(JanelaJogo janela) {
        this.janela = janela;
    }

    @Override
    public int escolherAcao(Personagem jogador, Dinossauro inimigo,
                             boolean temDardos, boolean temKit, boolean temBastao) {

        SecondaryLoop loop = Toolkit.getDefaultToolkit().getSystemEventQueue().createSecondaryLoop();

        janela.mostrarCombate(inimigo, temDardos, temKit, temBastao, codigo -> {
            resultado = codigo;
            loop.exit();
        });

        loop.enter();

        return resultado;
    }
}