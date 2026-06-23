/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.sobrevivenciajurassica.entidades;

import trabalho.sobrevivenciajurassica.interfaces.Atacante;
import trabalho.sobrevivenciajurassica.interfaces.ComportamentoMovimento;

/**
 *
 * @author deldex
 */
public abstract class Dinossauro extends EntidadeViva implements Atacante {
    protected int saude;
    protected ComportamentoMovimento comportamentoMovimento;
    public Dinossauro(int saudeInicial, int linha, int coluna, char simbolo) {
    super(linha, coluna, simbolo);
    this.saude = saudeInicial;
}
    @Override
    public boolean estaVivo() { return this.saude > 0; }
    @Override
    public void receberDano(int pontos) { this.saude -= pontos; }

    public int getSaude(){ return this.saude;}
    public void setSaude(int saude){ this.saude = saude; }
}
