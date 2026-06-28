/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.sobrevivenciajurassica.entidades;
import trabalho.sobrevivenciajurassica.comportamentos.MovimentoParado;
/**
 * Representa um Tiranossauro Rex, um dinossauro grande e poderoso.
 * TiranossauroRex
 * @author deldex
 */
public class TiranossauroRex extends Dinossauro {

    public TiranossauroRex(int saudeInicial, int linha, int coluna, char simbolo) {
        super(saudeInicial, linha, coluna, simbolo);
        this.comportamentoMovimento = new MovimentoParado();
    }

    @Override
    public void atacar(EntidadeViva alvo) {
        alvo.receberDano(2);
    }
    
}
