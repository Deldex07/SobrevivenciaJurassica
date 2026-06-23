package trabalho.sobrevivenciajurassica.comportamentos;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;

public class MovimentoAleatorio implements ComportamentoMovimento {
    @Override
    public void mover(Dinossauro dino, Mapa mapa) {
        int direcao = (int) (Math.random() * 4); // 0: cima, 1: baixo, 2: esquerda, 3: direita
        int nX = dino.getLinha();
        int nY = dino.getColuna();

        switch (direcao) {
            case 0: nX--; break; // cima
            case 1: nX++; break; // baixo
            case 2: nY--; break; // esquerda
            case 3: nY++; break; // direita
        }

        if (mapa.posicaoLivre(nX, nY)) {
            mapa.moverDinossauro(dino, nX, nY);
        }
    }
}