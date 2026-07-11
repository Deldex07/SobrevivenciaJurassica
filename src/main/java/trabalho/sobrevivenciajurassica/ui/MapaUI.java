package trabalho.sobrevivenciajurassica.ui;

import java.io.IOException;
import java.util.Scanner;
import trabalho.sobrevivenciajurassica.logica.Mapa;
import trabalho.sobrevivenciajurassica.logica.Dificuldade;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.itens.ConteudoCaixa;

public class MapaUI extends Mapa {

    public MapaUI(int tamanho, Scanner scanner) {
        super(tamanho, scanner);
    }

    @Override
    public void gerar(Personagem personagem, Dificuldade dificuldade) {
        this.personagem = personagem;
        grade[0][0] = personagem;
        personagem.setLinha(0);
        personagem.setColuna(0);

        gerarParedes();

        int tamanho = getTamanho();

        posicionarDinossauro(new TiranossauroRexUI(3, tamanho - 1, tamanho - 1, 'R'), tamanho - 1, tamanho - 1);
        posicionarDinossauroAleatorio(new CompsognatoUI(1, 0, 0, 'C'));
        posicionarDinossauroAleatorio(new CompsognatoUI(1, 0, 0, 'C'));

        for (int i = 0; i < 5; i++) {
            posicionarDinossauroAleatorio(new TroodonteUI(2, 0, 0, 'T'));
        }

        posicionarDinossauroAleatorio(new VelociraptorUI(2, 0, 0, 'V'));
        posicionarDinossauroAleatorio(new VelociraptorUI(2, 0, 0, 'V'));

        posicionarCaixaComTratamento(ConteudoCaixa.KIT_MEDICO);
        posicionarCaixaComTratamento(ConteudoCaixa.BASTAO_ELETRICO);
        posicionarCaixaComTratamento(ConteudoCaixa.DARDOS);
        posicionarCaixaComTratamento(ConteudoCaixa.COMPSOGNATO);
    }

    @Override
    protected void gerarParedes() {
    int tamanho = getTamanho();
    int total = tamanho * tamanho;
    int quantidade = total / 10 + random.nextInt(total / 10);

    for (int i = 0; i < quantidade; i++) {
        int linha, coluna;
        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (grade[linha][coluna] != null || (linha == tamanho - 1 && coluna == tamanho - 1));
        grade[linha][coluna] = new ParedeUI(linha, coluna);
    }
}

    /**
     * Envolve posicionarCaixaAleatoria (que pode lançar IOException por causa
     * do carregamento de imagem no construtor de CaixaSuprimentosUI) num
     * tratamento local, já que a assinatura de Mapa.gerar() não declara throws.
     */
    private void posicionarCaixaComTratamento(ConteudoCaixa conteudo) {
        try {
            posicionarCaixaAleatoria(conteudo);
        } catch (IOException e) {
            System.out.println("Erro ao posicionar caixa (" + conteudo + "): " + e.getMessage());
        }
    }

    @Override
    protected void posicionarCaixaAleatoria(ConteudoCaixa conteudo) throws IOException {
        int tamanho = getTamanho();
        int linha, coluna;
        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (grade[linha][coluna] != null || (linha == 0 && coluna == 0));
        grade[linha][coluna] = new CaixaSuprimentosUI(conteudo, linha, coluna, 'X');
    }
}