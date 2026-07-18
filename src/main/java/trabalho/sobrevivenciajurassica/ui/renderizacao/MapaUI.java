package trabalho.sobrevivenciajurassica.ui.renderizacao;

import trabalho.sobrevivenciajurassica.logica.Mapa;
import trabalho.sobrevivenciajurassica.ui.entidades.CaixaSuprimentosUI;
import trabalho.sobrevivenciajurassica.ui.entidades.CompsognatoUI;
import trabalho.sobrevivenciajurassica.ui.entidades.ParedeUI;
import trabalho.sobrevivenciajurassica.ui.entidades.TiranossauroRexUI;
import trabalho.sobrevivenciajurassica.ui.entidades.TroodonteUI;
import trabalho.sobrevivenciajurassica.ui.entidades.VelociraptorUI;
import trabalho.sobrevivenciajurassica.logica.Dificuldade;
import trabalho.sobrevivenciajurassica.entidades.Personagem;
import trabalho.sobrevivenciajurassica.entidades.CaixaSuprimentos;
import trabalho.sobrevivenciajurassica.interfaces.EntradaCombate;
import trabalho.sobrevivenciajurassica.itens.ConteudoCaixa;

public class MapaUI extends Mapa {

    public MapaUI(int tamanho, EntradaCombate entradaCombate) {
        super(tamanho, entradaCombate);
    }

    public MapaUI(int tamanho, EntradaCombate entradaCombate, long seed) {
        super(tamanho, entradaCombate, seed);
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

        posicionarCaixaAleatoria(ConteudoCaixa.BASTAO_ELETRICO);
        posicionarCaixaAleatoria(ConteudoCaixa.KIT_MEDICO);
        posicionarCaixaAleatoria(sortearConteudoSecundario());
        posicionarCaixaAleatoria(sortearConteudoSecundario());
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

    @Override
    protected void posicionarCaixaAleatoria(ConteudoCaixa conteudo) {
        int tamanho = getTamanho();
        int linha, coluna;
        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (!posicaoLivre(linha, coluna));

        try {
            grade[linha][coluna] = new CaixaSuprimentosUI(conteudo, linha, coluna, 'X');
        } catch (java.io.IOException e) {
            grade[linha][coluna] = new CaixaSuprimentos(conteudo, linha, coluna, 'X');
        }
    }
}