/*
 * Classe abstrata base para todas as entidades vivas do jogo.
 */
package trabalho.sobrevivenciajurassica.entidades;

/**
 *
 * @author deldex
 */
public abstract class EntidadeViva extends ElementoMapa {

    public EntidadeViva(int linha, int coluna, char simbolo) {
        super(linha, coluna, simbolo);
    }

    public abstract void receberDano(int dano);
    public abstract boolean estaVivo();
}
