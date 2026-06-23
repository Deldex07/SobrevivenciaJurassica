/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.sobrevivenciajurassica.entidades;
import trabalho.sobrevivenciajurassica.comportamentos.MovimentoVelociraptor;
/**
 *
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
