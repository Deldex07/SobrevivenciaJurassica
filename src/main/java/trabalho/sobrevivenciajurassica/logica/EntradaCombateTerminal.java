package trabalho.sobrevivenciajurassica.logica;

import java.util.InputMismatchException;
import java.util.Scanner;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;

public class EntradaCombateTerminal implements EntradaCombate {
    private final Scanner scanner;

    public EntradaCombateTerminal(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int escolherAcao(boolean temDardos, boolean temKit, boolean temBastao) {
        System.out.println(temBastao ? "1 - Bastão Elétrico" : "1 - Soco");
        if (temDardos) System.out.println("2 - Dardos Tranquilizantes");
        if (temKit) System.out.println("3 - Usar Kit Médico");
        System.out.println("4 - Fugir");

        while (true) {
            System.out.print("Escolha: ");
            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Entrada inválida, digite um número.");
                continue;
            }

            boolean valida = opcao == 1
                    || (opcao == 2 && temDardos)
                    || (opcao == 3 && temKit)
                    || opcao == 4;

            if (valida) return opcao;
            System.out.println("Opção inválida.");
        }
    }
}