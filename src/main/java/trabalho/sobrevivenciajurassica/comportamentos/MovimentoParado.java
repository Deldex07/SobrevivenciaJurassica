package trabalho.sobrevivenciajurassica.comportamentos;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
/**
 * Movimentação do T-Rex, que não se move.
 * MovimentoParado
 */
public class MovimentoParado implements ComportamentoMovimento {
    @Override
    public void mover(Dinossauro dino, Mapa mapa) {
        // T-Rex não se move
    }
}