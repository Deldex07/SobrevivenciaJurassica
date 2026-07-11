package trabalho.sobrevivenciajurassica.logica;

import java.util.Scanner;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Personagem;

/**
 * Classe responsável por gerenciar o jogo, incluindo o menu principal, escolha
 * da dificuldade, criação do mapa, execução do loop principal e o menu de
 * fim de jogo (reiniciar / novo jogo).
 * GerenciadorJogo
 */
public class GerenciadorJogo {
    private final Scanner scanner;
    private Dificuldade dificuldade;
    private Mapa mapa;
    private Personagem jogador;
    private boolean debug;
    private long seedAtual;

    public GerenciadorJogo() {
        scanner = new Scanner(System.in);
    }

    public void iniciarJogo() throws IOException {
        System.out.println("======================================");
        System.out.println("      SOBREVIVÊNCIA JURÁSSICA");
        System.out.println("======================================");

        menuPrincipal();
    }

    private void menuPrincipal() throws IOException {
        boolean sair = false;

        while (!sair) {
            System.out.println();
            System.out.println("1 - Jogar");
            System.out.println("2 - Sair");
            System.out.print("Opção: ");
            int opcao = lerOpcao(1, 2);

            if (opcao == 1) {
                jogarNovaPartida();
            } else {
                sair = true;
            }
        }

        encerrar();
    }

    /**
     * Cuida de uma "sessão" de jogo: escolhe dificuldade e debug uma vez,
     * depois fica em loop entre jogar/reiniciar até o jogador escolher
     * "Novo Jogo" (o que devolve o controle para o menu principal).
     */
    private void jogarNovaPartida() throws IOException {
        escolherDificuldade();
        escolherModoDebug();
        seedAtual = System.nanoTime(); // define o mapa desta sessão

        boolean voltarAoMenuPrincipal = false;
        while (!voltarAoMenuPrincipal) {
            criarJogo(seedAtual);
            System.out.println();
            System.out.println("Jogo iniciado!");
            executarJogo();

            int opcao = menuFimDeJogo();
            if (opcao == 1) {
                // Reiniciar Jogo: mesma dificuldade e mesma seed -> mesmo mapa
                System.out.println();
                System.out.println("Reiniciando com o mesmo mapa...");
            } else {
                voltarAoMenuPrincipal = true; // Novo Jogo
            }
        }
    }

    private int menuFimDeJogo() {
        System.out.println();
        System.out.println("1 - Reiniciar Jogo");
        System.out.println("2 - Novo Jogo");
        System.out.print("Opção: ");
        return lerOpcao(1, 2);
    }

    private int lerOpcao(int min, int max) {
        int opcao;
        do {
            opcao = scanner.nextInt();
            if (opcao < min || opcao > max) {
                System.out.println("Opção inválida.");
            }
        } while (opcao < min || opcao > max);
        return opcao;
    }

    private void escolherDificuldade() {
        System.out.println();
        System.out.println("Escolha a dificuldade:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");
        System.out.print("Opção: ");
        int opcao = lerOpcao(1, 3);

        switch (opcao) {
            case 1 -> dificuldade = Dificuldade.FACIL;
            case 2 -> dificuldade = Dificuldade.MEDIO;
            case 3 -> dificuldade = Dificuldade.DIFICIL;
        }
    }

    private void escolherModoDebug() {
        System.out.println();
        System.out.print("Ativar modo Debug? (S/N): ");
        String resposta = scanner.next();
        debug = resposta.equalsIgnoreCase("S");
    }

    private void criarJogo(long seed) throws IOException {
        jogador = new Personagem(
                0,
                0,
                5,
                dificuldade.getPercepcao());
        mapa = new Mapa(
                dificuldade.getTamanhoMapa(),
                scanner,
                seed);
        mapa.gerar(jogador, dificuldade);
    }

    private void executarJogo() {
        while (true) {
            System.out.println();
            mapa.imprimir(debug);
            System.out.println();
            System.out.println("Vida: " + jogador.getSaude());
            System.out.println(jogador.getInventario());

            char comando = lerComando();
            boolean movimentoRealizado = processarMovimento(comando);
            if (!movimentoRealizado) {
                continue;
            }
            mapa.moverDinossauros();

            if (mapa.venceu()) {
                mapa.imprimir(debug);
                exibirVitoria();
                break;
            }

            if (mapa.perdeu()) {
                mapa.imprimir(debug);
                exibirDerrota();
                break;
            }
        }
    }

    private char lerComando() {
        System.out.println();
        System.out.print("Movimento (W A S D): ");
        return scanner.next().toUpperCase().charAt(0);
    }

    private boolean processarMovimento(char comando) {
        int linha = jogador.getLinha();
        int coluna = jogador.getColuna();

        switch (comando) {
            case 'W':
                linha--;
                break;

            case 'S':
                linha++;
                break;

            case 'A':
                coluna--;
                break;

            case 'D':
                coluna++;
                break;

            default:
                System.out.println("Comando inválido.");
                return false;
        }

        return mapa.moverPersonagem(linha, coluna);
    }

    private void exibirVitoria() {
        System.out.println();
        System.out.println("======================================");
        System.out.println("          VOCÊ VENCEU!");
        System.out.println("Você conseguiu escapar da ilha.");
        System.out.println("======================================");
    }

    private void exibirDerrota() {
        System.out.println();
        System.out.println("======================================");
        System.out.println("          GAME OVER");
        System.out.println("Você foi derrotado.");
        System.out.println("======================================");
    }

    private void encerrar() {
        scanner.close();
    }
}