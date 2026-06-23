package trabalho.sobrevivenciajurassica.comportamentos;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;

public class MovimentoPerseguidor implements ComportamentoMovimento {
    @Override
    public void mover(Dinossauro dino, Mapa mapa) {
        int linhaDino = dino.getLinha();
        int colunaDino = dino.getColuna();
        int linhaPersonagem = mapa.getPersonagem().getLinha();
        int colunaPersonagem = mapa.getPersonagem().getColuna();

        int novaLinha = linhaDino;
        int novaColuna = colunaDino;

        // Move na direção do personagem
        if (linhaDino < linhaPersonagem) novaLinha++;
        else if (linhaDino > linhaPersonagem) novaLinha--;

        if (colunaDino < colunaPersonagem) novaColuna++;
        else if (colunaDino > colunaPersonagem) novaColuna--;

        // Verifica se a nova posição é válida (dentro do mapa e sem obstáculos)
       if (mapa.posicaoLivre(novaLinha, colunaDino)) {
            mapa.moverDinossauro(dino, novaLinha, colunaDino);
        } else if (mapa.posicaoLivre(linhaDino, novaColuna)) {
            mapa.moverDinossauro(dino, linhaDino, novaColuna);
        }
    }
}