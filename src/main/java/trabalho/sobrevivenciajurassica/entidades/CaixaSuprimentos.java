package trabalho.sobrevivenciajurassica.entidades;

import trabalho.sobrevivenciajurassica.itens.*;
/**
 * Representa uma caixa de suprimentos que pode conter diferentes itens.
 * CaixaSuprimentos
 */
public class CaixaSuprimentos extends ElementoMapa {
    private final ConteudoCaixa conteudo;

    public CaixaSuprimentos(ConteudoCaixa conteudo, int linha, int coluna, char simbolo) {
        super(linha, coluna, simbolo);
        this.conteudo = conteudo;
    }

    public boolean abrir(Personagem jogador) {
        switch (conteudo) {
            case KIT_MEDICO:
                jogador.getInventario().adicionarItem(
                        new KitMedico());
                System.out.println("Você encontrou um Kit Médico!");
                break;

            case BASTAO_ELETRICO:
                jogador.getInventario().adicionarItem(
                        new BastaoEletrico());
                System.out.println("Você encontrou um Bastão Elétrico!");
                break;

            case DARDOS:
                jogador.getInventario().adicionarItem(
                        new Dardos(1));
                System.out.println("Você encontrou Dardos Tranquilizantes!");
                break;

            case COMPSOGNATO:
                System.out.println("A caixa estava vazia...");
                System.out.println("Um Compsognato apareceu!");
                return true;
        }
        return false;
    }

    public ConteudoCaixa getConteudo() {
        return conteudo;
    }
}