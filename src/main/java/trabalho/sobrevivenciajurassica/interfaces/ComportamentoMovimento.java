package trabalho.sobrevivenciajurassica.interfaces;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
/**
 * Interface que define o comportamento de movimento para entidades vivas.
 * ComportamentoMovimento
 */
public interface ComportamentoMovimento {
    public void mover(Dinossauro dino, Mapa mapa);
}