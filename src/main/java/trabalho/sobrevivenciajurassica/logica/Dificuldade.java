package trabalho.sobrevivenciajurassica.logica;
/**
 * Classe que representa a dificuldade do jogo. Ela altera a percepção do jogador e o tamanho do mapa.
 * Dificuldade
 */
public enum Dificuldade {
    FACIL("Fácil", 3, 10),
    MEDIO("Médio", 2, 15),
    DIFICIL("Difícil", 1, 20);

    private final String nome;
    private final int percepcao;
    private final int tamanhoMapa;

    Dificuldade(String nome, int percepcao, int tamanhoMapa) {
        this.nome = nome;
        this.percepcao = percepcao;
        this.tamanhoMapa = tamanhoMapa;
    }

    public String getNome() {
        return nome;
    }

    public int getPercepcao() {
        return percepcao;
    }

    public int getTamanhoMapa() {
        return tamanhoMapa;
    }

    @Override
    public String toString() {
        return nome;
    }
}