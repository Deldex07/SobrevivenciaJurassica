package trabalho.sobrevivenciajurassica.logica;

import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.entidades.TiranossauroRex;
import trabalho.sobrevivenciajurassica.entidades.Velociraptor;
import trabalho.sobrevivenciajurassica.itens.Arma;
import trabalho.sobrevivenciajurassica.itens.Dardos;
import trabalho.sobrevivenciajurassica.itens.Inventario;

public class GerenciadorCombate {
    private final EntradaCombate entradaCombate;

    public GerenciadorCombate(EntradaCombate entradaCombate) {
        this.entradaCombate = entradaCombate;
    }

    public boolean iniciarCombate(Personagem jogador, Dinossauro inimigo) {
        return iniciarCombate(jogador, inimigo, false);
    }

    public boolean iniciarCombate(Personagem jogador, Dinossauro inimigo, boolean inimigoAtacaPrimeiro) {

        System.out.println();
        System.out.println("================================");
        System.out.println("Combate iniciado!");
        System.out.println("Inimigo: " + inimigo.getClass().getSimpleName());
        System.out.println("================================");

        if (inimigoAtacaPrimeiro) {
            System.out.println();
            System.out.println("O dinossauro te encontrou e ataca primeiro!");
            turnoDinossauro(jogador, inimigo);
            if (!jogador.estaVivo()) {
                System.out.println("Você morreu.");
                return false;
            }
        }

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
        boolean temBastao = inventario.temBastao();
        boolean temDardos = inventario.temDardos();
        boolean temKit = inventario.temKitMedico();

        System.out.println();
        System.out.println("Sua vida: " + jogador.getSaude());
        System.out.println("Vida do inimigo: " + inimigo.getSaude());
        System.out.println();

        int opcao = entradaCombate.escolherAcao(jogador, inimigo, temDardos, temKit, temBastao);

        switch (opcao) {
            case 1:
                if (temBastao) atacarComArma(inventario.getBastao(), inimigo);
                else atacarSoco(inimigo);
                break;
            case 2:
                atacarComDardos(inventario.getDardos(), inimigo);
                break;
            case 3:
                jogador.usarKitMedico();
                break;
            case 4:
                return fugir();
        }
        return false;
    }

    private void atacarSoco(Dinossauro inimigo) {
        if (inimigo instanceof TiranossauroRex) {
            System.out.println("Suas mãos nuas não fazem nenhum efeito contra o Tiranossauro Rex!");
            return;
        }

        Dado dado = new Dado(6);
        int resultado = dado.rolar();
        System.out.println("Você atacou com um soco.");

        if (resultado <= 2) {
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