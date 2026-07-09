package trabalho.sobrevivenciajurassica.logica;

import java.util.Scanner;
import java.io.IOException;
import trabalho.sobrevivenciajurassica.entidades.Personagem;

/**
 * Classe responsável por gerenciar o jogo, incluindo a escolha da dificuldade, criação do mapa e execução do loop principal do jogo.
 * GerenciadorJogo
 */
public class GerenciadorJogo {
    private final Scanner scanner;
    private Dificuldade dificuldade;
    private Mapa mapa;
    private Personagem jogador;
    private boolean debug;

    public GerenciadorJogo() {
        scanner = new Scanner(System.in);
    }

    public void iniciarJogo() throws IOException {
        System.out.println("======================================");
        System.out.println("      SOBREVIVÊNCIA JURÁSSICA");
        System.out.println("======================================");
        System.out.println();

        escolherDificuldade();
        escolherModoDebug();
        criarJogo();
        System.out.println();
        System.out.println("Jogo iniciado!");
        executarJogo();
    }

    private void escolherDificuldade() {
        int opcao;
        do {
            System.out.println();
            System.out.println("Escolha a dificuldade:");
            System.out.println("1 - Fácil");
            System.out.println("2 - Médio");
            System.out.println("3 - Difícil");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    dificuldade = Dificuldade.FACIL;
                    break;

                case 2:
                    dificuldade = Dificuldade.MEDIO;
                    break;

                case 3:
                    dificuldade = Dificuldade.DIFICIL;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        while (opcao < 1 || opcao > 3);
    }

    private void escolherModoDebug() {
        System.out.println();
        System.out.print("Ativar modo Debug? (S/N): ");
        String resposta = scanner.next();
        debug = resposta.equalsIgnoreCase("S");
    }

    private void criarJogo() throws IOException {
        jogador = new Personagem(
                0,
                0,
                5,
                dificuldade.getPercepcao());
        mapa = new Mapa(
                dificuldade.getTamanhoMapa(),
                scanner);
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

        encerrar();
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
    
    void exibirDerrota() {
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