package trabalho.sobrevivenciajurassica.itens;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o inventário do jogador, contendo os itens coletados durante o jogo.
 * Inventario
 */

public class Inventario {
     private final List<Itens> itens;
    
     public Inventario() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Itens item) {
        if (item instanceof Dardos novos) {
            Dardos existente = getDardos();
            if (existente != null) {
                existente.adicionarMunicao(novos.getMunicao());
                System.out.println("Munição adicionada.");
                return;
            }
        }

        itens.add(item);
        System.out.println(item.getNome() + " adicionado ao inventário.");
        }

    public boolean temKitMedico() {
        return getQuantidadeKitMedico() > 0;
    }

    public int getQuantidadeKitMedico() {
        int quantidade = 0;
        for (Itens item : itens)
            if (item instanceof KitMedico)
                quantidade++;
            return quantidade;
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
        return getBastao() != null;
    }

    public BastaoEletrico getBastao() {
        for (Itens item : itens)
            if (item instanceof BastaoEletrico)
                return (BastaoEletrico) item;
            return null;
        }

    public boolean temDardos() {
        Dardos d = getDardos();
        return d != null && d.podeSerUsada();
    }

    public Dardos getDardos() {
        for (Itens item : itens)
            if (item instanceof Dardos)
                return (Dardos) item;
            return null;
        }

    public List<Itens> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        if (itens.isEmpty())
            return "Inventário vazio";

        StringBuilder sb = new StringBuilder();
        sb.append("Inventário:");

        for (Itens item : itens)
            sb.append("\n- ").append(item);
        return sb.toString();
    }
}