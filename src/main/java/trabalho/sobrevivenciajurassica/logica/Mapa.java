package trabalho.sobrevivenciajurassica.logica;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.*;
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
    protected static final Random random = new Random();

    public Mapa(int tamanho, Scanner scanner) {
        this.tamanho = tamanho;
        this.grade = new ElementoMapa[tamanho][tamanho];
        this.dinossauros = new Dinossauro[tamanho][tamanho];
        this.gerenciadorCombate = new GerenciadorCombate(scanner);
    }

    public void gerar(Personagem personagem, Dificuldade dificuldade) throws IOException {
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
        posicionarCaixaAleatoria(ConteudoCaixa.KIT_MEDICO);
        posicionarCaixaAleatoria(ConteudoCaixa.BASTAO_ELETRICO);
        posicionarCaixaAleatoria(ConteudoCaixa.DARDOS);
        posicionarCaixaAleatoria(ConteudoCaixa.COMPSOGNATO);
    }

    protected void gerarParedes() {
        int total = tamanho * tamanho;
        int quantidade =
                total / 10 + random.nextInt(total / 10);
        for (int i = 0; i < quantidade; i++) {
            int linha;
            int coluna;

            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);
            } while (grade[linha][coluna] != null);
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

    protected void posicionarCaixaAleatoria( ConteudoCaixa conteudo ) throws IOException {
        int linha;
        int coluna;

        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (grade[linha][coluna] != null || (linha == 0 && coluna == 0));
        grade[linha][coluna] = new CaixaSuprimentos(conteudo, linha, coluna, 'X');
    }

    public void moverDinossauro( Dinossauro dino, int novaLinha, int novaColuna) {

        dinossauros[dino.getLinha()][dino.getColuna()] = null;
        dino.moverPara(novaLinha, novaColuna);
        dinossauros[novaLinha][novaColuna] = dino;
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
            boolean venceu = gerenciadorCombate.iniciarCombate(personagem, dino);
            if (!venceu) {
                return false;
            }

            if (!dino.estaVivo()) {
                dinossauros[novaLinha][novaColuna] = null;
            }
        }

        ElementoMapa elemento = grade[novaLinha][novaColuna];
        if (elemento instanceof CaixaSuprimentos caixa) {
            System.out.println("Você encontrou uma caixa de suprimentos!");

            boolean apareceuCompsognato = caixa.abrir(personagem);
            grade[novaLinha][novaColuna] = null;

            if (apareceuCompsognato) {
                Compsognato compsognato = new Compsognato(1, novaLinha, novaColuna, 'C');
                boolean venceu = gerenciadorCombate.iniciarCombate(personagem, compsognato);
                if (!venceu) {
                    return false;
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

    private boolean estaNaLinhaDeVisao(int linha, int coluna) {
        return linha == personagem.getLinha() || coluna == personagem.getColuna();
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