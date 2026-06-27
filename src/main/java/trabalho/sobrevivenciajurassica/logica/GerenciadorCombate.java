package trabalho.sobrevivenciajurassica.logica;

import java.util.Scanner;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.entidades.Velociraptor;
import trabalho.sobrevivenciajurassica.itens.Arma;
import trabalho.sobrevivenciajurassica.itens.Dardos;
import trabalho.sobrevivenciajurassica.itens.Inventario;

public class GerenciadorCombate {
    private final Scanner scanner;
    public GerenciadorCombate(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean iniciarCombate(Personagem jogador, Dinossauro inimigo) {

        System.out.println();
        System.out.println("================================");
        System.out.println("Combate iniciado!");
        System.out.println("Inimigo: " + inimigo.getClass().getSimpleName());
        System.out.println("================================");

        while (jogador.estaVivo() && inimigo.estaVivo()) {

            if (turnoJogador(jogador, inimigo)) {
                System.out.println("Você conseguiu fugir!");
                return true;
            }

            if (!inimigo.estaVivo()) {
                System.out.println("Você derrotou o dinossauro!");
                return true;
            }

            turnoDinossauro(jogador, inimigo);
            if (!jogador.estaVivo()) {
                System.out.println("Você morreu.");
                return false;
            }
        }
        return jogador.estaVivo();
    }

    private boolean turnoJogador(Personagem jogador, Dinossauro inimigo) {
        Inventario inventario = jogador.getInventario();

        System.out.println();
        System.out.println("Sua vida: " + jogador.getSaude());
        System.out.println("Vida do inimigo: " + inimigo.getSaude());
        System.out.println();
        System.out.println("1 - Soco");

        if (inventario.temBastao())
            System.out.println("2 - Bastão Elétrico");
        if (inventario.temDardos())
            System.out.println("3 - Dardos Tranquilizantes");
        if (inventario.temKitMedico())
            System.out.println("4 - Usar Kit Médico");

        System.out.println("5 - Fugir");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                atacarSoco(inimigo);
                break;

            case 2:
                if (inventario.temBastao())
                    atacarComArma(inventario.getBastao(), inimigo);
                else
                    System.out.println("Você não possui Bastão Elétrico.");
                break;

            case 3:
                if (inventario.temDardos())
                    atacarComDardos(inventario.getDardos(), inimigo);
                else
                    System.out.println("Você não possui Dardos.");
                break;

            case 4:
                if (inventario.temKitMedico())
                    jogador.usarKitMedico();
                else
                    System.out.println("Você não possui Kit Médico.");
                break;

            case 5:
                return fugir();

            default:
                System.out.println("Opção inválida.");
        }
        return false;
    }

    private void atacarSoco(Dinossauro inimigo) {

        Dado dado = new Dado(6);
        int resultado = dado.rolar();
        System.out.println("Você atacou com um soco.");

        if (resultado == 1) {
            System.out.println("Você errou!");
            return;
        }
        if (resultado == 6) {
            inimigo.receberDano(2);
            System.out.println("Acerto crítico!");
        } else {
            inimigo.receberDano(1);
        }
    }

    private void atacarComArma(Arma arma, Dinossauro inimigo) {
        if (!arma.podeSerUsada()) {
            System.out.println("Essa arma não pode ser utilizada.");
            return;
        }

        int dano = arma.calcularDanoTotal();
        inimigo.receberDano(dano);
        System.out.println("Dano causado: " + dano);
    }

    private void atacarComDardos(Dardos dardos, Dinossauro inimigo) {
        if (inimigo instanceof Velociraptor) {
            System.out.println("Velociraptors são imunes aos dardos!");
            return;
        }
        atacarComArma(dardos, inimigo);

    }

    private boolean fugir() {
        Dado dado = new Dado(6);
        int resultado = dado.rolar();
        System.out.println("Você tentou fugir.");
        return resultado <= 3;
    }

    private void turnoDinossauro(Personagem jogador, Dinossauro inimigo) {
        System.out.println();
        System.out.println("Turno do dinossauro.");

        if (jogador.tentarDesviar()) {
            System.out.println("Você desviou do ataque!");
            return;
        }

        inimigo.atacar(jogador);
        System.out.println("Você recebeu dano.");
    }
}