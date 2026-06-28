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
        int direcao = (int)(Math.random() * 4); // 0: cima, 1: baixo, 2: esquerda, 3: direita
        int linha = dino.getLinha();
        int coluna = dino.getColuna();

        switch (direcao) {
            case 0: linha--; break; // cima
            case 1: linha++; break; // baixo
            case 2: coluna--; break; // esquerda
            case 3: coluna++; break; // direita
        }

        if (mapa.posicaoLivre(linha, coluna)) {
            mapa.moverDinossauro(dino, linha, coluna);
        }
    }
}