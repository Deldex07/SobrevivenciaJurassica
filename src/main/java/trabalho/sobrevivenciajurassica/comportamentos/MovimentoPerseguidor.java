package trabalho.sobrevivenciajurassica.comportamentos;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
/**
 * Movimentação do dinossauro perseguidor, que se move em direção ao jogador.
 * MovimentoPerseguidor
 */
public class MovimentoPerseguidor implements ComportamentoMovimento {
    @Override
    public void mover(Dinossauro dino, Mapa mapa) {
        int linha = dino.getLinha();
        int coluna = dino.getColuna();
        int linhaJogador = mapa.getPersonagem().getLinha();
        int colunaJogador = mapa.getPersonagem().getColuna();

        if (linha < linhaJogador) {
            if (mapa.posicaoLivre(linha + 1, coluna))
                mapa.moverDinossauro(dino, linha + 1, coluna);
        }
        else if (linha > linhaJogador) {
            if (mapa.posicaoLivre(linha - 1, coluna))
                mapa.moverDinossauro(dino, linha - 1, coluna);
        }
        else if (coluna < colunaJogador) {
            if (mapa.posicaoLivre(linha, coluna + 1))
                mapa.moverDinossauro(dino, linha, coluna + 1);
        }
        else if (coluna > colunaJogador) {
            if (mapa.posicaoLivre(linha, coluna - 1))
                mapa.moverDinossauro(dino, linha, coluna - 1);
        }
    }
}