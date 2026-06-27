/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.sobrevivenciajurassica.entidades;

/**
 *
 * @author deldex
 */
public abstract class EntidadeViva extends ElementoMapa {

    public EntidadeViva(int linha, int coluna, char simbolo) {
        super(linha, coluna, simbolo);
    }

    public abstract void receberDano(int dano);
    public abstract boolean estaVivo();
}
