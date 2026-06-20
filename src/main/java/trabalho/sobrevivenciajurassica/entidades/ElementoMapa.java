package trabalho.sobrevivenciajurassica.entidades;
/*
 Classe inicial para QUALQUER elemento que possa existir no mapa do jogo (jogador, dinossauros, paredes, caixas de suprimentos), pois esses
 elementos são dependentes de linhas, colunas e símbolos (ícones) de identificação.
*/
abstract public class ElementoMapa {
    // Posição no tabuleiro
    protected int linha;
    protected int coluna;

    // Caractere usado para identificação no mapa
    protected char simbolo;

    public ElementoMapa(int linha, int coluna, char simbolo) {
        this.linha = linha;
        this.coluna = coluna;
        this.simbolo = simbolo;
    }

    public int getLinha() {
        return linha;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public int getColuna() {
        return coluna;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    public char getSimbolo() {
        return simbolo;
    }
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    // Movimentação
    public void moverPara(int novaLinha, int novaColuna) {
        this.linha = novaLinha;
        this.coluna = novaColuna;
    }

    // Exibição | Retorna o símbolo utilizado no mapa
    @Override
    public String toString() {
        return String.valueOf(simbolo);
    }
}