package trabalho.sobrevivenciajurassica.ui.renderizacao;
import java.io.IOException;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 * Classe utilitária para carregar imagens de recursos.
 * Evita duplicara lógica de leitura/tratamento de erro em cada classe UI.
 * CarregadorImagem
 */
public final class CarregadorImagem {
    
    private CarregadorImagem() {};

    /**
     * Carrega uma imagem a partir do Classpath (ex: pasta "imagens" dentro de scr).
     * @param caminho caminho relativo da imagem a ser carregada.
     * @throws IOException caso a imagem não seja encontrada.
     */

    public static Image carregar(String caminho) throws IOException {
        URL url = CarregadorImagem.class.getResource(caminho);
        if (url == null) {
            throw new IOException("Imagem não encontrada: " + caminho);
        }

        return ImageIO.read(url);
    }
}
