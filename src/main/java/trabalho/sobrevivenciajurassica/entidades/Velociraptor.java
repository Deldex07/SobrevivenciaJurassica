package trabalho.sobrevivenciajurassica.entidades;
import trabalho.sobrevivenciajurassica.comportamentos.MovimentoVelociraptor;
/**
 * Representa um Velociraptor, um dinossauro pequeno e rápido.
 * Velociraptor
 * @author deldex
 */
public class Velociraptor extends Dinossauro {

    public Velociraptor(int saudeInicial, int linha, int coluna, char simbolo) {
        super(saudeInicial, linha, coluna, simbolo);
        this.comportamentoMovimento = new MovimentoVelociraptor();
    }

    @Override
    public void atacar(EntidadeViva alvo) {
        alvo.receberDano(1);
    }
    
}
