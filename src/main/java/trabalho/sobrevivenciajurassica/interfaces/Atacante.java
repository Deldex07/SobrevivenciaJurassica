/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.sobrevivenciajurassica.interfaces;

import trabalho.sobrevivenciajurassica.entidades.EntidadeViva;

/**
 * Interface que define o comportamento de ataque para entidades vivas.
 * @author deldex
 */
public interface Atacante {
    void atacar(EntidadeViva alvo);
}
