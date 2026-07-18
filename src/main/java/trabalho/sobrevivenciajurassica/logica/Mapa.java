package trabalho.sobrevivenciajurassica.logica;

import java.util.Random;
import trabalho.sobrevivenciajurassica.entidades.*;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;
import trabalho.sobrevivenciajurassica.itens.ConteudoCaixa;

/**
 * Classe que representa o mapa do jogo, incluindo a grade de elementos, os dinossauros e o personagem. 
 * Ela é responsável por gerar o mapa, mover os dinossauros e o personagem, e verificar as condições de vitória e derrota.
 * Mapa
 */
public class Mapa {
    private final int tamanho;
    protected final ElementoMapa[][] grade;
    private final Dinossauro[][] dinossauros;
    protected Personagem personagem;
    private final GerenciadorCombate gerenciadorCombate;
    protected final Random random;

    public Mapa(int tamanho, EntradaCombate entradaCombate) {
        this(tamanho, entradaCombate, new Random().nextLong());
    }

    public Mapa(int tamanho, EntradaCombate entradaCombate, long seed) {
        this.tamanho = tamanho;
        this.grade = new ElementoMapa[tamanho][tamanho];
        this.dinossauros = new Dinossauro[tamanho][tamanho];
        this.gerenciadorCombate = new GerenciadorCombate(entradaCombate);
        this.random = new Random(seed);
    }

    public void gerar(Personagem personagem, Dificuldade dificuldade) {
        this.personagem = personagem;
        grade[0][0] = personagem;
        personagem.setLinha(0);
        personagem.setColuna(0);

        gerarParedes();
        posicionarDinossauro(new TiranossauroRex(3, tamanho - 1, tamanho - 1, 'R'), tamanho - 1, tamanho - 1);
        posicionarDinossauroAleatorio(new Compsognato(1, 0, 0, 'C'));
        posicionarDinossauroAleatorio(new Compsognato(1, 0, 0, 'C'));

        for (int i = 0; i < 5; i++) {
            posicionarDinossauroAleatorio(new Troodonte(2, 0, 0, 'T'));
        }

        posicionarDinossauroAleatorio(new Velociraptor(2, 0, 0, 'V'));
        posicionarDinossauroAleatorio(new Velociraptor(2, 0, 0, 'V'));

        posicionarCaixaAleatoria(ConteudoCaixa.BASTAO_ELETRICO);
        posicionarCaixaAleatoria(ConteudoCaixa.KIT_MEDICO);
        posicionarCaixaAleatoria(sortearConteudoSecundario());
        posicionarCaixaAleatoria(sortearConteudoSecundario());
    }

    protected ConteudoCaixa sortearConteudoSecundario() {
        return random.nextBoolean() ? ConteudoCaixa.DARDOS : ConteudoCaixa.COMPSOGNATO;
    }

