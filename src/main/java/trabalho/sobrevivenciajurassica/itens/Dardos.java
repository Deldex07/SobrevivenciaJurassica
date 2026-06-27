package trabalho.sobrevivenciajurassica.itens;

/**
 * Arma de dardos tranquilizantes.
 * Cada tiro gasta uma munição e causa sempre dano crítico (2).
 * Não funciona contra Velociraptors.
 */
public class Dardos extends Arma {

    private int municao;

    public Dardos(int municaoInicial) {
        super("Dardos Tranquilizantes", 2);
        this.municao = municaoInicial;
    }

    /** Adiciona munição ao coletar outra caixa com dardos. */
    public void adicionarMunicao(int quantidade) {
        this.municao += quantidade;
    }

    public int getMunicao() { 
        return municao; }

    @Override
    public int calcularDanoTotal() {
        if (!podeSerUsada()) {
            System.out.println("[Dardos] Sem munição!");
            return 0;
        }
        municao--;
        System.out.println("[Dardos] Tiro certeiro! Munição restante: " + municao);
        return 2; // sempre crítico
    }
    @Override
    public boolean podeSerUsada() { 
        return municao > 0;
    }

    @Override
    public void usar() {
        // efeito de uso passivo — dano calculado via calcularDano()
    }

    @Override
    public String toString() {
        return nome + " (" + municao + " munição)";
    }
}