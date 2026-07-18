package trabalho.sobrevivenciajurassica.logica;

import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;

public class GerenciadorJogo {
    private final Scanner scanner;
    private final EntradaCombate entradaCombate;
    private Dificuldade dificuldade;
    private Mapa mapa;
    private Personagem jogador;
    private boolean debugPermitido;
    private boolean debugAtivo;
    private long seedAtual;

    public GerenciadorJogo() {
        scanner = new Scanner(System.in);
        entradaCombate = new EntradaCombateTerminal(scanner);
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

    private void jogarNovaPartida() throws IOException {
        escolherDificuldade();
        escolherModoDebug();
        seedAtual = System.nanoTime();

        boolean voltarAoMenuPrincipal = false;
        while (!voltarAoMenuPrincipal) {
            criarJogo(seedAtual);
            System.out.println();
            System.out.println("Jogo iniciado!");
            executarJogo();

            int opcao = menuFimDeJogo();
            if (opcao == 1) {
                System.out.println();
                System.out.println("Reiniciando com o mesmo mapa...");
            } else {
                voltarAoMenuPrincipal = true;
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
        while (true) {
            try {
                int opcao = scanner.nextInt();
                if (opcao >= min && opcao <= max) {
                    return opcao;
                }
                System.out.println("Opção inválida.");
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Entrada inválida, digite um número.");
            }
        }
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
        debugPermitido = resposta.equalsIgnoreCase("S");
    }

    private void criarJogo(long seed) throws IOException {
        jogador = new Personagem(0, 0, 5, dificuldade.getPercepcao());
        mapa = new Mapa(dificuldade.getTamanhoMapa(), entradaCombate, seed);
        mapa.gerar(jogador, dificuldade);
        debugAtivo = debugPermitido;
    }

    private void executarJogo() {
        while (true) {
            System.out.println();
            mapa.imprimir(debugAtivo);
            System.out.println();
            System.out.println("Vida: " + jogador.getSaude());
            System.out.println(jogador.getInventario());
            exibirMenuExploracao();

            char comando = lerComando();
            boolean consomeTurno = processarComando(comando);
            if (!consomeTurno) {
                continue;
            }
            mapa.moverDinossauros();

            if (mapa.venceu()) {
                mapa.imprimir(debugAtivo);
                exibirVitoria();
                break;
            }

            if (mapa.perdeu()) {
                mapa.imprimir(debugAtivo);
                exibirDerrota();
                break;
            }
        }
    }

    private void exibirMenuExploracao() {
        System.out.println();
        System.out.println("Comandos disponíveis:");
        System.out.println("W A S D - Mover");
        if (jogador.getInventario().temKitMedico()) {
            System.out.println("C - Usar Kit Médico");
        }
        if (debugPermitido) {
            System.out.println("G - " + (debugAtivo ? "Desativar" : "Ativar") + " modo Debug");
        }
    }

    private char lerComando() {
        System.out.print("Comando: ");
        return scanner.next().toUpperCase().charAt(0);
    }

    private boolean processarComando(char comando) {
        return switch (comando) {
            case 'W', 'A', 'S', 'D' -> processarMovimento(comando);
            case 'C' -> usarCura();
            case 'G' -> alternarDebug();
            default -> {
                System.out.println("Comando inválido.");
                yield false;
            }
        };
    }

    private boolean usarCura() {
        return jogador.usarKitMedico();
    }

    private boolean alternarDebug() {
        if (!debugPermitido) {
            System.out.println("Comando inválido.");
            return false;
        }
        debugAtivo = !debugAtivo;
        System.out.println("Modo debug " + (debugAtivo ? "ativado." : "desativado."));
        return false;
    }

    private boolean processarMovimento(char comando) {
        int linha = jogador.getLinha();
        int coluna = jogador.getColuna();

        switch (comando) {
            case 'W': linha--; break;
            case 'S': linha++; break;
            case 'A': coluna--; break;
            case 'D': coluna++; break;
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