package trabalho.sobrevivenciajurassica.itens;
import trabalho.sobrevivenciajurassica.logica.Dado;
/**
 * Bastão elétrico: dá a capacidade de atacar inimigos com bastão ou soco.
 * Dado de 6 lados: 1 = erra | 2-5 = dano 1 | 6 = crítico (dano 2).
 */
public class BastaoEletrico extends Arma {

    private static final Dado dado = new Dado(6);

    public BastaoEletrico() {
        super("Bastão Elétrico", 1);
    }

    @Override
    public int calcularDanoTotal() {
        int resultado = dado.rolar();
        System.out.println("  [Bastão] Dado rolado: " + resultado);
        if (resultado == 1)       return 0; // errou
        if (resultado > 5)        return 2; // crítico
        return 1;                           // acerto normal
    }

    @Override
    public boolean podeSerUsada() { return true; } // sem munição limitada

    @Override
    public void usar() {
        // efeito de uso passivo — dano calculado via calcularDanoTotal()
    }
}