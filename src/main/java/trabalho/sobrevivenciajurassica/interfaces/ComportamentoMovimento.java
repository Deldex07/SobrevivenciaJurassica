package trabalho.sobrevivenciajurassica.interfaces;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
public interface ComportamentoMovimento {
    public void mover(Dinossauro dino, Mapa mapa);
}