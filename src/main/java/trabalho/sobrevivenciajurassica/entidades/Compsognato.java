package trabalho.sobrevivenciajurassica.entidades;
import trabalho.sobrevivenciajurassica.comportamentos.MovimentoAleatorio;
/**
 * Representa um Compsognathus, um dinossauro pequeno e rápido.
 * Compsognato
 * @author deldex
 */
public class Compsognato extends Dinossauro{

    public Compsognato(int saudeInicial, int linha, int coluna, char simbolo) {
        super(saudeInicial, linha, coluna, simbolo);
        this.comportamentoMovimento = new MovimentoAleatorio();
    }

    @Override
    public void atacar(EntidadeViva alvo) {
        alvo.receberDano(1);
    }
    
}
