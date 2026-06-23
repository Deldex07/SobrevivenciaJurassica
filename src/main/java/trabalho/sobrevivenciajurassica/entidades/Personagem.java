package trabalho.sobrevivenciajurassica.entidades;

import trabalho.sobrevivenciajurassica.itens.Inventario;
import trabalho.sobrevivenciajurassica.itens.KitMedico;
import trabalho.sobrevivenciajurassica.logica.Dado;

/**
 * Representa o personagem controlado pelo jogador.
 * Possui saúde, percepção e um inventário de itens coletados.
 */
public class Personagem extends EntidadeViva {

    private int saude;
    private final int saudeMaxima;
    private final int percepcao;
    private final Inventario inventario;
    private static final Dado dadoPercepcao = new Dado(3);

    public Personagem(int linha, int coluna, int saude, int percepcao) {
        super(linha, coluna, 'P');
        this.saude      = saude;
        this.saudeMaxima = saude;
        this.percepcao  = percepcao;
        this.inventario = new Inventario();
    }

    // --- Saúde ---

    public int getSaude()    { return saude; }
    public int getSaudeMaxima() { return saudeMaxima; }
    public boolean estaVivo() { return saude > 0; }

    @Override
    public void receberDano(int pontos) {
        saude = Math.max(0, saude - pontos);
    }

    public void curar(int pontos) {
        saude = Math.min(saudeMaxima, saude + pontos);
    }

    // --- Percepção ---

    public int getPercepcao() { return percepcao; }

    /**
     * Testa se o jogador desvia de um ataque.
     * Rola um dado de 3 lados — desvia se resultado <= percepção.
     */
    public boolean tentarDesviar() {
        int resultado = dadoPercepcao.rolar();
        System.out.println("  [Percepção] Dado: " + resultado + " | Percepção: " + percepcao);
        return resultado <= percepcao;
    }

    // --- Inventário ---

    public Inventario getInventario() { return inventario; }

    /**
     * Usa um kit médico do inventário, se disponível.
     * @return true se curou, false se não tinha kit.
     */
    public boolean usarKitMedico() {
        KitMedico kit = inventario.retirarKitMedico();
        if (kit == null) {
            System.out.println("  Você não possui kit médico!");
            return false;
        }
        kit.usar();
        curar(kit.getCura());
        System.out.println("  Saúde atual: " + saude);
        return true;
    }

    // --- Exibição ---

    @Override
    public String toString() {
        return "Personagem | Saúde: " + saude + "/" + saudeMaxima +
               " | Percepção: " + percepcao +
               " | " + inventario;
    }
}