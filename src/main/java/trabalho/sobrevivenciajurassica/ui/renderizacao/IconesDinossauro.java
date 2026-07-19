package trabalho.sobrevivenciajurassica.ui.renderizacao;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import trabalho.sobrevivenciajurassica.entidades.Dinossauro;

/**
 * Centraliza o carregamento das imagens de combate ("_atacar") e de
 * alerta ("_olho") de cada tipo de dinossauro, evitando a criação de
 * uma classe dedicada por dinossauro para esse fim. A chave de cada
 * tipo é derivada do nome da classe (ex: VelociraptorUI -> "velociraptor").
 */
public final class IconesDinossauro {

    private static final String BASE = "/trabalho/sobrevivenciajurassica/imagens/";
    private static final Map<String, Image> CACHE_OLHO = new HashMap<>();
    private static final Map<String, Image> CACHE_ATACAR = new HashMap<>();

    private IconesDinossauro() {}

    public static String chave(Dinossauro dino) {
        String nome = dino.getClass().getSimpleName();
        if (nome.endsWith("UI")) {
            nome = nome.substring(0, nome.length() - 2);
        }
        return nome.toLowerCase();
    }

    public static Image obterOlho(Dinossauro dino) {
        return CACHE_OLHO.computeIfAbsent(chave(dino), c -> carregar(c + "_olho.png"));
    }

    public static Image obterAtacar(Dinossauro dino) {
        return CACHE_ATACAR.computeIfAbsent(chave(dino), c -> carregar(c + "_atacar.png"));
    }

    private static Image carregar(String arquivo) {
        try {
            return CarregadorImagem.carregar(BASE + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem '" + arquivo + "': " + e.getMessage());
            return null;
        }
    }
}