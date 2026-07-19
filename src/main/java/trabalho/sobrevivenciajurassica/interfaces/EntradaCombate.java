package trabalho.sobrevivenciajurassica.interfaces;

import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.entidades.Personagem;

public interface EntradaCombate {
    int escolherAcao(Personagem jogador, Dinossauro inimigo,
                      boolean temDardos, boolean temKit, boolean temBastao);
}