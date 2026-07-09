package trabalho.sobrevivenciajurassica;

import trabalho.sobrevivenciajurassica.logica.GerenciadorJogo;

import java.io.IOException;
public class SobrevivenciaJurassica {
    
    public static void main(String[] args) throws IOException {
        GerenciadorJogo jogo = new GerenciadorJogo();
        jogo.iniciarJogo();
    }
}