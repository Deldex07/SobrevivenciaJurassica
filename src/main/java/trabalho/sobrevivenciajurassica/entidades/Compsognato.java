/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.sobrevivenciajurassica.entidades;
import trabalho.sobrevivenciajurassica.comportamentos.MovimentoAleatorio;
/**
 *
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
