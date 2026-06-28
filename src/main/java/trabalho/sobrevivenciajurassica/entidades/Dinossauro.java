package trabalho.sobrevivenciajurassica.entidades;

import trabalho.sobrevivenciajurassica.interfaces.Atacante;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;
import trabalho.sobrevivenciajurassica.interfaces.Movimentavel;
import trabalho.sobrevivenciajurassica.logica.Mapa;


/**
 * Dinossauro
 * Classe abstrata que representa um dinossauro no jogo. Um dinossauro é uma entidade viva que pode atacar e se mover.
 */
public abstract class Dinossauro extends EntidadeViva implements Atacante, Movimentavel {
    protected int saude;
    protected ComportamentoMovimento comportamentoMovimento;
    public Dinossauro(int saudeInicial, int linha, int coluna, char simbolo) {
    super(linha, coluna, simbolo);
    this.saude = saudeInicial;
}
    @Override
    public void receberDano(int dano) {
        saude -= dano;
        if(saude < 0)
            saude = 0;
    }

    @Override
    public boolean estaVivo() {
        return saude > 0;
    }

    @Override
    public void mover(Mapa mapa) {
        if (comportamentoMovimento != null) {
            comportamentoMovimento.mover(this, mapa);
        }
    }

    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public ComportamentoMovimento getComportamentoMovimento() {
        return comportamentoMovimento;
    }

    public void setComportamentoMovimento(ComportamentoMovimento comportamentoMovimento) {
        this.comportamentoMovimento = comportamentoMovimento;
    }
}