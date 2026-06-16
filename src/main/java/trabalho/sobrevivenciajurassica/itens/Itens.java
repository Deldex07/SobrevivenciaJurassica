package trabalho.sobrevivenciajurassica.itens;

/**
 * Classe abstrata base para todos os itens do jogo.
 * Todo item possui um nome e pode ser "usado" pelo personagem.
 */
public abstract class Itens {
    protected String nome;

    public Itens(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void usar();

    @Override
    public String toString() { return nome; }
}