    protected void gerarParedes() {
        int total = tamanho * tamanho;
        int quantidade = total / 10 + random.nextInt(total / 10);
        for (int i = 0; i < quantidade; i++) {
            int linha;
            int coluna;

            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);
            } while (grade[linha][coluna] != null || (linha == tamanho - 1 && coluna == tamanho - 1));
            grade[linha][coluna] = new Parede(linha, coluna);
        }
    }

    protected void posicionarDinossauro(Dinossauro dino, int linha, int coluna) {
        dino.setLinha(linha);
        dino.setColuna(coluna);
        dinossauros[linha][coluna] = dino;
    }

    protected void posicionarDinossauroAleatorio(Dinossauro dino) {
        int linha;
        int coluna;

        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (grade[linha][coluna] != null || dinossauros[linha][coluna] != null || (linha == 0 && coluna == 0));
        posicionarDinossauro(dino, linha, coluna);
    }

    protected void posicionarCaixaAleatoria(ConteudoCaixa conteudo) {
        int linha;
        int coluna;

        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (!posicaoLivre(linha, coluna));
        grade[linha][coluna] = new CaixaSuprimentos(conteudo, linha, coluna, 'X');
    }

    public void moverDinossauro(Dinossauro dino, int novaLinha, int novaColuna) {
        dinossauros[dino.getLinha()][dino.getColuna()] = null;
        dino.moverPara(novaLinha, novaColuna);
        dinossauros[novaLinha][novaColuna] = dino;
    }

    public boolean tentarMoverDinossauro(Dinossauro dino, int novaLinha, int novaColuna) {
        if (!dentroDosLimites(novaLinha, novaColuna)) {
            return false;
        }
        if (grade[novaLinha][novaColuna] instanceof Parede) {
            return false;
        }
        if (dinossauros[novaLinha][novaColuna] != null) {
            return false;
        }
        if (personagem.getLinha() == novaLinha && personagem.getColuna() == novaColuna) {
            gerenciadorCombate.iniciarCombate(personagem, dino, true);
            if (!dino.estaVivo()) {
                dinossauros[dino.getLinha()][dino.getColuna()] = null;
            }
            return true;
        }

        moverDinossauro(dino, novaLinha, novaColuna);
        return true;
    }

    public boolean moverPersonagem(int novaLinha, int novaColuna) {
        if (!dentroDosLimites(novaLinha, novaColuna)) {
            System.out.println("Você não pode sair do mapa.");
            return false;
        }

        if (grade[novaLinha][novaColuna] instanceof Parede) {
            System.out.println("Há uma parede nessa posição.");
            return false;
        }

        Dinossauro dino = dinossauros[novaLinha][novaColuna];
        if (dino != null) {
            boolean sobreviveu = gerenciadorCombate.iniciarCombate(personagem, dino);
            if (!sobreviveu) {
                return false;
            }
            if (dino.estaVivo()) {
                return true;
            }
            dinossauros[novaLinha][novaColuna] = null;
        }

        ElementoMapa elemento = grade[novaLinha][novaColuna];
        if (elemento instanceof CaixaSuprimentos caixa) {
            System.out.println("Você encontrou uma caixa de suprimentos!");

            boolean apareceuCompsognato = caixa.abrir(personagem);
            grade[novaLinha][novaColuna] = null;

            if (apareceuCompsognato) {
                Compsognato compsognato = new Compsognato(1, novaLinha, novaColuna, 'C');
                boolean sobreviveuSurpresa = gerenciadorCombate.iniciarCombate(personagem, compsognato);
                if (!sobreviveuSurpresa) {
                    return false;
                }
                if (compsognato.estaVivo()) {
                    return true;
                }
            }
        }

        grade[personagem.getLinha()][personagem.getColuna()] = null;
        personagem.moverPara(novaLinha, novaColuna);
        grade[novaLinha][novaColuna] = personagem;
        return true;
    }

    public void moverDinossauros() {
        Dinossauro[][] copia = new Dinossauro[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                copia[i][j] = dinossauros[i][j];
            }
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                Dinossauro dino = copia[i][j];
                if (dino != null && dino.estaVivo()) {
                    dino.mover(this);
                }
            }
        }
    }

    public boolean existeDinossauro(int linha, int coluna) {
        return dinossauros[linha][coluna] != null;
    }

    public boolean existeParede(int linha, int coluna) {
        return grade[linha][coluna] instanceof Parede;
    }

    public boolean existeCaixa(int linha, int coluna) {
        return grade[linha][coluna] instanceof CaixaSuprimentos;
    }

    public boolean venceu() {
        return personagem.getLinha() == tamanho - 1 && personagem.getColuna() == tamanho - 1;
    }

    public boolean perdeu() {
        return !personagem.estaVivo();
    }

    public void imprimir(boolean debug) {
        System.out.print("   ");

        for (int c = 0; c < tamanho; c++) {
            System.out.printf("%2d ", c + 1);
        }

        System.out.println();
        for (int l = 0; l < tamanho; l++) {
            System.out.printf("%c  ", (char) ('A' + l));

            for (int c = 0; c < tamanho; c++) {
                char simbolo = '.';
                if (grade[l][c] != null) {
                    simbolo = grade[l][c].getSimbolo();
                }

                if (dinossauros[l][c] != null && (debug || estaNaLinhaDeVisao(l, c))) {
                    simbolo = dinossauros[l][c].getSimbolo();
                }

                System.out.printf("%c  ", simbolo);
            }

            System.out.println();
        }
    }

    protected boolean estaNaLinhaDeVisao(int linha, int coluna) {
        int linhaPersonagem = personagem.getLinha();
        int colunaPersonagem = personagem.getColuna();

        if (linha == linhaPersonagem) {
            int passo = coluna > colunaPersonagem ? 1 : -1;
            for (int c = colunaPersonagem + passo; c != coluna; c += passo) {
                if (bloqueiaVisao(linha, c)) {
                    return false;
                }
            }
            return true;
        }

        if (coluna == colunaPersonagem) {
            int passo = linha > linhaPersonagem ? 1 : -1;
            for (int l = linhaPersonagem + passo; l != linha; l += passo) {
                if (bloqueiaVisao(l, coluna)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private boolean bloqueiaVisao(int linha, int coluna) {
        return (grade[linha][coluna] instanceof Parede) || dinossauros[linha][coluna] != null;
    }

    public boolean dinossauroVisivel(int linha, int coluna, boolean debug) {
        return debug || estaNaLinhaDeVisao(linha, coluna);
    }

    /**
     * Indica se existe algum dinossauro vivo na linha de visão atual do
     * jogador, independente do modo debug. Usado para acionar alertas
     * visuais de perigo próximo na interface gráfica.
     */
    public boolean existeDinossauroNaLinhaDeVisao() {
        for (int l = 0; l < tamanho; l++) {
            for (int c = 0; c < tamanho; c++) {
                Dinossauro dino = dinossauros[l][c];
                if (dino != null && dino.estaVivo() && estaNaLinhaDeVisao(l, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public ElementoMapa getElemento(int linha, int coluna) {
        return grade[linha][coluna];
    }

    public Dinossauro getDinossauro(int linha, int coluna) {
        return dinossauros[linha][coluna];
    }

    public void setElemento(int linha, int coluna, ElementoMapa elemento) {
        grade[linha][coluna] = elemento;
    }

    public void setDinossauro(int linha, int coluna, Dinossauro dinossauro) {
        dinossauros[linha][coluna] = dinossauro;
    }

    public boolean dentroDosLimites(int linha, int coluna) {
        return linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho;
    }

    public boolean posicaoLivre(int linha, int coluna) {
        boolean ehPosicaoDoPersonagem = personagem != null
                && personagem.getLinha() == linha
                && personagem.getColuna() == coluna;

        return dentroDosLimites(linha, coluna)
                && !(grade[linha][coluna] instanceof Parede)
                && dinossauros[linha][coluna] == null
                && !ehPosicaoDoPersonagem;
    }
}