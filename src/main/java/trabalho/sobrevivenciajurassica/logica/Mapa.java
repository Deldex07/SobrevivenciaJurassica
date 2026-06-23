package trabalho.sobrevivenciajurassica.logica;

import trabalho.sobrevivenciajurassica.entidades.*;
import trabalho.sobrevivenciajurassica.itens.*;
import java.util.Random;

public class Mapa {

    private final int tamanho;
    private final ElementoMapa[][] grade;      // paredes, caixas, personagem
    private final Dinossauro[][] dinossauros;  // camada separada para dinossauros
    private Personagem personagem;
    private static final Random random = new Random();

    public Mapa(int tamanho) {
        this.tamanho = tamanho;
        this.grade = new ElementoMapa[tamanho][tamanho];
        this.dinossauros = new Dinossauro[tamanho][tamanho];
    }

    // --- Geração ---

    public void gerar(Personagem personagem, Dificuldade dificuldade) {
        this.personagem = personagem;

        // Posiciona o personagem no canto superior esquerdo
        grade[0][0] = personagem;
        personagem.setLinha(0);
        personagem.setColuna(0);

        // Gera paredes aleatoriamente (entre 10% e 20% do mapa)
        int totalCelulas = tamanho * tamanho;
        int numParedes = totalCelulas / 10 + random.nextInt(totalCelulas / 10);
        for (int i = 0; i < numParedes; i++) {
            int l, c;
            do {
                l = random.nextInt(tamanho);
                c = random.nextInt(tamanho);
            } while (grade[l][c] != null); // não sobrescreve nada
            grade[l][c] = new Parede(l, c);
        }

        // Posiciona T-Rex no canto oposto
        posicionarDinossauro(new TiranossauroRex(3, tamanho - 1, tamanho - 1, 'R'), tamanho - 1, tamanho - 1);

        // Posiciona os outros dinossauros aleatoriamente
        posicionarDinossauroAleatorio(new Compsognato(1, 0, 0, 'C'));
        posicionarDinossauroAleatorio(new Compsognato(1, 0, 0, 'C'));
        posicionarDinossauroAleatorio(new Troodonte(2, 0, 0, 'T'));
        posicionarDinossauroAleatorio(new Troodonte(2, 0, 0, 'T'));
        posicionarDinossauroAleatorio(new Troodonte(2, 0, 0, 'T'));
        posicionarDinossauroAleatorio(new Troodonte(2, 0, 0, 'T'));
        posicionarDinossauroAleatorio(new Troodonte(2, 0, 0, 'T'));
        posicionarDinossauroAleatorio(new Velociraptor(2, 0, 0, 'V'));
        posicionarDinossauroAleatorio(new Velociraptor(2, 0, 0, 'V'));

        // Posiciona as 4 caixas de suprimentos
        posicionarCaixaAleatoria(ConteudoCaixa.KIT_MEDICO);
        posicionarCaixaAleatoria(ConteudoCaixa.BASTAO_ELETRICO);
        posicionarCaixaAleatoria(ConteudoCaixa.DARDOS);
        posicionarCaixaAleatoria(ConteudoCaixa.COMPSOGNATO);
    }

    private void posicionarDinossauro(Dinossauro dino, int linha, int coluna) {
        dino.setLinha(linha);
        dino.setColuna(coluna);
        dinossauros[linha][coluna] = dino;
    }

    private void posicionarDinossauroAleatorio(Dinossauro dino) {
        int l, c;
        do {
            l = random.nextInt(tamanho);
            c = random.nextInt(tamanho);
        } while (grade[l][c] != null || dinossauros[l][c] != null || (l == 0 && c == 0));
        posicionarDinossauro(dino, l, c);
    }

    private void posicionarCaixaAleatoria(ConteudoCaixa conteudo) {
        int l, c;
        do {
            l = random.nextInt(tamanho);
            c = random.nextInt(tamanho);
        } while (grade[l][c] != null || (l == 0 && c == 0));
        grade[l][c] = new CaixaSuprimentos(conteudo, l, c, 'X');
    }

    public void moverDinossauro(Dinossauro dino, int novaLinha, int novaColuna) {
        dinossauros[dino.getLinha()][dino.getColuna()] = null; // limpa posição antiga
        dino.moverPara(novaLinha, novaColuna);                 // atualiza o objeto
        dinossauros[novaLinha][novaColuna] = dino;             // marca nova posição
    }

    // --- Impressão ---

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

                // Dinossauro tem prioridade visual se debug ativo
                if (dinossauros[l][c] != null && (debug || estaNoVision(l, c))) {
                    simbolo = dinossauros[l][c].getSimbolo();
                }

                System.out.printf("%c  ", simbolo);
            }
            System.out.println();
        }
    }

    private boolean estaNoVision(int linha, int coluna) {
        // Linha de visão horizontal e vertical a partir do personagem
        return linha == personagem.getLinha() || coluna == personagem.getColuna();
    }

    // --- Acesso ---

    public int getTamanho() { return tamanho; }

    public ElementoMapa getElemento(int linha, int coluna) {
        return grade[linha][coluna];
    }

    public Dinossauro getDinossauro(int linha, int coluna) {
        return dinossauros[linha][coluna];
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setElemento(int linha, int coluna, ElementoMapa elemento) {
        grade[linha][coluna] = elemento;
    }

    public void setDinossauro(int linha, int coluna, Dinossauro dino) {
        dinossauros[linha][coluna] = dino;
    }

    public boolean dentroDosLimites(int linha, int coluna) {
        return linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho;
    }

    public boolean posicaoLivre(int linha, int coluna) {
        return dentroDosLimites(linha, coluna)
            && !(grade[linha][coluna] instanceof Parede)
            && dinossauros[linha][coluna] == null;
    }
}