package trabalho.sobrevivenciajurassica.comportamentos;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
/**
 * Movimentação do Compsognathus, que se move aleatoriamente.
 * MovimentoAleatorio
 */
public class MovimentoAleatorio implements ComportamentoMovimento {
    @Override
    public void mover(Dinossauro dino, Mapa mapa) {
        int direcao = (int)(Math.random() * 4);
        int linha = dino.getLinha();
        int coluna = dino.getColuna();
        switch (direcao) {
            case 0: linha--; break;
            case 1: linha++; break;
            case 2: coluna--; break;
            case 3: coluna++; break;
        }
        mapa.tentarMoverDinossauro(dino, linha, coluna);
    }
}