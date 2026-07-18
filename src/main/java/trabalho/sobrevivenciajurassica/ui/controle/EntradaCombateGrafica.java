package trabalho.sobrevivenciajurassica.ui.controle;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;

public class EntradaCombateGrafica implements EntradaCombate {

    private final Component parent;

    public EntradaCombateGrafica(Component parent) {
        this.parent = parent;
    }

    @Override
    public int escolherAcao(boolean temDardos, boolean temKit, boolean temBastao) {
        List<Integer> codigos = new ArrayList<>();
        List<String> rotulos = new ArrayList<>();

        codigos.add(1);
        rotulos.add(temBastao ? "Bastão Elétrico" : "Soco");

        if (temDardos) {
            codigos.add(2);
            rotulos.add("Dardos Tranquilizantes");
        }
        if (temKit) {
            codigos.add(3);
            rotulos.add("Usar Kit Médico");
        }
        codigos.add(4);
        rotulos.add("Fugir");

        Object[] opcoes = rotulos.toArray();
        int indice = JOptionPane.showOptionDialog(parent,
                "Escolha sua ação:", "Combate",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opcoes, opcoes[0]);

        if (indice < 0) {
            return 4; // fechar o diálogo (Esc/X) equivale a tentar fugir
        }
        return codigos.get(indice);
    }
}