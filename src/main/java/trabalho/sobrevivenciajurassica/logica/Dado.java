package trabalho.sobrevivenciajurassica.logica;

import java.util.Random;

/**
 * Representa um dado genérico de N lados.
 * Utilizado para rolar resultados aleatórios no combate e testes de percepção.
 */
public class Dado {

    private final int lados;
    private static final Random random = new Random();

    public Dado(int lados) {
        if (lados < 2) throw new IllegalArgumentException("Um dado precisa ter ao menos 2 lados.");
        this.lados = lados;
    }

    /** Rola o dado e retorna um valor entre 1 e lados (inclusive). */
    public int rolar() {
        return random.nextInt(lados) + 1;
    }

    public int getLados() {
        return lados;
    }
}