package trabalho.sobrevivenciajurassica.comportamentos;

import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
/**
 * Movimentação do Velociraptor, que se move aleatoriamente.
 * MovimentoVelociraptor
 */
public class MovimentoVelociraptor implements ComportamentoMovimento {
    @Override
public void mover(Dinossauro dino, Mapa mapa) {
    for (int i = 0; i < 2 && dino.estaVivo(); i++) {
        int direcao = (int)(Math.random() * 4);
        int linha = dino.getLinha();
        int coluna = dino.getColuna();
        switch (direcao) {
            case 0: linha--; break;
            case 1: linha++; break;
            case 2: coluna--; break;
            case 3: coluna++; break;
        }

        boolean eraPosicaoDoJogador = linha == mapa.getPersonagem().getLinha()
                && coluna == mapa.getPersonagem().getColuna();

        boolean conseguiu = mapa.tentarMoverDinossauro(dino, linha, coluna);

        if (!conseguiu) {
            return; // bloqueado por parede ou outro dinossauro — para de se mover
        }
        if (eraPosicaoDoJogador) {
            return; // encontrou o jogador nesse passo — combate já ocorreu, não continua
        }
    }
}
}