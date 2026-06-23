// Cansei de escrever essa porra, Java é muito verboso e isso me enche o saco. 
// Não tenho nem certeza se as outras coisas estãi funcionando da forma como deveriam.
// Fiz pra funcionar.

package trabalho.sobrevivenciajurassica.comportamentos;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.logica.Mapa;
import trabalho.sobrevivenciajurassica.entidades.Velociraptor;
import trabalho.sobrevivenciajurassica.entidades.Personagem;

public class MovimentoVelociraptor implements ComportamentoMovimento {
    @Override
    public void mover(Dinossauro dino, Mapa mapa) {
        int direcao = (int) (Math.random() * 4); // 0: cima, 1: baixo, 2: esquerda, 3: direita
        int nX1 = dino.getLinha();
        int nY1 = dino.getColuna();
        int nX2 = dino.getLinha();
        int nY2 = dino.getColuna();

        switch (direcao) {
            case 0: nX--; break; // cima
            case 1: nX++; break; // baixo
            case 2: nY--; break; // esquerda
            case 3: nY++; break; // direita
        }

        if (personagem)

        if (mapa.posicaoLivre(nX, nY)) {
            mapa.moverDinossauro(dino, nX, nY);
        }
    }
}