package trabalho.sobrevivenciajurassica.itens;

/**
 *
 * Kit médico: recupera pontos de vida quando usado.
 */
public class KitMedico extends Itens {
    private static final int CURA = 2;

    public KitMedico() {
        super("Kit Médico");
    }

    public int getCura() { return CURA; }

    @Override
    public void usar() {
        System.out.println("Você usou um Kit Médico e recuperou " + CURA + " pontos de vida!");
    }
}
