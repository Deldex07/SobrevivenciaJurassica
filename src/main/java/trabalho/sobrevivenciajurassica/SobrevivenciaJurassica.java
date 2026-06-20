/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trabalho.sobrevivenciajurassica;
import trabalho.sobrevivenciajurassica.entidades.*;
import trabalho.sobrevivenciajurassica.itens.*;
import trabalho.sobrevivenciajurassica.logica.*;
/**
 *
 * @author deldex
 */
public class SobrevivenciaJurassica {

    public static void main(String[] args) {
        System.out.println("Bem-Vindo ao Sobrevivencia Jurassica!");
        // Testa Dado
        Dado d6 = new Dado(6);
        System.out.println("Dado 6: " + d6.rolar());

        // Testa Personagem
        Personagem p = new Personagem(0, 0, 5, 2);
        System.out.println(p);

        // Testa inventário
        Dardos dardos = new Dardos(1);
        p.getInventario().adicionarItem(dardos);
        System.out.println(p.getInventario());

        // Testa kit médico
        p.recebeDano(3);
        System.out.println("Após dano: " + p.getSaude());

        p.getInventario().adicionarItem(new KitMedico());

        p.usarKitMedico();
        System.out.println("Após cura: " + p.getSaude());
    }
}