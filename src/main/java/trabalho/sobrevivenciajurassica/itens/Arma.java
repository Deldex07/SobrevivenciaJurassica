package trabalho.sobrevivenciajurassica.itens;

public abstract class Arma extends Itens {
    protected int danoBase;

    public Arma(String nome, int danoBase) {
        super(nome);
        this.danoBase = danoBase;
    }
    
    public int getDanoBase() {
        return danoBase;
    }

    /**
     * Calcula e retorna o dano do ataque
     * Retorna 0 se errou, 1 se acertou e 2 se critou
     */
    public abstract int calcularDanoTotal();
    /** indica se tem munição e pode ser usada */
    public abstract boolean podeSerUsada();
}