package trabalho.sobrevivenciajurassica.ui.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.itens.Dardos;
import trabalho.sobrevivenciajurassica.itens.Inventario;
import trabalho.sobrevivenciajurassica.ui.renderizacao.IconeItens;

/**
 * Painel que exibe os itens do jogador (arma corpo a corpo, dardos e
 * kit médico), cada um como um JLabel com ícone e quantidade. Usa
 * FlowLayout para centralização automática e confiável, sem cálculo
 * manual de posição.
 */
public class InventarioPanel extends JPanel {

    private static final int TAMANHO_ICONE = 48;

    private final JLabel labelArma;
    private final JLabel labelDardos;
    private final JLabel labelKit;
    private Personagem jogador;

    // Ícones pré-escalados, criados uma única vez (não a cada turno)
    private final ImageIcon iconeSoco;
    private final ImageIcon iconeBastao;
    private final ImageIcon iconeDardos;
    private final ImageIcon iconeKit;

    public InventarioPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 24, 10));
        setBackground(Color.DARK_GRAY);

        labelArma = criarLabelItem();
        labelDardos = criarLabelItem();
        labelKit = criarLabelItem();

        add(labelArma);
        add(labelDardos);
        add(labelKit);

        iconeSoco = criarIconeEscalado("soco");
        iconeBastao = criarIconeEscalado("bastao_eletrico");
        iconeDardos = criarIconeEscalado("dardos");
        iconeKit = criarIconeEscalado("kit_medico");
    }

    private JLabel criarLabelItem() {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        label.setPreferredSize(new Dimension(70, 80));
        return label;
    }

    private ImageIcon criarIconeEscalado(String chave) {
        Image imagem = IconeItens.obter(chave);
        if (imagem == null) return null;
        Image escalada = imagem.getScaledInstance(TAMANHO_ICONE, TAMANHO_ICONE, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }

    public void setJogador(Personagem jogador) {
        this.jogador = jogador;
        atualizar();
    }

    public void atualizar() {
        if (jogador == null) return;

        Inventario inventario = jogador.getInventario();
        boolean temBastao = inventario.temBastao();
        Dardos dardos = inventario.getDardos();
        int municaoDardos = dardos != null ? dardos.getMunicao() : 0;
        int quantidadeKit = inventario.getQuantidadeKitMedico();

        labelArma.setIcon(temBastao ? iconeBastao : iconeSoco);
        labelArma.setText(null);

        labelDardos.setIcon(iconeDardos);
        labelDardos.setText("x" + municaoDardos);

        labelKit.setIcon(iconeKit);
        labelKit.setText("x" + quantidadeKit);
    }
}