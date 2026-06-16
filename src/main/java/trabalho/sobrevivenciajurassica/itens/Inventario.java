package trabalho.sobrevivenciajurassica.itens;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventário do jogador.
 * Armazena itens coletados e fornece acesso às armas e kits médicos.
 */
public class Inventario {

    private final List<Itens> itens = new ArrayList<>();

    public void adicionarItem(Itens item) {
        // Se já tem dardos e coletou outro, adiciona munição em vez de duplicar
        if (item instanceof Dardos novos) {
            Dardos existente = getDardos();
            if (existente != null) {
                existente.adicionarMunicao(novos.getMunicao());
                System.out.println("  [Inventário] Munição adicionada: +" + novos.getMunicao());
                return;
            }
        }
        itens.add(item);
        System.out.println("  [Inventário] Item adicionado: " + item.getNome());
    }

    public boolean temKitMedico() {
        return itens.stream().anyMatch(i -> i instanceof KitMedico);
    }

    public KitMedico retirarKitMedico() {
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i) instanceof KitMedico kit) {
                itens.remove(i);
                return kit;
            }
        }
        return null;
    }

    public boolean temBastao() {
        return itens.stream().anyMatch(i -> i instanceof BastaoEletrico);
    }

    public BastaoEletrico getBastao() {
        return (BastaoEletrico) itens.stream()
                .filter(i -> i instanceof BastaoEletrico)
                .findFirst().orElse(null);
    }

    public boolean temDardos() {
        Dardos d = getDardos();
        return d != null && d.podeSerUsada();
    }

    public Dardos getDardos() {
        return (Dardos) itens.stream()
                .filter(i -> i instanceof Dardos)
                .findFirst().orElse(null);
    }

    public List<Itens> getItens() { return itens; }

    @Override
    public String toString() {
        if (itens.isEmpty()) return "Inventário vazio";
        StringBuilder sb = new StringBuilder("Inventário: ");
        for (Itens i : itens) sb.append("[").append(i).append("] ");
        return sb.toString().trim();
    }
}